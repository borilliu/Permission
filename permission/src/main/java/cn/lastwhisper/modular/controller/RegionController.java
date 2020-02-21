
package cn.lastwhisper.modular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.core.util.Tree;
import cn.lastwhisper.core.util.UserUtils;
import cn.lastwhisper.modular.pojo.Region;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.service.RegionService;


@Controller
public class RegionController {
	
	@Autowired
	private RegionService regionService;

	@RequestMapping(value="/region/regionlist")
	@ResponseBody
	public List<Tree> findAll() {
		return regionService.findRegionList(); 
	}
	
	@RequestMapping("/region/getregions")
	@ResponseBody	
	public List<Region> findRegionsByPId(String pid){
		return regionService.findRegionsByPId(pid);
	}

	@RequestMapping("/region/regionfindById")
	@ResponseBody
	public List<Region> findById(String region_id) {
		return regionService.findRegionById(region_id);
	}

	@RequestMapping(value="/region/regionadd")
	@ResponseBody
	public GlobalResult insert(Region region) {
		return regionService.addRegion(region);
	}
	
	@RequestMapping(value="/region/regiondelete")
	@ResponseBody
	public GlobalResult deleteById(String region_id) {
		return regionService.deleteRegionById(region_id);
	}
	

	@RequestMapping(value="/region/regionupdate")
	@ResponseBody
	public GlobalResult updateById(Region region) {
		return regionService.updateRegionById(region);
	}
	
	
	/**
	 * 
	* @Title: loadRegion 
	* @Description: 根据session中的user_id加载菜单
	* @return Region
	 */
	@RequestMapping(value="/region/loadRegions")
	@ResponseBody
	public Region loadRegions() {
		User user = UserUtils.getSubjectUser();
		Region regions = null;
		if(user!=null) {
			regions = regionService.findRegionByUserid(user.getUser_id());
		}
		return regions;
	}
}
