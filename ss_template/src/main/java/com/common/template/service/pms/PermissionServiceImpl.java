package com.common.template.service.pms;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.template.entity.DataBean;
import com.common.template.entity.Permission;
import com.common.template.entity.PmsOption;
import com.common.template.entity.Role;
import com.common.template.repository.pms.PermissionDao;
import com.common.template.repository.pms.PmsDao;
import com.common.template.repository.pms.RoleDao;
import com.common.template.shiro.SimpleFilterChainDefinitionsService;
import com.common.template.util.DateUtil;
import com.common.template.util.JsonUtil;
import com.common.template.util.StringUtil;
import com.common.template.util.UserUtil;
import com.google.gson.Gson;

@Service("pmsService")
@Transactional
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PmsDao pmsDao;
	
	@Autowired
	private SimpleFilterChainDefinitionsService filterChainDefinitionsService;
	
	public List<Role> getAllRoles() {
		return (List<Role>) roleDao.findAll();
	}
	
	public Object getRole(Long id) {
		return roleDao.findOne(id);
	}
	
	public Role findRoleByCode(String roleCode) {
		return roleDao.findRoleByCode(roleCode);
	}
	
	// 添加角色
	public void addRole(Role role) {
		role.setRoleStatus(1);//状态默认设置有效
		role.setCreateTime(DateUtil.currentTimeMillis());
		role.setCreateUser(UserUtil.getLoginName());
		roleDao.save(role);
	}
	
	/**
	 * 添加角色 并配置权限
	 */
	public void addRole(Role role, String pmsids){
		role.setRoleStatus(1);//状态默认设置有效
		role.setCreateTime(DateUtil.currentTimeMillis());
		role.setCreateUser(UserUtil.getLoginName());
		updateRole(role,pmsids);
	}

	// 更新角色
	public void updateRole(Role role,String pmsids) {
		roleDao.save(role);
		permissionDao.deleteRolePmsByRoleCode(role.getRoleCode());
		if(StringUtil.isNoEmpty(pmsids)){
			String[] pms = pmsids.split(",");
			if(pms != null && pms.length > 0){
				// 配置权限 
				permissionDao.addRolePms(role.getRoleCode(),pms);
			}
		}
	}

	public void deleteRole(String roleCode) {
		roleDao.deleteByCode(roleCode);
		permissionDao.deleteRolePmsByRoleCode(roleCode);
	}

	public List<Permission> getPermissionsByRoles(List<Role> roles) {
		return permissionDao.getPermissionsByRoles(roles);
	}

	public void addPermission(Permission permission) {
		permissionDao.addPermission(permission);
	}

	public void updatePermission(Permission permission) {
		permissionDao.updatePermission(permission);
	}

	public void deletePermission(String pmsCode) {
		permissionDao.deletePermission(pmsCode);
	}

	//获取全部权限 
	public List<Permission> getAllPms() {
		return (List<Permission>) pmsDao.findAll();
	}
	
	// 根据角色获取全部权限
	public List<String> getPms(List<String> roleList){
		String str = StringUtil.joinStrForSql(roleList);
		String sql = " select pms_code,                                 \n"+
					"      pms_name,                                   \n"+
					" 	  parent_code,                                 \n"+
					" 	  access_uri                                   \n"+
					" from tb_permission                               \n"+
					" where pms_code in (                              \n"+
					" 			        select distinct pms_code           \n"+
					" 					  from tb_role_permission              \n"+
					" 					  where role_code in ("+str+")  \n"+
					" 				  )                                      \n"+
					" and pms_status = 1                               \n";
		List<Map<String,Object>> list = permissionDao.queryData(sql, new Object[]{});
		return changeMapToStr(list);
	}
	
	private List<String> changeMapToStr(List<Map<String, Object>> list) {
		List<String> data = new ArrayList<String>();
		if(list != null && list.size() > 0){
			for(Map<String,Object> map : list){
				for(Map.Entry<String, Object> entry : map.entrySet()){
					if(entry.getKey().equals("access_uri")){
						data.add(String.valueOf(entry.getValue()));
					}
				}
			}
		}
		return data;
	}

	//创建权限下拉框选择项 
	public List<PmsOption> getPmsSelectOpt(String roleCode) {
		return permissionDao.findPmsCodes(roleCode);
	}

	//查询权限-创建ztree数据
	public String getPmsForTree() {
		List<Permission> list = (List<Permission>) pmsDao.findAll();
		return createPmsTreeData(list);
	}
	
	// 获取权限树的数据 创建菜单
	public String getPmsForMenu() {
		List<String> roles = UserUtil.getUserRoles();
		if(roles.isEmpty()){
			return "[{\"data\":\"no\"}]";
		}
		String str = StringUtil.joinStrForSql(roles);
		String sql = " select a.pms_code,                                                    \n"+
					"        a.pms_name,                                                    \n"+
					" 		 a.parent_code,                                                   \n"+
					" 		 a.access_uri,                                                    \n"+
					" 		 case when b.children is null then 0 else b.children end children \n"+
					" from                                                                  \n"+
					" (                                                                     \n"+
					" 	 select pms_code,                                                   \n"+
					" 	        pms_name,                                                   \n"+
					" 			  parent_code,                                              \n"+
					" 			  access_uri                                                \n"+
					" 	from tb_permission                                                  \n"+
					" 	where pms_code in (                                                 \n"+
					" 					select distinct pms_code                            \n"+
					" 					from tb_role_permission                             \n"+
					" 					where role_code in ("+str+")                        \n"+
					" 				)                                                       \n"+
					"     and pms_status = 1                                                \n"+
					" ) a                                                                   \n"+
					" left join                                                             \n"+
					" (                                                                     \n"+
					" 	select parent_code,                                                 \n"+
					" 	       count(1) children                                            \n"+
					" 	from tb_permission                                                  \n"+
					" 	where parent_code in (                                              \n"+
					" 	                     select distinct pms_code                       \n"+
					" 						 from tb_role_permission                        \n"+
					" 						 where role_code in ("+str+")                   \n"+
					" 						)                                               \n"+
					"     and pms_status = 1                                                \n"+
					" 	group by parent_code                                                \n"+
					" ) b                                                                   \n"+
					" on a.pms_code = b.parent_code                                         \n"; 
		List<Map<String,Object>> data = permissionDao.queryData(sql,new Object[]{});
		return JsonUtil.createJsonForMenu(data);
	}
	
	//创建数据 ztree
	private String createPmsTreeData(List<Permission> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("{id:'0',pId:'',name:'权限菜单',accessUri:'#',nocheck:true, open:true},");
		for(Permission pms : list){
			sb.append("{id:'"+pms.getPmsCode()+"',pId:'"+pms.getParentCode()+"',name:'"+pms.getPmsName()+"',accessUri:'"+pms.getAccessUri()+"', open:true}");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	
	//创建数据 ztree
	private String createPmsTreeDataForChecked(List<PmsOption> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(PmsOption pms : list){
			sb.append("{id:'"+pms.getPmsCode()+"',pId:'"+pms.getParentCode()+"',name:'"+pms.getPmsName()+"',checked:"+pms.checked+", open:true}");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	
	//根据角色code获取ztree数据
	public String getPmsForTree(String roleCode) {
		List<PmsOption> pmsOpts = permissionDao.findPmsCodes(roleCode);
		return createPmsTreeDataForChecked(pmsOpts);
	}

	//删除权限
	public void deletePms(String ids,String pids) {
		List<String> pCodes = new ArrayList<String>();
		List<String> codes = new ArrayList<String>();
		List<String> codesTemp = new ArrayList<String>();
		if(StringUtil.isNoEmpty(pids)){
			for(String s : pids.split(",")){
				pCodes.add(s.trim());
			}
		}
		if(StringUtil.isNoEmpty(ids)){
			for(String s : ids.split(",")){
				codes.add(s.trim());
			}
		}
		if( pCodes != null && pCodes.size() > 0){
			for( String pcode : pCodes){
				// 判断子权限集合是否在要删除的权限集合中 如果都在 就把父权限一块删除
				List<String> temp = permissionDao.getPmsCodeByPid(pcode);
				if(codes.containsAll(temp)){
					codesTemp.add(pcode);// 把父节点放入临时集合中
				}
			}
		}
		codes.addAll(codesTemp);// 把需要删掉的父节点放入删除id集合中
		// 去重
		List<String> delCodes = new ArrayList<String>(new LinkedHashSet<String>(codes));
		permissionDao.deletePermission(delCodes);
		permissionDao.deleteRolePms(delCodes);
		
		initUrlFilterChain();
	}

	//添加权限
	//@CacheEvict(value="shiroCache",allEntries=true)
	public void addPms(String pid, String pmsName, String accessUri) {
		Permission pms = new Permission();
		String pmsCode = createPmsCode(pid);
		pms.setPmsCode(pmsCode);
		pms.setParentCode(pid.trim());
		pms.setPmsName(pmsName.trim());
		pms.setAccessUri(accessUri.trim());
		pms.setPmsStatus(1);
		pms.setCreateTime(DateUtil.currentTimeMillis());
		pms.setCreateUser(UserUtil.getLoginName());
		pmsDao.save(pms);
		
		initUrlFilterChain();
	}

	// 根据父id创建权限id
	private String createPmsCode(String pid) {
		List<String> pmsCodes = permissionDao.getPmsCodeByPid(pid);
		if(pmsCodes == null || pmsCodes.size() <= 0){
			return pid+"_1";
		}
		int max = 0 ;
		for(String code : pmsCodes){
			int index = Integer.valueOf(code.substring(code.lastIndexOf("_")+1));
			if( index > max ){
				max = index;
			}
		}
		return pid+"_"+(max+1);
	}

	//更新权限
	public void updatePms(String id, String pmsName, String accessUri) {
		permissionDao.updatePms(id.trim(),pmsName.trim(),accessUri.trim());
		
		initUrlFilterChain();
	}

	//为user配置角色
	public void updateUserRole(Long id, String[] roleCodes) {
		String sql = " update tb_user set roles = ? where id = ? ";
		permissionDao.update(sql, new Object[]{StringUtil.joinString(roleCodes,","),id});
	}

	//获取所有的权限路径where status=1 and access_uri != '#'
	@Override
	public List<String> getPmsUri() {
		return pmsDao.getPmsUri();
	}
	
	//获取所有的权限路径 where access_uri != '#'
	@Override
	public List<String> getAllPmsUri() {
		return pmsDao.getAllPmsUri();
	}

	//返回json数据的角色
	@Override
	public String getRoleJSON() {
		List<Role> roles = getAllRoles();
		return JsonUtil.createJsonForRols(roles);
	}

	//为权限 分配角色
	@Override
	public void configPmsRoles(String[] roles, String pmsIds) {
		if(pmsIds != null && pmsIds.length() > 0 ){
			String[] pms = pmsIds.split(",");
			for(String roleCode : roles){
				permissionDao.addRolePms(roleCode.trim(), pms);
			}
		}
	}
	
	/**
	 * 调用 shiroFilterChainManager重新初始化URL与权限的对应映射 
	 */
	public void initUrlFilterChain() {
		filterChainDefinitionsService.updatePermission();
	}

	//分页查询角色信息
	@Override
	public String getRoleData(String pageSize, String pageNum, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select"); 
		sb.append(" role_code,");   
		sb.append(" role_name,");   
		sb.append(" role_desc,");
		sb.append(" case when role_status = 1 then '有效' else '失效' end role_status,");
		sb.append(" date_format(create_time,'%Y-%c-%d %h:%i:%s') create_time,");
		sb.append(" create_user ");
		sb.append(" from tb_role limit ?,?");
		
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt from tb_role ");
		
		List<Map<String,Object>> result = permissionDao.queryData( sb.toString(),new Object[]{Integer.valueOf(pageNum),Integer.valueOf(pageSize)});
		List<Map<String,Object>> resultCount = permissionDao.queryData(sbCount.toString(),null);
		int total = Integer.valueOf(String.valueOf(resultCount.get(0).get("cnt")));
		DataBean<Map<String,Object>> data = new DataBean<Map<String,Object>>();
		data.setDraw(Integer.valueOf(draw));
		data.setRecordsTotal(total);
		data.setRecordsFiltered(total);
		data.setData(result);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}

}
