package com.common.template.repository.income;

import java.util.List;
import java.util.Map;

public interface IncomeDao {


	/**
	 * 批量更新
	 * @param sql
	 * @param params
	 * @return
	 */
	int[] batchUpdate(String sql , List<Map<Integer, String>> params);
	
	/**
	 * 查询数据
	 * @param sql
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryData(String sql,Object[] params);
}
