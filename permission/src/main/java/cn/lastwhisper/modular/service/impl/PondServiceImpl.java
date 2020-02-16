package cn.lastwhisper.modular.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.lastwhisper.core.util.EasyUIDataGridResult;
import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.modular.mapper.PondMapper;
import cn.lastwhisper.modular.pojo.Pond;
import cn.lastwhisper.modular.service.PondService;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class PondServiceImpl implements PondService {
	@Autowired
	private PondMapper pondMapper;

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
//	
//
//	/**
//	 * 导出excel文件
//	 */
//	@LogAnno(operateType = "excel导出堰塘信息")
//	@Transactional(propagation = Propagation.NOT_SUPPORTED)
//	@Override
//	public void export(OutputStream os, Pond Pond) {
//		// 获取所有供应商信息
//		List<Pond> PondList = pondMapper.findPondlistByPage(Pond);
//		// 1.创建excel工作薄
//		HSSFWorkbook wk = new HSSFWorkbook();
//		// 2.创建一个工作表
//		HSSFSheet sheet = wk.createSheet("堰塘信息");
//		// 3.写入表头
//		HSSFRow row = sheet.createRow(0);
//		// 表头
//		String[] headerName = { "账号", "密码", "真实姓名 ", "出生日期 " };
//		// 列宽
//		int[] columnWidths = { 6000, 6000, 6000, 6000 };
//		HSSFCell cell = null;
//		for (int i = 0; i < headerName.length; i++) {
//			// 创建表头单元格
//			cell = row.createCell(i);
//			// 向表头单元格写值
//			cell.setCellValue(headerName[i]);
//			sheet.setColumnWidth(i, columnWidths[i]);
//		}
//		// 4.向内容单元格写值
//		int i = 1;
//		for (Pond p : PondList) {
//			row = sheet.createRow(i);
//			row.createCell(0).setCellValue(p.getPond_id());// 账号
//			row.createCell(1).setCellValue("********");// 密码
//			if (p.getPondname() != null) {
//				row.createCell(2).setCellValue(p.getPondname());// "真实姓名
//			}
//			if (p.getUpdate_time() != null) {
//				HSSFCellStyle style_date = wk.createCellStyle();
//				DataFormat df = wk.createDataFormat();
//				style_date.setDataFormat(df.getFormat("yyyy-MM-dd"));
//				row.createCell(3).setCellValue(p.getUpdate_time());// 出生日期
//				sheet.getRow(i).getCell(3).setCellStyle(style_date);
//			}
//			i++;
//		}
//		try {
//			// 写入到输出流中
//			wk.write(os);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				// 关闭工作簿
//				wk.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	/**
//	 * 数据导入
//	 */
//	@LogAnno(operateType = "excel导入堰塘信息")
//	@Override
//	public void doImport(InputStream is) throws IOException {
//		HSSFWorkbook wb = null;
//		try {
//			wb = new HSSFWorkbook(is);
//			HSSFSheet sheet = wb.getSheetAt(0);
//			// 读取数据
//			// 最后一行的行号
//			int lastRow = sheet.getLastRowNum();
//			Pond pond = null;
//			for (int i = 1; i <= lastRow; i++) {
//				// 账号
//				pond = new Pond();
//				pond.setPond_id(sheet.getRow(i).getCell(0).getStringCellValue());
//				// 判断是否已经存在，通过账号来判断
//				List<Pond> list = pondMapper.findPondFuzzyName(pond.getPondname());
//				if (list.size() > 0) {
//					// 说明存在堰塘，需要更新
//					pond = list.get(0);
//				}
//				HSSFCell cell = null;
//				// 密码
//				cell = sheet.getRow(i).getCell(1);
//				cell.setCellType(CellType.STRING);
//
//				// 真实姓名
//				cell = sheet.getRow(i).getCell(2);
//				cell.setCellType(CellType.STRING);
//				pond.setPondname(sheet.getRow(i).getCell(2).getStringCellValue());
//				// 出生日期
//				cell = sheet.getRow(i).getCell(3);
//				cell.setCellType(CellType.NUMERIC);
//				pond.setVillage_addr(sheet.getRow(i).getCell(3).getStringCellValue());
//				if (list.size() == 0) {
//					// 说明不存在堰塘信息，需要新增
//					pondMapper.insertPond(pond);
//				} else {
//					// 更新堰塘信息
//					pondMapper.updatePond(pond);
//				}
//			}
//		} finally {
//			if (null != wb) {
//				try {
//					wb.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//
//
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

	@Override
	public void export(OutputStream os, Pond pond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doImport(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		
	}

}