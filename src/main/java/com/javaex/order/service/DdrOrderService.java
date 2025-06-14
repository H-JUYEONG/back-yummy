package com.javaex.order.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.mypage.model.DdrMypageOrderDetailVo;
import com.javaex.mypage.repository.DdrMypagePointHistoryDao;
import com.javaex.order.model.DdrOrderStatusVo;
import com.javaex.order.model.DdrOrderVo;
import com.javaex.order.repository.DdrOrderDao;

@Service
public class DdrOrderService {

    @Autowired
    private DdrOrderDao ddrOrderDao;

    @Autowired
    private DdrMypagePointHistoryDao ddrMypagePointHistoryDao;

    public int exeOrder(DdrOrderVo ddrordervo) {
        return ddrOrderDao.insertOrder(ddrordervo);
    }

    @Transactional
    public int execreateOrder(DdrOrderVo ddrordervo) {
        try {

            String paymentOrderId = "order_" + System.currentTimeMillis();
            ddrordervo.setPaymentOrderId(paymentOrderId);

            // 1. 정산 금액 계산
            BigDecimal totalPrice = ddrordervo.getTotalPrice();
            BigDecimal settlementPrice = totalPrice.subtract(BigDecimal.valueOf(1000)); // 1000원 차감
            ddrordervo.setSettlementPrice(settlementPrice);

            // 2. 주문 생성
            System.out.println("[서비스 계층 주문 데이터] " + ddrordervo);
            int orderId = ddrOrderDao.insertOrder(ddrordervo);
            System.out.println("Insert Order SQL 실행 후 반환된 Order ID: " + orderId);

            // 3. 추가 검증: 데이터가 저장되었는지 확인
            Map<String, Object> insertedOrder = ddrOrderDao.getOrderInfo(orderId);
            if (insertedOrder == null) {
                throw new RuntimeException("주문 데이터가 저장되지 않았습니다: ID = " + orderId);
            }
            // ✅ Toss 결제용 ID를 DB에 저장 (새로 추가)
            ddrOrderDao.updatePaymentOrderId(orderId, paymentOrderId);

            // ✅ 포인트 적립을 여기서 제거하고 결제 승인 시 처리
            return orderId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("주문 생성 중 오류 발생", e);
        }
    }

    public void accumulatePoints(int orderId) {
        Map<String, Object> orderInfo = ddrOrderDao.getOrderInfo(orderId);
        System.out.println("Order Info: " + orderInfo);

        if (orderInfo == null) {
            throw new IllegalArgumentException("해당 주문 정보를 찾을 수 없습니다.");
        }

        int designerId = (int) orderInfo.get("designerId");
        int buyerId = (int) orderInfo.get("buyerId");
        String designTitle = (String) orderInfo.get("designTitle"); // 디자인 제목 가져오기
        int cakeDesignId = (int) orderInfo.get("cakeDesignId");

        System.out.println("Designer ID: " + designerId);
        System.out.println("Buyer ID: " + buyerId);
        System.out.println("Design Title: " + designTitle);
        System.out.println("Cake Design ID: " + cakeDesignId);

        if (designerId == buyerId) {
            System.out.println("본인이 만든 도안으로 주문했으므로 적립 불가");
            return;
        }

        // 포인트 적립 로직
        int pointsToAccumulate = 1000;
        String reason = designTitle != null ? designTitle : "도안사용 적립"; // 사유 기본값 설정
        ddrMypagePointHistoryDao.insertPointHistory(designerId, pointsToAccumulate, cakeDesignId, orderId, reason);
        ddrMypagePointHistoryDao.updateMemberPoints(designerId, pointsToAccumulate);

        System.out.println("포인트 적립 완료 - Designer ID: " + designerId + ", Points: " + pointsToAccumulate);
    }

    public DdrMypageOrderDetailVo exegetOrderDetail(int orderId) {
        DdrMypageOrderDetailVo result = ddrOrderDao.selectOrderDetail(orderId);
        System.out.println("Order detail from DAO for orderId " + orderId + ": " + result);
        return result;
    }

//    public boolean exeupdateOrderStatus(String orderId, String status) {
//        try {
//            int updatedRows = ddrOrderDao.updateOrderStatus(orderId, status);
//
//            if (updatedRows > 0) {
//                return true;
//            } else {
//                throw new RuntimeException("주문 상태 변경 실패: orderId = " + orderId);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    @Transactional
    public boolean exeupdateOrderStatus(DdrOrderStatusVo ddrorderstatusvo) {
        try {
            System.out.println("🔄 주문 상태 변경 요청: " + ddrorderstatusvo);

            // ✅ 주문 상태 업데이트 실행
            int updatedRows = ddrOrderDao.updateOrderStatus(ddrorderstatusvo);

            if (updatedRows > 0) {
                System.out.println("✅ 주문 상태 업데이트 성공: " + ddrorderstatusvo);

                // ✅ 주문이 'SUCCESS' 상태가 되면 포인트 적립 실행
                if ("SUCCESS".equals(ddrorderstatusvo.getStatus())) {
                    accumulatePoints(ddrorderstatusvo.getOrderId());
                    System.out.println("✅ 포인트 적립 완료");
                }
                return true;
            } else {
                throw new RuntimeException("❌ 주문 상태 변경 실패: orderId = " + ddrorderstatusvo.getOrderId());
            }
        } catch (Exception e) {
            System.out.println("❌ 주문 상태 변경 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Integer findOrderIdByPaymentOrderId(String paymentOrderId) {
        return ddrOrderDao.findOrderIdByPaymentOrderId(paymentOrderId);
    }

}