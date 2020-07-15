package pg.orderModel;

public class Style {
	String styleId;
	String buyerId;
	String buyerName;
	String styleNo;
	String itemType;
	String size;
	
	public Style() {}
	
	public Style(String styleId, String buyerId, String buyerName, String styleNo, String itemType, String size) {
		super();
		this.styleId = styleId;
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.styleNo = styleNo;
		this.itemType = itemType;
		this.size = size;
	}



	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
