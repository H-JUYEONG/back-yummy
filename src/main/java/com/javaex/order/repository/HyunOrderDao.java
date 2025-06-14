package com.javaex.order.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.order.model.HyunOrderDetailVo;
import com.javaex.order.model.HyunOrderVo;

@Repository
public class HyunOrderDao {

	@Autowired
	private SqlSession sqlSession;
	
    public List<HyunOrderVo> getOrdersByVenderId(int venderId) {
        return sqlSession.selectList("hyunOrderMapper.getOrdersByVenderId", venderId);
    }
    
    public HyunOrderDetailVo getOrderDetailsById(int orderId) {
        return sqlSession.selectOne("hyunOrderMapper.getOrderDetailsById", orderId);
    }
    
    public int updateOrderStatus(int orderId, String orderStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("orderStatus", orderStatus);
        return sqlSession.update("hyunOrderMapper.updateOrderStatus", params);
    }
    
    public void updateOrderMedia(int orderId, String videoUrl, String photoUrl) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("videoUrl", videoUrl);
        params.put("photoUrl", photoUrl);

        System.out.println("업데이트 요청 파라미터: " + params);

        try {
            sqlSession.update("hyunOrderMapper.updateOrderMedia", params);
            System.out.println("업데이트 성공");
        } catch (Exception e) {
            System.err.println("MyBatis 업데이트 중 오류 발생: " + e.getMessage());
            throw e;
        }
    }

//    public void updateOrderMedia(int orderId, String videoUrl, String photoUrl) {
//        // 테스트용 SQL 로깅 추가
//        System.out.println("저장된 비디오 URL: " + videoUrl);
//        System.out.println("저장된 사진 URL: " + photoUrl);
//
//        // 실제 업데이트 로직
//        sqlSession.update("HyunOrderMapper.updateOrderMedia", Map.of("orderId", orderId, "videoUrl", videoUrl, "photoUrl", photoUrl));
//    }
    

    
    
    
}
