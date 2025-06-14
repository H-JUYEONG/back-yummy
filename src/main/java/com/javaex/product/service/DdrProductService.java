package com.javaex.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.option.model.DdrOptionVo;
import com.javaex.product.model.DdrProductVo;
import com.javaex.product.repository.DdrProductDao;

@Service
public class DdrProductService {

	@Autowired
	private DdrProductDao ddrProductDao;

	public DdrProductVo exeProductDetail(int productId) {
		System.out.println("productservice.ready");

		return ddrProductDao.selectproduct(productId);
	}

	public List<DdrOptionVo> exeProductOptions(int productId) {
		System.out.println("productId: " + productId);
		List<DdrOptionVo> result = ddrProductDao.getProductOptions(productId);
		System.out.println("result: " + result); // null인지 empty list인지 확인
		return result;
	}
}