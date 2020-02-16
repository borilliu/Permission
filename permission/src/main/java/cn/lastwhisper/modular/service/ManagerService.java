
package cn.lastwhisper.modular.service;

import java.util.List;

import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.core.util.Tree;
import cn.lastwhisper.modular.pojo.Manager;
import cn.lastwhisper.modular.pojo.Region;

public interface ManagerService {
	

	List<Tree> findRegionList();

	List<Region> findRegionById(String region_id);
	
	List<Region> findAllRegionList();
	
	List<Manager> findManagerByRegionId(String region_id);

	Region findRegionTree();

	/**
	 * 
	 * @Description: 添加数据
	 * @return
	 */
	GlobalResult addManager(Manager manager);
	
	/**
	 * 
	 * @Description: 根据id删除数据
	 * @return
	 */
	GlobalResult deleteManagerById(String manager_id);

	/**
	 * 
	 * @Description: 根据id修改数据
	 * @return
	 */
	GlobalResult updateManagerById(Manager manager);
}
