package pg.registerModel;

public class Size {
	String sizeId;
	String groupId;
	String groupName;
	String sizeName;
	String sizeSorting;
	String userId;
	
	public Size() {}
	
	public Size(String sizeId, String groupId,String groupName, String sizeName, String sizeSorting, String userId) {
		super();
		this.sizeId = sizeId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.sizeName = sizeName;
		this.sizeSorting = sizeSorting;
		this.userId = userId;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getSizeSorting() {
		return sizeSorting;
	}
	public void setSizeSorting(String sizeSorting) {
		this.sizeSorting = sizeSorting;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
