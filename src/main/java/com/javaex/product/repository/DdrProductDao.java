package com.javaex.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.option.model.DdrOptionVo;
import com.javaex.product.model.DdrProductVo;

@Repository
public class DdrProductDao {

	@Autowired
	private SqlSession sqlSession;

	public DdrProductVo selectproduct(int productId) {
		System.out.println("selectproduct.ready");

		return sqlSession.selectOne("product.selectproduct", productId);

	}

	public List<DdrOptionVo> getProductOptions(int productId) {
		return sqlSession.selectList("product.getProductOptions", productId);
	}

	public int getCakeDesignId(int productId) {
		Integer result = sqlSession.selectOne("product.getCakeDesignId", productId);
		return result != null ? result : 0; // null일 경우 0을 반환
	}
}