package com.javaex.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.mypage.model.DdrWishlistVo;
import com.javaex.mypage.repository.DdrWishlistDao;

@Service
public class DdrWishlistService {

	@Autowired
	private DdrWishlistDao ddrWishlistDao;

	public int exeaddWishlist(DdrWishlistVo ddrwishlistvo) {
		return ddrWishlistDao.insertWishlist(ddrwishlistvo);
	}

	public int exeremoveWishlist(DdrWishlistVo ddrwishlistvo) { 
		return ddrWishlistDao.deleteWishlist(ddrwishlistvo);
	}

	public int execheckWishlist(int productId, int memberId) {
		return ddrWishlistDao.checkWishlist(productId, memberId);
	}

	public int exegetWishlistCount(int productId) {
		return ddrWishlistDao.selectWishlistCount(productId);

	}
}