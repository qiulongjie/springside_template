/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.common.template.web.income;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.POIXMLException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.template.service.income.IncomeService;
import com.common.template.util.StreamUtil;
import com.common.template.util.StringUtil;
import com.common.template.web.controllers.BaseController;

@Controller
@RequestMapping(value = "/income")
public class IncomeController extends BaseController {

	@Resource
	private IncomeService incomeService;

	@RequestMapping(value = "dataUpload", method = RequestMethod.GET)
	public String dataUploadPage() {
		return "allpages/income/dataUpload";
	}
	
	@RequestMapping(value = "upstream", method = RequestMethod.GET)
	public String upstreamPage() {
		return "allpages/income/upstream";
	}
	
	@RequestMapping(value = "channel", method = RequestMethod.GET)
	public String channelPage() {
		return "allpages/income/channel";
	}
	
	@RequestMapping(value = "upstream/getData")
	@ResponseBody
	public String getUpstreamData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String company = getParameter(request,"company","");
		company = java.net.URLDecoder.decode(company, "UTF-8");//一次解码
		String startMonth = getParameter(request,"m1","");
		String endMonth = getParameter(request,"m2","");
		String advertiser = getParameter(request,"ad","");
		advertiser = java.net.URLDecoder.decode(advertiser, "UTF-8");//一次解码
		String draw = getParameter(request,"draw","1");
		String data = incomeService.queryUpstreamData(pageNum,pageSize,startMonth,endMonth,company,advertiser,draw);
		return data;
	}
	
	@RequestMapping(value = "channel/getData")
	@ResponseBody
	public String getChannelData(HttpServletRequest request) throws Exception {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String company = getParameter(request,"company","");
		company = java.net.URLDecoder.decode(company, "UTF-8");//一次解码
		String startMonth = getParameter(request,"m1","");
		String endMonth = getParameter(request,"m2","");
		String channelCompany = getParameter(request,"chcom","");
		channelCompany = java.net.URLDecoder.decode(channelCompany, "UTF-8");//一次解码
		String draw = getParameter(request,"draw","1");
		String data = incomeService.queryChannelData(pageNum,pageSize,startMonth,endMonth,company,channelCompany,draw);
		return data;
	}

	/**
	 * 上游数据上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upstreamDataInput", method = RequestMethod.POST)
	public String upstreamDataInput(HttpServletRequest request) throws Exception {
		insertData(request,1);
		return "allpages/income/dataUpload";
	}
	
	/**
	 * 渠道数据上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "channelDataInput", method = RequestMethod.POST)
	public String channelDataInput(HttpServletRequest request) throws Exception {
		insertData(request,2);
		return "allpages/income/dataUpload";
	}

	/**
	 * 解析excel数据并落地数据库
	 * @param request
	 * @param dataType 上传的数据类型： 1-上游数据  调用 incomeService.uploadUpstreamData(in,isExcel2003)
	 *                           2-渠道数据 调用incomeService.uploadChannelData(in,isExcel2003)
	 * @throws Exception
	 */
	private void insertData(HttpServletRequest request,int dataType) throws Exception{
		int count = 0;
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dff);
			List<FileItem> fileItems = sfu.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if ((item.isFormField()) && (item.getFieldName().equals("act_index"))) {
					String act_index = StreamUtil.read(item.getInputStream());;
					request.setAttribute("act_index", act_index);
				}
				if ((!item.isFormField()) && (item.getName().length() > 0)) {
					//判断文件是不是excel文件
					if(!StringUtil.isExcelFileName(item.getName())){
						request.setAttribute("message", "警告：请选择的是excel文件吗？");
						break;
					}
					//是否为2003版本的excel
					boolean isExcel2003 = StringUtil.isExcel2003File(item.getName());
					InputStream in = item.getInputStream();
					try {
						switch (dataType) {
						case 1: // 上游数据
							count = incomeService.uploadUpstreamData(in,isExcel2003);
							break;
						case 2:// 渠道数据 
							count = incomeService.uploadChannelData(in,isExcel2003);
							break;

						default:
							break;
						}
						
						request.setAttribute("message", "成功上传"+count+"条数据!");
					}catch(OfficeXmlFileException e1){
						//The supplied data appears to be in the Office 2007+ XML. POI only supports OLE2 Office documents
						//后缀为xlsx的2007版本的excel被改为了xls
						e1.printStackTrace();
						request.setAttribute("message", "sorry!系统发现您的文件有问题。可能的原因：excel文件是2007版本的，但文件后缀却是2003版的xls，请把后缀改为xlsx后再试试！");
					}catch(POIXMLException e2){
						//org.apache.poi.POIXMLException: org.apache.poi.openxml4j.exceptions.InvalidFormatException: Package should contain a content type part [M1.13]
						//后缀为xls的2003版本的excel被改为了xlsx
						e2.printStackTrace();
						request.setAttribute("message", "sorry!系统发现您的文件有问题。可能的原因：excel文件是2003版本的，但文件后缀却是2007版的xlsx，请把后缀改为xls后再试试！");
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("message", "sorry!数据上传出现异常！请联系管理员!");
					} finally {
						try {
							if (in != null) {
								in.close();
								in = null;
							}
						} catch (IOException e) {
							e.printStackTrace();
							throw e;
						}
					}
				}
			}
		}
	}
}
