package pg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private RegisterService registerService;

	//Order Create 
	@RequestMapping(value = "/style_create",method=RequestMethod.GET)
	public ModelAndView style_create(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/style_create");
		//List<Brand> brandList = registerService.getBrandList();
		//System.out.println("list size="+brandList.size());

		//map.addAttribute("brandList",);

		return view; //JSP - /WEB-INF/view/index.jsp
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
		}

		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		return objmain;
	}

	@RequestMapping(value = "/buyerLoad",method=RequestMethod.GET)
	public @ResponseBody JSONObject buyerLoad() {
		JSONObject objmain = new JSONObject();


		JSONArray mainarray = new JSONArray();

		/*		List<Buyer> List= orderService.getBuyerList();

		for(int a=0;a<List.size();a++) {
			JSONObject obj = new JSONObject();
			obj.put("id", List.get(a).getBuyerId());
			obj.put("value", List.get(a).getBuyerName());
			mainarray.add(obj);
		}*/

		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		return objmain;
	}


	//Buyer Purchase Create
	@RequestMapping(value = "/buyer_purchase_order",method=RequestMethod.GET)
	public ModelAndView buyer_purchase_order(ModelMap map,HttpSession session) {

		ModelAndView view = new ModelAndView("order/buyer-purchase-order");
		List<SizeGroup> groupList = registerService.getStyleSizeGroupList();
		List<BuyerModel> buyerList= registerService.getAllBuyers();
		List<FactoryModel> factoryList = registerService.getAllFactories();
		List<Color> colorList = registerService.getColorList();
		view.addObject("groupList",groupList);
		view.addObject("buyerList",buyerList);
		view.addObject("factoryList",factoryList);
		view.addObject("colorList",colorList);
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
	

	
}
