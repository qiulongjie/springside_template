package com.common.template.service.kpi.impl;

import org.springframework.stereotype.Service;

import com.common.template.service.kpi.PVUVService;


@Service("pvuvService")
public class PVUVServiceImpl implements PVUVService {

	//获取pvuv数据
	public String getPVUVData(String period, String date) {
		// 模拟数据
		StringBuffer sb = new StringBuffer();
		sb.append("xx test");
		
		String string = "{\"series\": {                                                                          "+
				"    \"data1\": \"2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3\", "+
				"    \"data2\": \"2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3\","
				+ "  \"data3\": \"3.6, 8.9, 10.0, 26.4, 58.7, 40.7, 155.6, 102.2, 38.7, 19.8, 5.0, 4.3\"  "+
				"},                                                                                      "+
				"\"xAxis\": \"1月,2月,3月,4月,5月,6月,7月,8月,9月,10月,11月,12月\",                      "+
				"\"legend\": \"pv,uv,xx\"}";
		return string;
	}

}
