package cn.lastwhisper.modular.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.core.util.Tree;
import cn.lastwhisper.modular.mapper.ManagerMapper;
import cn.lastwhisper.modular.pojo.Manager;
import cn.lastwhisper.modular.pojo.Region;
import cn.lastwhisper.modular.service.ManagerService;


@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class ManagerServiceImpl implements ManagerService {

	private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private ManagerMapper managerMapper;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Tree> findRegionList() {
		List<Tree> tree = managerMapper.selectRegionList();
		return tree;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Region> findRegionById(String region_id) {
		return managerMapper.selectRegionById(region_id);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Manager> findManagerByRegionId(String region_id) {
		return managerMapper.findManagerByRegionId(region_id);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Manager> findManagerByNameRegion(String region_id, String manager_name) {
		return managerMapper.findManagerByNameRegion(region_id,manager_name);
	}	
	@Override
	public Region findRegionTree() {
		Region region;
		List<Region> rootRegions = managerMapper.selectRegion("4205");
		region = cloneRegion(rootRegions.get(0));
		Region _m1 = null;
		Region _m2 = null;
		for (Region m1 : rootRegions) {
			_m1 = cloneRegion(m1);
			List<Region> leafRegions = managerMapper.selectRegion(_m1.getRegion_id());
			for (Region m2 : leafRegions) {
				_m2 = cloneRegion(m2);
				_m1.getRegions().add(_m2);
			 }
			if (_m1.getRegions().size() > 0) {
				region.getRegions().add(_m1);
			}
		}
		return region;
	}

	@Override
	public GlobalResult addManager(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GlobalResult deleteManagerById(String manager_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GlobalResult updateManagerById(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> findAllRegionList() {
		return managerMapper.findAllRegionList();
	}
	private Region cloneRegion(Region src) {
		Region region = new Region();
		region.setRegion_id(src.getRegion_id());
		region.setRegion_code(src.getRegion_code());
		region.setRegion_name(src.getRegion_name());
		region.setRegion_order(src.getRegion_order());
		region.setRegions(new ArrayList<Region>());
		return region;
	}



}
