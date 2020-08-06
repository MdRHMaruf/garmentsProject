package pg.dao;

import java.util.List;

import pg.model.commonModel;
import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.orderModel.Costing;
import pg.orderModel.FabricsIndent;
import pg.orderModel.PurchaseOrderItem;
import pg.orderModel.Style;
import pg.orderModel.accessorieIndent;
import pg.registerModel.AccessoriesItem;
import pg.registerModel.Color;
import pg.registerModel.FabricsItem;
import pg.registerModel.ItemDescription;
import pg.registerModel.ParticularItem;
import pg.registerModel.SizeGroup;
import pg.registerModel.StyleItem;

public interface OrderDAO {
	List<ItemDescription> getItemDescriptionList();
	List<Style> getBuyerWiseStylesItem(String buyerId);
	List<ItemDescription> getStyleWiseItem(String styleId);

	List<Style> getStyleList();

	List<ParticularItem> getTypeWiseParticularList(String type);
	boolean saveCosting(Costing costing);
	boolean editCosting(Costing costing);
	boolean deleteCosting(String autoId);
	boolean cloningCosting(String oldStyleId,String oldItemId,String newStyleId,String newItemId,String userId);
	List<Costing> getCostingList(String styleId,String itemId);
	List<Costing> getCostingList();
	Costing getCostingItem(String autoId);

	boolean addBuyerPoItem(BuyerPoItem buyerPoItem);
	boolean editBuyerPoItem(BuyerPoItem buyerPoItem);
	List<BuyerPoItem> getBuyerPOItemList(String buyerPOId);
	BuyerPoItem getBuyerPOItem(String itemAutoId);
	boolean deleteBuyerPoItem(String itemAutoId);
	boolean submitBuyerPO(BuyerPO buyerPo);
	boolean editBuyerPO(BuyerPO buyerPo);
	List<BuyerPO> getBuyerPoList();
	BuyerPO getBuyerPO(String buyerPoNo);


	//Fabrics Indent
	List<String> getPurchaseOrderList();
	List<Color> getStyleItemWiseColor(String styleId,String itemId);
	List<Style> getPOWiseStyleList(String purchaseOrder);
	boolean saveFabricsIndent(FabricsIndent fabricsIndent);
	boolean editFabricsIndent(FabricsIndent fabricsIndent);
	boolean isFabricsIndentExist(FabricsIndent fabricsIndent);
	List<FabricsIndent> getFabricsIndentList();
	FabricsIndent getFabricsIndent(String indentId);
	double getOrderQuantity(String purchaseOrder,String styleId,String itemId,String colorId);


	//Accessories
	public String maxAIno(); 
	public List<commonModel>PurchaseOrders();
	public List<commonModel>Styles(String po);
	public List<commonModel>Colors(String style, String item);
	public List<commonModel>Items(String buyerorderid,String style);
	public List<commonModel>AccessoriesItem();
	public List<commonModel>Size(String buyerorderid, String style, String item, String color);
	public List<commonModel>Unit();
	public List<commonModel>Brands();

	public List<commonModel>ShippingMark(String po, String style, String item);
	public List<commonModel>AllColors();
	public List<commonModel>SizewiseQty(String buyerorderid, String style,String item,String color,String size);

	public boolean insertaccessoriesIndent(accessorieIndent ai);

	public List<accessorieIndent>PendingList();
	List<commonModel> styleItemsWiseColor(String buyerorderid,String style,String item);

	List<accessorieIndent> getAccessoriesIndent(String po, String style, String itemname, String itemcolor);
	List<accessorieIndent> getPendingAccessoriesIndent();
	List<accessorieIndent> getAccessoriesIndentItemDetails(String id);
	boolean editaccessoriesIndent(accessorieIndent v);
	boolean confrimAccessoriesIndent(String user, String aiNo);

	//Purchase Order
	List<AccessoriesItem> getTypeWiseIndentItems(String purchaseOrder,String styleId,String type);
	List<PurchaseOrderItem> getPurchaseOrderItemList(PurchaseOrderItem purchaseOrderItem);
}
