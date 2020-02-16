package cn.lastwhisper.modular.pojo;

public class Manager {
	private String manager_id;
	private String region_id;
	private String manager_name;
	private String manager_title;
	private String manager_contact1;
	private String manager_contact2;
	
	public String getManager_contact1() {
		return manager_contact1;
	}

	public String getManager_contact2() {
		return manager_contact2;
	}

	public void setManager_contact1(String manager_contact1) {
		this.manager_contact1 = manager_contact1;
	}

	public void setManager_contact2(String manager_contact2) {
		this.manager_contact2 = manager_contact2;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getManager_title() {
		return manager_title;
	}

	public void setManager_title(String pid) {
		this.manager_title = pid;
	}

}
