package pg.services;

import java.sql.SQLException;
import java.util.List;

import pg.model.login;
import pg.orderModel.Style;
import pg.registerModel.ItemDescription;
import pg.registerModel.SizeGroup;

public interface OrderService {
	
	List<ItemDescription> getItemDescriptionList();

	boolean SaveStyleCreate(String user, String buyerName, String itemName, String styleNo,String size, String date,
			String frontimg, String backimg) throws SQLException;

	List<Style> getStyleWiseItemList();

	List<Style> getStyleList();

	List<Style> getStyleWiseItem(String value);

	

}
