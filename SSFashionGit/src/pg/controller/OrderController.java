package pg.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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
import pg.model.login;
import pg.orderModel.Style;
import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.orderModel.Costing;
import pg.orderModel.FabricsIndent;
import pg.orderModel.Style;
import pg.registerModel.Brand;
import pg.registerModel.BuyerModel;
import pg.registerModel.Color;
import pg.registerModel.Department;
import pg.registerModel.FabricsItem;
import pg.registerModel.Factory;
import pg.registerModel.FactoryModel;
import pg.registerModel.ItemDescription;
import pg.registerModel.Line;
import pg.registerModel.MerchandiserInfo;
import pg.registerModel.ParticularItem;
import pg.registerModel.Size;
import pg.registerModel.SizeGroup;
import pg.registerModel.Unit;
import pg.services.OrderService;
import pg.services.RegisterService;
import pg.share.PaymentType;

@Controller
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


	//Costing Create Work of nasir bai...
	@RequestMapping(value = "/costing_create",method=RequestMethod.GET)
	public ModelAndView costing_create(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/costing_create");
		List<Unit> unitList= registerService.getUnitList();	
		List<Style> styleList= orderService.getStyleList();
		List<ParticularItem> particularList = orderService.getTypeWiseParticularList("1");
		List<Costing> costingList = orderService.getCostingList();
		map.addAttribute("styleList",styleList);
		map.addAttribute("unitList",unitList);
		map.addAttribute("particularList",particularList);
		map.addAttribute("costingList",costingList);

		return view; //JSP - /WEB-INF/view/index.jsp
	}

	@RequestMapping(value = "/saveCosting",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveCosting(Costing costing) {
		JSONObject objmain = new JSONObject();
		if(orderService.saveCosting(costing)) {

			List<Costing> costingList = orderService.getCostingList(costing.getStyleId(),costing.getItemId());

			objmain.put("result",costingList);
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}

	@RequestMapping(value = "/editCosting",method=RequestMethod.POST)
	public @ResponseBody JSONObject editCosting(Costing costing) {
		JSONObject objmain = new JSONObject();
		if(orderService.editCosting(costing)) {

			List<Costing> costingList = orderService.getCostingList(costing.getStyleId(),costing.getItemId());

			objmain.put("result",costingList);
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}

	@RequestMapping(value = "/deleteCosting",method=RequestMethod.GET)
	public @ResponseBody JSONObject deleteCosting(String autoId,String styleId,String itemId) {
		JSONObject objmain = new JSONObject();
		if(orderService.deleteCosting(autoId)) {

			List<Costing> costingList = orderService.getCostingList(styleId,itemId);

			objmain.put("result",costingList);
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}

	@RequestMapping(value = "/cloningCosting",method=RequestMethod.GET)
	public @ResponseBody JSONObject cloningCosting(String oldStyleId,String oldItemId,String newStyleId,String newItemId,String userId) {
		JSONObject objmain = new JSONObject();

		if(orderService.cloningCosting(oldStyleId,oldItemId,newStyleId,newItemId,userId)) {

			List<Costing> costingList = orderService.getCostingList(newStyleId,newItemId);
			objmain.put("result",costingList);
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}

	@RequestMapping(value = "/searchCosting",method=RequestMethod.GET)
	public @ResponseBody JSONObject searchCosting(String styleId,String itemId) {
		JSONObject objmain = new JSONObject();

		JSONArray mainArray = new JSONArray();
		List<Costing> costingList = orderService.getCostingList(styleId,itemId);
		objmain.put("result",costingList);
		return objmain;
	}

	@RequestMapping(value = "/searchCostingItem",method=RequestMethod.GET)
	public @ResponseBody JSONObject searchCostingItem(String autoId) {
		JSONObject objmain = new JSONObject();

		JSONArray mainArray = new JSONArray();
		Costing costing = orderService.getCostingItem(autoId);
		objmain.put("result",costing);
		return objmain;
	}

	@RequestMapping(value = "/typeWiseParticularLoad",method=RequestMethod.GET)
	public @ResponseBody JSONObject typeWiseParticularLoad(String type) {
		JSONObject objmain = new JSONObject();
		List<ParticularItem> particularList= orderService.getTypeWiseParticularList(type);

		objmain.put("particularList", particularList);

		return objmain;
	}

	@RequestMapping(value = "/itemDescriptionLoad",method=RequestMethod.GET)
	public @ResponseBody JSONObject itemDescriptionLoad() {
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();

		List<ItemDescription> List= orderService.getItemDescriptionList();

		for(int a=0;a<List.size();a++) {
			JSONObject obj = new JSONObject();
			obj.put("id", List.get(a).getItemId());
			obj.put("value", List.get(a).getItemName());
			mainarray.add(obj);

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

	//Buyer Purchase Order Create
	@RequestMapping(value = "/buyer_purchase_order",method=RequestMethod.GET)
	public ModelAndView buyer_purchase_order(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/buyer-purchase-order");
		List<SizeGroup> groupList = registerService.getStyleSizeGroupList();
		List<BuyerModel> buyerList= registerService.getAllBuyers();
		List<FactoryModel> factoryList = registerService.getAllFactories();
		List<Color> colorList = registerService.getColorList();
		List<BuyerPO> buyerPoList = orderService.getBuyerPoList();
		view.addObject("groupList",groupList);
		view.addObject("buyerList",buyerList);
		view.addObject("factoryList",factoryList);
		view.addObject("colorList",colorList);
		view.addObject("buyerPoList",buyerPoList);
		return view; //JSP - /WEB-INF/view/index.jsp
	}
		
  @ResponseBody
	@RequestMapping(value = "/sizesLoadByGroup",method=RequestMethod.GET)
	public JSONObject sizesLoadByGroup() {

		JSONObject objmain = new JSONObject();	
		JSONObject size = new JSONObject() ;
		JSONArray sizeArray = new JSONArray();
		JSONObject groupObj = new JSONObject();

		List<Size> sizeList = registerService.getStyleSizeList();
		int lastSize= sizeList.size();

		for(int i=0;i<lastSize;i++) {

			size.put("sizeId", sizeList.get(i).getSizeId());
			size.put("sizeName", sizeList.get(i).getSizeName());
			sizeArray.add(size);
			if((i+1 == lastSize) || !sizeList.get(i).getGroupId().equals( sizeList.get(i+1).getGroupId())) {

				groupObj.put("groupId"+sizeList.get(i).getGroupId(), sizeArray);
				sizeArray = new JSONArray();
			}
			size = new JSONObject();

		}
		objmain.put("sizeList", groupObj);
		return objmain;

	}

	@ResponseBody
	@RequestMapping(value = "/getBuyerWiseStylesItem",method=RequestMethod.GET)
	public JSONObject getBuyerWiseStylesItem(String buyerId) {
		JSONObject objMain = new JSONObject();	
		List<Style> styleList = orderService.getBuyerWiseStylesItem(buyerId);

		objMain.put("styleList",styleList);
		return objMain; 
	}

	@ResponseBody
	@RequestMapping(value = "/getStyleWiseItem",method=RequestMethod.GET)
	public JSONObject getStyleWiseItem(String styleId) {
		JSONObject objMain = new JSONObject();	
		List<ItemDescription> itemList = orderService.getStyleWiseItem(styleId);

		objMain.put("itemList",itemList);
		return objMain; 
	}



	@RequestMapping(value = "/addItemToBuyerPO",method=RequestMethod.POST)
	public @ResponseBody JSONObject addItemToBuyerPO(BuyerPoItem buyerPoItem) {
		JSONObject objmain = new JSONObject();

		if(orderService.addBuyerPoItem(buyerPoItem)) {
			JSONArray mainArray = new JSONArray();
			List<BuyerPoItem> buyerPOItemList = orderService.getBuyerPOItemList(buyerPoItem.getBuyerPOId());
			objmain.put("result",buyerPOItemList);
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}

	@RequestMapping(value = "/editBuyerPoItem",method=RequestMethod.POST)
	public @ResponseBody JSONObject editBuyerPoItem(BuyerPoItem buyerPoItem) {
		JSONObject objmain = new JSONObject();

		if(orderService.editBuyerPoItem(buyerPoItem)) {
			JSONArray mainArray = new JSONArray();
			List<BuyerPoItem> buyerPOItemList = orderService.getBuyerPOItemList(buyerPoItem.getBuyerPOId());
			objmain.put("result",buyerPOItemList);
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}
  
   @RequestMapping(value = "/getBuyerPOItemsList",method=RequestMethod.GET)
	public @ResponseBody JSONObject getBuyerPOItemsList(String buyerPoNo) {
		JSONArray mainArray = new JSONArray();
		List<BuyerPoItem> buyerPOItemList = orderService.getBuyerPOItemList(buyerPoNo);
		objmain.put("result",buyerPOItemList);

		return objmain;
	}

	@RequestMapping(value = "/getBuyerPOItem",method=RequestMethod.GET)
	public @ResponseBody JSONObject getBuyerPOItem(String itemAutoId) {
		JSONObject objmain = new JSONObject();


		JSONArray mainArray = new JSONArray();
		BuyerPoItem buyerPoItem = orderService.getBuyerPOItem(itemAutoId);
		objmain.put("poItem",buyerPoItem);

		return objmain;
	}

	@RequestMapping(value = "/deleteBuyerPoItem",method=RequestMethod.GET)
	public @ResponseBody JSONObject deleteBuyerPoItem(String buyerPoNo,String itemAutoId) {
		JSONObject objmain = new JSONObject();

		if(orderService.deleteBuyerPoItem(itemAutoId)) {
			JSONArray mainArray = new JSONArray();
			List<BuyerPoItem> buyerPOItemList = orderService.getBuyerPOItemList(buyerPoNo);
			objmain.put("result",buyerPOItemList);
		}else {
			objmain.put("result","Something Wrong");
    }
		return objmain;
	}

      @RequestMapping(value = "/getBuyerPO",method=RequestMethod.GET)
	public @ResponseBody JSONObject getBuyerPO(String buyerPoNo) {
		JSONObject objmain = new JSONObject();


		JSONArray mainArray = new JSONArray();
		BuyerPO buyerPo = orderService.getBuyerPO(buyerPoNo);

		objmain.put("buyerPO",buyerPo);

		return objmain;
	}

	@RequestMapping(value = "/submitBuyerPO",method=RequestMethod.POST)
	public @ResponseBody JSONObject submitBuyerPO(BuyerPO buyerPO) {
		JSONObject objmain = new JSONObject();

		if(orderService.submitBuyerPO(buyerPO)) {
			objmain.put("result", "Successfull");
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}
  
  @RequestMapping(value = "/editBuyerPO",method=RequestMethod.POST)
	public @ResponseBody JSONObject editBuyerPO(BuyerPO buyerPO) {
		JSONObject objmain = new JSONObject();

		if(orderService.editBuyerPO(buyerPO)) {
			objmain.put("result", "Successfull");
		}else {
			objmain.put("result", "Something Wrong");
		}

		return objmain;
	}

	//Accessoires Indent Create
	@RequestMapping(value = "/accessories_indent",method=RequestMethod.GET)
	public ModelAndView accessories_indent(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/accessories_indent");

		return view; //JSP - /WEB-INF/view/index.jsp
	}

	//Fabrics Indent 
	@RequestMapping(value = "/fabrics_indent",method=RequestMethod.GET)
	public ModelAndView fabrics_indent(ModelMap map,HttpSession session) {

		List<String> poList = orderService.getPurchaseOrderList();
		List<Color> colorList = registerService.getColorList();
		List<FabricsItem> fabricsItemList = registerService.getFabricsItemList();
		List<Brand> brandList = registerService.getBrandList();
		List<Unit> unitList = registerService.getUnitList();
		List<FabricsIndent> fabricsIndentList = orderService.getFabricsIndentList();
		ModelAndView view = new ModelAndView("order/fabrics-indent");
		view.addObject("poList", poList);
		view.addObject("fabricsList",fabricsItemList);
		view.addObject("colorList",colorList);
		view.addObject("brandList",brandList);
		view.addObject("unitList",unitList);
		view.addObject("fabricsIndentList",fabricsIndentList);
		return view; //JSP - /WEB-INF/view/index.jsp
	}

	@RequestMapping(value = "/saveFabricsIndent",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveFabricsIndent(FabricsIndent	fabricsIndent) {
		JSONObject objmain = new JSONObject();
		if(!orderService.isFabricsIndentExist(fabricsIndent)) {
			if(orderService.saveFabricsIndent(fabricsIndent)) {

				List<FabricsIndent> fabricsIndentList = orderService.getFabricsIndentList();

				objmain.put("result",fabricsIndentList);
			}else {
				objmain.put("result", "Something Wrong");
			}
		}else {
			objmain.put("result", "duplicate");
		}
		return objmain;
	}

	@RequestMapping(value = "/editFabricsIndent",method=RequestMethod.POST)
	public @ResponseBody JSONObject editFabricsIndent(FabricsIndent	fabricsIndent) {
		JSONObject objmain = new JSONObject();
		if(!orderService.isFabricsIndentExist(fabricsIndent)) {
			if(orderService.editFabricsIndent(fabricsIndent)) {
				List<FabricsIndent> fabricsIndentList = orderService.getFabricsIndentList();
				objmain.put("result",fabricsIndentList);
			}else {
				objmain.put("result", "Something Wrong");
			}
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}

	@RequestMapping(value = "/getFabricsIndent",method=RequestMethod.GET)
	public @ResponseBody JSONObject getFabricsIndent(String autoId) {
		JSONObject objmain = new JSONObject();
		FabricsIndent fabricsIndent = orderService.getFabricsIndent(autoId);
		objmain.put("fabricsIndent",fabricsIndent);
		return objmain;
	}

	@RequestMapping(value = "/getPOWiseStyleLoad",method=RequestMethod.GET)
	public @ResponseBody JSONObject getPOWiseStyleLoad(String purchaseOrder) {
		JSONObject objmain = new JSONObject();
		List<Style> styleList = orderService.getPOWiseStyleList(purchaseOrder);
		objmain.put("styleList",styleList);
		return objmain;
	}

	@RequestMapping(value = "/getStyleItemWiseColor",method=RequestMethod.GET)
	public @ResponseBody JSONObject getItemWiseColor(String styleId,String itemId) {
		JSONObject objmain = new JSONObject();
		List<Color> colorList = orderService.getStyleItemWiseColor(styleId,itemId);
		objmain.put("colorList",colorList);
		return objmain;
	}

	@RequestMapping(value = "/getOrderQtyByPOStyleItemAndColor",method=RequestMethod.GET)
	public @ResponseBody JSONObject getOrderQtyByPOStyleItemAndColor(String purchaseOrder,String styleId,String itemId,String colorId) {
		JSONObject objmain = new JSONObject();
		double orderQty = orderService.getOrderQuantity(purchaseOrder,styleId,itemId,colorId);
		objmain.put("orderQuantity",orderQty);
		objmain.put("dozenQuantity", orderQty/12);
		return objmain;
	}

	@RequestMapping(value = "/getUnitValue",method=RequestMethod.GET)
	public @ResponseBody JSONObject getUnitValue(String unitId) {
		JSONObject objmain = new JSONObject();
		Unit unit = registerService.getUnit(unitId);
		objmain.put("unitValue",unit.getUnitValue());
		return objmain;
	}
	
	
	//Purchase Order 
	@RequestMapping(value = "/purchase_order",method=RequestMethod.GET)
	public ModelAndView purchase_order(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/purchase-order");
		List<String> poList = orderService.getPurchaseOrderList();
		List<Factory> factoryList = registerService.getFactoryNameList();
		List<MerchandiserInfo> merchandiserList = registerService.getMerchandiserList();
		view.addObject("poList",poList);
		view.addObject("factoryList",factoryList);
		view.addObject("merchendiserList",merchandiserList);
		return view; //JSP - /WEB-INF/view/index.jsp
	}
}
