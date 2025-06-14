package com.javaex.order.controller;

import com.javaex.common.util.JsonResult;
import com.javaex.mypage.model.DdrMypageOrderDetailVo;
import com.javaex.order.model.DdrOrderStatusVo;
import com.javaex.order.model.DdrOrderVo;
import com.javaex.order.service.DdrOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class DdrOrderController {

    @Autowired
    private DdrOrderService ddrOrderService;

    /**
     * ✅ 주문 생성 및 결제 준비
     **/
    @PostMapping
    public JsonResult createOrder(@RequestBody Map<String, Object> requestData) {
        try {
            System.out.println("받은 데이터: " + requestData);

            // 주문 객체 생성
            DdrOrderVo order = new DdrOrderVo();
            order.setProductId(Integer.parseInt(requestData.get("productId").toString()));
            order.setUserId(Integer.parseInt(requestData.get("userId").toString()));
            order.setTotalPrice(new BigDecimal(requestData.get("totalPrice").toString()));
            order.setOrderStatus("PENDING"); // ✅ 초기 상태는 PENDING으로 설정

            // 추가 데이터 매핑
            order.setDeliveryMethod(requestData.getOrDefault("deliveryMethod", "").toString());
            order.setDeliveryAddress(requestData.getOrDefault("deliveryAddress", "").toString());
            order.setRecipientName(requestData.getOrDefault("recipientName", "").toString());
            order.setRecipientPhone(requestData.getOrDefault("recipientPhone", "").toString());
            order.setCakeLettering(requestData.getOrDefault("cakeLettering", "").toString());
            order.setPlateLettering(requestData.getOrDefault("plateLettering", "").toString());
            order.setAdditionalRequests(requestData.getOrDefault("additionalRequests", "").toString());

            // 날짜/시간 데이터 처리
            if (requestData.get("desiredPickupDatetime") != null) {
                order.setDesiredPickupDatetime(LocalDate.parse(requestData.get("desiredPickupDatetime").toString()));
            }
            if (requestData.get("desiredPickupTime") != null) {
                order.setDesiredPickupTime(requestData.get("desiredPickupTime").toString());
            }
            if (requestData.get("desiredDeliveryDate") != null) {
                order.setDesiredDeliveryDate(LocalDate.parse(requestData.get("desiredDeliveryDate").toString()));
            }
            if (requestData.get("desiredDeliveryTime") != null) {
                order.setDesiredDeliveryTime(requestData.get("desiredDeliveryTime").toString());
            }

            // 주문 옵션 데이터 매핑
            order.setProductType(requestData.getOrDefault("productType", "").toString());
            order.setCakeSize(requestData.getOrDefault("cakeSize", "").toString());
            order.setFlavorSheet(requestData.getOrDefault("flavorSheet", "").toString());
            order.setFlavorCream(requestData.getOrDefault("flavorCream", "").toString());
            order.setCakeBackgroundColor(requestData.getOrDefault("cakeBackgroundColor", "").toString());
            order.setCreamPosition(requestData.getOrDefault("creamPosition", "").toString());
            order.setCreamColor(requestData.getOrDefault("creamColor", "").toString());
            order.setDecorationType(requestData.getOrDefault("decorationType", "").toString());
            order.setDecorationColor(requestData.getOrDefault("decorationColor", "").toString());
            order.setCategory(requestData.getOrDefault("category", "").toString());
            order.set식재료(requestData.getOrDefault("식재료", "").toString());
            order.set장식품(requestData.getOrDefault("장식품", "").toString());
            order.set소모품(requestData.getOrDefault("소모품", "").toString());

            // ✅ 주문 저장 (DB)
            int orderId = ddrOrderService.execreateOrder(order);
            order.setOrderId(orderId);

            // ✅ 결제 요청을 위한 정보 반환
            Map<String, Object> responseData = Map.of(
                    "orderId", orderId,
                    "paymentOrderId", order.getPaymentOrderId(),
                    "amount", order.getTotalPrice(),
                    "status", "PENDING"

            );
            return JsonResult.success(responseData);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("주문 처리 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ 주문 상세 조회
     **/
    @GetMapping("/{orderId}")
    public JsonResult getOrderDetail(@PathVariable int orderId) {
        System.out.println("Received orderId: " + orderId);
        try {
            DdrMypageOrderDetailVo orderDetail = ddrOrderService.exegetOrderDetail(orderId);
            System.out.println("Fetched Order Detail: " + orderDetail);
            if (orderDetail == null) {
                return JsonResult.fail("주문 정보를 찾을 수 없습니다.");
            }
            return JsonResult.success(orderDetail);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching order detail: " + e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("주문 상세 조회 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ 주문 상태 업데이트 (결제 승인 후 상태 변경)
     **/
    @PutMapping("/{orderId}/status")
    public JsonResult updateOrderStatus(@PathVariable("orderId") int orderId, @RequestBody DdrOrderStatusVo statusVo) {
        System.out.println("=== Update Order Status API Called ===");
        System.out.println("orderId: " + orderId);
        System.out.println("Request Body: " + statusVo);

        try {
            statusVo.setOrderId(orderId);
            boolean result = ddrOrderService.exeupdateOrderStatus(statusVo);
            System.out.println("Update result: " + result);

            if (result) {
                // ✅ 주문이 'SUCCESS' 상태로 변경되면 포인트 적립 실행
                if ("SUCCESS".equals(statusVo.getStatus())) {
                    ddrOrderService.accumulatePoints(orderId);
                }
                return JsonResult.success("주문 상태가 업데이트되었습니다.");
            } else {
                return JsonResult.fail("주문 상태 업데이트에 실패했습니다.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("주문 상태 업데이트 중 오류 발생: " + e.getMessage());
        }
    }
}
