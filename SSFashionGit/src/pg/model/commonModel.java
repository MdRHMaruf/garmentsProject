package pg.model;

public class commonModel {
	
	String id;
	String name;
	String qty;
	
	public commonModel() {
		
	}
	public commonModel( String qty) {
		super();
		
		this.qty = qty;
	}
	
	public commonModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
