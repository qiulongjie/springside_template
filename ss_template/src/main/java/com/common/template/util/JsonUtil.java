package com.common.template.util;

import java.util.List;
import java.util.Map;

import com.common.template.entity.Role;

/**
 * json工具类
 * @author qiulongjie
 *
 */
public final class JsonUtil {

	/**
	 * 创建json数据 -- to 创建权限菜单
	 * @param data
	 * @return
	 */
	public static String createJsonForMenu(List<Map<String, Object>> data) {
		if( data != null && data.size() > 0 ){
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for(Map<String,Object> map : data){
				sb.append("{");
				for(Map.Entry<String, Object> entry : map.entrySet()){
					String key = entry.getKey();
					Object value = entry.getValue();
					sb.append("\"").append(key).append("\":\"").append(value).append("\",");
				}
				sb.deleteCharAt(sb.length()-1);
				sb.append("},");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			return sb.toString();
		}
		return "[{\"data\":\"no\"}]";
	}
	
	/**
	 * 创建json数据 -- 角色
	 * @param data
	 * @return
	 */
	public static String createJsonForRols(List<Role> roles) {
		if( roles != null && roles.size() > 0 ){
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for(Role role : roles){
				sb.append("{");
				sb.append("\"role_code\":\"").append(role.getRoleCode()).append("\",");
				sb.append("\"role_name\":\"").append(role.getRoleName()).append("\"");
				sb.append("},");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			return sb.toString();
		}
		return "[{\"data\":\"no\"}]";
	}

	
}
