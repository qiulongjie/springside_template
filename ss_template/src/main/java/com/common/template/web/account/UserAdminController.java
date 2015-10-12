/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.common.template.web.account;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.common.template.entity.Role;
import com.common.template.entity.User;
import com.common.template.service.account.AccountService;
import com.common.template.service.pms.PermissionService;
import com.common.template.web.controllers.BaseController;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserAdminController extends BaseController{
	private static final String PAGE_SIZE = "10";

	@Autowired
	private AccountService accountService;
	
	@Resource
	private PermissionService pmsService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			Model model) {
		//List<User> users = accountService.getAllUser();
		//Page<User> users = accountService.getUsers(pageNumber, pageSize);
		//model.addAttribute("users", users);

		return "account/adminUserList";
	}
	
	@RequestMapping(value = "getUserData")
	@ResponseBody
	public String getUserData(HttpServletRequest request) {
		String pageSize = getParameter(request,"length","10");
		String pageNum = getParameter(request,"start","0");
		String draw = getParameter(request,"draw","1");
		String data = accountService.getUsers(pageSize,pageNum,draw);
		return data;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", accountService.getUser(id));
		return "account/adminUserForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user,String act_index , RedirectAttributes redirectAttributes) {
		accountService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getLoginName() + "成功");
		return "redirect:/admin/user?act_index="+act_index;
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, String act_index ,RedirectAttributes redirectAttributes) {
		User user = accountService.getUser(id);
		accountService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
		return "redirect:/admin/user?act_index="+act_index;
	}
	
	// 配置角色
	@RequestMapping(value = "configRole/{id}")
	public String configRole(@PathVariable("id") Long id, Model model) {
		User user = accountService.getUser(id);
		List<Role> roles = pmsService.getAllRoles();
		configRoles(roles,user.getRoleList());
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		return "account/configRole";
	}

	private void configRoles(List<Role> roles, List<String> roleList) {
		for(Role role : roles ){
			if(roleList.contains(role.getRoleCode())){
				role.setSelected("selected");
			}
		}
	}
	
	// 修改角色
	@RequestMapping(value = "updateRole",method = RequestMethod.POST)
	public String updateRole(Long id,HttpServletRequest request,String act_index , RedirectAttributes redirectAttributes) {
		String[] roleCodes = request.getParameterValues("roles");
		pmsService.updateUserRole(id,roleCodes);
		redirectAttributes.addFlashAttribute("message", "角色配置成功");
		return "redirect:/admin/user?act_index="+act_index;
	}
	

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("user", accountService.getUser(id));
		}
	}
}
