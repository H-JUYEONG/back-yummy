package com.javaex.order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.common.storage.S3Service;
import com.javaex.order.model.HyunOrderDetailVo;
import com.javaex.order.model.HyunOrderVo;
import com.javaex.order.service.HyunOrderService;

@RestController
@RequestMapping("/api")
public class HyunOrderController {
	@Autowired
	private HyunOrderService hyunOrderService;
	@Autowired
	private S3Service s3Service;

	@GetMapping("/vender/orders")
	public List<HyunOrderVo> getOrders(@RequestParam int venderId) {
		return hyunOrderService.getOrdersByVenderId(venderId);
	}

	@GetMapping("/vender/orders/{orderId}")
	public HyunOrderDetailVo getOrderDetails(@PathVariable int orderId) {
		return hyunOrderService.getOrderDetails(orderId);
	}

	@PostMapping("/vender/orders/{orderId}/status")
	public ResponseEntity<String> updateOrderStatus(@PathVariable("orderId") int orderId, // URL 경로에서 orderId 가져오기
			@RequestParam("orderStatus") String orderStatus // 쿼리 파라미터에서 orderStatus 가져오기
	) {
		try {
			hyunOrderService.updateOrderStatus(orderId, orderStatus);
			return ResponseEntity.ok("Order status updated successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to update order status.");
		}
	}

	@PostMapping("/vender/{orderId}/upload")
	public ResponseEntity<Map<String, String>> uploadOrderMedia(@PathVariable int orderId,
			@RequestParam(value = "video", required = false) MultipartFile videoFile,
			@RequestParam(value = "photo", required = false) MultipartFile photoFile) {

		try {
			if (orderId <= 0) {
				return ResponseEntity.badRequest().body(Map.of("message", "Invalid orderId"));
			}

			// 업로드된 파일 처리
			String videoUrl = null;
			String photoUrl = null;

			if (videoFile != null && !videoFile.isEmpty()) {
				videoUrl = s3Service.uploadFile(videoFile); // S3Service를 통해 업로드
			}

			if (photoFile != null && !photoFile.isEmpty()) {
				photoUrl = s3Service.uploadFile(photoFile); // S3Service를 통해 업로드1
			}

			if (videoUrl != null || photoUrl != null) {
				hyunOrderService.updateOrderMedia(orderId, videoFile, photoFile);
			}

			Map<String, String> response = new HashMap<>();
			response.put("message", "파일이 성공적으로 업로드되었습니다.");
			if (photoUrl != null)
				response.put("photoUrl", photoUrl);
			if (videoUrl != null)
				response.put("videoUrl", videoUrl);

			return ResponseEntity.ok(response);
		} catch (IOException e) {
			return ResponseEntity.status(500).body(Map.of("message", "파일 업로드 실패: " + e.getMessage()));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
		}
	}

}
