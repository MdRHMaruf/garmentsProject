package pg.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.model.login;
import pg.orderModel.Style;
import pg.registerModel.Brand;
import pg.registerModel.BuyerModel;
import pg.registerModel.Color;
import pg.registerModel.Department;
import pg.registerModel.Factory;
import pg.registerModel.FactoryModel;
import pg.registerModel.ItemDescription;
import pg.registerModel.Line;
import pg.registerModel.Size;
import pg.registerModel.SizeGroup;
import pg.services.OrderService;
import pg.services.RegisterService;

@Controller
@RestController
@SessionAttributes({"pg_admin"})
public class OrderController {
	
	private static final String UPLOAD_DIRECTORY ="/WEB-INF/upload";  
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private RegisterService registerService;

	//Order Create 
	@RequestMapping(value = "/style_create",method=RequestMethod.GET)
	public ModelAndView style_create(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/style_create");
		List<BuyerModel> List= registerService.getAllBuyers();
		List<ItemDescription> itemList= orderService.getItemDescriptionList();
		
		List<Style> styleList= orderService.getStyleWiseItemList();

		map.addAttribute("buyerList",List);
		map.addAttribute("itemList",itemList);
		map.addAttribute("styleList",styleList);

		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
 	  @ResponseBody
	  @RequestMapping(value = "/submitStyleFiles", method = RequestMethod.POST)
	  public ModelAndView submitFiles(@RequestParam String buyerId,@RequestParam String itemId,@RequestParam String styleNo,@RequestParam String size,@RequestParam String date,@RequestParam CommonsMultipartFile frontImage,@RequestParam CommonsMultipartFile backImage,HttpSession session) throws IOException, SQLException {
		
 		List<login> user=(List<login>)session.getAttribute("pg_admin");
 		
 		
 		 
		String frontimg=getImageName(frontImage,session);
		System.out.println("frontimg "+frontimg);
		
		String backimg=getImageName(backImage,session);
		System.out.println("backimg "+backimg);
		
		String userId=Integer.toString(user.get(0).getId());
		
		boolean flag=orderService.SaveStyleCreate(userId,buyerId,itemId,styleNo,size,date,frontimg,backimg) ;
		
		if(flag) {
			System.out.println("Sucess");
		}
		
		ModelAndView view=new ModelAndView("order/style_create");
				
		return view;
	  }
 	  
 	  
 

	private String getImageName(CommonsMultipartFile frontImage, HttpSession session) throws IOException  {
 			ServletContext context = session.getServletContext();  
 		    String path = context.getRealPath(UPLOAD_DIRECTORY);  
 		    
 		    String filename = frontImage.getOriginalFilename();  
 		    
 		    byte[] bytes = frontImage.getBytes();  
 		    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
 		         new File(path + File.separator + filename)));  
 		    stream.write(bytes);  
 		    stream.flush();  
 		    stream.close();  

 			String frontfile=session.getServletContext().getRealPath("WEB-INF/upload/"+filename);
 			
 			return frontfile;
 	  }



/*	@RequestMapping(value = "/btnSaveAction",method=RequestMethod.POST)
	public @ResponseBody String btnSaveAction(Style v,@RequestPart("file") CommonsMultipartFile frontimage,HttpSession session) throws Exception{

		System.out.println("buyername "+v.getBuyername());
 		System.out.println("itemname "+v.getItemname());
 		System.out.println("styleno "+v.getStyleno());
 		System.out.println("date "+v.getDate());
 		
 		System.out.println("frontiamge "+v.getFrontimage());
 		
		
 		
 		
 		String frontimg=getImageName(v.getFrontImage(),session);
		System.out.println("frontimg "+frontimg);
		
		String backimg=getImageName(v.getBackImage(),session);
		System.out.println("backimg "+backimg);
		
		
		ServletContext context = session.getServletContext();  
	    String path = context.getRealPath(UPLOAD_DIRECTORY);  
	    

	    String filename = file.getOriginalFilename();  
	    
	    byte[] bytes = file.getBytes();  
	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
	         new File(path + File.separator + filename)));  
	    stream.write(bytes);  
	    stream.flush();  
	    stream.close();  

	    
		
		String pfile=session.getServletContext().getRealPath("WEB-INF/upload/"+filename);
		
	    return "You Success";
	}
	*/
/*	@RequestMapping(value="/fronimage",method=RequestMethod.POST)
	public void fronimageupload(@RequestParam CommonsMultipartFile backimage,HttpSession session) throws Exception
	{
		ServletContext context = session.getServletContext();  
	    String path = context.getRealPath(UPLOAD_DIRECTORY);  
	    
	    System.out.println("pathname"+path);  
	   
	    
	    String filename = backimage.getOriginalFilename();  
	    
	    byte[] bytes = backimage.getBytes();  
	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
	         new File(path + File.separator + filename)));  
	    stream.write(bytes);  
	    stream.flush();  
	    stream.close();  

	           
	    
	    System.out.println("filename"+filename);  
	    System.out.println(path+"'"+filename);        
	    
		
		String pfile=session.getServletContext().getRealPath("WEB-INF/upload/"+filename);
	    System.out.println("pfile"+pfile);  
		
	}*/
	
	
	
	//Order Create 
	@RequestMapping(value = "/costing_create",method=RequestMethod.GET)
	public ModelAndView costing_create(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/costing_create");
		
		
		List<BuyerModel> List= registerService.getAllBuyers();
		List<ItemDescription> itemList= orderService.getItemDescriptionList();
		
		List<Style> styleList= orderService.getStyleList();

		map.addAttribute("styleList",styleList);
		map.addAttribute("itemList",itemList);
		//map.addAttribute("styleList",styleList);

		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/getStyleWiseItem/{value}",method=RequestMethod.GET)
	public @ResponseBody JSONObject getStyleWiseItem(@PathVariable ("value") String value) {
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<Style> lablist=orderService.getStyleWiseItem(value);
		

		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();

			//obj.put("id", lablist.get(a).getItemId());
			//obj.put("value", lablist.get(a).getItemName());
			
			mainarray.add(obj);

		}


		objmain.put("result", mainarray);

		System.out.println(objmain.toString());

		return objmain;


		
	}
	
}
