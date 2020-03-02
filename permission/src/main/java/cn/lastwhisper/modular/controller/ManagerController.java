
package cn.lastwhisper.modular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.core.util.Tree;
import cn.lastwhisper.core.util.UserUtils;
import cn.lastwhisper.modular.pojo.Manager;
import cn.lastwhisper.modular.pojo.Region;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.service.ManagerService;


@Controller
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping(value="/manager/regionlist")
	@ResponseBody
	public List<Tree> findAll() {
		return managerService.findRegionList(); 
	}
	

	@RequestMapping("/manager/regionfindById")
	@ResponseBody
	public List<Region> findById(String region_id) {
		return managerService.findRegionById(region_id);
	}

	@RequestMapping("/manager/findManagerById")
	@ResponseBody
	public Manager findManagerById(String manager_id) {
		return managerService.findManagerById(manager_id);
	}
	
	@RequestMapping("/manager/findManagerByRegionId")
	@ResponseBody
	public List<Manager> findManagerByRegionId(String region_id) {
		return managerService.findManagerByRegionId(region_id);
	}
	@RequestMapping(value="/manager/manageradd")
	@ResponseBody
	public GlobalResult insert(Manager manager) {
		return managerService.addManager(manager);
	}
	
	@RequestMapping(value="/manager/managerdelete")
	@ResponseBody
	public GlobalResult deleteById(String manager_id) {
		return managerService.deleteManagerById(manager_id);
	}
	

	@RequestMapping(value="/manager/managerupdate")
	@ResponseBody
	public GlobalResult updateById(Manager manager) {
		return managerService.updateManagerById(manager);
	}
	
	
	/**
	 * 
	* @Title: loadRegion 
	* @Description: 根据session中的user_id加载菜单
	* @return Region
	 */
	@RequestMapping(value="/manager/loadRegions")
	@ResponseBody
	public Region loadRegions() {
		User user = UserUtils.getSubjectUser();
		Region regions = null;
		if(user!=null) {
			regions = managerService.findRegionTree();
		}
		return regions;
	}
}
