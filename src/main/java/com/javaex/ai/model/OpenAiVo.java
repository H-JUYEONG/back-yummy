package com.javaex.ai.model;

import java.util.List;

public class OpenAiVo {

	private int faqId;
	private String faqQuestion;
	private String faqAnswer;
	private String faqTags;

	public static List<OpenAiVo> findByFaqQuestionContaining(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public OpenAiVo() {
		super();
	}

	public OpenAiVo(int faqId, String faqQuestion, String faqAnswer, String faqTags) {
		super();
		this.faqId = faqId;
		this.faqQuestion = faqQuestion;
		this.faqAnswer = faqAnswer;
		this.faqTags = faqTags != null ? faqTags : ""; // null 처리
	}

	public int getFaqId() {
		return faqId;
	}

	public void setFaqId(int faqId) {
		this.faqId = faqId;
	}

	public String getFaqQuestion() {
		return faqQuestion;
	}

	public void setFaqQuestion(String faqQuestion) {
		this.faqQuestion = faqQuestion;
	}

	public String getFaqAnswer() {
		return faqAnswer;
	}

	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}

	public String getFaqTags() {
		return faqTags;
	}

	public void setFaqTags(String faqTags) {
		this.faqTags = faqTags != null ? faqTags : ""; // null 처리
	}

	@Override
	public String toString() {
		return "OpenAiVo [faqId=" + faqId + ", faqQuestion=" + faqQuestion + ", faqAnswer=" + faqAnswer + ", faqTags="
				+ faqTags + "]";
	}

}
