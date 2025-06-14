package com.javaex.vendor.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.review.model.HyunReviewVo;
import com.javaex.vendor.model.HyunVenderVo;
import com.javaex.vendor.service.HyunVenderService;

@RestController
@RequestMapping("/api")
public class HyunVenderController {

	@Autowired
	private HyunVenderService hyunVenderService;

	@GetMapping("/vender/dashboard")
	public ResponseEntity<List<Map<String, Object>>> getDashboardReservations(@RequestParam("venderId") int venderId) {
		try {
			List<Map<String, Object>> reservations = hyunVenderService.getReservations(venderId);
			return ResponseEntity.ok(reservations);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonList(Map.of("error", e.getMessage())));
		}
	}

	@GetMapping("/vender/monthlyCount")
	public ResponseEntity<Integer> getMonthlyOrderCount(@RequestParam Long venderId) {
		int orderCount = hyunVenderService.getMonthlyOrderCount(venderId);
		return ResponseEntity.ok(orderCount);
	}

	@GetMapping("/vender/points")
	public ResponseEntity<HyunVenderVo> getMemberPoints(@RequestParam int memberId) {
		HyunVenderVo venderPoints = hyunVenderService.getMemberPoints(memberId);
		return ResponseEntity.ok(venderPoints);
	}

	// 매출 데이터를 반환하는 API
	@GetMapping("/vender/revenue")
	public ResponseEntity<HyunVenderVo> getVenderRevenue(@RequestParam Long venderId) {
		try {
			HyunVenderVo revenue = hyunVenderService.getVenderRevenue(venderId);
			return ResponseEntity.ok(revenue);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 신규 리뷰 조회 API
	@GetMapping("/vender/newreviews")
	public ResponseEntity<List<HyunReviewVo>> getVenderReview(@RequestParam int venderId) {
		List<HyunReviewVo> newReviews = hyunVenderService.getVenderReview(venderId);
		return new ResponseEntity<>(newReviews, HttpStatus.OK);
	}

	// 리뷰 통계 API
	@GetMapping("/vender/review-stats")
	public ResponseEntity<Map<String, Object>> getReviewStats(@RequestParam int venderId) {
		Map<String, Object> reviewStats = hyunVenderService.getReviewStats(venderId);
		return new ResponseEntity<>(reviewStats, HttpStatus.OK);
	}

	// 매출 그래프
	@GetMapping("/vender/sales")
	public ResponseEntity<?> getSalesData(@RequestParam String filterType, @RequestParam String venderId,
			@RequestParam String startDate, @RequestParam String endDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("filterType", filterType);
		params.put("venderId", venderId);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		List<Map<String, Object>> salesData = hyunVenderService.getSalesDataByPeriod(params);
		return ResponseEntity.ok(salesData);
	}

	// 리뷰 목록 조회
	@GetMapping("/vender/reviews")
	public ResponseEntity<List<Map<String, Object>>> getReviews(@RequestParam int venderId) {
		List<Map<String, Object>> reviews = hyunVenderService.getReviewsByVenderId(venderId);
		return ResponseEntity.ok(reviews);
	}

	// alias 기반으로 venderId 가져오기
	@GetMapping("/vender/getIdByAlias/{alias}")
	public ResponseEntity<Long> getVenderIdByAlias(@PathVariable String alias) {
		Long venderId = hyunVenderService.getVenderIdByAlias(alias);

		if (venderId == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // alias에 맞는 venderId가 없으면 404 반환
		}

		return ResponseEntity.ok(venderId); // venderId가 존재하면 200 OK와 함께 반환
	}

	// venderId로 alias 조회
    @GetMapping("/vender/getAliasById/{venderId}")
    public ResponseEntity<String> getAliasById(@PathVariable Long venderId) {
        String alias = hyunVenderService.getAliasById(venderId);

        if (alias == null) {
            return ResponseEntity.status(404).body("Alias not found for the given venderId");
        }

        return ResponseEntity.ok(alias);
    }
}
