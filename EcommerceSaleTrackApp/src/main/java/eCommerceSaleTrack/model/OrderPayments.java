package eCommerceSaleTrack.model;

public class OrderPayments {
	protected String orderId;
	protected int paymentSequential;
	protected PaymentType paymentType;
	protected int paymentInstallments;
	protected double paymentValue;

	public enum PaymentType {
		credit_card, boleto, voucher, debit_card, not_defined
	}

	/**
	 * Construct a OrderPayments instance, with input arguments:
	 * 
	 * @param orderId             is the order id
	 * @param paymentSequential   is the sequential of payment
	 * @param paymentType         is the payment type
	 * @param paymentInstallments is installment
	 * @param paymentValue        is the value
	 */
	public OrderPayments(String orderId, int paymentSequential, PaymentType paymentType, int paymentInstallments,
			double paymentValue) {
		super();
		this.orderId = orderId;
		this.paymentSequential = paymentSequential;
		this.paymentType = paymentType;
		this.paymentInstallments = paymentInstallments;
		this.paymentValue = paymentValue;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getPaymentSequential() {
		return paymentSequential;
	}

	public void setPaymentSequential(int paymentSequential) {
		this.paymentSequential = paymentSequential;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public int getPaymentInstallments() {
		return paymentInstallments;
	}

	public void setPaymentInstallments(int paymentInstallments) {
		this.paymentInstallments = paymentInstallments;
	}

	public double getPaymentValue() {
		return paymentValue;
	}

	public void setPaymentValue(double paymentValue) {
		this.paymentValue = paymentValue;
	}

}
