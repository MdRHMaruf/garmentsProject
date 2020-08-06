package pg.orderModel;

public class accessorieIndent {
	String autoid;
	String user;
	String po;
	String style;
	String itemname;
	String itemcolor;
	String shippingmark;
	String accessoriesname;
	String accessoriessize;
	String size;
	String orderqty;
	String qtyindozen;
	String reqperpcs;
	String reqperdozen;
	String perunit;
	String totalbox;
	String dividedby;
	String extrainpercent;
	String percentqty;
	String totalqty;
	String unit;
	String grandqty;
	String brand;
	String accessoriescolor;
	String accessoriesName;
	String sizeName;
	String requiredUnitQty;
	String indentBrandId;
	String indentColorId;
	
	public accessorieIndent() {
		
	}
	
	
	public accessorieIndent(String AccIndentId,String PurchaseOrder,String StyleNo,String ItemName,String ColorName,String ShippingMarks,String AccessoriesName,String SizeName,String accessoriesSize,String PerUnit,String TotalBox,String OrderQty,String QtyInDozen,String ReqPerPices,String ReqPerDoz,String DividedBy,String PercentageExtra,String PercentageExtraQty,String TotalQty,String UnitName,String RequireUnitQty,String IndentBrandId,String IndentColorId) {
		this.autoid=AccIndentId;
		System.out.println("StyleNo "+StyleNo);
		this.style=StyleNo;
		this.po=PurchaseOrder;
		this.itemname=ItemName;
		this.itemcolor=ColorName;
		this.shippingmark=ShippingMarks;
		this.accessoriesname=AccessoriesName;
		this.sizeName=SizeName;
		this.accessoriessize=accessoriesSize;
		this.perunit=PerUnit;
		this.totalbox=TotalBox;
		this.orderqty=OrderQty;
		this.qtyindozen=QtyInDozen;
		this.reqperpcs=ReqPerPices;
		this.reqperdozen=ReqPerDoz;
		this.dividedby=DividedBy;
		this.extrainpercent=PercentageExtra;
		this.percentqty=PercentageExtraQty;
		this.totalqty=TotalQty;
		this.unit=UnitName;
		this.requiredUnitQty=RequireUnitQty;
		this.indentBrandId=IndentBrandId;
		this.indentColorId=IndentColorId;
	}
	
	public accessorieIndent(String autoId,String po, String style, String itemname, String itemcolor,String shippingmark,String accessoriesName,String sizeName,String requiredUnitQty) {
		this.autoid=autoId;
		this.po=po;
		this.style=style;
		this.itemname=itemname;
		this.itemcolor=itemcolor;
		this.shippingmark=shippingmark;
		this.accessoriesName=accessoriesName;
		this.sizeName=sizeName;
		this.requiredUnitQty=requiredUnitQty;
	}
	
	
	
	public String getIndentBrandId() {
		return indentBrandId;
	}


	public void setIndentBrandId(String indentBrandId) {
		this.indentBrandId = indentBrandId;
	}


	public String getIndentColorId() {
		return indentColorId;
	}


	public void setIndentColorId(String indentColorId) {
		this.indentColorId = indentColorId;
	}


	public String getAccessoriesName() {
		return accessoriesName;
	}

	public void setAccessoriesName(String accessoriesName) {
		this.accessoriesName = accessoriesName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getRequiredUnitQty() {
		return requiredUnitQty;
	}

	public void setRequiredUnitQty(String requiredUnitQty) {
		this.requiredUnitQty = requiredUnitQty;
	}

	public String getAutoid() {
		return autoid;
	}
	public void setAutoid(String autoid) {
		this.autoid = autoid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemcolor() {
		return itemcolor;
	}
	public void setItemcolor(String itemcolor) {
		this.itemcolor = itemcolor;
	}
	public String getShippingmark() {
		return shippingmark;
	}
	public void setShippingmark(String shippingmark) {
		this.shippingmark = shippingmark;
	}
	public String getAccessoriesname() {
		return accessoriesname;
	}
	public void setAccessoriesname(String accessoriesname) {
		this.accessoriesname = accessoriesname;
	}
	public String getAccessoriessize() {
		return accessoriessize;
	}
	public void setAccessoriessize(String accessoriessize) {
		this.accessoriessize = accessoriessize;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getOrderqty() {
		return orderqty;
	}
	public void setOrderqty(String orderqty) {
		this.orderqty = orderqty;
	}
	public String getQtyindozen() {
		return qtyindozen;
	}
	public void setQtyindozen(String qtyindozen) {
		this.qtyindozen = qtyindozen;
	}
	public String getReqperpcs() {
		return reqperpcs;
	}
	public void setReqperpcs(String reqperpcs) {
		this.reqperpcs = reqperpcs;
	}
	public String getReqperdozen() {
		return reqperdozen;
	}
	public void setReqperdozen(String reqperdozen) {
		this.reqperdozen = reqperdozen;
	}
	public String getPerunit() {
		return perunit;
	}
	public void setPerunit(String perunit) {
		this.perunit = perunit;
	}
	public String getTotalbox() {
		return totalbox;
	}
	public void setTotalbox(String totalbox) {
		this.totalbox = totalbox;
	}
	public String getDividedby() {
		return dividedby;
	}
	public void setDividedby(String dividedby) {
		this.dividedby = dividedby;
	}
	public String getExtrainpercent() {
		return extrainpercent;
	}
	public void setExtrainpercent(String extrainpercent) {
		this.extrainpercent = extrainpercent;
	}
	public String getPercentqty() {
		return percentqty;
	}
	public void setPercentqty(String percentqty) {
		this.percentqty = percentqty;
	}
	public String getTotalqty() {
		return totalqty;
	}
	public void setTotalqty(String totalqty) {
		this.totalqty = totalqty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGrandqty() {
		return grandqty;
	}
	public void setGrandqty(String grandqty) {
		this.grandqty = grandqty;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getAccessoriescolor() {
		return accessoriescolor;
	}
	public void setAccessoriescolor(String accessoriescolor) {
		this.accessoriescolor = accessoriescolor;
	}
	
	
	
	

}
