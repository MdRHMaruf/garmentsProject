package pg.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import pg.model.commonModel;
import pg.orderModel.BuyerPO;
import pg.orderModel.BuyerPoItem;
import pg.orderModel.Costing;
import pg.orderModel.FabricsIndent;
import pg.orderModel.PurchaseOrderItem;
import pg.orderModel.Style;
import pg.orderModel.accessorieIndent;
import pg.registerModel.AccessoriesItem;
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
import pg.registerModel.SupplierModel;
import pg.registerModel.Unit;
import pg.services.OrderService;
import pg.services.RegisterService;
import pg.share.PaymentType;

@Controller
@RestController
public class OrderController {
	
	DecimalFormat df = new DecimalFormat("#.00");

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
		JSONObject objmain = new JSONObject();


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

			List<commonModel>purchaseorders=orderService.PurchaseOrders();

			List<accessorieIndent>listAccPending=orderService.getPendingAccessoriesIndent();
			
			List<commonModel>accessoriesitem=orderService.AccessoriesItem();

			List<commonModel>unit=orderService.Unit();
			List<commonModel>brand=orderService.Brands();
			List<commonModel>color=orderService.AllColors();
			ModelAndView view = new ModelAndView("order/accessories_indent");
			view.addObject("purchaseorders",purchaseorders);
			view.addObject("accessories",accessoriesitem);
			view.addObject("unit",unit);
			view.addObject("brand",brand);
			view.addObject("color",color);
			
			view.addObject("listAccPending",listAccPending);

