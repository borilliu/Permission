package cn.lastwhisper.modular.service;

import java.util.List;

import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.core.util.Tree;
import cn.lastwhisper.modular.pojo.Region;

public interface RegionService {

	List<Tree> findRegionList();

	List<Region> findRegionById(String menuid);

	GlobalResult addRegion(Region region);

	GlobalResult deleteRegionById(String regionId);

	GlobalResult updateRegionById(Region region);
	
	List<Region> findRegionsByPId(String pid);
	
	Region findRegionByUserid(Integer userid);
	
	List<Region> findRegionListByUserid(Integer userid);

}
