package com.common.template.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 权限
 * 
 * @author qiulongjie
 *
 */
@Entity
@Table(name = "tb_permission")
public class Permission extends IdEntity {

	private String pmsCode;
	private String parentCode;
	private String pmsName;
	private Integer pmsStatus = 1;
	private Date createTime;
	private String createUser;
	private String accessUri;
	private Integer children;

	private List<Role> roles = new ArrayList<Role>();

	public Permission() {
	}

	public Permission(String pmsCode, String parentCode, String pmsName, Integer pmsStatus, Date createTime,
			String createUser, String accessUri) {
		this.pmsCode = pmsCode;
		this.parentCode = parentCode;
		this.pmsName = pmsName;
		this.pmsStatus = pmsStatus;
		this.createTime = createTime;
		this.createUser = createUser;
		this.accessUri = accessUri;
	}

	public String getPmsCode() {
		return pmsCode;
	}

	public void setPmsCode(String pmsCode) {
		this.pmsCode = pmsCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getPmsName() {
		return pmsName;
	}

	public void setPmsName(String pmsName) {
		this.pmsName = pmsName;
	}

	public Integer getPmsStatus() {
		return pmsStatus;
	}

	public void setPmsStatus(Integer pmsStatus) {
		this.pmsStatus = pmsStatus;
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

	public String getAccessUri() {
		return accessUri;
	}

	public void setAccessUri(String accessUri) {
		this.accessUri = accessUri;
	}

	@Transient
	@JsonIgnore
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Transient
	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}
	
}
