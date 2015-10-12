/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.common.template.web.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.common.template.entity.Role;
import com.common.template.service.pms.PermissionService;
import com.common.template.util.StringUtil;

@Controller
@RequestMapping(value = "/pms")
public class PermissionController extends BaseController{
	
	@Resource
	private PermissionService pmsService;

	@RequestMapping(method = RequestMethod.GET)
	public String pmsManager(Model model) {
		List<Role> roles = pmsService.getAllRoles();
		model.addAttribute("roles", roles);
		return "allpages/permission/pmsManager";
	}
	
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
	
	// 添加 更新 权限
	@RequestMapping(value = "mergePms",method = RequestMethod.POST)
	public String mergePms(String act_index ,String pid ,String actionTyle, String pmsName ,String accessUri ,RedirectAttributes redirectAttributes) {
		if(StringUtil.isNoEmpty(actionTyle)){
			if(actionTyle.equals("add")){
				pmsService.addPms(pid,pmsName,accessUri);
			}
			if(actionTyle.equals("update")){
				pmsService.updatePms(pid,pmsName,accessUri);
			}
		}
		redirectAttributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/pms?act_index="+act_index;
	}
	
	// 删除权限
	@RequestMapping(value = "deletePms",method = RequestMethod.POST)
	public String deletePms(String ids ,String pids, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		pmsService.deletePms(ids,pids);
		sendData(response,"success");
		redirectAttributes.addFlashAttribute("message", "操作成功!");
		return null;
	}
	
	// 分配角色
	@RequestMapping(value = "configPmsRoles",method = RequestMethod.POST)
	public String configPmsRoles(String act_index ,HttpServletRequest request , RedirectAttributes redirectAttributes) {
		String[] roles = request.getParameterValues("roles");
		String pmsIds = request.getParameter("pms_ids");
		pmsService.configPmsRoles(roles,pmsIds);
		redirectAttributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/pms?act_index="+act_index;
	}
	
}
