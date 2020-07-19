package pg.services;

import java.util.List;

import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pg.dao.OrderDAO;
import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.orderModel.Style;
import pg.registerModel.ItemDescription;
import pg.registerModel.StyleItem;
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
	public List<Style> getBuyerWiseStylesItem(String buyerId) {
		// TODO Auto-generated method stub
		return orderDAO.getBuyerWiseStylesItem(buyerId);
	}

	@Override
	public List<ItemDescription> getStyleWiseItem(String styleId) {
		// TODO Auto-generated method stub
		return orderDAO.getStyleWiseItem(styleId);
	}

	@Override
	public boolean addBuyerPoItem(BuyerPoItem buyerPoItem) {
		// TODO Auto-generated method stub
		return orderDAO.addBuyerPoItem(buyerPoItem);
	}

	@Override
	public List<BuyerPoItem> getBuyerPOItemList(String buyerPOId) {
		// TODO Auto-generated method stub
		return orderDAO.getBuyerPOItemList(buyerPOId);
	}

	@Override
	public boolean submitBuyerPO(BuyerPO buyerPo) {
		// TODO Auto-generated method stub
		return orderDAO.submitBuyerPO(buyerPo);
	}

	@Override
	public List<BuyerPO> getBuyerPoList() {
		// TODO Auto-generated method stub
		return orderDAO.getBuyerPoList();
	}

	@Override
	public BuyerPO getBuyerPO(String buyerPoNo) {
		// TODO Auto-generated method stub
		return orderDAO.getBuyerPO(buyerPoNo);
	}

}
