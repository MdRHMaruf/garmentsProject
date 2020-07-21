package pg.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import pg.config.SpringRootConfig;
import pg.model.login;
import pg.orderModel.Style;
import pg.registerModel.BuyerModel;
import pg.registerModel.ItemDescription;
import pg.registerModel.SizeGroup;
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
	public boolean SaveStyleCreate(String user, String buyerId, String itemId, String styleNo,String size, String date,
			String frontimg, String backimg) throws SQLException{
		SpringRootConfig sp=new SpringRootConfig();
	
	   
	   try {
		   boolean frontimgexists=false;
		   boolean backimgexists=false;
		   File frontimgfile =new File(frontimg);
		   File backimgfile =new File(frontimg);
		   
		   if(frontimgfile.exists()) {
			   frontimgexists=true;
		   }
		   
		   if(backimgfile.exists()) {
			   backimgexists=true;
		   }
		   
	
		   String StyleId="";
			
		
			
			if(!CheckStyleAlreadyExist(buyerId,styleNo)) {
				StyleId=getMaxStyleId();
				String sql="insert into TbStyleCreate (StyleId,BuyerId,StyleNo,Finished,date,EntryTime,UserId) values('"+StyleId+"','"+buyerId+"','"+styleNo+"','0',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+user+"');";
				System.out.println(sql);
				sp.getDataSource().getConnection().createStatement().executeUpdate(sql);
				
				String sqlStyleItem="insert into tbStyleWiseItem (StyleId,BuyerId,ItemId,size,EntryTime,UserId) values('"+StyleId+"','"+buyerId+"','"+itemId+"','"+size+"',CURRENT_TIMESTAMP,'"+user+"');";
				System.out.println(sqlStyleItem);
				sp.getDataSource().getConnection().createStatement().executeUpdate(sqlStyleItem);
			}
			else {
				StyleId=getStyleId(buyerId,styleNo);
				String sqlStyleItem="insert into tbStyleWiseItem (StyleId,BuyerId,ItemId,size,EntryTime,UserId) values('"+StyleId+"','"+buyerId+"','"+itemId+"','"+size+"',CURRENT_TIMESTAMP,'"+user+"');";
				System.out.println(sqlStyleItem);
				sp.getDataSource().getConnection().createStatement().executeUpdate(sqlStyleItem);
			}

			
			if(frontimgexists) {
				
			       BufferedImage bufferedImage=ImageIO.read(frontimgfile);
			       ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
			       ImageIO.write(bufferedImage, "png", byteOutStream);
			       
			       
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					//use another encoding if JPG is innappropriate for you
					ImageIO.write(bufferedImage, "jpg", baos );
					baos.flush();
					byte[] immAsBytes = baos.toByteArray();
					baos.close();
					
				java.sql.PreparedStatement pstmt=sp.getDataSource().getConnection().prepareStatement("update tbStyleWiseItem set frontpic = ? where StyleId= '"+StyleId+"' and BuyerId='"+buyerId+"'");
				ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
				//pstmt.setString(1, txtSl.getText().trim());
				pstmt.setBinaryStream(1, bais, immAsBytes.length);
				pstmt.executeUpdate();
				pstmt.close();
			}

			if(backimgexists) {
				
			       BufferedImage bufferedImage=ImageIO.read(backimgfile);
			       ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
			       ImageIO.write(bufferedImage, "png", byteOutStream);
			       
			       
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					//use another encoding if JPG is innappropriate for you
					ImageIO.write(bufferedImage, "jpg", baos );
					baos.flush();
					byte[] immAsBytes = baos.toByteArray();
					baos.close();
					
				java.sql.PreparedStatement pstmt=sp.getDataSource().getConnection().prepareStatement("update tbStyleWiseItem set backpic = ? where StyleId= '"+StyleId+"' and BuyerId='"+buyerId+"'");
				ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
				//pstmt.setString(1, txtSl.getText().trim());
				pstmt.setBinaryStream(1, bais, immAsBytes.length);
				pstmt.executeUpdate();
				pstmt.close();
			}
			
		 	sp.getDataSource().close();
			
			return true;
			
			
			} catch (Exception e) {
				System.out.println("Error,"+e.getMessage());
			}
		
	  
		return false;
	}
	
	
	private String getStyleId(String buyerId, String styleNo) {
		
		String Id="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		
		
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select StyleId from TbStyleCreate where StyleNo='"+styleNo+"' and BuyerId='"+buyerId+"' and Finished='0'";
			System.out.println("sql "+sql);
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Id=iter.next().toString();
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
		
		return Id;
	}
	
	private boolean CheckStyleAlreadyExist(String buyerId, String styleNo) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		String query="";
		
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select StyleId from TbStyleCreate where StyleNo='"+styleNo+"' and BuyerId='"+buyerId+"' and Finished='0'";
			
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				return true;
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
		
		return false;
	}

	public String getMaxStyleId() {
		// TODO Auto-generated method stub

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		String query="";
		
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select isnull(max(StyleId),0)+1 from TbStyleCreate";
			
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				query=iter.next().toString();
			}

			tx.commit();
			return query;
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

		return query;

	}

	@Override
	public List<Style> getStyleWiseItemList() {
		
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		List<Style> datalist=new ArrayList<Style>();
		
		try{	
			tx=session.getTransaction();
			tx.begin();
			
			
			
			String sql="select a.Id,(select StyleNo from TbStyleCreate where StyleId=a.StyleId) as StyleNo,(select itemname from tbItemDescription where itemid=a.ItemId) as ItemName,a.ItemId from tbStyleWiseItem a order by StyleId,BuyerId";
			
			List<?> list = session.createSQLQuery(sql).list();


			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new Style(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),"",""));
				
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

				datalist.add(new Style(element[0].toString(),element[1].toString(),"","","",""));
				
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
	public List<Style> getStyleWiseItem(String value) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		
		List<Style> datalist=new ArrayList<Style>();
		
		try{	
			tx=session.getTransaction();
			tx.begin();
			
			
			String sql="select a.StyleId,a.ItemId,(select itemname from tbItemDescription where itemid=a.ItemId) as ItemName from tbStyleWiseItem a where a.StyleId='"+value+"'";
			
			List<?> list = session.createSQLQuery(sql).list();


			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new Style(element[0].toString(),element[1].toString(),element[2].toString(),"","",""));
				
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


}