			return view; //JSP - /WEB-INF/view/index.jsp
		}

		@ResponseBody
		@RequestMapping(value = "/maxAIno",method=RequestMethod.POST)
		public String maxAIno( ) {
			System.out.println(" maxAIno Id ");
			String maxaino="";

			maxaino=orderService.maxAIno();

			return maxaino;

		}

		@ResponseBody
		@RequestMapping(value = "/poWiseStyles/{po}",method=RequestMethod.POST)
		public JSONObject poWiseStyles(@PathVariable ("po") String po) {
			System.out.println(" powisestyles ");


			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();

			List<commonModel>styles=orderService.Styles(po);

			for (int i = 0; i < styles.size(); i++) {
				JSONObject obj=new JSONObject();

				obj.put("id", styles.get(i).getId());
				obj.put("name", styles.get(i).getName());

				mainarray.add(obj);

			}

			objmain.put("result", mainarray);
			System.out.println(" obj main "+objmain);

			return objmain;

		}

		@ResponseBody
		@RequestMapping(value = "/stylewiseitems",method=RequestMethod.GET)
		public JSONObject stylewiseitems(String buyerorderid,String style) {

			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();

			List<commonModel>items=orderService.Items(buyerorderid,style);

			for (int i = 0; i < items.size(); i++) {
				JSONObject obj=new JSONObject();

				obj.put("id", items.get(i).getId());
				obj.put("name", items.get(i).getName());

				mainarray.add(obj);

			}

			objmain.put("result", mainarray);
			System.out.println(" obj main "+objmain);

			return objmain;

		}


		@ResponseBody
		@RequestMapping(value = "/styleItemsWiseColor",method=RequestMethod.GET)
		public JSONObject styleItemsWiseColor(String buyerorderid,String style,String item) {
			System.out.println(" stylewisei items ");


			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();

			System.out.println("item "+item);

			List<commonModel>items=orderService.styleItemsWiseColor(buyerorderid,style,item);

			for (int i = 0; i < items.size(); i++) {
				JSONObject obj=new JSONObject();

				obj.put("id", items.get(i).getId());
				obj.put("name", items.get(i).getName());

				mainarray.add(obj);

			}

			objmain.put("result", mainarray);
			System.out.println(" obj main "+objmain);

			return objmain;

		}

		@ResponseBody
		@RequestMapping(value = "/shippingMark/{po}/{style}/{item}",method=RequestMethod.POST)
		public List purchaseOrders(@PathVariable ("po") String po,@PathVariable ("style") String style,@PathVariable ("item") String item) {
			System.out.println(" shippingmarks ");

			List<commonModel>shippingMarks=orderService.ShippingMark(po, style, item);



			return shippingMarks;

		}
		
		@ResponseBody
		@RequestMapping(value = "/styleitemColorWiseSize",method=RequestMethod.GET)
		public JSONObject itemWiseSize(String buyerorderid,String style,String item,String color) {

			System.out.println("size");
			
			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();

			List<commonModel>sizes=orderService.Size(buyerorderid, style,item,color);

			for (int i = 0; i < sizes.size(); i++) {
				JSONObject obj=new JSONObject();

				obj.put("id", sizes.get(i).getId());
				obj.put("name", sizes.get(i).getName());

				mainarray.add(obj);

			}

			objmain.put("size", mainarray);
			return objmain;

		}
		
		@ResponseBody
		@RequestMapping(value = "/styleitemColorWiseOrderQty",method=RequestMethod.GET)
		public JSONObject styleitemColorWiseOrderQty(String buyerorderid,String style,String item,String color,String size) {

			
			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();

			List<commonModel>sizes=orderService.SizewiseQty(buyerorderid, style,item,color,size);

			for (int i = 0; i < sizes.size(); i++) {
				JSONObject obj=new JSONObject();

				obj.put("qty", sizes.get(i).getQty());
				mainarray.add(obj);

			}

			objmain.put("size", mainarray);
			return objmain;

		}


		@ResponseBody
		@RequestMapping(value = "/itemWiseColor/{style}/{item}",method=RequestMethod.POST)
		public JSONObject itemWiseColor(@PathVariable ("style") String style,@PathVariable ("item") String item) {
			System.out.println(" Purchase Orders ");
			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();




			List<commonModel>colors=orderService.Colors(style, item);

			for (int i = 0; i < colors.size(); i++) {
				JSONObject obj=new JSONObject();

				obj.put("id", colors.get(i).getId());
				obj.put("name", colors.get(i).getName());

				mainarray.add(obj);

			}

			objmain.put("color", mainarray);
			System.out.println(" obj main "+objmain);

			return objmain;

		}


		@ResponseBody
		@RequestMapping(value = "/SizeWiseQty/{style}/{item}/{size}/{color}/{type}",method=RequestMethod.POST)
		public JSONObject SizeWiseQty(@PathVariable ("style") String style,@PathVariable ("item") String item,@PathVariable ("size") String size,@PathVariable ("color") String color,@PathVariable ("type") String type) {
			System.out.println(" Purchase Orders ");
			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();




			List<commonModel>qty=orderService.SizewiseQty(style,item,size,color,type);

			for (int i = 0; i < qty.size(); i++) {
				JSONObject obj=new JSONObject();


				obj.put("name", qty.get(i).getQty());

				mainarray.add(obj);

			}

			objmain.put("size", mainarray);
			System.out.println(" obj main "+objmain);

			return objmain;

		}


		@ResponseBody
		@RequestMapping(value = "/insertAccessoriesIndent",method=RequestMethod.POST)
		public JSONObject insertAccessoriesIndent(accessorieIndent v) {
			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();
			boolean insert= orderService.insertaccessoriesIndent(v);
			
			if(insert) {
				List<accessorieIndent>qty=orderService.getAccessoriesIndent(v.getPo(),v.getStyle(),v.getItemname(),v.getItemcolor());
				
				for (int i = 0; i < qty.size(); i++) {
					JSONObject obj=new JSONObject();

					obj.put("autoId", qty.get(i).getAutoid());
					obj.put("po", qty.get(i).getPo());
					obj.put("style", qty.get(i).getStyle());
					obj.put("itemname", qty.get(i).getItemname());
					obj.put("itemcolor", qty.get(i).getItemcolor());
					obj.put("shippingmark", qty.get(i).getShippingmark());
					obj.put("accessoriesName", qty.get(i).getAccessoriesName());
					obj.put("sizeName", qty.get(i).getSizeName());
					obj.put("requiredUnitQty", qty.get(i).getRequiredUnitQty());
					mainarray.add(obj);

				}
				
				objmain.put("result", mainarray);
				System.out.println(" obj main "+objmain);

				return objmain;
			}
			else {
				return null;
			}

		}
		
		@ResponseBody
		@RequestMapping(value = "/confrimAccessoriesIndent",method=RequestMethod.POST)
		public String confrimAccessoriesIndent(String user,String aiNo) {
			String msg="Create Occured while cofrim accessories indent";
			
			boolean update= orderService.confrimAccessoriesIndent(user,aiNo);
			if(update){
				msg="Update Accessories successfully Confrimed";
			}
			
			return msg;
		}
		
		@ResponseBody
		@RequestMapping(value = "/editAccessoriesIndent",method=RequestMethod.POST)
		public String editAccessoriesIndent(accessorieIndent v) {
			//JSONObject objmain = new JSONObject();
			//JSONArray mainarray = new JSONArray();
			String msg="Create Occured while updating accessories indent";
			boolean update= orderService.editaccessoriesIndent(v);
			if(update) {
				msg="Update Accessories successfully";
			}
	/*		if(insert) {
				List<accessorieIndent>qty=orderService.getAccessoriesIndent(v.getPo(),v.getStyle(),v.getItemname(),v.getItemcolor());
				
				for (int i = 0; i < qty.size(); i++) {
					JSONObject obj=new JSONObject();

					obj.put("autoId", qty.get(i).getAutoid());
					obj.put("po", qty.get(i).getPo());
					obj.put("style", qty.get(i).getStyle());
					obj.put("itemname", qty.get(i).getItemname());
					obj.put("itemcolor", qty.get(i).getItemcolor());
					obj.put("shippingmark", qty.get(i).getShippingmark());
					obj.put("accessoriesName", qty.get(i).getAccessoriesName());
					obj.put("sizeName", qty.get(i).getSizeName());
					obj.put("requiredUnitQty", qty.get(i).getRequiredUnitQty());
					mainarray.add(obj);

				}
				
				objmain.put("result", mainarray);
				System.out.println(" obj main "+objmain);

				return objmain;
			}
			else {
				return null;
			}*/

			return msg;
		}
		
		
		@ResponseBody
		@RequestMapping(value = "/accessoriesItemSet/{id}",method=RequestMethod.GET)
		public JSONObject accessoriesItemSet(@PathVariable ("id") String id) {
			JSONObject objmain = new JSONObject();
			JSONArray mainarray = new JSONArray();
			
			List<accessorieIndent>list=orderService.getAccessoriesIndentItemDetails(id);
			
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj=new JSONObject();

				obj.put("autoid", list.get(i).getAutoid());
				obj.put("po", list.get(i).getPo());
				obj.put("style", list.get(i).getStyle());
				obj.put("itemname", list.get(i).getItemname());
				obj.put("itemcolor", list.get(i).getItemcolor());
				obj.put("shippingmark", list.get(i).getShippingmark());
				obj.put("accessoriesname", list.get(i).getAccessoriesName());
				obj.put("sizeName", list.get(i).getSizeName());
				
				System.out.println("itemcolor "+list.get(i).getItemcolor());
				obj.put("accessoriessize", list.get(i).getAccessoriessize());
				obj.put("perunit",df.format(Double.parseDouble( list.get(i).getPerunit())));
				obj.put("totalbox", df.format(Double.parseDouble(list.get(i).getTotalbox())));
				obj.put("orderqty", df.format(Double.parseDouble(list.get(i).getOrderqty())));
				obj.put("qtyindozen", df.format(Double.parseDouble(list.get(i).getQtyindozen())));
				obj.put("reqperpcs", df.format(Double.parseDouble(list.get(i).getReqperpcs())));
				obj.put("reqperdozen", df.format(Double.parseDouble(list.get(i).getReqperdozen())));
				obj.put("dividedby", df.format(Double.parseDouble(list.get(i).getDividedby())));
				obj.put("extrainpercent", df.format(Double.parseDouble(list.get(i).getExtrainpercent())));
				obj.put("percentqty", df.format(Double.parseDouble(list.get(i).getPercentqty())));
				obj.put("totalqty", df.format(Double.parseDouble(list.get(i).getTotalqty())));
				obj.put("unit", list.get(i).getUnit());
				obj.put("requiredUnitQty", df.format(Double.parseDouble(list.get(i).getRequiredUnitQty())));
				obj.put("indentBrandId", list.get(i).getIndentBrandId());
				obj.put("indentColorId", list.get(i).getIndentColorId());
				
				obj.put("indentColorId", list.get(i).getIndentColorId());
				
				mainarray.add(obj);

			}
			
			objmain.put("result", mainarray);
			System.out.println(" obj main "+objmain);

			return objmain;

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
		List<SupplierModel> supplierList = registerService.getAllSupplier();
		view.addObject("poList",poList);
		view.addObject("factoryList",factoryList);
		view.addObject("merchendiserList",merchandiserList);
		view.addObject("supplierList",supplierList);
		return view; //JSP - /WEB-INF/view/index.jsp
	}

	@RequestMapping(value = "/getTypeWiseIndentItems",method=RequestMethod.GET)
	public @ResponseBody JSONObject getTypeWiseIndentItems(String purchaseOrder,String styleId,String type) {
		JSONObject objmain = new JSONObject();
		List<AccessoriesItem>  itemList = orderService.getTypeWiseIndentItems(purchaseOrder,styleId,type);
		objmain.put("itemList", itemList);
		return objmain;
	}
	
	@RequestMapping(value = "/addIndentItem",method=RequestMethod.GET)
	public @ResponseBody JSONObject addIndentItem(PurchaseOrderItem purchaseOrderItem) {
		JSONObject objmain = new JSONObject();
		List<PurchaseOrderItem> poItemList = orderService.getPurchaseOrderItemList(purchaseOrderItem);
		objmain.put("poItemList", poItemList);
		return objmain;
	}

}
