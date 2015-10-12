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
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.common.template.entity.PmsOption;
import com.common.template.entity.Role;
import com.common.template.service.pms.PermissionService;


@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController{
	
	@Resource
	private PermissionService pmsService;

	@RequestMapping(method = RequestMethod.GET)
	public String roleList(Model model) {
		//List<Role> roles = pmsService.getAllRoles();
		//model.addAttribute("roles", roles);
		return "allpages/permission/roleList";
	}
	
	@RequestMapping(value = "getRoleData")
	@ResponseBody
	public String getRoleData(HttpServletRequest request) {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String draw = getParameter(request,"draw","1");
		String data = pmsService.getRoleData(pageSize,pageNum,draw);
		return data;
	}
	
	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add() {
		return "allpages/permission/addRole";
	}
	
	@RequestMapping(value = "addRole",method = RequestMethod.POST)
	public String addRole(@Valid Role role,String act_index ,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String pmsids = request.getParameter("pmsids");
		pmsService.addRole(role,pmsids);
		redirectAttributes.addFlashAttribute("message", "操作成功,添加角色[" + role.getRoleName() + "]");
		return "redirect:/role?act_index="+act_index;
	}
	
	@RequestMapping(value = "update/{roleCode}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("roleCode") String roleCode, Model model) {
		Role role = pmsService.findRoleByCode(roleCode);
		List<PmsOption> opts = pmsService.getPmsSelectOpt(role.getRoleCode());
		String[] ops = getCheckedIdNames(opts);
		model.addAttribute("role", role);
		model.addAttribute("ids", ops[0]); 
		model.addAttribute("names", ops[1]); 
		return "allpages/permission/updateRole";
	}
	
	// 获取该角色的权限树数据
	@RequestMapping(value = "getPmsZtree")
	public String getPmsZtree(String roleCode,HttpServletResponse response) {
		String data = pmsService.getPmsForTree(roleCode);
		sendData(response, data);
		return null;
	}
	
	// 获取该角色的权限树数据
	@RequestMapping(value = "getPmsZtreeAll")
	public String getPmsZtreeAll(HttpServletResponse response) {
		String data = pmsService.getPmsForTree();
		sendData(response, data);
		return null;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("role") Role role,String act_index ,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String pmsids = request.getParameter("pmsids");
		pmsService.updateRole(role,pmsids);
		redirectAttributes.addFlashAttribute("message", "更新角色成功!");
		return "redirect:/role?act_index="+act_index;
	}
	
	@RequestMapping(value = "delete/{roleCode}", method = RequestMethod.GET)
	public String delete(@PathVariable("roleCode") String roleCode,String act_index , RedirectAttributes redirectAttributes) {
		pmsService.deleteRole(roleCode);
		redirectAttributes.addFlashAttribute("message", "删除角色成功!");
		return "redirect:/role?act_index="+act_index;
	}
	
	//json 返回全部角色
	@RequestMapping(value = "getRolesForJSON", method = RequestMethod.POST)
	public String getRolesForJSON(HttpServletResponse response) {
		String json = pmsService.getRoleJSON();
		sendData(response, json);
		return null;
	}
	
	/**
	 * Ajax请求校验roleCode是否唯一。
	 */
	@RequestMapping(value = "checkRoleCode")
	@ResponseBody
	public String checkRoleCode(@RequestParam("roleCode") String roleCode) {
		if (pmsService.findRoleByCode(roleCode) == null) {
			return "true";
		} else {
			return "false";
		}
	}
	
	@ModelAttribute
	public void getRole(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("role", pmsService.getRole(id));
		}
	}
}
