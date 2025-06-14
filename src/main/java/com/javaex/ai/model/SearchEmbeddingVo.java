package com.javaex.ai.model;

public class SearchEmbeddingVo {
    private int id;
    private String tableName;
    private int originalId;
    private String combinedText;
    private String embedding; // JSON 문자열 형태

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }

    public String getCombinedText() {
        return combinedText;
    }

    public void setCombinedText(String combinedText) {
        this.combinedText = combinedText;
    }

    public String getEmbedding() {
        return embedding;
    }

    public void setEmbedding(String embedding) {
        this.embedding = embedding;
    }

    @Override
    public String toString() {
        return "SearchEmbeddingVo{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", originalId=" + originalId +
                ", combinedText='" + combinedText + '\'' +
                ", embedding='" + embedding + '\'' +
                '}';
    }
}
