package cn.lastwhisper.modular.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.lastwhisper.core.util.EasyUIDataGridResult;
import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.modular.pojo.Pond;
import cn.lastwhisper.modular.pojo.User;

public interface PondService {

	public EasyUIDataGridResult findPondlistByPage(Pond pond, Integer page, Integer rows);

	public List<Pond> findPondFuzzyName(String pondName);
	
	public Pond getPondById(String pond_id);

	public GlobalResult addPond(Pond pond);

	public GlobalResult updatePond(Pond pond);

	public GlobalResult deletePond(String pond_id);

	public void export(OutputStream os, Pond pond);

	public void doImport(InputStream is) throws IOException;
}
