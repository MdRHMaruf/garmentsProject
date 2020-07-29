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
import pg.orderModel.Costing;
import pg.orderModel.FabricsIndent;
import pg.orderModel.Style;
import pg.registerModel.AccessoriesItem;
import pg.registerModel.Color;
import pg.registerModel.ItemDescription;
import pg.registerModel.ParticularItem;
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
	public List<Style> getStyleList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		List<Style> datalist=new ArrayList<Style>();	
		try{	
			tx=session.getTransaction();
			tx.begin();		
			String sql="select StyleId,StyleNo from TbStyleCreate order by StyleNo";		
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				datalist.add(new Style(element[0].toString(),element[1].toString()));				
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
		return datalist;
	}
	
	@Override
	public List<ParticularItem> getTypeWiseParticularList(String type) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		List<ParticularItem> datalist=new ArrayList<ParticularItem>();	
		try{	
			tx=session.getTransaction();
			tx.begin();	
			String sql="";
			if(type.equals("1")) {
				sql="select id,ItemName,UserId from TbFabricsItem where ItemName IS NOT NULL order by ItemName ";
			}else {
				sql="select AutoId,Name,UserId from TbParticularItemInfo order by Name";
			}
					
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				datalist.add(new ParticularItem(element[0].toString(), element[1].toString(), element[2].toString()));				
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
		return datalist;
	}

	@Override
	public boolean saveCosting(Costing costing) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			String sql="insert into TbCostingCreate ("
					+ "StyleId,"
					+ "ItemId,"
					+ "GroupType,"
					+ "FabricationItemId,"
					+ "size,"
					+ "UnitId,"
					+ "width,"
					+ "yard,"
					+ "gsm,"
					+ "consumption,"
					+ "UnitPrice,"
					+ "Amount,"
					+ "Comission,"
					+ "SubmissionDate,"
					+ "EntryTime,"
					+ "UserId)"
					+ " values "
					+ "("
					+ "'"+costing.getStyleId()+"',"
					+ "'"+costing.getItemId()+"',"
					+ "'"+costing.getParticularType()+"',"
					+ "'"+costing.getParticularId()+"',"
					+ "'"+costing.getSize()+"',"
					+ "'"+costing.getUnitId()+"',"
					+ "'"+costing.getWidth()+"',"
					+ "'"+costing.getYard()+"',"
					+ "'"+costing.getGsm()+"',"
					+ "'"+costing.getConsumption()+"',"
					+ "'"+costing.getUnitPrice()+"',"
					+ "'"+costing.getAmount()+"',"
					+ "'"+costing.getCommission()+"',"
					+ "'"+costing.getDate()+"',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+costing.getUserId()+"')";
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
	public boolean editCosting(Costing costing) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			String sql="update TbCostingCreate set "
					+ "StyleId ='"+costing.getStyleId()+"',"
					+ "ItemId ='"+costing.getItemId()+"',"
					+ "GroupType ='"+costing.getParticularType()+"',"
					+ "FabricationItemId ='"+costing.getParticularId()+"',"
					+ "size = '"+costing.getSize()+"',"
					+ "UnitId = '"+costing.getUnitId()+"',"
					+ "width = '"+costing.getWidth()+"',"
					+ "yard = '"+costing.getYard()+"',"
					+ "gsm = '"+costing.getGsm()+"',"
					+ "consumption = '"+costing.getConsumption()+"',"
					+ "UnitPrice = '"+costing.getUnitPrice()+"',"
					+ "Amount = '"+costing.getAmount()+"',"
					+ "Comission = '"+costing.getCommission()+"',"
					+ "SubmissionDate = '"+costing.getDate()+"',"
					+ "EntryTime = CURRENT_TIMESTAMP,"
					+ "UserId='"+costing.getUserId()+"' where autoId='"+costing.getAutoId()+"';";
					
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
	public boolean deleteCosting(String autoId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			String sql="delete from TbCostingCreate where autoId='"+autoId+"';";
					
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
	public boolean cloningCosting(String oldStyleId, String oldItemId, String newStyleId, String newItemId,String userId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Costing> datalist=new ArrayList<Costing>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="select cc.AutoId,cc.StyleId,sc.StyleNo,cc.ItemId,id.itemname,cc.GroupType,cc.FabricationItemId,ISNULL(fi.ItemName,pi.Name) as particula,isnull(si.size,'')as size,cc.UnitId,cc.width,cc.yard,cc.gsm,cc.consumption,cc.UnitPrice,cc.amount,cc.Comission,(select convert(varchar,cc.SubmissionDate,103))as date,cc.UserId \r\n" + 
					"from TbCostingCreate cc\r\n" + 
					"left join TbStyleCreate sc\r\n" + 
					"on cc.StyleId = sc.StyleId\r\n" + 
					"left join tbItemDescription id\r\n" + 
					"on cc.ItemId = id.itemid\r\n" + 
					"left join tbStyleWiseItem si\r\n" + 
					"on cc.ItemId = si.ItemId and cc.StyleId = si.styleid\r\n" + 
					"left join TbFabricsItem fi\r\n" + 
					"on cc.FabricationItemId = fi.id and cc.GroupType='1'\r\n" + 
					"left join TbParticularItemInfo pi\r\n" + 
					"on cc.FabricationItemId = pi.AutoId and cc.GroupType='2' \r\n"+
					"where cc.styleId='"+oldStyleId+"' and cc.itemId='"+oldItemId+"'";
				
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				String date[] = element[17].toString().split("/");
				
				datalist.add(new Costing(element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString(), element[4].toString(),element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(), element[9].toString(), Double.valueOf(element[10].toString()), Double.valueOf(element[11].toString()), Double.valueOf(element[12].toString()),Double.valueOf(element[13].toString()), Double.valueOf(element[14].toString()), Double.valueOf(element[15].toString()), Double.valueOf(element[16].toString()), date[2]+"-"+date[1]+"-"+date[1], element[18].toString()));				
			}
			
			for (Costing costing : datalist) {
				sql="insert into TbCostingCreate ("
						+ "StyleId,"
						+ "ItemId,"
						+ "GroupType,"
						+ "FabricationItemId,"
						+ "size,"
						+ "UnitId,"
						+ "width,"
						+ "yard,"
						+ "gsm,"
						+ "consumption,"
						+ "UnitPrice,"
						+ "Amount,"
						+ "Comission,"
						+ "SubmissionDate,"
						+ "EntryTime,"
						+ "UserId)"
						+ " values "
						+ "("
						+ "'"+newStyleId+"',"
						+ "'"+newItemId+"',"
						+ "'"+costing.getParticularType()+"',"
						+ "'"+costing.getParticularId()+"',"
						+ "'"+costing.getSize()+"',"
						+ "'"+costing.getUnitId()+"',"
						+ "'"+costing.getWidth()+"',"
						+ "'"+costing.getYard()+"',"
						+ "'"+costing.getGsm()+"',"
						+ "'"+costing.getConsumption()+"',"
						+ "'"+costing.getUnitPrice()+"',"
						+ "'"+costing.getAmount()+"',"
						+ "'"+costing.getCommission()+"',"
						+ "'"+costing.getDate()+"',"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+userId+"')";
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
	public List<Costing> getCostingList(String styleId, String itemId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		List<Costing> datalist=new ArrayList<Costing>();	
		try{	
			tx=session.getTransaction();
			tx.begin();	
			String sql="select cc.AutoId,cc.StyleId,sc.StyleNo,cc.ItemId,id.itemname,cc.GroupType,cc.FabricationItemId,ISNULL(fi.ItemName,pi.Name) as particula,isnull(si.size,'')as size,cc.UnitId,cc.width,cc.yard,cc.gsm,cc.consumption,cc.UnitPrice,cc.amount,cc.Comission,(select convert(varchar,cc.SubmissionDate,103))as date,cc.UserId \r\n" + 
					"from TbCostingCreate cc\r\n" + 
					"left join TbStyleCreate sc\r\n" + 
					"on cc.StyleId = sc.StyleId\r\n" + 
					"left join tbItemDescription id\r\n" + 
					"on cc.ItemId = id.itemid\r\n" + 
					"left join tbStyleWiseItem si\r\n" + 
					"on cc.ItemId = si.ItemId and cc.StyleId = si.styleid \r\n" + 
					"left join TbFabricsItem fi\r\n" + 
					"on cc.FabricationItemId = fi.id and cc.GroupType='1'\r\n" + 
					"left join TbParticularItemInfo pi\r\n" + 
					"on cc.FabricationItemId = pi.AutoId and cc.GroupType='2' \r\n"+
					"where cc.styleId='"+styleId+"' and cc.itemId='"+itemId+"'";
				
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				
				datalist.add(new Costing(element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString(), element[4].toString(),element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(), element[9].toString(), Double.valueOf(element[10].toString()), Double.valueOf(element[11].toString()), Double.valueOf(element[12].toString()),Double.valueOf(element[13].toString()), Double.valueOf(element[14].toString()), Double.valueOf(element[15].toString()), Double.valueOf(element[16].toString()), element[17].toString(), element[18].toString()));				
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
		return datalist;
	}

	@Override
	public List<Costing> getCostingList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		List<Costing> datalist=new ArrayList<Costing>();	
		try{	
			tx=session.getTransaction();
			tx.begin();	
			String sql="select cc.StyleId,sc.StyleNo,cc.ItemId,id.itemname,sum(cc.amount) as amount,(select convert(varchar,cc.SubmissionDate,103))as date \r\n" + 
					"from TbCostingCreate cc\r\n" + 
					"left join TbStyleCreate sc\r\n" + 
					"on cc.StyleId = sc.StyleId\r\n" + 
					"left join tbItemDescription id\r\n" + 
					"on cc.ItemId = id.itemid\r\n" + 
					"group by cc.StyleId,sc.StyleNo,cc.ItemId,id.itemname,cc.SubmissionDate";
				
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				datalist.add(new Costing("0", element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString(), "","","", "", "",0.0, 0.0, 0.0, 0.0, 0.0, Double.valueOf(element[4].toString()), 0.0, element[5].toString(), "0"));				
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
		return datalist;
	}

	@Override
	public Costing getCostingItem(String autoId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		Costing costing=null;	
		try{	
			tx=session.getTransaction();
			tx.begin();	
			String sql="select cc.AutoId,cc.StyleId,sc.StyleNo,cc.ItemId,id.itemname,cc.GroupType,cc.FabricationItemId,ISNULL(fi.ItemName,pi.Name) as particula,isnull(si.size,'')as size,cc.UnitId,cc.width,cc.yard,cc.gsm,cc.consumption,cc.UnitPrice,cc.amount,cc.Comission,(select convert(varchar,cc.SubmissionDate,103))as date,cc.UserId \r\n" + 
					"from TbCostingCreate cc\r\n" + 
					"left join TbStyleCreate sc\r\n" + 
					"on cc.StyleId = sc.StyleId\r\n" + 
					"left join tbItemDescription id\r\n" + 
					"on cc.ItemId = id.itemid\r\n" + 
					"left join tbStyleWiseItem si\r\n" + 
					"on cc.ItemId = si.ItemId \r\n" + 
					"left join TbFabricsItem fi\r\n" + 
					"on cc.FabricationItemId = fi.id and cc.GroupType='1'\r\n" + 
					"left join TbParticularItemInfo pi\r\n" + 
					"on cc.FabricationItemId = pi.AutoId and cc.GroupType='2' \r\n"+
					"where cc.autoId='"+autoId+"'";
				
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				
				costing = new Costing(element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString(), element[4].toString(),element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(), element[9].toString(), Double.valueOf(element[10].toString()), Double.valueOf(element[11].toString()), Double.valueOf(element[12].toString()),Double.valueOf(element[13].toString()), Double.valueOf(element[14].toString()), Double.valueOf(element[15].toString()), Double.valueOf(element[16].toString()), element[17].toString(), element[18].toString());				
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
		return costing;
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
	public boolean editBuyerPoItem(BuyerPoItem buyerPoItem) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="update TbBuyerOrderEstimateDetails set buyerId='"+buyerPoItem.getBuyerId()+"',BuyerOrderId='"+buyerPoItem.getBuyerPOId()+"',CustomerOrder='"+buyerPoItem.getCustomerOrder()+"',PurchaseOrder='"+buyerPoItem.getPurchaseOrder()+"',ShippingMarks='"+buyerPoItem.getShippingMark()+"',FactoryId='"+buyerPoItem.getShippingMark()+"',StyleId='"+buyerPoItem.getStyleId()+"',ItemId='"+buyerPoItem.getItemId()+"',ColorId='"+buyerPoItem.getColorId()+"',SizeReg='',sizeGroupId='"+buyerPoItem.getSizeGroupId()+"',TotalUnit='"+buyerPoItem.getTotalUnit()+"',UnitCmt='"+buyerPoItem.getUnitCmt()+"',TotalPrice='"+buyerPoItem.getTotalPrice()+"',UnitFob='"+buyerPoItem.getUnitFob()+"',TotalAmount='"+buyerPoItem.getTotalAmount()+"',EntryTime=CURRENT_TIMESTAMP,UserId='"+buyerPoItem.getUserId()+"' where autoId='"+buyerPoItem.getAutoId()+"'";		
			session.createSQLQuery(sql).executeUpdate();
			
			sql = "delete from tbBuyerOrderEstimateSize where orderItemId='"+buyerPoItem.getAutoId()+"'";
			session.createSQLQuery(sql).executeUpdate();

			int listSize=buyerPoItem.getSizeList().size();
			for(int i=0;i<listSize;i++) {
				sql = "insert into tbBuyerOrderEstimateSize (orderItemId,groupId,sizeId,sizeQuantity,entryTime,userId) values('"+buyerPoItem.getAutoId()+"','"+buyerPoItem.getSizeGroupId()+"','"+buyerPoItem.getSizeList().get(i).getSizeId()+"','"+buyerPoItem.getSizeList().get(i).getSizeQuantity()+"',CURRENT_TIMESTAMP,'"+buyerPoItem.getUserId()+"');";
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

			String sql="select autoId,BuyerOrderId,bo.StyleId,sc.StyleNo,bo.ItemId,id.itemname,FactoryId,bo.ColorId,c.Colorname,CustomerOrder,PurchaseOrder,ShippingMarks,SizeReg,sizeGroupId,TotalUnit,UnitCmt,TotalPrice,UnitFob,TotalAmount,bo.userId \r\n" + 
					"from TbBuyerOrderEstimateDetails bo\r\n" + 
					"left join TbStyleCreate sc \r\n" + 
					"on bo.StyleId = sc.StyleId\r\n" + 
					"left join tbItemDescription id\r\n" + 
					"on bo.ItemId = id.itemid\r\n" + 
					"left join tbColors c\r\n" + 
					"on bo.ColorId = c.ColorId\r\n" + 
					"where BuyerOrderId='"+buyerPOId+"' order by sizeGroupId";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();							
				dataList.add(new BuyerPoItem(element[0].toString(), buyerPOId, "0", element[2].toString(), element[3].toString(),element[4].toString(), element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(), element[9].toString(), element[10].toString(), element[11].toString(), element[12].toString(), element[13].toString(), Double.valueOf(element[14].toString()), Double.valueOf(element[15].toString()), Double.valueOf(element[16].toString()), Double.valueOf(element[17].toString()), Double.valueOf(element[18].toString()), element[19].toString()));
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
	public BuyerPoItem getBuyerPOItem(String itemAutoId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		BuyerPoItem buyerPoItem = null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select bod.autoId,BuyerOrderId,isnull(bod.BuyerId,'')as buyerId,bod.StyleId,sc.StyleNo,bod.ItemId,id.itemname,FactoryId,bod.ColorId,c.Colorname,CustomerOrder,PurchaseOrder,ShippingMarks,SizeReg,sizeGroupId,bod.TotalUnit,bod.UnitCmt,bod.TotalPrice,bod.UnitFob,bod.TotalAmount,bod.userId \r\n" + 
					"from TbBuyerOrderEstimateDetails bod\r\n" + 
					"left join TbStyleCreate sc \r\n" + 
					"on bod.StyleId = sc.StyleId\r\n" + 
					"left join tbItemDescription id\r\n" + 
					"on bod.ItemId = id.itemid\r\n" + 
					"left join tbColors c\r\n" + 
					"on bod.ColorId = c.ColorId\r\n" + 
					"where bod.autoId='"+itemAutoId+"' order by sizeGroupId";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();							
				buyerPoItem = new BuyerPoItem(element[0].toString(), "0", element[2].toString(), element[3].toString(),element[4].toString(), element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(), element[9].toString(), element[10].toString(), element[11].toString(), element[12].toString(), element[13].toString(),element[14].toString(), Double.valueOf(element[15].toString()), Double.valueOf(element[16].toString()), Double.valueOf(element[17].toString()), Double.valueOf(element[18].toString()), Double.valueOf(element[19].toString()), element[20].toString());
			}


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
		return buyerPoItem;
	}

	@Override
	public boolean deleteBuyerPoItem(String itemAutoId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();



			String sql="delete from  TbBuyerOrderEstimateDetails where autoId='"+itemAutoId+"';";
			session.createSQLQuery(sql).executeUpdate();

			sql="delete from  tbBuyerOrderEstimateSize where orderItemId='"+itemAutoId+"';";
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
	public boolean editBuyerPO(BuyerPO buyerPo) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="update TbBuyerOrderEstimateSummary set BuyerId='"+buyerPo.getBuyerId()+"',Period='',PaymentTerm='"+buyerPo.getPaymentTerm()+"',Incoterm='',Currency='"+buyerPo.getCurrency()+"',QCFor='',TotalUnit='"+buyerPo.getTotalUnit()+"',UnitCmt='"+buyerPo.getUnitCmt()+"',TotalPrice='"+buyerPo.getTotalPrice()+"',UnitFob='"+buyerPo.getUnitFob()+"',TotalAmount='"+buyerPo.getTotalAmount()+"',note='"+buyerPo.getNote()+"',remarks='"+buyerPo.getRemarks()+"',EntryTime=CURRENT_TIMESTAMP,UserId='"+buyerPo.getUserId()+"' where autoId='"+buyerPo.getBuyerPoId()+"'";		
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

	@Override
	public List<String> getPurchaseOrderList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<String> dataList=new ArrayList<String>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select PurchaseOrder from TbBuyerOrderEstimateDetails where PurchaseOrder != '' group by PurchaseOrder order by PurchaseOrder";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{		
				dataList.add(iter.next().toString());
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
	public List<Color> getStyleItemWiseColor(String styleId, String itemId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Color> dataList=new ArrayList<Color>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select a.colorId,(select ColorName from tbColors where ColorId=a.ColorId) as ColorName \r\n" + 
					"from TbBuyerOrderEstimateDetails a \r\n" + 
					"where a.StyleId='"+styleId+"' and a.ItemId='"+itemId+"'  group by a.ColorId";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{		
				Object[] element = (Object[]) iter.next();
				dataList.add(new Color(element[0].toString(), element[1].toString(), "", ""));
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
	public List<Style> getPOWiseStyleList(String purchaseOrder) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Style> dataList=new ArrayList<Style>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql=" select a.StyleId,sc.StyleNo \r\n" + 
					" from TbBuyerOrderEstimateDetails a \r\n" + 
					" left join TbStyleCreate sc\r\n" + 
					" on a.StyleId = sc.StyleId\r\n" + 
					" where a.PurchaseOrder='"+purchaseOrder+"' group by a.StyleId,sc.StyleNo";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	

				Object[] element = (Object[]) iter.next();

				dataList.add(new Style(element[0].toString(),element[1].toString()));
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
	public boolean saveFabricsIndent(FabricsIndent fabricsIndent) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			String sql="insert into tbrequiredfabrics  "
					+ "(PurchaseOrder,"
					+ "styleId,"
					+ "itemid,"
					+ "itemcolor,"
					+ "fabricsid,"
					+ "fabricscolor,"
					+ "brand,"
					+ "width,"
					+ "Yard,"
					+ "GSM,"
					+ "qty,"
					+ "dozenqty,"
					+ "consumption,"
					+ "inPercent,"
					+ "PercentQty,"
					+ "TotalQty,"
					+ "unitId,"
					+ "mdapproval,"
					+ "entrytime,"
					+ "entryby) values ("
					+ "'"+fabricsIndent.getPurchaseOrder()+"',"
					+ "'"+fabricsIndent.getStyleId()+"',"
					+ "'"+fabricsIndent.getItemId()+"',"
					+ "'"+fabricsIndent.getItemColorId()+"',"
					+ "'"+fabricsIndent.getFabricsId()+"',"
					+ "'"+fabricsIndent.getFabricsColorId()+"',"
					+ "'"+fabricsIndent.getBrandId()+"',"
					+ "'"+fabricsIndent.getWidth()+"',"
					+ "'"+fabricsIndent.getYard()+"',"
					+ "'"+fabricsIndent.getGsm()+"',"
					+ "'"+fabricsIndent.getQty()+"',"
					+ "'"+fabricsIndent.getDozenQty()+"',"
					+ "'"+fabricsIndent.getConsumption()+"',"
					+ "'"+fabricsIndent.getInPercent()+"',"
					+ "'"+fabricsIndent.getPercentQty()+"',"
					+ "'"+fabricsIndent.getTotalQty()+"',"
					+ "'"+fabricsIndent.getUnitId()+"','0',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+fabricsIndent.getUserId()+"'"
					+ ") ";
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
	public boolean editFabricsIndent(FabricsIndent fabricsIndent) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			String sql="update tbrequiredfabrics set "
					+ "PurchaseOrder='"+fabricsIndent.getPurchaseOrder()+"',"
					+ "styleId='"+fabricsIndent.getStyleId()+"',"
					+ "itemid='"+fabricsIndent.getItemId()+"',"
					+ "itemcolor='"+fabricsIndent.getItemColorId()+"',"
					+ "fabricsid='"+fabricsIndent.getFabricsId()+"',"
					+ "fabricscolor='"+fabricsIndent.getFabricsColorId()+"',"
					+ "brand='"+fabricsIndent.getBrandId()+"',"
					+ "width='"+fabricsIndent.getWidth()+"',"
					+ "Yard='"+fabricsIndent.getYard()+"',"
					+ "GSM='"+fabricsIndent.getGsm()+"',"
					+ "qty='"+fabricsIndent.getQty()+"',"
					+ "dozenqty='"+fabricsIndent.getDozenQty()+"',"
					+ "consumption='"+fabricsIndent.getConsumption()+"',"
					+ "inPercent='"+fabricsIndent.getInPercent()+"',"
					+ "PercentQty='"+fabricsIndent.getPercentQty()+"',"
					+ "TotalQty='"+fabricsIndent.getTotalQty()+"',"
					+ "unitId='"+fabricsIndent.getUnitId()+"',"
					+ "entrytime=CURRENT_TIMESTAMP,"
					+ "entryby='"+fabricsIndent.getUserId()+"' where id = '"+fabricsIndent.getAutoId()+"' ";
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
	public boolean isFabricsIndentExist(FabricsIndent fabricsIndent) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		boolean exist = false;
		try{	
			tx=session.getTransaction();
			tx.begin();	
			String sql="select id,PurchaseOrder,styleId,itemid,itemcolor,fabricsid,qty,dozenqty,consumption from tbRequiredFabrics rf where PurchaseOrder='"+fabricsIndent.getPurchaseOrder()+"' and styleId='"+fabricsIndent.getStyleId()+"' and itemid='"+fabricsIndent.getItemId()+"' and itemcolor = '"+fabricsIndent.getItemColorId()+"' and fabricsid='"+fabricsIndent.getFabricsId()+"' and id != '"+fabricsIndent.getAutoId()+"'";
				
			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0) {
				exist = true;
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
		return exist;
	}

	@Override
	public FabricsIndent getFabricsIndent(String indentId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		FabricsIndent indent= null;
		try{	
			tx=session.getTransaction();
			tx.begin();	
			String sql="select id,PurchaseOrder,styleId,itemid,itemcolor,fabricsid,qty,dozenqty,consumption,inPercent,PercentQty,TotalQty,unitId,width,Yard,GSM,fabricscolor,brand,entryby from tbRequiredFabrics rf where id = '"+indentId+"'";
				
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				
				indent = new FabricsIndent(element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString(), element[4].toString(), element[5].toString(), Double.valueOf(element[6].toString()),  Double.valueOf(element[7].toString()),  Double.valueOf(element[8].toString()),  Double.valueOf(element[9].toString()),  Double.valueOf(element[10].toString()),  Double.valueOf(element[11].toString()),  element[12].toString(),  Double.valueOf(element[13].toString()),  Double.valueOf(element[14].toString()),  Double.valueOf(element[15].toString()), 0.0, element[16].toString(),  element[17].toString(),  element[18].toString());
						
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
		return indent;
	}

	@Override
	public List<FabricsIndent> getFabricsIndentList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		List<FabricsIndent> datalist=new ArrayList<FabricsIndent>();	
		try{	
			tx=session.getTransaction();
			tx.begin();	
			String sql="select rf.id,rf.PurchaseOrder,rf.styleId,sc.StyleNo,rf.itemId,id.itemname,rf.itemcolor,c.Colorname,rf.fabricsid,fi.ItemName,rf.qty,rf.dozenqty,rf.consumption,rf.inPercent,rf.PercentQty,TotalQty,rf.unitId,u.unitname  \r\n" + 
					"from tbrequiredfabrics rf\r\n" + 
					"left join TbStyleCreate sc\r\n" + 
					"on rf.StyleId = sc.StyleId\r\n" + 
					"left join tbItemDescription id\r\n" + 
					"on rf.itemid = id.itemid\r\n" + 
					"left join tbColors c\r\n" + 
					"on rf.itemcolor = c.ColorId\r\n" + 
					"left join TbFabricsItem fi\r\n" + 
					"on rf.fabricsid = fi.id\r\n" + 
					"left join tbunits u\r\n" + 
					"on rf.unitId = u.Unitid\r\n" + 
					"order by rf.id desc";
				
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				datalist.add(new FabricsIndent(element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString(), element[4].toString(), element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(), element[9].toString(), Double.valueOf(element[10].toString()),  Double.valueOf(element[11].toString()),  Double.valueOf(element[12].toString()),  Double.valueOf(element[13].toString()),  Double.valueOf(element[14].toString()),  Double.valueOf(element[15].toString()), element[16].toString(), element[17].toString()));		
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
		return datalist;
	}

	@Override
	public double getOrderQuantity(String purchaseOrder, String styleId, String itemId, String colorId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		double qty=0;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="SELECT sum(ISNULL(TotalUnit,0)) as TotalUnit FROM TbBuyerOrderEstimateDetails where PurchaseOrder='"+purchaseOrder+"' and styleid='"+styleId+"' and itemid='"+itemId+"' and colorid='"+colorId+"' group by ColorId";

			List<?> list = session.createSQLQuery(sql).list();
			if(list.iterator().hasNext())
			{	
				qty = Double.valueOf(list.iterator().next().toString());
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
		return qty;
	}

	@Override
	public List<AccessoriesItem> getTypeWiseIndentItems(String purchaseOrder, String styleId, String type) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccessoriesItem> dataList=new ArrayList<AccessoriesItem>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql = "";
			if(type.equals("1")) {
				sql = "select a.itemid,a.itemname \r\n" + 
						"from tbAccessoriesIndent ai \r\n" + 
						"left join TbAccessoriesItem a \r\n" + 
						"on ai.accessoriesItemId = a.itemid \r\n" + 
						"where styleid='"+styleId+"'  group by a.itemid,a.itemname";
			}else if(type.equals("2")) {
				sql = "select fi.id,fi.ItemName \r\n" + 
						"from tbrequiredfabrics rf \r\n" + 
						"left join TbFabricsItem fi \r\n" + 
						"on rf.itemid = fi.id \r\n" + 
						"where styleid='"+styleId+"'  group by fi.id,fi.ItemName";
			}else {
				return null;
			}
			
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				dataList.add(new AccessoriesItem(element[0].toString(), element[1].toString(), "", ""));
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

}
