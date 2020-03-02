/**  
 * @Title:  MenuMapper.java   
 * @Package cn.lastwhisper.mapper   
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 最后的轻语_dd43     
 * @date:   2019年4月6日 下午2:35:30   
 * @version V1.0 
 */
package cn.lastwhisper.modular.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.core.util.Tree;
import cn.lastwhisper.modular.pojo.Manager;
import cn.lastwhisper.modular.pojo.Region;


public interface ManagerMapper {

	List<Tree> selectRegionList();

	
	List<Region> selectRegionById(@Param("region_id") String region_id);
	
	Manager findManagerById(@Param("manager_id") String manager_id);
	
	List<Manager> findManagerByRegionId(@Param("region_id") String region_id);
	
	List<Manager> findManagerByNameRegion(@Param("region_id") String region_id,@Param("manager_name") String manager_name);

	Integer insertManager(Manager manager);


	Integer deleteManagerById(String manager_id);


	Integer updateManagerById(Manager manager);
	
	/**
	 * 
	 * @Description: 根据pid获取所有权限Region(menuid,menuname)
	 * @return List<Menu>
	 */
	public List<Region> selectRegionIdName(@Param("pid") String pid);

	/**
	 * 
	 * @Description: 加载对应Region
	 * @param userid
	 * @return List<Menu>
	 */
	public List<Region> findAllRegionList();
	
	/**
	 * 
	* @Title: selectRegion 
	* @Description: 查询所有Region的所有属性 
	* @param pid
	* @return List<Region>
	 */
	public List<Region> selectRegion(@Param("pid") String pid);

}
