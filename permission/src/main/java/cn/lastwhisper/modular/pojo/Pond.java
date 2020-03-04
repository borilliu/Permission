package cn.lastwhisper.modular.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import cn.lastwhisper.modular.pojo.Region;
import cn.lastwhisper.modular.pojo.Manager;

public class Pond  implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private String pond_id; 
	 private Integer  date_from; 
	 private String  region_id; 
	 private Region region;
	 private String  region_name;
	 private String  village_addr; 
	 private String   pondname;
	 private BigDecimal pondarea;
	 private BigDecimal pondage; 
	 private BigDecimal  cover_area; 
	 private Integer benifit_farmers; 
	 private Integer lng_deg; 
	 private Integer lng_min; 
	 private Integer lng_sec; 
	 private Integer  lat_deg; 
	 private Integer  lat_min; 
	 private Integer  lat_sec; 
	 private String  town_manager_id; 
	 private Manager manager;
	 private String  village_manager_name; 
	 private String  village_manager_title; 
	 private String  village_manager_tel; 
	 private String  village_cleaner_name;
	 private String  village_cleaner_title; 
	 private String  village_cleaner_tel;
	 private String  pond_inspector;
	 private String  pond_inspector_title; 
	 private String  pond_inspector_tel;
	 private Integer  status_zhipai ;
	 private Integer  status_piaofu ;
	 private Integer  status_laji ;
	 private Integer  status_govern ;
	 private Integer  pond_maintainance ;
	 private String  remark ;
	 private Integer  status ;
	 private Timestamp update_time ;
	public String getPond_id() {
		return pond_id;
	}
	public void setPond_id(String pond_id) {
		this.pond_id = pond_id;
	}
	public Integer getDate_from() {
		return date_from;
	}
	public void setDate_from(Integer date_from) {
		this.date_from = date_from;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getVillage_addr() {
		return village_addr;
	}
	public void setVillage_addr(String village_addr) {
		this.village_addr = village_addr;
	}
	public String getPondname() {
		return pondname;
	}
	public void setPondname(String pondname) {
		this.pondname = pondname;
	}
	public BigDecimal getPondarea() {
		return pondarea;
	}
	public void setPondarea(BigDecimal pondarea) {
		this.pondarea = pondarea;
	}
	public BigDecimal getPondage() {
		return pondage;
	}
	public void setPondage(BigDecimal pondage) {
		this.pondage = pondage;
	}
	public BigDecimal getCover_area() {
		return cover_area;
	}
	public void setCover_area(BigDecimal cover_area) {
		this.cover_area = cover_area;
	}
	public Integer getBenifit_farmers() {
		return benifit_farmers;
	}
	public void setBenifit_farmers(Integer benifit_farmers) {
		this.benifit_farmers = benifit_farmers;
	}
	public Integer getLng_deg() {
		return lng_deg;
	}
	public void setLng_deg(Integer lng_deg) {
		this.lng_deg = lng_deg;
	}
	public Integer getLng_min() {
		return lng_min;
	}
	public void setLng_min(Integer lng_min) {
		this.lng_min = lng_min;
	}
	public Integer getLng_sec() {
		return lng_sec;
	}
	public void setLng_sec(Integer lng_sec) {
		this.lng_sec = lng_sec;
	}
	public Integer getLat_deg() {
		return lat_deg;
	}
	public void setLat_deg(Integer lat_deg) {
		this.lat_deg = lat_deg;
	}
	public Integer getLat_min() {
		return lat_min;
	}
	public void setLat_min(Integer lat_min) {
		this.lat_min = lat_min;
	}
	public Integer getLat_sec() {
		return lat_sec;
	}
	public void setLat_sec(Integer lat_sec) {
		this.lat_sec = lat_sec;
	}
	public String getTown_manager_id() {
		return town_manager_id;
	}
	public void setTown_manager_id(String town_manager_id) {
		this.town_manager_id = town_manager_id;
	}
	public String getVillage_manager_name() {
		return village_manager_name;
	}
	public void setVillage_manager_name(String village_manager_name) {
		this.village_manager_name = village_manager_name;
	}
	public String getVillage_manager_title() {
		return village_manager_title;
	}
	public void setVillage_manager_title(String village_manager_title) {
		this.village_manager_title = village_manager_title;
	}
	public String getVillage_manager_tel() {
		return village_manager_tel;
	}
	public void setVillage_manager_tel(String village_manager_tel) {
		this.village_manager_tel = village_manager_tel;
	}
	public String getVillage_cleaner_name() {
		return village_cleaner_name;
	}
	public void setVillage_cleaner_name(String village_cleaner_name) {
		this.village_cleaner_name = village_cleaner_name;
	}
	public String getVillage_cleaner_title() {
		return village_cleaner_title;
	}
	public void setVillage_cleaner_title(String village_cleaner_title) {
		this.village_cleaner_title = village_cleaner_title;
	}
	public String getVillage_cleaner_tel() {
		return village_cleaner_tel;
	}
	public void setVillage_cleaner_tel(String village_cleaner_tel) {
		this.village_cleaner_tel = village_cleaner_tel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	 public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public String getPond_inspector() {
		return pond_inspector;
	}
	public void setPond_inspector(String pond_inspector) {
		this.pond_inspector = pond_inspector;
	}
	public String getPond_inspector_title() {
		return pond_inspector_title;
	}
	public void setPond_inspector_title(String pond_inspector_title) {
		this.pond_inspector_title = pond_inspector_title;
	}
	public String getPond_inspector_tel() {
		return pond_inspector_tel;
	}
	public void setPond_inspector_tel(String pond_inspector_tel) {
		this.pond_inspector_tel = pond_inspector_tel;
	}
	public Integer getStatus_zhipai() {
		return status_zhipai;
	}
	public void setStatus_zhipai(Integer status_zhipai) {
		this.status_zhipai = status_zhipai;
	}
	public Integer getStatus_piaofu() {
		return status_piaofu;
	}
	public void setStatus_piaofu(Integer status_piaofu) {
		this.status_piaofu = status_piaofu;
	}
	public Integer getStatus_laji() {
		return status_laji;
	}
	public void setStatus_laji(Integer status_laji) {
		this.status_laji = status_laji;
	}
	public Integer getStatus_govern() {
		return status_govern;
	}
	public void setStatus_govern(Integer status_govern) {
		this.status_govern = status_govern;
	}
	public Integer getPond_maintainance() {
		return pond_maintainance;
	}
	public void setPond_maintainance(Integer pond_maintainance) {
		this.pond_maintainance = pond_maintainance;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void transerCheckStatus() {
		if(this.status_zhipai == null) {
			this.setStatus_zhipai(0);
		};
		if(this.status_piaofu == null) {
			this.setStatus_piaofu(0);
		};
		if(this.status_laji == null) {
			this.setStatus_laji(0);
		};
		if(this.pond_maintainance == null) {
			this.setStatus_govern(0);
		}
	}

}
