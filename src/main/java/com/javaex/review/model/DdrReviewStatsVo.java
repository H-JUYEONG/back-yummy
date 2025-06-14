package com.javaex.review.model;

public class DdrReviewStatsVo {
	private double averageRating;
    private int totalReviews;
    private int ratingCounts5;
    private int ratingCounts4;
    private int ratingCounts3;
    private int ratingCounts2;
    private int ratingCounts1;
	public DdrReviewStatsVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DdrReviewStatsVo(double averageRating, int totalReviews, int ratingCounts5, int ratingCounts4,
			int ratingCounts3, int ratingCounts2, int ratingCounts1) {
		super();
		this.averageRating = averageRating;
		this.totalReviews = totalReviews;
		this.ratingCounts5 = ratingCounts5;
		this.ratingCounts4 = ratingCounts4;
		this.ratingCounts3 = ratingCounts3;
		this.ratingCounts2 = ratingCounts2;
		this.ratingCounts1 = ratingCounts1;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public int getTotalReviews() {
		return totalReviews;
	}
	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}
	public int getRatingCounts5() {
		return ratingCounts5;
	}
	public void setRatingCounts5(int ratingCounts5) {
		this.ratingCounts5 = ratingCounts5;
	}
	public int getRatingCounts4() {
		return ratingCounts4;
	}
	public void setRatingCounts4(int ratingCounts4) {
		this.ratingCounts4 = ratingCounts4;
	}
	public int getRatingCounts3() {
		return ratingCounts3;
	}
	public void setRatingCounts3(int ratingCounts3) {
		this.ratingCounts3 = ratingCounts3;
	}
	public int getRatingCounts2() {
		return ratingCounts2;
	}
	public void setRatingCounts2(int ratingCounts2) {
		this.ratingCounts2 = ratingCounts2;
	}
	public int getRatingCounts1() {
		return ratingCounts1;
	}
	public void setRatingCounts1(int ratingCounts1) {
		this.ratingCounts1 = ratingCounts1;
	}
	@Override
	public String toString() {
		return "DdrReviewStatsVo [averageRating=" + averageRating + ", totalReviews=" + totalReviews
				+ ", ratingCounts5=" + ratingCounts5 + ", ratingCounts4=" + ratingCounts4 + ", ratingCounts3="
				+ ratingCounts3 + ", ratingCounts2=" + ratingCounts2 + ", ratingCounts1=" + ratingCounts1 + "]";
	}

}
