package com.common.template.service.pms;

import java.util.List;

import com.common.template.entity.Permission;
import com.common.template.entity.PmsOption;
import com.common.template.entity.Role;

/**
 * 权限管理业务接口
 * @author qiulongjie
 *
 */
public interface PermissionService {

	/**
	 * 获取全部角色 
	 * @return
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 查找角色
	 * @param id
	 * @return
	 */
	public Object getRole(Long id);
	
	/**
	 * 根据角色编码查找角色
	 * @param roleCode
	 * @return
	 */
	public Role findRoleByCode(String roleCode);
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * 添加角色 并配置权限
	 * @param role
	 * @param pmsids
	 */
	public void addRole(Role role, String pmsids);
	
	/**
	 * 更新角色
	 * @param role
	 * @param pmsids 
	 */
	public void updateRole(Role role, String pmsids);
	
	/**
	 *  根据角色编码删除角色
	 * @param roleCode
	 */
	public void deleteRole(String roleCode);
	
	/**
	 * 根据角色获取权限
	 * @param roles
	 * @return
	 */
	public List<Permission> getPermissionsByRoles(List<Role> roles);
	
	/**
	 * 根据角色获取全部权限
	 * @param roleList
	 * @return
	 */
	public List<String> getPms(List<String> roleList);
	
	/**
	 * 获取全部权限 
	 * @return
	 */
	public List<Permission> getAllPms();
	
	/**
	 * 添加权限
	 * @param permission
	 */
	public void addPermission(Permission permission);
	
	/**
	 * 更新权限
	 * @param permission
	 */
	public void updatePermission(Permission permission);
	
	/**
	 * 删除权限  根据权限编码
	 * @param pmsCode
	 */
	public void deletePermission(String pmsCode);

	/**
	 * 创建权限下拉框选择项 
	 * @param roleCode
	 * @return
	 */
	public List<PmsOption> getPmsSelectOpt(String roleCode);

	/**
	 * 查询权限-创建ztree数据
	 * @return
	 */
	public String getPmsForTree();
	
	/**
	 * 获取权限树的数据 创建菜单
	 * @return
	 */
	public String getPmsForMenu();

	/**
	 * 根据角色code获取ztree数据
	 * @param roleCode
	 * @return
	 */
	public String getPmsForTree(String roleCode);
	
	/**
	 * 删除权限
	 * @param ids 权限id字符串 如 1,2,3
	 * @param pids 父权限id字符串 如 1,2,3
	 */
	public void deletePms(String ids,String pids);

	/**
	 * 添加权限
	 * @param pid
	 * @param pmsName
	 * @param accessUri
	 */
	public void addPms(String pid, String pmsName, String accessUri);

	/**
	 * 更新权限
	 * @param pid
	 * @param pmsName
	 * @param accessUri
	 */
	public void updatePms(String id, String pmsName, String accessUri);

	/**
	 * 为user配置角色
	 * @param id
	 * @param roleCodes
	 */
	public void updateUserRole(Long id, String[] roleCodes);

	/**
	 * 获取所有的权限路径 status=1 and access_uri != '#'
	 * @return
	 */
	public List<String> getPmsUri();
	
	/**
	 *  获取所有的权限路径  access_uri != '#'
	 * @return
	 */
	public List<String> getAllPmsUri();

	/**
	 * 返回json数据的角色
	 * @return
	 */
	public String getRoleJSON();

	/**
	 * 为权限 分配角色
	 * @param roles
	 * @param pmsIds
	 */
	public void configPmsRoles(String[] roles, String pmsIds);

	/**
	 * 分页查询角色信息
	 * @param pageSize
	 * @param pageNum
	 * @param draw
	 * @return
	 */
	public String getRoleData(String pageSize, String pageNum, String draw);

}
