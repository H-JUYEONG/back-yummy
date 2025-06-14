package com.javaex.mypage.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.mypage.model.DdrWishlistVo;

@Repository
public class DdrWishlistDao {
	 @Autowired
	    private SqlSession sqlSession;

	    public int insertWishlist(DdrWishlistVo ddrwishlistvo) {
	        return sqlSession.insert("DdrWishlist.addWishlist", ddrwishlistvo);
	    }

	    public int deleteWishlist(DdrWishlistVo ddrwishlistvo) {
	        return sqlSession.delete("DdrWishlist.removeWishlist", ddrwishlistvo);
	    }

	    public int checkWishlist(int productId, int memberId) {
	        DdrWishlistVo vo = new DdrWishlistVo();
	        vo.setProductId(productId);
	        vo.setMemberId(memberId);
	        return sqlSession.selectOne("DdrWishlist.checkWishlist", vo);
	    }

	    public int selectWishlistCount(int productId) {
	        return sqlSession.selectOne("DdrWishlist.getWishlistCount", productId);
	    }
	}