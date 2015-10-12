/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.common.template.web.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.template.service.pms.PermissionService;


@Controller
@RequestMapping(value = "/readPms")
public class ReadPermissionController extends BaseController{
	
	@Resource
	private PermissionService pmsService;
	
	// 获取权限树的数据 创建菜单
	@RequestMapping(value = "getPmsForMenu")
	public String getPmsForMenu(HttpServletResponse response) {
		String data = pmsService.getPmsForMenu();
		sendData(response,data);
		return null;
	}
	
	// 获取权限树的数据
	@RequestMapping(value = "getPmsForTree")
	public String getPmsForTree(HttpServletResponse response) {
		String data = pmsService.getPmsForTree();
		sendData(response,data);
		return null;
	}
}
