package com.common.template.service.kpi;

/**
 * pv uv 业务类
 * @author qiulongjie
 *
 */
public interface PVUVService {

	/**
	 * 获取pvuv数据
	 * @param period 周期
	 * @param date 时间
	 * @return
	 */
	public String getPVUVData(String period,String date);
}
