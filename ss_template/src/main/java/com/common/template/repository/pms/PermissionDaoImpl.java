package com.common.template.repository.pms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.common.template.entity.Permission;
import com.common.template.entity.PmsOption;
import com.common.template.entity.Role;
import com.common.template.util.StringUtil;

/**
 * 权限持久层      写得比较乱啊  一开始就没有好好设计囫囵瞎写求速度造成很多重复代码 烦死  
 * @author qiulongjie
 *
 */
@Repository("permissionDao")
public class PermissionDaoImpl implements PermissionDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Role> getAllRoles() {
		String sql = " select role_id ,          \n"+
					" role_code      ,          \n"+
					" role_name      ,          \n"+
					" role_desc      ,          \n"+
					" role_status    ,          \n"+
					" create_time    ,          \n"+
					" create_user from tb_roles \n";
		return jdbcTemplate.query(sql, new RowMapper<Role>(){

			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role role = new Role();
				role.setId(rs.getLong("role_id"));
				role.setRoleCode(rs.getString("role_code"));
				role.setRoleName(rs.getString("role_name"));
				role.setRoleDesc(rs.getString("role_desc"));
				role.setRoleStatus(rs.getInt("role_status"));
				role.setCreateTime(rs.getTimestamp("create_time"));
				role.setRoleCode(rs.getString("create_user"));
				return role;
			}
			
		});
	}
	
	// 配置权限 
	public void addRolePms(final String roleCode, final String[] pms) {
		String sql = " insert into tb_role_permission(role_code,pms_code)                                         \n"+
					" select ?,? from dual                                                                       \n"+
					" where not exists (select id from tb_role_permission where role_code = ? and pms_code = ? ) \n"; 
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, roleCode);
				ps.setString(2, pms[i]);
				ps.setString(3, roleCode);
				ps.setString(4, pms[i]);
			}
			
			public int getBatchSize() {
				return pms.length;
			}
		});
	}
	
	// 查找权限根据角色code
	public List<PmsOption> findPmsCodes(String roleCode) {
		String sql = " select distinct a.pms_code pms_code,"
				+ "           a.parent_code parent_code, "
				+ "           a.pms_name pms_name,"
				+ "           case when b.pms_code is null then 'false' else 'true' end checked, "
				+ "           b.pms_code code "
				+ "   from tb_permission a \n"+
					" left join                                                           \n"+
					" (select pms_code from tb_role_permission where role_code = ? ) b    \n"+
					" on a.pms_code = b.pms_code                                          \n";
		return jdbcTemplate.query(sql, new Object[] {roleCode}, new RowMapper<PmsOption>() {

			public PmsOption mapRow(ResultSet rs, int rowNum) throws SQLException {
				PmsOption option = new PmsOption();
				option.pmsCode = rs.getString("pms_code");
				option.pmsName = rs.getString("pms_name");
				option.parentCode = rs.getString("parent_code");
				option.checked = rs.getString("checked");
				option.selected = StringUtil.isNoEmpty(rs.getString("code"))?"selected":"";
				return option;
			}
		});
	}

	public void addRole(Role role) {
		
	}

	public void updateRole(Role role) {
		
	}

	public void deleteRole(String roleCode) {
		
	}

	public List<Permission> getPermissionsByRoles(List<Role> roles) {
		return null;
	}

	public void addPermission(Permission permission) {
		
	}

	public void updatePermission(Permission permission) {
		
	}

	public void deletePermission(String pmsCode) {
		
	}
	
	//获取全部权限
	public List<Permission> getAllPms() {
		return null;
	}

	//根据父权限id获取子权限
	public List<String> getPmsCodeByPid(String pid) {
		String sql = " select pms_code from tb_permission where parent_code = ?";
		return jdbcTemplate.query(sql, new Object[]{pid}, new RowMapper<String>(){

			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}});
	}

	// 更新
	public void updatePms(String id, String pmsName, String accessUri) {
		String sql = " update tb_permission set pms_name = ? , access_uri=? where pms_code = ? ";
		jdbcTemplate.update(sql, new Object[]{pmsName,accessUri,id});
	}

	// 删除权限
	public void deletePermission(final List<String> delCodes) {
		String sql = " delete from tb_permission where pms_code = ? ";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, delCodes.get(i));
			}
			
			public int getBatchSize() {
				return delCodes.size();
			}
		});
	}
	
	// 删除权限角色关系
	public void deleteRolePms(final List<String> delPmsCodes) {
		String sql = " delete from tb_role_permission where pms_code = ? ";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, delPmsCodes.get(i));
			}
			
			public int getBatchSize() {
				return delPmsCodes.size();
			}
		});
	}

	//查询数据 返回listMap
	public List<Map<String, Object>> queryData(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public void deleteRolePmsByRoleCode(String roleCode) {
		String sql = " delete from tb_role_permission where role_code = ? ";
		jdbcTemplate.update(sql, new Object[]{roleCode});
	}

	//更新操作
	@Override
	public int update(String sql, Object[] params) {
		return jdbcTemplate.update(sql,params);
	}

}
