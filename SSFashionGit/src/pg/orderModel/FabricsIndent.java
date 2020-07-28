package pg.orderModel;

public class FabricsIndent {
	String autoId;
	String purchaseOrder;
	String styleId;
	String styleName;
	String itemId;
	String itemName;
	String itemColorId;
	String itemColorName;
	String fabricsId;
	String fabricsName;
	double qty;
	double dozenQty;
	double consumption;
	double inPercent;
	double percentQty;
	double totalQty;
	String unitId;
	String unit;
	double width;
	double yard;
	double gsm;
	double grandQty;
	String fabricsColorId;
	String brandId;
	String userId;
	
	public FabricsIndent() {}
	
	public FabricsIndent(String autoId, String purchaseOrder, String styleId, String itemId, String itemColorId,
			String fabricsId, double qty, double dozenQty, double consumption, double inPercent, double percentQty,
			double totalQty, String unitId, double width, double yard, double gsm, double grandQty,
			String fabricsColorId, String brandId, String userId) {
		super();
		this.autoId = autoId;
		this.purchaseOrder = purchaseOrder;
		this.styleId = styleId;
		this.itemId = itemId;
		this.itemColorId = itemColorId;
		this.fabricsId = fabricsId;
		this.qty = qty;
		this.dozenQty = dozenQty;
		this.consumption = consumption;
		this.inPercent = inPercent;
		this.percentQty = percentQty;
		this.totalQty = totalQty;
		this.unitId = unitId;
		this.width = width;
		this.yard = yard;
		this.gsm = gsm;
		this.grandQty = grandQty;
		this.fabricsColorId = fabricsColorId;
		this.brandId = brandId;
		this.userId = userId;
	}
	
	public FabricsIndent(String autoId, String purchaseOrder, String styleId,String styleName, String itemId,String itemName, String itemColorId,String itemColorName,
			String fabricsId,String fabricsName, double qty, double dozenQty, double consumption, double inPercent, double percentQty,
			double totalQty, String unitId,String unit) {
		super();
		this.autoId = autoId;
		this.purchaseOrder = purchaseOrder;
		this.styleId = styleId;
		this.styleName = styleName;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemColorId = itemColorId;
		this.itemColorName = itemColorName;
		this.fabricsId = fabricsId;
		this.fabricsName = fabricsName;
		this.qty = qty;
		this.dozenQty = dozenQty;
		this.consumption = consumption;
		this.inPercent = inPercent;
		this.percentQty = percentQty;
		this.totalQty = totalQty;
		this.unitId = unitId;
		this.unit = unit;
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemColorId() {
		return itemColorId;
	}
	public void setItemColorId(String itemColorId) {
		this.itemColorId = itemColorId;
	}
	public String getFabricsId() {
		return fabricsId;
	}
	public void setFabricsId(String fabricsId) {
		this.fabricsId = fabricsId;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getDozenQty() {
		return dozenQty;
	}
	public void setDozenQty(double dozenQty) {
		this.dozenQty = dozenQty;
	}
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	public double getInPercent() {
		return inPercent;
	}
	public void setInPercent(double inPercent) {
		this.inPercent = inPercent;
	}
	public double getPercentQty() {
		return percentQty;
	}
	public void setPercentQty(double percentQty) {
		this.percentQty = percentQty;
	}
	public double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(double totalQty) {
		this.totalQty = totalQty;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getYard() {
		return yard;
	}
	public void setYard(double yard) {
		this.yard = yard;
	}
	public double getGsm() {
		return gsm;
	}
	public void setGsm(double gsm) {
		this.gsm = gsm;
	}
	public double getGrandQty() {
		return grandQty;
	}
	public void setGrandQty(double grandQty) {
		this.grandQty = grandQty;
	}
	public String getFabricsColorId() {
		return fabricsColorId;
	}
	public void setFabricsColorId(String fabricsColorId) {
		this.fabricsColorId = fabricsColorId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemColorName() {
		return itemColorName;
	}

	public void setItemColorName(String itemColorName) {
		this.itemColorName = itemColorName;
	}

	public String getFabricsName() {
		return fabricsName;
	}

	public void setFabricsName(String fabricsName) {
		this.fabricsName = fabricsName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
