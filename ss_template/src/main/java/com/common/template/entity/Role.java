/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.common.template.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 角色
 * 
 * @author qiulongjie
 *
 */
@Entity
@Table(name = "tb_role")
public class Role extends IdEntity {
	private String roleCode;
	private String roleName;
	private String roleDesc;
	private Integer roleStatus;
	private Date createTime;
	private String createUser;
	private String selected = "";

	private List<Permission> permissions = new ArrayList<Permission>();

	public Role() {
	}

	public Role(String roleCode, String roleName, String roleDesc, Integer roleStatus, Date createTime,
			String createUser) {
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatus = roleStatus;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	@Transient
	@JsonIgnore
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Transient
	@JsonIgnore
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}