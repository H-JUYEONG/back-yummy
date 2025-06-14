package com.javaex.order.controller;

import com.javaex.order.model.DdrOrderStatusVo;
import com.javaex.order.service.DdrOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private DdrOrderService ddrOrderService;

    @Value("${toss.secret-key}")
    private String secretKey;

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> request) {

        String paymentKey = request.get("paymentKey");
        String paymentOrderId = request.get("paymentOrderId");
        int amount = Integer.parseInt(request.get("amount"));
        if (paymentOrderId == null || paymentOrderId.isEmpty()) {
            System.out.println("❌ [오류] paymentOrderId 값이 없습니다.");
            return ResponseEntity.badRequest().body("❌ paymentOrderId 값이 없습니다.");
        }
        if (paymentKey == null || paymentKey.isEmpty()) {
            System.out.println("❌ [오류] paymentKey 값이 없습니다.");
            return ResponseEntity.badRequest().body("❌ paymentKey 값이 없습니다.");
        }
        try {
            // ✅ Toss API 인증 (Base64 인코딩 적용)
            String encodedAuth = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // ✅ Toss 결제 승인 API 요청
            String url = "https://api.tosspayments.com/v1/payments/confirm";
            Map<String, Object> body = Map.of(
                    "paymentKey", paymentKey,
                    "orderId", paymentOrderId,
                    "amount", amount
            );


            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

     
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("✅ 결제 승인 성공! 주문 상태 업데이트 진행");

                // ✅ paymentOrderId를 기준으로 orderId 조회
                Integer orderId = ddrOrderService.findOrderIdByPaymentOrderId(paymentOrderId);
                if (orderId == null) {
                    throw new RuntimeException("❌ 해당 paymentOrderId와 매칭되는 주문 ID 없음");
                }

                // ✅ 주문 상태 업데이트 실행
                DdrOrderStatusVo orderStatusVo = new DdrOrderStatusVo(orderId, "SUCCESS");
                boolean updateSuccess = ddrOrderService.exeupdateOrderStatus(orderStatusVo);

                if (!updateSuccess) {
                    System.out.println("❌ 주문 상태 업데이트 실패: orderId=" + orderId);
                    throw new RuntimeException("❌ 주문 상태 업데이트 실패");
                }

                System.out.println("✅ 주문 상태 SUCCESS로 변경 완료: orderId=" + orderId);
                return ResponseEntity.ok(response.getBody());
            } else {
                System.out.println("❌ 결제 승인 실패: " + response.getBody());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getBody());
            }
        } catch (Exception e) {
            System.out.println("❌ 결제 처리 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
