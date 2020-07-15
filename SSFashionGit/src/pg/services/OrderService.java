package pg.services;

import java.util.List;

import pg.orderModel.Style;
import pg.registerModel.ItemDescription;
import pg.registerModel.SizeGroup;
import pg.registerModel.StyleItem;

public interface OrderService {

	List<ItemDescription> getItemDescriptionList();
	List<Style> getBuyerWiseStylesItem(String buyerId);
	List<ItemDescription> getStyleWiseItem(String styleId);

}
