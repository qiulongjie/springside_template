package com.common.template.service.income;

import java.io.InputStream;

public interface IncomeService {

	/**
	 * 上传上游数据文件
	 * 
	 * @param in
	 * @param isExcel2003 
	 */
	int uploadUpstreamData(InputStream in, boolean isExcel2003) throws Exception;

	/**
	 * 上传渠道数据
	 * 
	 * @param in
	 * @param isExcel2003 
	 * @throws Exception
	 */
	int uploadChannelData(InputStream in, boolean isExcel2003) throws Exception;

	/**
	 * 查询上游数据
	 * 
	 * @param pageNum
	 *            当前页 从0开始
	 * @param pageSize
	 * @param startMonth
	 * @param endMonth
	 * @param company
	 * @param draw
	 * @return
	 */
	String queryUpstreamData(String pageNum, String pageSize, String startMonth, String endMonth, String company,
			String advertiser,String draw);

	/**
	 * 查询渠道数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param startMonth
	 * @param endMonth
	 * @param company
	 * @param draw
	 * @return
	 */
	String queryChannelData(String pageNum, String pageSize, String startMonth, String endMonth, String company,
			String channelCompany,String draw);

}
