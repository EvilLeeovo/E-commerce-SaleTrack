package eCommerceSaleTrack.model;

import java.util.Date;

public class OrderReviews {
	protected String reviewId;
	protected String orderId;
	protected int reviewScore;
	protected String reviewCommentTitle;
	protected String reviewCommentMessage;
	protected Date reviewCreationDate;
	protected Date reviewAnswerTimestamp;

	/**
	 * Construct a OrderReviews instance, with input arguments:
	 * 
	 * @param reviewId              is the review id
	 * @param orderId               is the order id
	 * @param reviewScore           is score of review
	 * @param reviewCommentTitle    is comment title
	 * @param reviewCommentMessage  is comment message
	 * @param reviewCreationDate    is created time
	 * @param reviewAnswerTimestamp is answered time
	 */
	public OrderReviews(String reviewId, String orderId, int reviewScore, String reviewCommentTitle,
			String reviewCommentMessage, Date reviewCreationDate, Date reviewAnswerTimestamp) {
		this.reviewId = reviewId;
		this.orderId = orderId;
		this.reviewScore = reviewScore;
		this.reviewCommentTitle = reviewCommentTitle;
		this.reviewCommentMessage = reviewCommentMessage;
		this.reviewCreationDate = reviewCreationDate;
		this.reviewAnswerTimestamp = reviewAnswerTimestamp;
	}
	
	/**
	 * Construct a OrderReviews instance, using aotu-generate review id by mySQL, with input arguments:
	 * 
	 * @param orderId               is the order id
	 * @param reviewScore           is score of review
	 * @param reviewCommentTitle    is comment title
	 * @param reviewCommentMessage  is comment message
	 * @param reviewCreationDate    is created time
	 * @param reviewAnswerTimestamp is answered time
	 */
	public OrderReviews(String orderId, int reviewScore, String reviewCommentTitle,
			String reviewCommentMessage, Date reviewCreationDate, Date reviewAnswerTimestamp) {
		this.orderId = orderId;
		this.reviewScore = reviewScore;
		this.reviewCommentTitle = reviewCommentTitle;
		this.reviewCommentMessage = reviewCommentMessage;
		this.reviewCreationDate = reviewCreationDate;
		this.reviewAnswerTimestamp = reviewAnswerTimestamp;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}

	public String getReviewCommentTitle() {
		return reviewCommentTitle;
	}

	public void setReviewCommentTitle(String reviewCommentTitle) {
		this.reviewCommentTitle = reviewCommentTitle;
	}

	public String getReviewCommentMessage() {
		return reviewCommentMessage;
	}

	public void setReviewCommentMessage(String reviewCommentMessage) {
		this.reviewCommentMessage = reviewCommentMessage;
	}

	public Date getReviewCreationDate() {
		return reviewCreationDate;
	}

	public void setReviewCreationDate(Date reviewCreationDate) {
		this.reviewCreationDate = reviewCreationDate;
	}

	public Date getReviewAnswerTimestamp() {
		return reviewAnswerTimestamp;
	}

	public void setReviewAnswerTimestamp(Date reviewAnswerTimestamp) {
		this.reviewAnswerTimestamp = reviewAnswerTimestamp;
	}

}
