package pg.dao;

import java.util.List;

import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.orderModel.Costing;
import pg.orderModel.FabricsIndent;
import pg.orderModel.Style;
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
	
	//Purchase Order
	
	List<AccessoriesItem> getTypeWiseIndentItems(String purchaseOrder,String styleId,String type);
}
