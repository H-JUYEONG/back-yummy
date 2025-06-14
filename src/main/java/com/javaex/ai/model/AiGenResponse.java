package com.javaex.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AiGenResponse {
    private String status;
    private String message;

    @JsonProperty("image_urls")
    private List<String> imageUrls;

    @JsonProperty("image_url")  // 🔥 FastAPI의 "image_url" 필드도 추가 (단일 값)
    private String imageUrl;

    private String prompt;

    @JsonProperty("original_prompt")  // 🔥 FastAPI의 "original_prompt"와 정확히 일치하도록 설정
    private String originalPrompt;

    // Getter & Setter
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getOriginalPrompt() {
        return originalPrompt;
    }

    public void setOriginalPrompt(String originalPrompt) {
        this.originalPrompt = originalPrompt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public String toString() {
        return "AiGenResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", imageUrls=" + imageUrls +
                ", imageUrl='" + imageUrl + '\'' +
                ", prompt='" + prompt + '\'' +
                ", originalPrompt='" + originalPrompt + '\'' +
                '}';
    }
}
