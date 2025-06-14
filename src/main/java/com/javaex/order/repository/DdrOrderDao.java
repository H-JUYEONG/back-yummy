package com.javaex.order.repository;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.mypage.model.DdrMypageOrderDetailVo;
import com.javaex.order.model.DdrOrderStatusVo;
import com.javaex.order.model.DdrOrderVo;

@Repository
public class DdrOrderDao {
	@Autowired
	private SqlSession sqlSession;

	public int insertOrder(DdrOrderVo ddrordervo) {
		System.out.println("[DAO 계층 주문 데이터] " + ddrordervo);
		int result = sqlSession.insert("order.insertOrder", ddrordervo);
		System.out.println("[생성된 주문 ID] " + ddrordervo.getOrderId());
		return ddrordervo.getOrderId();
	}

	// 결제 상세에서 결제 완료할 때 DB로 전송
	public int insertOrderlist(DdrOrderVo ddrordervo) {
		return sqlSession.insert("order.insertOrderList", ddrordervo);
	}

	public void updateSettlementPrice(int orderId, BigDecimal settlementPrice) {
		sqlSession.update("order.updateSettlementPrice", Map.of("orderId", orderId, "settlementPrice", settlementPrice));
	}

	public DdrMypageOrderDetailVo selectOrderDetail(int orderId) {
		return sqlSession.selectOne("order.getOrderDetail", orderId);
	}

	public int updateOrderStatus(DdrOrderStatusVo ddrorderstatusvo) {
		System.out.println("🚀 SQL 실행: updateOrderStatusChange - " + ddrorderstatusvo);
		int result = sqlSession.update("order.updateOrderStatusChange", ddrorderstatusvo);
		System.out.println("✅ SQL 업데이트된 행 수: " + result);
		return result;
	}

	public Map<String, Object> getOrderInfo(int orderId) {
		return sqlSession.selectOne("order.getOrderInfo", orderId);
	}

	public void updatePaymentOrderId(int orderId, String paymentOrderId) {
		sqlSession.update("order.updatePaymentOrderId", Map.of("orderId", orderId, "paymentOrderId", paymentOrderId));
	}

	// ✅ 추가된 메서드: paymentOrderId로 orderId 조회
	public Integer findOrderIdByPaymentOrderId(String paymentOrderId) {
		return sqlSession.selectOne("order.findOrderIdByPaymentOrderId", paymentOrderId);
	}
}
