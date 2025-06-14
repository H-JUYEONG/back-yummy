package com.javaex.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.common.util.JsonResult;
import com.javaex.option.model.DdrOptionVo;
import com.javaex.product.model.DdrProductVo;
import com.javaex.product.service.DdrProductService;

@RestController
@RequestMapping("/api/products")
public class DdrUserProductController {

	@Autowired
	private DdrProductService ddrProductService;
	
	//상품 정보 가져오기
	 @GetMapping("/{productId}")
	    public JsonResult getProductDetail(@PathVariable int productId) {
	        try {
	            DdrProductVo ddrproductvo = ddrProductService.exeProductDetail(productId);
	            return JsonResult.success(ddrproductvo);
	        
	        } catch(Exception e) {
	            return JsonResult.fail(e.getMessage());
	        }
	    }

	 @GetMapping("/{productId}/options")
	 public JsonResult getProductOptions(@PathVariable int productId) {
	     try {
	         System.out.println("요청된 productId: " + productId);
	         List<DdrOptionVo> ddroptionvo = ddrProductService.exeProductOptions(productId);
	         System.out.println("조회된 옵션 개수: " + (ddroptionvo != null ? ddroptionvo.size() : "null"));
	         return JsonResult.success(ddroptionvo);
	     } catch(Exception e) {
	         System.out.println("에러 발생: " + e.getMessage());
	         e.printStackTrace();
	         return JsonResult.fail(e.getMessage());
	     }
	 }
}


