package com.common.template.shiro;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.common.template.service.pms.PermissionService;

/**
 * 
 * 加载第三方角色资源配置服务类
 * 
 * @author qiulongjie
 * 
 */
public class SimpleFilterChainDefinitionsService extends AbstractFilterChainDefinitionsService {
	
	@Resource
	private PermissionService pmsService;
	
	@Override
	public Map<String, String> initOtherPermission() {
		// extend to load other permission
		List<String> urls = pmsService.getAllPmsUri();
		Map<String,String> map = new HashMap<String, String>();
		if( urls != null && urls.size() > 0){
			for( String s : urls ){
				map.put(s+"/**", MessageFormat.format(PREMISSION_STRING,s));
			}
		}
		return map;
	}

}