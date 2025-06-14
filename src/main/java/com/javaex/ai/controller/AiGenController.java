package com.javaex.ai.controller;

import com.javaex.ai.model.AiGenRequest;
import com.javaex.ai.model.AiGenResponse;
import com.javaex.ai.service.AiGenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/ai")
public class AiGenController {

    private final AiGenService aiGenService;

    public AiGenController(AiGenService aiGenService) {
        this.aiGenService = aiGenService;
    }

    /**
     * 텍스트 기반 스케치 생성 요청 (클라이언트는 JSON 형식으로 요청)
     */
    @PostMapping("/generate-sketch")
    public ResponseEntity<AiGenResponse> generateSketch(@RequestBody AiGenRequest request) throws IOException {
        AiGenResponse response = aiGenService.generateSketch(request);
        System.out.println("컨트롤러 응답: " + response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/select-concept")
    public ResponseEntity<AiGenResponse> selectConcept(@RequestBody AiGenRequest request) throws IOException {
        AiGenResponse response = aiGenService.generateDraft(request);
        System.out.println("select 컨트롤러 응답: " + response);
        return ResponseEntity.ok(response);
    }

    /**
     * 텍스트 기반 초안 이미지 편집 요청 (JSON 형식)
     * FastAPI의 /edit-draft/text 엔드포인트 호출
     */
    @PostMapping("/edit-draft/text")
    public ResponseEntity<AiGenResponse> editDraftText(@RequestBody AiGenRequest request) {
        AiGenResponse response = aiGenService.editDraft(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 최종 디자인 생성 요청 (JSON 형식)
     */
    @PostMapping("/finalize")
    public ResponseEntity<AiGenResponse> generateFinalDesign(@RequestBody AiGenRequest request) {
        AiGenResponse response = aiGenService.generateFinalDesign(request);
        return ResponseEntity.ok(response);
    }
}
