package com.javaex.mypage.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.mypage.model.DdrMypageWishlistVo;

@Repository
public class DdrMypageWishlistDao {

	@Autowired
	private SqlSession sqlsession;

	   
    public List<DdrMypageWishlistVo> selectWishlistProducts(DdrMypageWishlistVo vo) {
        return sqlsession.selectList("DdrMypageWishlist.selectWishlistProducts", vo);
    }
    
    public int selectWishlistProductsCount(DdrMypageWishlistVo vo) {
        return sqlsession.selectOne("DdrMypageWishlist.selectWishlistProductsCount", vo);
    }
}

