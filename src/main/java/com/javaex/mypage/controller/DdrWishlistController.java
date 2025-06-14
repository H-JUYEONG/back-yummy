package com.javaex.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.common.util.JsonResult;
import com.javaex.mypage.model.DdrWishlistVo;
import com.javaex.mypage.service.DdrWishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class DdrWishlistController {

	@Autowired
	
	private DdrWishlistService ddrWishListService;
	
	@GetMapping("/count/{productId}")
    public JsonResult getWishlistCount(@PathVariable int productId) {
        System.out.println("[찜 개수 조회] productId: " + productId);
        int count = ddrWishListService.exegetWishlistCount(productId);
        System.out.println("[찜 개수 결과] count: " + count);
        return JsonResult.success(count);
    }

    @GetMapping("/check")
    public JsonResult checkWishlist(@RequestParam("productId") int productId, 
                                  @RequestParam("memberId") int memberId) {
        System.out.println("[찜 상태 확인] productId: " + productId + ", memberId: " + memberId);
        int count = ddrWishListService.execheckWishlist(productId, memberId);
        System.out.println("[찜 상태 결과] count: " + count);
        return JsonResult.success(count);
    }

    @PostMapping("/add")
    public JsonResult addWishlist(@RequestBody DdrWishlistVo wishlistVo) {
        System.out.println("[찜하기] 요청 데이터: " + wishlistVo);
        int count = ddrWishListService.exeaddWishlist(wishlistVo);
        System.out.println("[찜하기 결과] count: " + count);
        return JsonResult.success(count);
    }

    @DeleteMapping("/remove")
    public JsonResult removeWishlist(@RequestBody DdrWishlistVo wishlistVo) {
        System.out.println("[찜 취소] 요청 데이터: " + wishlistVo);
        
        // 삭제 전에 해당 데이터가 있는지 확인
        int exists = ddrWishListService.execheckWishlist(
            wishlistVo.getProductId(), 
            wishlistVo.getMemberId()
        );
        
        if (exists > 0) {
            int count = ddrWishListService.exeremoveWishlist(wishlistVo);
            System.out.println("[찜 취소 결과] count: " + count);
            return JsonResult.success(count);
        } else {
            return JsonResult.fail("삭제할 데이터가 없습니다.");
        }
    }
}