package com.common.template.repository.pms;

import java.util.List;
import java.util.Map;

import com.common.template.entity.Permission;
import com.common.template.entity.PmsOption;
import com.common.template.entity.Role;

/**
 * 权限管理数据接口
 * @author qiulongjie
 *
 */
public interface PermissionDao {

	/**
	 * 获取全部角色 
	 * @return
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * 更新角色
	 * @param role
	 */
	public void updateRole(Role role);
	
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
	 * 添加角色权限
	 * @param roleCode
	 * @param pms
	 */
	public void addRolePms(String roleCode, String[] pms);

	/**
	 * 查找权限根据角色code
	 * @param roleCode
	 * @return
	 */
	public List<PmsOption> findPmsCodes(String roleCode);

	/**
	 * 获取全部权限
	 * @return
	 */
	public List<Permission> getAllPms();

	/**
	 * 根据父权限id获取子权限
	 * @param pid
	 * @return
	 */
	public List<String> getPmsCodeByPid(String pid);

	/**
	 * 更新
	 * @param id
	 * @param pmsName
	 * @param accessUri
	 */
	public void updatePms(String id, String pmsName, String accessUri);

	/**
	 * 删除权限
	 * @param delCodes
	 */
	public void deletePermission(List<String> delCodes);
	
	/**
	 * 删除权限角色关系
	 * @param delPmsCodes
	 */
	public void deleteRolePms(List<String> delPmsCodes);

	/**
	 * 查询数据 返回listMap
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryData(String sql, Object[] args);

	/**
	 * 根据role_code 删除角色权限关系
	 * @param roleCode
	 */
	public void deleteRolePmsByRoleCode(String roleCode);

	/**
	 * 更新操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object[] params);
}
