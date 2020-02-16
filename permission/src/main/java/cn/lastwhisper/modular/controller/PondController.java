package cn.lastwhisper.modular.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.lastwhisper.core.annotation.LogAnno;
import cn.lastwhisper.core.util.EasyUIDataGridResult;
import cn.lastwhisper.core.util.EasyUIOptionalTreeNode;
import cn.lastwhisper.core.util.GlobalResult;
import cn.lastwhisper.core.util.UserUtils;
import cn.lastwhisper.modular.pojo.Log;
import cn.lastwhisper.modular.pojo.Pond;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.service.LogService;
import cn.lastwhisper.modular.service.PondService;


@Controller
public class PondController {
	@Autowired
	private PondService pondService;
	@Autowired
	private LogService logService;

	@RequestMapping(value = "/pond/pondlistByPage", method = RequestMethod.POST)
	@ResponseBody
	public EasyUIDataGridResult PondlistByPage(Pond pond,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = true, defaultValue = "20") Integer rows) {
		return pondService.findPondlistByPage(pond, page, rows);
	}

	@RequestMapping(value = "/pond/searchPondName", method = RequestMethod.POST)
	@ResponseBody
	public List<Pond> searchUserName(String fuzzyName) {
		List<Pond> pondList = pondService.findPondFuzzyName(fuzzyName);
		return pondList;
	}

	@RequestMapping(value = "/pond/pondadd", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult pondadd(Pond pond) {
		return pondService.addPond(pond);
	}

	@RequestMapping(value = "/pond/pondupdate", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult pondupdate(Pond pond) {
		return pondService.updatePond(pond);
	}

	@RequestMapping(value = "/pond/ponddelete", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult ponddelete(Pond pond) {
		return pondService.deletePond(pond.getPond_id()); 
	}

	
	@RequestMapping(value = "/pond/pondexport", method = RequestMethod.POST)
	@ResponseBody
	public void pondexport(Pond user, HttpServletResponse response) {
		String filename = "Users_exportBy" + UserUtils.getSubjectUser().getUser_name() + ".xls";
		// 响应对象
		try {
			// 设置输出流,实现下载文件
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));

			pondService.export(response.getOutputStream(), user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: userdoImport
	 * @Description: 导入用户信息excel
	 * @author: 最后的轻语_dd43
	 * @return
	 */
	@RequestMapping(value = "/pond/ponddoImport", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult userdoImport(MultipartFile file) {
		try {
			pondService.doImport(file.getInputStream());
			return new GlobalResult(200, "文件上传成功", null);
		} catch (IOException e) {
			e.printStackTrace();
			return new GlobalResult(400, "文件上传失败", null);
		}
	}
}