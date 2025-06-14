package com.javaex.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.user.model.DdrUserNicknameVo;
import com.javaex.user.service.DdrUserService;
import com.javaex.vendor.model.DdrVenderVo;
import com.javaex.common.util.JsonResult;

@RestController
@RequestMapping("/api/user")
public class DdrUserController {

	@Autowired
	private DdrUserService ddrUserService;

	@GetMapping("/nickname/{userId}")
	@ResponseBody
	public JsonResult getUserNickname(@PathVariable("userId") int userId) {
		System.out.println("[유저 닉네임 조회] 시작 - userId: " + userId);

		DdrUserNicknameVo ddrusernicknamevo = ddrUserService.exeGetUserNickname(userId);

		return JsonResult.success(ddrusernicknamevo);
	}

	@PostMapping("/{reviewId}/report")
	public ResponseEntity<String> reportReview(@PathVariable int reviewId, @RequestParam int userId,
			@RequestParam String reason) {
		String result = ddrUserService.reportReview(reviewId, userId, reason);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/vender/profile/{venderId}")
	public JsonResult getVenderProfile(@PathVariable("venderId") int venderId) {

		DdrVenderVo venderImg = ddrUserService.exeGetVenderProfile(venderId);

		return JsonResult.success(venderImg);
	}

}
