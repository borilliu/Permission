package cn.lastwhisper.modular.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

//腾讯地图类
public class TMap implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private BigDecimal lat;
	 private BigDecimal lng; 
	 private String status;
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
