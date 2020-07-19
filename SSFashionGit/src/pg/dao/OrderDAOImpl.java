package pg.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.orderModel.Style;
import pg.registerModel.ItemDescription;
import pg.registerModel.Size;
import pg.registerModel.SizeGroup;
import pg.registerModel.StyleItem;
import pg.share.HibernateUtil;

@Repository
public class OrderDAOImpl implements OrderDAO{

	 
	@Override
	public List<ItemDescription> getItemDescriptionList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ItemDescription> dataList=new ArrayList<ItemDescription>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select ItemId,ItemName from tbItemDescription order by ItemName";
			
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				
				Object[] element = (Object[]) iter.next();
				
				dataList.add(new ItemDescription(element[0].toString(), element[1].toString()));
			}
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return dataList;
	}

	@Override
	public List<Style> getBuyerWiseStylesItem(String buyerId) {
		// TODO Auto-generated method stub
		
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Style> dataList=new ArrayList<Style>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select styleId,styleNo from tbstyleCreate where buyerId='"+buyerId+"'";
			
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				
				Object[] element = (Object[]) iter.next();
				
				dataList.add(new Style(element[0].toString(), buyerId, "", element[1].toString(),"", ""));
			}
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return dataList;
	}

	@Override
	public List<ItemDescription> getStyleWiseItem(String styleId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ItemDescription> dataList=new ArrayList<ItemDescription>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select itemId,(select itemName from tbItemDescription where itemid = si.itemId) as itemName from tbStyleWiseItem si where styleId='"+styleId+"'";
			
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				
				Object[] element = (Object[]) iter.next();
				
				dataList.add(new ItemDescription(element[0].toString(), element[1].toString()));
			}
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return dataList;
	}

	@Override
	public boolean addBuyerPoItem(BuyerPoItem buyerPoItem) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			
			
			
			String sql="insert into TbBuyerOrderEstimateDetails (buyerId,BuyerOrderId,CustomerOrder,PurchaseOrder,ShippingMarks,FactoryId,StyleId,ItemId,ColorId,SizeReg,sizeGroupId,TotalUnit,UnitCmt,TotalPrice,UnitFob,TotalAmount,EntryTime,UserId) "
					+ "values('"+buyerPoItem.getBuyerId()+"','"+buyerPoItem.getBuyerPOId()+"','"+buyerPoItem.getCustomerOrder()+"','"+buyerPoItem.getPurchaseOrder()+"','"+buyerPoItem.getShippingMark()+"','"+buyerPoItem.getFactoryId()+"','"+buyerPoItem.getStyleId()+"','"+buyerPoItem.getItemId()+"','"+buyerPoItem.getColorId()+"','','"+buyerPoItem.getSizeGroupId()+"','"+buyerPoItem.getTotalUnit()+"','"+buyerPoItem.getUnitCmt()+"','"+buyerPoItem.getTotalPrice()+"','"+buyerPoItem.getUnitFob()+"','"+buyerPoItem.getTotalAmount()+"',CURRENT_TIMESTAMP,'"+buyerPoItem.getUserId()+"');";
			session.createSQLQuery(sql).executeUpdate();
			
			String itemAutoId ="";
			sql="select max(autoId) as itemAutoId from TbBuyerOrderEstimateDetails where BuyerOrderId='"+buyerPoItem.getBuyerPOId()+"' and customerOrder='"+buyerPoItem.getCustomerOrder()+"' and purchaseOrder='"+buyerPoItem.getPurchaseOrder()+"' and userId='"+buyerPoItem.getUserId()+"'";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				itemAutoId =  iter.next().toString();	
			}
			
			int listSize=buyerPoItem.getSizeList().size();
			for(int i=0;i<listSize;i++) {
				sql = "insert into tbBuyerOrderEstimateSize (orderItemId,groupId,sizeId,sizeQuantity,entryTime,userId) values('"+itemAutoId+"','"+buyerPoItem.getSizeGroupId()+"','"+buyerPoItem.getSizeList().get(i).getSizeId()+"','"+buyerPoItem.getSizeList().get(i).getSizeQuantity()+"',CURRENT_TIMESTAMP,'"+buyerPoItem.getUserId()+"');";
				session.createSQLQuery(sql).executeUpdate();
			}
			tx.commit();
			return true;
		}
		catch(Exception ee){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			ee.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<BuyerPoItem> getBuyerPOItemList(String buyerPOId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<BuyerPoItem> dataList=new ArrayList<BuyerPoItem>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select autoId,BuyerOrderId,StyleId,ItemId,FactoryId,ColorId,CustomerOrder,PurchaseOrder,ShippingMarks,SizeReg,sizeGroupId,TotalUnit,UnitCmt,TotalPrice,UnitFob,TotalAmount,userId from TbBuyerOrderEstimateDetails where BuyerOrderId='"+buyerPOId+"' order by sizeGroupId";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();	
				dataList.add(new BuyerPoItem(element[0].toString(), buyerPOId, "0", element[2].toString(), element[3].toString(),element[4].toString(), element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(), element[9].toString(), element[10].toString(), "", Double.valueOf(element[11].toString()), Double.valueOf(element[12].toString()), Double.valueOf(element[13].toString()), Double.valueOf(element[14].toString()), Double.valueOf(element[15].toString()), element[16].toString()));
			}
			
			for (BuyerPoItem buyerPoItem : dataList) {
				sql = "select bs.sizeId,ss.sizeName,bs.sizeQuantity from tbBuyerOrderEstimateSize bs\r\n" + 
						"join tbStyleSize ss \r\n" + 
						"on ss.id = bs.sizeId \r\n" + 
						"where bs.orderItemId = '"+buyerPoItem.getAutoId()+"' and bs.groupId = '"+buyerPoItem.getSizeGroupId()+"' \r\n" + 
						"order by ss.sortingNo";
				List<?> list2 = session.createSQLQuery(sql).list();
				ArrayList<Size> sizeList=new ArrayList<Size>();
				for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();	
					sizeList.add(new Size(element[0].toString(), element[2].toString()));
				}
				buyerPoItem.setSizeList(sizeList);
			}
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return dataList;
	}

	@Override
	public boolean submitBuyerPO(BuyerPO buyerPo) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
				
			String sql="insert into TbBuyerOrderEstimateSummary (BuyerId,Period,PaymentTerm,Incoterm,Currency,QCFor,TotalUnit,UnitCmt,TotalPrice,UnitFob,TotalAmount,note,remarks,EntryTime,UserId) "
					+ "values('"+buyerPo.getBuyerId()+"','','"+buyerPo.getPaymentTerm()+"','','"+buyerPo.getCurrency()+"','','"+buyerPo.getTotalUnit()+"','"+buyerPo.getUnitCmt()+"','"+buyerPo.getTotalPrice()+"','"+buyerPo.getUnitFob()+"','"+buyerPo.getTotalAmount()+"','"+buyerPo.getNote()+"','"+buyerPo.getRemarks()+"',CURRENT_TIMESTAMP,'"+buyerPo.getUserId()+"');";
			session.createSQLQuery(sql).executeUpdate();
			
			String buyerPoId ="";
			sql="select max(autoId) as buyerPoId from TbBuyerOrderEstimateSummary where BuyerId='"+buyerPo.getBuyerId()+"'  and userId='"+buyerPo.getUserId()+"'";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				buyerPoId =  iter.next().toString();	
			}
			
			sql = "update TbBuyerOrderEstimateDetails set BuyerOrderId='"+buyerPoId+"' where buyerOrderId='"+buyerPo.getBuyerPoId()+"' and buyerId='"+buyerPo.getBuyerId()+"' and userId='"+buyerPo.getUserId()+"'";
			session.createSQLQuery(sql).executeUpdate();
			
			tx.commit();
			return true;
		}
		catch(Exception ee){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			ee.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<BuyerPO> getBuyerPoList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<BuyerPO> dataList=new ArrayList<BuyerPO>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select autoId,BuyerId,b.name,(select convert(varchar,bos.EntryTime,103))as date from TbBuyerOrderEstimateSummary bos\r\n" + 
					"join tbBuyer b\r\n" + 
					"on b.id = bos.BuyerId\r\n" + 
					"order by bos.autoId desc";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();	
				dataList.add(new BuyerPO(element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString()));
			}
			
			
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return dataList;
	}

	@Override
	public BuyerPO getBuyerPO(String buyerPoNo) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		BuyerPO buyerPo = null;
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="select autoId,BuyerId,b.name,PaymentTerm,Currency,note,remarks,bos.UserId\r\n" + 
					"from TbBuyerOrderEstimateSummary bos\r\n" + 
					"join tbBuyer b\r\n" + 
					"on bos.BuyerId = b.id\r\n" + 
					"where autoId = '"+buyerPoNo+"'";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
							
				buyerPo= new BuyerPO(element[0].toString(), element[1].toString(), element[3].toString(), element[4].toString(), 0.0, 0.0, 0.0,0.0,0.0, element[5].toString(),element[6].toString(), element[7].toString());
				buyerPo.setBuyerName(element[2].toString());
			}
			buyerPo.setItemList(getBuyerPOItemList(buyerPoNo));
			
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return buyerPo;
	}

}
