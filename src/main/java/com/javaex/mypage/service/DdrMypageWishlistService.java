package com.javaex.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.mypage.model.DdrMypageWishlistVo;
import com.javaex.mypage.repository.DdrMypageWishlistDao;

@Service
public class DdrMypageWishlistService {
    
    @Autowired
    private DdrMypageWishlistDao ddrmywishlistdao;
    
    public Map<String, Object> exegetWishlistProducts(DdrMypageWishlistVo vo) {
        // 페이징 계산
        int offset = (vo.getPage() - 1) * vo.getSize();
        vo.setOffset(offset);
        
        // 데이터 조회 전 로그
        System.out.println("조회 조건: " + vo.toString());
        
        // 데이터 조회
        List<DdrMypageWishlistVo> content = ddrmywishlistdao.selectWishlistProducts(vo);
        int totalElements = ddrmywishlistdao.selectWishlistProductsCount(vo);
        
        // 조회 결과 로그
        System.out.println("총 데이터 수: " + totalElements);
        System.out.println("조회된 데이터: " + content.size() + "건");
        
        int totalPages = (int) Math.ceil((double) totalElements / vo.getSize());
        
        Map<String, Object> result = new HashMap<>();
        result.put("content", content);
        result.put("totalPages", totalPages);
        result.put("totalElements", totalElements);
        result.put("currentPage", vo.getPage());
        
        return result;
    }
}