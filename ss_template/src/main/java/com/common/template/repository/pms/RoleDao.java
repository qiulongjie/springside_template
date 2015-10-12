package com.common.template.repository.pms;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.common.template.entity.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long>, JpaSpecificationExecutor<Role> {
	
	@Modifying
	@Query("delete from Role r where r.roleCode=?1")
	void deleteByCode(String roleCode);
	
	@Query("from Role r where r.roleCode=?1")
	Role findRoleByCode(String roleCode);
}
