package com.common.template.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.template.entity.PmsOption;


public abstract class BaseController {
	// 向客户端发送信息
	public void sendData(HttpServletResponse response, String data) {
		PrintWriter printWriter = null;
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			printWriter.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}
	
	/**
	 * 从request中取出name参数的值
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getParameter(HttpServletRequest request, String name, String defaultValue) {
		return request.getParameter(name) == null ? defaultValue : request.getParameter(name);
	}

	/**
	 * 从权限ztree树数据中取出已经被选择的code和name
	 * @param opts
	 * @return
	 */
	public String[] getCheckedIdNames(List<PmsOption> opts) {
		if( opts == null || opts.size() <= 0 ){
			return new String[]{"",""};
		}
		StringBuffer ids = new StringBuffer();
		StringBuffer names = new StringBuffer();
		for(PmsOption opt : opts){
			if(Boolean.valueOf(opt.checked)){
				ids.append(opt.pmsCode).append(",");
				names.append(opt.pmsName).append(",");
			}
		}
		if( ids.length() <= 0 ){
			return new String[]{"",""};
		}
		ids.deleteCharAt(ids.length()-1);
		names.deleteCharAt(names.length()-1);
		return new String[]{ids.toString(),names.toString()};
	}
}
