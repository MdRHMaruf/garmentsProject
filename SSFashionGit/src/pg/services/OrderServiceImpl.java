package pg.services;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pg.dao.OrderDAO;
import pg.model.login;
import pg.orderModel.Style;
import pg.registerModel.ItemDescription;
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired 
	OrderDAO orderDAO;

	@Override
	public List<ItemDescription> getItemDescriptionList() {
		// TODO Auto-generated method stub
		return orderDAO.getItemDescriptionList();
	}

	@Override
	public boolean SaveStyleCreate(String user, String buyerName, String itemName, String styleNo,String size, String date,
			String frontimg, String backimg) throws SQLException {
		// TODO Auto-generated method stub
		return orderDAO.SaveStyleCreate(user, buyerName, itemName, styleNo,size, date, frontimg, backimg);
	}

	@Override
	public List<Style> getStyleWiseItemList() {
		// TODO Auto-generated method stub
		return orderDAO.getStyleWiseItemList();
	}

	@Override
	public List<Style> getStyleList() {
		// TODO Auto-generated method stub
		return orderDAO.getStyleList();
	}
	
	@Override
	public List<Style> getStyleWiseItem(String value) {
		// TODO Auto-generated method stub
		return orderDAO.getStyleWiseItem(value);
	}

}
