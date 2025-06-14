package com.javaex.ai.service.processor;

public interface EmbeddingProcessor {
    void processAll();  // 전체 레코드 임베딩
    void processOne(int id); // 단일 레코드 임베딩 (선택)
}