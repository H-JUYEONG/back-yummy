package com.javaex.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class AiGenRequest {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("mode")
    private String mode;

    @JsonProperty("image_url")
    private String imageUrl;

    // 새 필드: concept_url
    @JsonProperty("concept_url")
    private String conceptUrl;

    // 필요시 추가 필드들
    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("num_inference_steps")
    private int numInferenceSteps;

    @JsonProperty("use_english_prompt")
    private boolean useEnglishPrompt;

    @JsonProperty("negative_prompt")
    private String negativePrompt;

    @JsonProperty("sketch_style")
    private String sketchStyle;

    // 파일은 MultipartFile로 받을 경우 별도의 처리가 필요하지만, JSON 요청에서는 null로 올 수 있음.
    private MultipartFile file;

    @JsonProperty("draft_url")
    private String draftUrl;

    // Getter & Setter
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getConceptUrl() {
        return conceptUrl;
    }
    public void setConceptUrl(String conceptUrl) {
        this.conceptUrl = conceptUrl;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getNumInferenceSteps() {
        return numInferenceSteps;
    }
    public void setNumInferenceSteps(int numInferenceSteps) {
        this.numInferenceSteps = numInferenceSteps;
    }
    public boolean isUseEnglishPrompt() {
        return useEnglishPrompt;
    }
    public void setUseEnglishPrompt(boolean useEnglishPrompt) {
        this.useEnglishPrompt = useEnglishPrompt;
    }
    public String getNegativePrompt() {
        return negativePrompt;
    }
    public void setNegativePrompt(String negativePrompt) {
        this.negativePrompt = negativePrompt;
    }
    public String getSketchStyle() {
        return sketchStyle;
    }
    public void setSketchStyle(String sketchStyle) {
        this.sketchStyle = sketchStyle;
    }
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public String getDraftUrl() {
        return draftUrl;
    }
    public void setDraftUrl(String draftUrl) {
        this.draftUrl = draftUrl;
    }
}
