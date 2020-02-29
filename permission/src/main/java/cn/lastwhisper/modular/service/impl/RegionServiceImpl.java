package cn.lastwhisper.modular.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.lastwhisper.core.annotation.LogAnno;
import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.core.util.Tree;
import cn.lastwhisper.modular.mapper.RegionMapper;
import cn.lastwhisper.modular.pojo.Menu;
import cn.lastwhisper.modular.pojo.Region;
import cn.lastwhisper.modular.service.RegionService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class RegionServiceImpl implements RegionService {
private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private RegionMapper regionMapper;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Tree> findRegionList() {
		List<Tree> tree = regionMapper.selectRegionList();
		return tree;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Region> findRegionById(String region_id) {
		return regionMapper.selectRegionById(region_id);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Region> findRegionsByPId(String pid) {
		return regionMapper.selectRegion(pid);
	}

	@LogAnno(operateType = "添加乡镇")
	@Override
	public GlobalResult addRegion(Region region) {
		Integer insertCount = regionMapper.insertRegion(region);
		if (insertCount != null && insertCount > 0) {
			// 更新标签为父标签
			List<Region> regList = regionMapper.selectRegionById(region.getPid());
			if( regList.get(0).getIs_parent()!=1) {
				Region reg = new Region();
				reg.setRegion_id(region.getPid());
				reg.setIs_parent(1);
				Integer upStatue =updateRegionById(reg).getStatus();
				if (200 == upStatue) {
					return new GlobalResult(200, "数据添加成功", null);
				} else {
					return new GlobalResult(400, "数据添加失败", null);
				}
			}
		} 
		return new GlobalResult(200, "数据添加成功", null);
	}

	@LogAnno(operateType = "删除乡镇")
	@Override
	public GlobalResult deleteRegionById(String region_id) {
		Integer deleteCount = regionMapper.deleteRegionById(region_id);
		if (deleteCount != null && deleteCount > 0) {
			return new GlobalResult(200, "数据删除成功", null);
		} else {
			return new GlobalResult(400, "数据删除失败", null);
		}
	}

	@LogAnno(operateType = "更新乡镇")
	@Override
	public GlobalResult updateRegionById(Region region) {
		Integer updateCount = regionMapper.updateRegionById(region);
		if (updateCount != null && updateCount > 0) {
			return new GlobalResult(200, "数据修改成功", null);
		} else {
			return new GlobalResult(400, "数据修改失败", null);
		}
	}

	@Override
	public Region findRegionByUserid(Integer userid) {
		Region region;
				// 获取根菜单
				List<Region> root = regionMapper.selectRegion("-1");
				// 用户下的菜单集合 找数据库
				//			List<Menu> userMenus = menuMapper.selectMenuByUserid(userid);
				// 用户下的菜单集合 找缓存
				List<Region> userRegions = findRegionListByUserid(userid);
				// 根菜单
				region = cloneRegion(root.get(0));
				// 暂存一级菜单
				Region _m1 = null;
				// 暂存二级菜单
				Region _m2 = null;
				// 获取全部的一级菜单
				List<Region> parentRegions = regionMapper.selectRegion("0");
				// 循环一级菜单
				for (Region m1 : parentRegions) {
					_m1 = cloneRegion(m1);
					// 获取当前一级菜单的所有二级菜单
					List<Region> leafMenus = regionMapper.selectRegion(_m1.getRegion_id());
					// 循环匹配二级菜单
					for (Region m2 : leafMenus) {
						for (Region userRegion : userRegions) {
							if (userRegion.getRegion_id().equals(m2.getRegion_id())) {
								// 将二级菜单加入一级菜单
								_m2 = cloneRegion(m2);
								_m1.getRegions().add(_m2);
							}
						}
					}
					// 有二级菜单我们才加进来
					if (_m1.getRegions().size() > 0) {
						// 把一级菜单加入到根菜单下
						region.getRegions().add(_m1);
					}
				}
	
		return region;
	}

	@Override
	public List<Region> findRegionListByUserid(Integer userid) {
		return regionMapper.selectRegionByUserid(userid);
	}


	/**
	 * 
	 * @Title: cloneRegion
	 * @param src
	 * @return
	 */
	private Region cloneRegion(Region src) {
		Region region = new Region();
		region.setRegion_id(src.getRegion_id());
		region.setRegion_code(src.getRegion_code());
		region.setRegion_name(src.getRegion_name());
		region.setRegion_order(src.getRegion_order());
		region.setPid(src.getPid());
		region.setIs_parent(src.getIs_parent());
		region.setRegions(new ArrayList<Region>());
		return region;
	}
	

}
