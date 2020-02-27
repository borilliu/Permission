package cn.lastwhisper.modular.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.lastwhisper.core.annotation.LogAnno;
import cn.lastwhisper.core.util.EasyUIDataGridResult;
import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.modular.mapper.ManagerMapper;
import cn.lastwhisper.modular.mapper.PondMapper;
import cn.lastwhisper.modular.pojo.Manager;
import cn.lastwhisper.modular.pojo.Pond;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.service.PondService;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class PondServiceImpl implements PondService {
	@Autowired
	private PondMapper pondMapper;
	@Autowired	
	private ManagerMapper managerMapper;
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public EasyUIDataGridResult findPondlistByPage(Pond pond, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<Pond> list = pondMapper.findPondlistByPage(pond);
		PageInfo<Pond> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int) pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Pond> findPondFuzzyName(String pond_name) {
		List<Pond> list = pondMapper.findPondFuzzyName(pond_name);
		return list;
	}
	
	public Pond getPondById(String pond_id) {
		Pond pond = pondMapper.getPondById(pond_id);
		return pond;
		
	}
	/**
	 * 导出excel文件
	 */
	@LogAnno(operateType = "excel导出堰塘信息")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void export(OutputStream os, Pond Pond) {
		List<Pond> PondList = pondMapper.findPondlistByPage(Pond);
		HSSFWorkbook wk = new HSSFWorkbook();
		HSSFSheet sheet = wk.createSheet("堰塘信息");
		HSSFRow row = sheet.createRow(0);
		// 表头
		String[][] headerName = {   
									{"编号","2000"}, {"堰塘名称","6000"},{"所属乡镇 ","4000"},{"详细地址 ","6000"},
									{"堰塘面积（平方米)","3000"},{"蓄水量（立方米)","3000"},{"灌溉面积（平方米）","3000"},
									{"经度-度","2000"},{"经度-分","2000"},{"经度-秒","2000"},{"纬度-度","2000"},{"纬度-分","2000"},{"纬度-秒","2000"},
									{"镇级塘长姓名","3000"},{"镇级塘长职务","5000"},{"镇级塘长电话","5000"},
									{"村级塘长姓名","3000"},{"村级塘长职务","5000"},{"村级塘长电话","5000"},
									{"保洁员姓名","3000"},{"保洁员职务","5000"},{"保洁员电话","5000"},
									{"监督员姓名","3000"},{"监督员职务","5000"},{"监督员电话","5000"},
									{"污水直排","3000"},{"水面漂浮物","3000"},{"岸坡垃圾","3000"},
									{"整治情况","3000"},{"日常维护","3000"},{"备注","3000"}
								};
		HSSFCell cell = null;
		for (int i = 0; i < headerName.length; i++) {
			// 创建表头单元格
			cell = row.createCell(i);
			// 向表头单元格写值
			cell.setCellValue(headerName[i][0]); 
			int with = new Integer(headerName[i][1]).intValue();
			sheet.setColumnWidth(i, with);
		}
		// 4.向内容单元格写值
		int i = 1;
		for (Pond p : PondList) {
			row = sheet.createRow(i);
			Pond pd = this.getPondById(p.getPond_id());
			row.createCell(0).setCellValue(pd.getPond_id());
			row.createCell(1).setCellValue(pd.getPondname());
			row.createCell(2).setCellValue(pd.getRegion().getRegion_name());
			row.createCell(3).setCellValue(pd.getVillage_addr());
			
			row.createCell(4).setCellValue(pd.getPondarea().toString());
			row.createCell(5).setCellValue(pd.getPondage().toString());
			row.createCell(6).setCellValue(pd.getCover_area().toString());
			
			row.createCell(7).setCellValue(pd.getLng_deg());
			row.createCell(8).setCellValue(pd.getLng_min());
			row.createCell(9).setCellValue(pd.getLng_sec());
			row.createCell(10).setCellValue(pd.getLat_deg());
			row.createCell(11).setCellValue(pd.getLat_min());
			row.createCell(12).setCellValue(pd.getLat_sec());
			
			row.createCell(13).setCellValue(pd.getManager().getManager_name());
			row.createCell(14).setCellValue(pd.getManager().getManager_title());
			row.createCell(15).setCellValue(pd.getManager().getManager_contact1());
			
			row.createCell(16).setCellValue(pd.getVillage_manager_name());
			row.createCell(17).setCellValue(pd.getVillage_manager_title());
			row.createCell(18).setCellValue(pd.getVillage_manager_tel());
			
			row.createCell(19).setCellValue(pd.getVillage_cleaner_name());
			row.createCell(20).setCellValue(pd.getVillage_cleaner_title());
			row.createCell(21).setCellValue(pd.getVillage_cleaner_tel());
			
			row.createCell(22).setCellValue(pd.getPond_inspector());
			row.createCell(23).setCellValue(pd.getPond_inspector_title());
			row.createCell(24).setCellValue(pd.getPond_inspector_tel());
			
			row.createCell(25).setCellValue(convertStatus(pd.getStatus_zhipai()));
			row.createCell(26).setCellValue(convertStatus(pd.getStatus_piaofu()));
			row.createCell(27).setCellValue(convertStatus(pd.getStatus_laji()));
			
			row.createCell(28).setCellValue(convertStatus(pd.getStatus_govern()));
			row.createCell(29).setCellValue(convertStatus(pd.getPond_maintainance()));
			row.createCell(30).setCellValue(pd.getRemark());
			i++;
		}
		try {
			wk.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wk.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

//	@LogAnno(operateType = "添加堰塘")
//	@RequiresPermissions("堰塘管理")
//	@Override
//	public GlobalResult addPond(Pond pond) {
//		if (pond == null) {
//			return new GlobalResult(400, "堰塘信息为空，添加失败！", null);
//		}
//		Integer integer = pondMapper.insertPond(pond);
//		if (integer == 0) {
//			return new GlobalResult(400, "堰塘添加失败", null);
//		} else {
//			return new GlobalResult(200, "堰塘添加成功", null);
//		}
//	}
// 
//	@LogAnno(operateType = "更新堰塘信息")
//	@RequiresPermissions("堰塘管理")
//	@Override
//	public GlobalResult updatePond(Pond pond) {
//		if (pond == null) {
//			return new GlobalResult(400, "堰塘信息为空，修改失败！", 400);
//		}
//		Integer integer = pondMapper.updatePond(pond);
//		if (integer == 0) {
//			return new GlobalResult(400, "堰塘信息更新失败", null);
//		} else {
//			return new GlobalResult(200, "堰塘信息更新成功", null);
//		}
//	}
//
//	@LogAnno(operateType = "删除堰塘信息")
//	@RequiresPermissions("堰塘管理")
//	@Override
//	public GlobalResult deletePond(String pond_id) {
//		try {
//			if (pond_id == null) {
//				return new GlobalResult(400, "堰塘id为空，添加失败！", 400);
//			}
//			Integer integer = pondMapper.deletePondById(pond_id);
//			if (integer == 0) {
//				return new GlobalResult(400, "堰塘删除失败", null);
//			} else {
//
//				return new GlobalResult(200, "堰塘删除成功", null);
//			}
//		} finally {
//		}
//
//	}

	@Override
	public GlobalResult addPond(Pond pond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GlobalResult updatePond(Pond pond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GlobalResult deletePond(String pond_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@LogAnno(operateType = "导入堰塘信息")
	@Override
	public void doImport(Pond pondCrit,InputStream is) throws IOException {
		String regionId = pondCrit.getRegion_id();
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			Pond pond = null;
			for (int i = 1; i <= lastRow; i++) {
				HSSFRow row =sheet.getRow(i);
				for (Cell cell : row) {
					cell.setCellType(CellType.STRING);
				}
				pond = new Pond();
				pond.setDate_from(99887766);// TODO, replace with data date.
				pond.setRegion_id(pondCrit.getRegion_id());
				pond.setPondname(default_StringValue(row.getCell(2)));
				pond.setVillage_addr(default_StringValue(row.getCell(3)));
				
				//堰塘数据信息
				BigDecimal pondarea=new BigDecimal(default_NumberString(row.getCell(4)));
				BigDecimal pondage=new BigDecimal(default_NumberString(row.getCell(5)));
				BigDecimal cover_area=new BigDecimal(default_NumberString(row.getCell(6)));
				Integer benifit_farmers=new Integer(default_NumberString(row.getCell(7)));
				pond.setPondarea(pondarea);
				pond.setPondage(pondage);
				pond.setCover_area(cover_area);
				pond.setBenifit_farmers(benifit_farmers);
				
				//堰塘地理位置西悉尼
				Integer lng_deg = new Integer(default_NumberString(row.getCell(8)));
				Integer lng_min = new Integer(default_NumberString(row.getCell(9)));
				Integer lng_sec = new Integer(default_NumberString(row.getCell(10)));
				Integer lat_deg = new Integer(default_NumberString(row.getCell(11)));
				Integer lat_min = new Integer(default_NumberString(row.getCell(12)));
				Integer lat_sec = new Integer(default_NumberString(row.getCell(13)));
				
		
				pond.setLng_deg(lng_deg);
				pond.setLng_min(lng_min);
				pond.setLng_sec(lng_sec);
				pond.setLat_deg(lat_deg);
				pond.setLat_min(lat_min);
				pond.setLat_sec(lat_sec);
				
				//镇管理员
				String managerName = default_StringValue(row.getCell(14)).trim();
				List<Manager> managerList = managerMapper.findManagerByNameRegion(regionId, managerName);
				if(managerList.size() != 1) {
					throw new IOException("乡镇塘长数据异常,请检查数据！ 堰塘数据初始编号"+ default_StringValue(row.getCell(0)));
				}else {
					String town_manager_id = managerList.get(0).getManager_id();
					pond.setTown_manager_id(town_manager_id);
				}
					
				
				
				//村级塘长
				pond.setVillage_manager_name(default_StringValue(row.getCell(17)));
				pond.setVillage_manager_title(default_StringValue(row.getCell(18)));
				pond.setVillage_manager_tel(default_StringValue(row.getCell(19)));
				//保洁员
				pond.setVillage_cleaner_name(default_StringValue(row.getCell(20)));
				pond.setVillage_cleaner_title(default_StringValue(row.getCell(21)));
				pond.setVillage_cleaner_tel(default_StringValue(row.getCell(22)));
				
				//监督员
				pond.setPond_inspector(default_StringValue(row.getCell(23)));
				pond.setPond_inspector_title(default_StringValue(row.getCell(24)));
				pond.setPond_inspector_tel(default_StringValue(row.getCell(25)));
				
				pond.setStatus_zhipai(defaultStatusValue(row.getCell(26)));
				pond.setStatus_piaofu(defaultStatusValue(row.getCell(27)));
				pond.setStatus_laji(defaultStatusValue(row.getCell(28)));
				pond.setStatus_govern(defaultStatusValue(row.getCell(29)));
				pond.setPond_maintainance(defaultStatusValue(row.getCell(30)));
				
				pond.setRemark("");
				pond.setStatus(1);
				
/*				cell = sheet.getRow(i).getCell(2);
				cell.setCellType(CellType.STRING);
				pond.setUser_name(sheet.getRow(i).getCell(2).getStringCellValue());
				// 出生日期
				cell = sheet.getRow(i).getCell(3);
				cell.setCellType(CellType.NUMERIC);
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//				Date birthday = df.parse(sheet.getRow(i).getCell(3).getDateCellValue());
				pond.setUser_birthday(sheet.getRow(i).getCell(3).getDateCellValue());
				*/
				
				pondMapper.insertPond(pond);
			}
		} finally {
			if (null != wb) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private String default_NumberString(Cell cell){
			if(null == cell || "".equals(cell.getStringCellValue())) {
				return "0";
			}else {
				return cell.getStringCellValue();
			}
				
	}
	private String default_StringValue(Cell cell){
		if(null == cell) {
			return "";
		}else {
			return cell.getStringCellValue();
		}
			
	}
	private Integer defaultStatusValue(Cell cell) {
		if(null == cell || "".equals(cell.getStringCellValue())) {
			return 2;
		}else if (
					"是".equals(cell.getStringCellValue() )||
					"正常".equals(cell.getStringCellValue())||
					"已整治".endsWith(cell.getStringCellValue())
				) {
			return 1;
		}else {
			return 0;
		}
	}
	private String convertStatus(Integer integer) {
		if (integer==null) return "";
		switch (integer) {
			case 0: return "否";
			case 1: return "是";
			case 2: return "";
			default: return "";
		}
	
	}	
}	
