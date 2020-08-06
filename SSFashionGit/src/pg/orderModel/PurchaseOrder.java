package pg.orderModel;

public class PurchaseOrder {
	
	String orderDate;
	String deliveryDate;
	String deliveryTo;
	String orderBy;
	String billTo;
	String manulPO;
	String paymentType;
	String currency;
	String userId;
	
	
	public PurchaseOrder(String orderDate, String deliveryDate, String deliveryTo, String orderBy, String billTo,
			String manulPO, String paymentType, String currency, String userId) {
		super();
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.deliveryTo = deliveryTo;
		this.orderBy = orderBy;
		this.billTo = billTo;
		this.manulPO = manulPO;
		this.paymentType = paymentType;
		this.currency = currency;
		this.userId = userId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryTo() {
		return deliveryTo;
	}
	public void setDeliveryTo(String deliveryTo) {
		this.deliveryTo = deliveryTo;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public String getManulPO() {
		return manulPO;
	}
	public void setManulPO(String manulPO) {
		this.manulPO = manulPO;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
