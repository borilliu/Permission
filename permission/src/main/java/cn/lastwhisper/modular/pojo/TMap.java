package cn.lastwhisper.modular.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lastwhisper.core.util.DsTransform;
import cn.lastwhisper.modular.service.impl.MenuServiceImpl;


//腾讯地图类
public class TMap implements Serializable{
	private static Logger logger = LoggerFactory.getLogger(TMap.class);
	 private static final long serialVersionUID = 1L;
	 private BigDecimal lat;
	 private BigDecimal lng; 
	 private String status;
	 private String title;
	 public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private static DsTransform transform = new DsTransform();
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
	public void transferGPS() {
		try {
			double[] result = transform.wgs84togcj02(this.getLng().doubleValue(),this.getLat().doubleValue());
			this.setLng(BigDecimal.valueOf(result[0]));
			this.setLat(BigDecimal.valueOf(result[1]));
		}catch(RuntimeException  re) {
			logger.info("GPS转换异常:lat:{}Lng:{}",this.getLat(),this.getLng());;
		}
	}
}
