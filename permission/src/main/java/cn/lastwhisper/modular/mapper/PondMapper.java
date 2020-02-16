package cn.lastwhisper.modular.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.Pond;

public interface PondMapper {

	public List<Pond> findPondlistByPage(Pond pond);

	public List<Pond> findPondFuzzyName(@Param("pondname")String pond_name);

	public List<Pond> findPondByID(String pond_id);

	public Integer insertPond(Pond pond);
	
	public Integer updatePond(Pond pond);

	public Integer deletePondById(@Param("pond_id") String pond_id);

}
