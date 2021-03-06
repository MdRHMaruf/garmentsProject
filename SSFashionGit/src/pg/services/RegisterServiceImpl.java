package pg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pg.dao.RegisterDao;
import pg.registerModel.AccessoriesItem;
import pg.registerModel.Brand;
import pg.registerModel.BuyerModel;
import pg.registerModel.Color;
import pg.registerModel.Country;
import pg.registerModel.CourierModel;
import pg.registerModel.Department;
import pg.registerModel.FabricsItem;
import pg.registerModel.Factory;
import pg.registerModel.FactoryModel;
import pg.registerModel.InchargeInfo;
import pg.registerModel.Line;
import pg.registerModel.LocalItem;
import pg.registerModel.MerchandiserInfo;
import pg.registerModel.ParticularItem;
import pg.registerModel.SampleType;
import pg.registerModel.Size;
import pg.registerModel.SizeGroup;
import pg.registerModel.StyleItem;
import pg.registerModel.SupplierModel;
import pg.registerModel.Unit;
import pg.registerModel.WareHouse;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	private RegisterDao registerDao;

	@Override
	public boolean saveBrand(Brand brand) {
		// TODO Auto-generated method stub
		return registerDao.saveBrand(brand);
	}

	@Override
	public boolean editBrand(Brand brand) {
		// TODO Auto-generated method stub
		return registerDao.editBrand(brand);
	}

	@Override
	public List<Brand> getBrandList() {
		// TODO Auto-generated method stub
		return registerDao.getBrandList();
	}

	@Override
	public boolean isBrandExist(Brand brand) {
		// TODO Auto-generated method stub
		return registerDao.isBrandExist(brand);
	}

	@Override
	public boolean saveFabricsItem(FabricsItem fabricsItem) {
		// TODO Auto-generated method stub
		return registerDao.saveFabricsItem(fabricsItem);
	}

	@Override
	public boolean editFabricsItem(FabricsItem fabricsItem) {
		// TODO Auto-generated method stub
		return registerDao.editFabricsItem(fabricsItem);
	}

	@Override
	public List<FabricsItem> getFabricsItemList() {
		// TODO Auto-generated method stub
		return registerDao.getFabricsItemList();
	}

	@Override
	public boolean isFabricsItemExist(FabricsItem fabricsItem) {
		// TODO Auto-generated method stub
		return registerDao.isFabricsItemExist(fabricsItem);
	}

	@Override
	public boolean saveAccessoriesItem(AccessoriesItem accessoriesItem) {
		// TODO Auto-generated method stub
		return registerDao.saveAccessoriesItem(accessoriesItem);
	}

	@Override
	public boolean editAccessoriesItem(AccessoriesItem accessoriesItem) {
		// TODO Auto-generated method stub
		return registerDao.editAccessoriesItem(accessoriesItem);
	}

	@Override
	public List<AccessoriesItem> getAccessoriesItemList() {
		// TODO Auto-generated method stub
		return registerDao.getAccessoriesItemList();
	}

	@Override
	public boolean isAccessoriesItemExist(AccessoriesItem accessoriesItem) {
		// TODO Auto-generated method stub
		return registerDao.isAccessoriesItemExist(accessoriesItem);
	}

	@Override
	public boolean saveStyleItem(StyleItem styleItem) {
		// TODO Auto-generated method stub
		return registerDao.saveStyleItem(styleItem);
	}

	@Override
	public boolean editStyleItem(StyleItem styleItem) {
		// TODO Auto-generated method stub
		return registerDao.editStyleItem(styleItem);
	}

	@Override
	public List<StyleItem> getStyleItemList() {
		// TODO Auto-generated method stub
		return registerDao.getStyleItemList();
	}

	@Override
	public boolean isStyleItemExist(StyleItem styleItem) {
		// TODO Auto-generated method stub
		return registerDao.isStyleItemExist(styleItem);
	}

	@Override
	public boolean saveUnit(Unit unit) {
		// TODO Auto-generated method stub
		return registerDao.saveUnit(unit);
	}

	@Override
	public boolean editUnit(Unit unit) {
		// TODO Auto-generated method stub
		return registerDao.editUnit(unit);
	}

	@Override
	public List<Unit> getUnitList() {
		// TODO Auto-generated method stub
		return registerDao.getUnitList();
	}

	@Override
	public boolean isUnitExist(Unit unit) {
		// TODO Auto-generated method stub
		return registerDao.isUnitExist(unit);
	}

	@Override
	public boolean saveColor(Color color) {
		// TODO Auto-generated method stub
		return registerDao.saveColor(color);
	}

	@Override
	public boolean editColor(Color color) {
		// TODO Auto-generated method stub
		return registerDao.editColor(color);
	}

	@Override
	public List<Color> getColorList() {
		// TODO Auto-generated method stub
		return registerDao.getColorList();
	}

	@Override
	public boolean isColorExist(Color color) {
		// TODO Auto-generated method stub
		return registerDao.isColorExist(color);
	}

	@Override
	public boolean saveLocalItem(LocalItem localItem) {
		// TODO Auto-generated method stub
		return registerDao.saveLocalItem(localItem);
	}

	@Override
	public boolean editLocalItem(LocalItem localItem) {
		// TODO Auto-generated method stub
		return registerDao.editLocalItem(localItem);
	}

	@Override
	public List<LocalItem> getLocalItemList() {
		// TODO Auto-generated method stub
		return registerDao.getLocalItemList();
	}

	@Override
	public boolean isLocalItemExist(LocalItem localItem) {
		// TODO Auto-generated method stub
		return registerDao.isLocalItemExist(localItem);
	}

	@Override
	public boolean saveParticularItem(ParticularItem particularItem) {
		// TODO Auto-generated method stub
		return registerDao.saveParticularItem(particularItem);
	}

	@Override
	public boolean editParticularItem(ParticularItem particularItem) {
		// TODO Auto-generated method stub
		return registerDao.editParticularItem(particularItem);
	}

	@Override
	public List<ParticularItem> getParticularItemList() {
		// TODO Auto-generated method stub
		return registerDao.getParticularItemList();
	}

	@Override
	public boolean isParticularItemExist(ParticularItem particularItem) {
		// TODO Auto-generated method stub
		return registerDao.isParticularItemExist(particularItem);
	}

	@Override
	public boolean saveCountry(Country country) {
		// TODO Auto-generated method stub
		return registerDao.saveCountry(country);
	}

	@Override
	public boolean editCountry(Country country) {
		// TODO Auto-generated method stub
		return registerDao.editCountry(country);
	}

	@Override
	public List<Country> getCountryList() {
		// TODO Auto-generated method stub
		return registerDao.getCountryList();
	}

	@Override
	public boolean isCountryExist(Country country) {
		// TODO Auto-generated method stub
		return registerDao.isCountryExist(country);
	}

	@Override
	public boolean saveSampleType(SampleType sampleType) {
		// TODO Auto-generated method stub
		return registerDao.saveSampleType(sampleType);
	}

	@Override
	public boolean editSampleType(SampleType sampleType) {
		// TODO Auto-generated method stub
		return registerDao.editSampleType(sampleType);
	}

	@Override
	public List<SampleType> getSampleTypeList() {
		// TODO Auto-generated method stub
		return registerDao.getSampleTypeList();
	}

	@Override
	public boolean isSampleTypeExist(SampleType sampleType) {
		// TODO Auto-generated method stub
		return registerDao.isSampleTypeExist(sampleType);
	}

	@Override
	public boolean saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return registerDao.saveDepartment(department);
	}

	@Override
	public boolean editDepartment(Department department) {
		// TODO Auto-generated method stub
		return registerDao.editDepartment(department);
	}

	@Override
	public List<Department> getDepartmentList() {
		// TODO Auto-generated method stub
		return registerDao.getDepartmentList();
	}

	@Override
	public boolean isDepartmentExist(Department department) {
		// TODO Auto-generated method stub
		return registerDao.isDepartmentExist(department);
	}

	@Override
	public boolean isMerchandiserExist(MerchandiserInfo v) {
		// TODO Auto-generated method stub
		return registerDao.isMerchandiserExist(v);
	}

	@Override
	public boolean saveMerchandiser(MerchandiserInfo v) {
		// TODO Auto-generated method stub
		return registerDao.saveMerchandiser(v);
	}

	@Override
	public List<MerchandiserInfo> getMerchandiserList() {
		// TODO Auto-generated method stub
		return registerDao.getMerchandiserList();
	}

	@Override
	public boolean editMerchandiser(MerchandiserInfo v) {
		// TODO Auto-generated method stub
		return registerDao.editMerchandiser(v);
	}

	@Override
	public boolean isInchargeExist(InchargeInfo v) {
		// TODO Auto-generated method stub
		return registerDao.isInchargeExist(v);
	}

	@Override
	public boolean saveIncharge(InchargeInfo v) {
		// TODO Auto-generated method stub
		return registerDao.saveIncharge(v);
	}

	@Override
	public List<InchargeInfo> getInchargeList() {
		// TODO Auto-generated method stub
		return registerDao.getInchargeList();
	}

	@Override
	public boolean saveWareHouse(WareHouse wareHouse) {
		// TODO Auto-generated method stub
		return registerDao.saveWareHouse(wareHouse);
	}

	@Override
	public boolean editWareHouse(WareHouse wareHouse) {
		// TODO Auto-generated method stub
		return registerDao.editWareHouse(wareHouse);
	}

	@Override
	public List<WareHouse> getWareHouseList() {
		// TODO Auto-generated method stub
		return registerDao.getWareHouseList();
	}

	@Override
	public boolean isWareHouseExist(WareHouse wareHouse) {
		// TODO Auto-generated method stub
		return registerDao.isWareHouseExist(wareHouse);
	}

	@Override
	public boolean saveLine(Line line) {
		// TODO Auto-generated method stub
		return registerDao.saveLine(line);
	}

	@Override
	public boolean editLine(Line line) {
		// TODO Auto-generated method stub
		return registerDao.editLine(line);
	}

	@Override
	public List<Line> getLineList() {
		// TODO Auto-generated method stub
		return registerDao.getLineList();
	}

	@Override
	public boolean isLineExist(Line line) {
		// TODO Auto-generated method stub
		return registerDao.isLineExist(line);
	}

	@Override
	public boolean saveStyleSize(Size size) {
		// TODO Auto-generated method stub
		return registerDao.saveStyleSize(size);
	}

	@Override
	public boolean editStyleSize(Size size) {
		// TODO Auto-generated method stub
		return registerDao.editStyleSize(size);
	}

	@Override
	public List<Size> getStyleSizeList() {
		// TODO Auto-generated method stub
		return registerDao.getStyleSizeList();
	}

	@Override
	public boolean isStyleSizeExist(Size size) {
		// TODO Auto-generated method stub
		return registerDao.isStyleSizeExist(size);
	}

	@Override
	public boolean saveStyleSizeGroup(SizeGroup sizeGroup) {
		// TODO Auto-generated method stub
		return registerDao.saveStyleSizeGroup(sizeGroup);
	}

	@Override
	public boolean editStyleSizeGroup(SizeGroup sizeGroup) {
		// TODO Auto-generated method stub
		return registerDao.editStyleSizeGroup(sizeGroup);
	}

	@Override
	public List<SizeGroup> getStyleSizeGroupList() {
		// TODO Auto-generated method stub
		return registerDao.getStyleSizeGroupList();
	}

	@Override
	public boolean isStyleSizeGroupExist(SizeGroup sizeGroup) {
		// TODO Auto-generated method stub
		return registerDao.isStyleSizeGroupExist(sizeGroup);
	}

	@Override
	public boolean insertBuyer(BuyerModel buyer) {
		// TODO Auto-generated method stub
		return registerDao.insertBuyer(buyer);
	}

	@Override
	public List<String> BuyersList(String value) {
		// TODO Auto-generated method stub
		return registerDao.BuyersList(value);
	}

	@Override
	public List<BuyerModel> BuyersDetails(String value) {
		// TODO Auto-generated method stub
		return registerDao.BuyersDetails(value);
	}

	@Override
	public boolean editBuyer(BuyerModel buyer) {
		// TODO Auto-generated method stub
		return registerDao.editBuyer(buyer);
	}

	@Override
	public List<BuyerModel> getAllBuyers() {
		// TODO Auto-generated method stub
		return registerDao.getAllBuyers();
	}

	@Override
	public String maxSupplierId() {
		// TODO Auto-generated method stub
		return registerDao.maxSupplierId();
	}

	@Override
	public boolean insertSupplier(SupplierModel supplier) {
		// TODO Auto-generated method stub
		return registerDao.insertSupplier(supplier);
	}

	@Override
	public List<String> supplierList(String value) {
		// TODO Auto-generated method stub
		return registerDao.supplierList(value);
	}

	@Override
	public List<SupplierModel> SupplierDetails(String value) {
		// TODO Auto-generated method stub
		return registerDao.SupplierDetails(value);
	}

	@Override
	public boolean editSupplier(SupplierModel supplier) {
		// TODO Auto-generated method stub
		return registerDao.editSupplier(supplier);
	}

	@Override
	public List<SupplierModel> getAllSupplier() {
		// TODO Auto-generated method stub
		return registerDao.getAllSupplier();
	}

	@Override
	public String maxFactoryId() {
		// TODO Auto-generated method stub
		return registerDao.maxFactoryId();
	}

	@Override
	public boolean insertFactory(FactoryModel factory) {
		// TODO Auto-generated method stub
		return registerDao.insertFactory(factory);
	}

	@Override
	public List<FactoryModel> FactoryDetails(String value) {
		// TODO Auto-generated method stub
		return registerDao.FactoryDetails(value);
	}

	@Override
	public List<String> factorylist(String value) {
		// TODO Auto-generated method stub
		return registerDao.factorylist(value);
	}

	@Override
	public boolean editFactory(FactoryModel factory) {
		// TODO Auto-generated method stub
		return registerDao.editFactory(factory);
	}

	@Override
	public List<FactoryModel> getAllFactories() {
		// TODO Auto-generated method stub
		return registerDao.getAllFactories();
	}

	@Override
	public String maxCourierId() {
		// TODO Auto-generated method stub
		return registerDao.maxCourierId();
	}

	@Override
	public boolean insertCourier(CourierModel courier) {
		// TODO Auto-generated method stub
		return registerDao.insertCourier(courier);
	}

	@Override
	public List<String> courierList(String value) {
		// TODO Auto-generated method stub
		return registerDao.courierList(value);
	}

	@Override
	public List<CourierModel> CourierDetails(String value) {
		// TODO Auto-generated method stub
		return registerDao.CourierDetails(value);
	}

	@Override
	public boolean editCourier(CourierModel courier) {
		// TODO Auto-generated method stub
		return registerDao.editCourier(courier);
	}

	@Override
	public List<CourierModel> getAllCouriers() {
		// TODO Auto-generated method stub
		return registerDao.getAllCouriers();
	}

	@Override
	public String maxBuyerId() {
		// TODO Auto-generated method stub
		return registerDao.maxBuyerId();
	}

	@Override
	public List<String> countries(String value) {
		// TODO Auto-generated method stub
		return registerDao.countries(value);
	}

	@Override
	public List<Factory> getFactoryNameList() {
		// TODO Auto-generated method stub
		return registerDao.getFactoryNameList();
	}

}
