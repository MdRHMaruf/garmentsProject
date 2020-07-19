package pg.dao;

import java.util.List;

import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.orderModel.Style;
import pg.registerModel.ItemDescription;
import pg.registerModel.SizeGroup;
import pg.registerModel.StyleItem;

public interface OrderDAO {
	List<ItemDescription> getItemDescriptionList();
	List<Style> getBuyerWiseStylesItem(String buyerId);
	List<ItemDescription> getStyleWiseItem(String styleId);
	
	boolean addBuyerPoItem(BuyerPoItem buyerPoItem);
	List<BuyerPoItem> getBuyerPOItemList(String buyerPOId);
	boolean submitBuyerPO(BuyerPO buyerPo);
	List<BuyerPO> getBuyerPoList();
	BuyerPO getBuyerPO(String buyerPoNo);
}
