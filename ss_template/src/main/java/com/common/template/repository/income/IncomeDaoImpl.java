package com.common.template.repository.income;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.template.repository.BaseDao;

@Repository
public class IncomeDaoImpl extends BaseDao implements IncomeDao {

	@Override
	public int[] batchUpdate(String sql, final List<Map<Integer, String>> params) {
		return super.batchUpdate(sql, params);
	}
	
	// 查询数据
	@Override
	public List<Map<String,Object>> queryData(String sql,Object[] params){
		return queryForListMap(sql, params);
	}

}
