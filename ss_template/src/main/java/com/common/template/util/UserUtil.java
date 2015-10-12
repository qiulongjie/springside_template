package com.common.template.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.common.template.service.account.ShiroDbRealm.ShiroUser;

/**
 * 用户工具类
 * @author qiulongjie
 *
 */
public class UserUtil {

	/**
	 * 获取登录用户的所有权限
	 * @return
	 */
	public static List<String> getUserRoles() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(user == null){
			return new ArrayList<String>();
		}
		return user.getRoleList();
	}
	
	/**
	 * 获取登录用户的所有权限
	 * @return
	 */
	public static List<String> getUserPermissions() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(user == null){
			return new ArrayList<String>();
		}
		return user.pms;
	}
	
	/**
	 * 判断当前访问路径是否有权限
	 * @param path
	 * @return
	 */
	public static boolean checkPermission(String path){
		return SecurityUtils.getSubject().isPermitted(path);
	}
	
	/**
	 * 获取登录名
	 * @return
	 */
	public static String getLoginName(){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(user == null){
			return null;
		}
		return user.loginName;
	}
	
	/**
	 * 验证登录用户
	 * @return
	 */
	public static boolean isAuthenticated(){
		return SecurityUtils.getSubject().isAuthenticated();
	}
}
