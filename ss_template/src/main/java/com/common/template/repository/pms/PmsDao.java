package com.common.template.repository.pms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.common.template.entity.Permission;

public interface PmsDao extends PagingAndSortingRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
	
	@Query("select accessUri from Permission p where p.pmsStatus=1 and p.accessUri != '#'")
	List<String> getPmsUri();
	
	@Query("select accessUri from Permission p where p.accessUri != '#'")
	List<String> getAllPmsUri();
}
