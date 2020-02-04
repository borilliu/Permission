package cn.lastwhisper.modular.pojo;

import java.util.LinkedList;
import java.util.List;

public class Region {
	private String region_id;
	private String region_code;
	private String region_name;
	private String pid;
	private Integer region_order;
	private Integer is_parent;
	private List<Region> regions = new LinkedList<Region>();

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getRegion_code() {
		return region_code;
	}

	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getRegion_order() {
		return region_order;
	}

	public void setRegion_order(Integer region_order) {
		this.region_order = region_order;
	}

	public Integer getIs_parent() {
		return is_parent;
	}

	public void setIs_parent(Integer is_parent) {
		this.is_parent = is_parent;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

}
