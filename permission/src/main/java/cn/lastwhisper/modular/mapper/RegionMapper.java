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
import cn.lastwhisper.modular.pojo.Region;


public interface RegionMapper {

	List<Tree> selectRegionList();

	List<Region> selectRegionById(@Param("region_id") String region_id);

	Integer insertRegion(Region region);


	Integer deleteRegionById(String region_id);


	Integer updateRegionById(Region region);
	
	/**
	 * 
	 * @Description: 根据pid获取所有权限Region(menuid,menuname)
	 * @return List<Menu>
	 */
	public List<Region> selectRegionIdName(@Param("pid") String pid);

	/**
	 * 
	 * @Description: 根据userid加载对应Region
	 * @param userid
	 * @return List<Menu>
	 */
	public List<Region> selectRegionByUserid(@Param("user_id") Integer userid);
	
	/**
	 * 
	* @Title: selectRegion 
	* @Description: 查询所有Region的所有属性 
	* @param pid
	* @return List<Region>
	 */
	public List<Region> selectRegion(@Param("pid") String pid);

}
