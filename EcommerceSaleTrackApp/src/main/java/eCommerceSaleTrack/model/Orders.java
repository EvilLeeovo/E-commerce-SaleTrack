package eCommerceSaleTrack.model;

import java.util.Date;

public class Orders {
	protected String orderId;
	protected String customerId;
	protected OrderStatus orderStatus;
	protected Date orderPurchaseTimestamp;
	protected Date orderApprovedAt;
	protected Date orderDeliveredCarrierDate;
	protected Date orderDeliveredCustomerDate;
	protected Date orderEstimatedDeliveryDate;

	public enum OrderStatus {
		delivered, shipped, canceled, unacailable, invoiced
	}

	/**
	 * Construct a Orders instance, with input arguments:
	 * 
	 * @param orderId                    is the id
	 * @param customerId                 is customer's id
	 * @param orderStatus                is status of the order
	 * @param orderPurchaseTimestamp     is purchase time
	 * @param orderApprovedAt            is approved time
	 * @param orderDeliveredCarrierDate  is carrier delivered date
	 * @param orderDeliveredCustomerDate is customer delivered date
	 * @param orderEstimatedDeliveryDate is estimated delivered date
	 */
	public Orders(String orderId, String customerId, OrderStatus orderStatus, Date orderPurchaseTimestamp,
			Date orderApprovedAt, Date orderDeliveredCarrierDate, Date orderDeliveredCustomerDate,
			Date orderEstimatedDeliveryDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.orderPurchaseTimestamp = orderPurchaseTimestamp;
		this.orderApprovedAt = orderApprovedAt;
		this.orderDeliveredCarrierDate = orderDeliveredCarrierDate;
		this.orderDeliveredCustomerDate = orderDeliveredCustomerDate;
		this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
	}
	
	/**
	 * Construct a Orders instance, using auto-generation order ID by mySQL, with input arguments:
	 * 
	 * @param customerId                 is customer's id
	 * @param orderStatus                is status of the order
	 * @param orderPurchaseTimestamp     is purchase time
	 * @param orderApprovedAt            is approved time
	 * @param orderDeliveredCarrierDate  is carrier delivered date
	 * @param orderDeliveredCustomerDate is customer delivered date
	 * @param orderEstimatedDeliveryDate is estimated delivered date
	 */
	public Orders(String customerId, OrderStatus orderStatus, Date orderPurchaseTimestamp,
			Date orderApprovedAt, Date orderDeliveredCarrierDate, Date orderDeliveredCustomerDate,
			Date orderEstimatedDeliveryDate) {
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.orderPurchaseTimestamp = orderPurchaseTimestamp;
		this.orderApprovedAt = orderApprovedAt;
		this.orderDeliveredCarrierDate = orderDeliveredCarrierDate;
		this.orderDeliveredCustomerDate = orderDeliveredCustomerDate;
		this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderPurchaseTimestamp() {
		return orderPurchaseTimestamp;
	}

	public void setOrderPurchaseTimestamp(Date orderPurchaseTimestamp) {
		this.orderPurchaseTimestamp = orderPurchaseTimestamp;
	}

	public Date getOrderApprovedAt() {
		return orderApprovedAt;
	}

	public void setOrderApprovedAt(Date orderApprovedAt) {
		this.orderApprovedAt = orderApprovedAt;
	}

	public Date getOrderDeliveredCarrierDate() {
		return orderDeliveredCarrierDate;
	}

	public void setOrderDeliveredCarrierDate(Date orderDeliveredCarrierDate) {
		this.orderDeliveredCarrierDate = orderDeliveredCarrierDate;
	}

	public Date getOrderDeliveredCustomerDate() {
		return orderDeliveredCustomerDate;
	}

	public void setOrderDeliveredCustomerDate(Date orderDeliveredCustomerDate) {
		this.orderDeliveredCustomerDate = orderDeliveredCustomerDate;
	}

	public Date getOrderEstimatedDeliveryDate() {
		return orderEstimatedDeliveryDate;
	}

	public void setOrderEstimatedDeliveryDate(Date orderEstimatedDeliveryDate) {
		this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
	}

}
