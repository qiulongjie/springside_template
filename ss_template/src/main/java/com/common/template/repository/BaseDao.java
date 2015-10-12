package com.common.template.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 更新
	 * @param jdbcTemplate
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object[] params) {
		return jdbcTemplate.update(sql,params);
	}
	
	/**
	 * 批量更新  List<Map<Integer, String>>中的integer类型key必须从0按顺序递增 
	 * @param jdbcTemplate
	 * @param sql
	 * @param params 
	 * @return
	 */
	public int[] batchUpdate(String sql, final List<Map<Integer, String>> params) {
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Map<Integer, String> map = params.get(i);
				int len = map.size();
				for(int index = 0 ; index < len ; index++){
					ps.setString(index+1, map.get(index));
				}
			}
			
			@Override
			public int getBatchSize() {
				return params.size();
			}
		});
	}
	
	/**
	 * 查询数据
	 * @param jdbcTemplate
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryForListMap(String sql,Object[] params){
		return jdbcTemplate.queryForList(sql, params);
	}
}
