package com.javaex.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.common.util.JsonResult;
import com.javaex.mypage.service.DdrMypagePointHistoryService;

@RestController
@RequestMapping("/api/user/points")
public class DdrMypagePointHistoryController {
	
    @Autowired
    private DdrMypagePointHistoryService ddrMypagePointHistoryService;
    
    @GetMapping("/total/{memberId}")
    public ResponseEntity<?> getTotalPoints(@PathVariable("memberId") Long memberId) {
        Integer totalPoints = ddrMypagePointHistoryService.getUserTotalPoint(memberId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("apiData", totalPoints);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/history")
    public JsonResult getPointHistory(@RequestParam int userId) {
        try {
            List<Map<String, Object>> history = ddrMypagePointHistoryService.getPointHistoryByMemberId(userId);
            return JsonResult.success(history);
        } catch (Exception e) {
            return JsonResult.fail("포인트 내역 조회 중 오류 발생");
        }
    }
 
 
}