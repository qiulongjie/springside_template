package com.common.template.service.income;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.template.entity.DataBean;
import com.common.template.repository.income.IncomeDao;
import com.common.template.util.RowAndCellUtil;
import com.common.template.util.StringUtil;
import com.google.gson.Gson;

@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {
	
	/**
	 * 线程池
	 */
	@Autowired
	private TaskExecutor taskExecutor ;
	
	@Resource
	private IncomeDao incomeDao;

	@Override
	public int uploadUpstreamData(InputStream in,boolean isExcel2003) throws Exception {
		String sql = "insert into tb_upstream_fee\n"+ 
					"(statis_month,             \n"+
					" sige_company,             \n"+
					" advertiser,               \n"+
					" b_type,                   \n"+
					" business,                 \n"+
					" info_fee,                 \n"+
					" unit_price,               \n"+
					" final_amount,             \n"+
					" remark)                   \n"+
					" values(?,?,?,?,?,?,?,?,?) \n";
		
		return readAndSaveData(in, sql,isExcel2003);
	}
	
	@Override
	public int uploadChannelData(InputStream in,boolean isExcel2003) throws Exception {
		String sql = "insert into tb_channel_fee\n"+ 
				"(statis_month,             \n"+
				" sige_company,             \n"+
				" channel_company,          \n"+
				" b_type,                   \n"+
				" business,                 \n"+
				" info_fee,                 \n"+
				" unit_price,               \n"+
				" final_amount,             \n"+
				" remark)                   \n"+
				" values(?,?,?,?,?,?,?,?,?) \n";
		
		return readAndSaveData(in, sql,isExcel2003);
	}

	/**
	 * 读取excel数据并保存
	 * @param in
	 * @param sql
	 * @throws IOException
	 */
	private int readAndSaveData(InputStream in, String sql,boolean isExcel2003) throws IOException {
		int dataCount = 0;
		
		List<Map<Integer, String>> list = null;
		Map<Integer, String> map = null;
		
		//将输入流转换成HSSFWorkbook对象进行处理
//		HSSFWorkbook workbook = null;
//		try {
//			workbook = new HSSFWorkbook(in);
//			//org.apache.poi.ss.usermodel.Workbook w = new XSSFWorkbook(in);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		if(isExcel2003) {
			workbook = new HSSFWorkbook(in);//2003版本
		} else{
			workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook(in);//2007版本
		}
		//HSSFWorkbook workbook = new HSSFWorkbook(in);
		int sheetsCount = workbook.getNumberOfSheets();
		// 遍历所有sheet
		list = new ArrayList<Map<Integer,String>>();
		for(int sheetNum = 0 ; sheetNum < sheetsCount ; sheetNum++ ){
			//HSSFSheet sheet = workbook.getSheetAt(sheetNum);
			Sheet sheet = workbook.getSheetAt(sheetNum);
			// 如果当前sheet页没有数据  第一行为标题
			if(sheet.getLastRowNum() <= 1){
				continue;
			}
			//遍历所有行
			//从第二行开始，第一行为标题
			int rowCount = sheet.getLastRowNum();
			int cellCount = sheet.getRow(0).getLastCellNum();
			for(int rowNum=1; rowNum <= rowCount; rowNum++){
				//HSSFRow rowline = sheet.getRow(rowNum);
				Row rowline = sheet.getRow(rowNum);
				
				// 如果此行是空行
				if(rowline==null || RowAndCellUtil.isAllEmpty(rowline)){
					System.out.println(sheetNum+"sheet页"+rowNum+"行是空行");
					continue;
				}
				//遍历每一行的每一个单元格
				//HSSFCell cell = null;
				Cell cell = null;
				map = new HashMap<Integer, String>(cellCount);
				for(int i=0; i < cellCount ; i++){
					cell = rowline.getCell(i);
					String cellvalue = RowAndCellUtil.getCellContent(cell);
					if(i == 0){
						int indexof = cellvalue.lastIndexOf(".");
						if( indexof > -1){
							cellvalue = cellvalue.substring(0, cellvalue.lastIndexOf("."));
						}
					}
					map.put(i, cellvalue);
				}
				list.add(map);
				
				// 读够50行就把数据插入到数据库一次
				if( list.size() >= 50 ){
					// 更新到数据库
					int row[] = incomeDao.batchUpdate(sql, list);
					// 计算更新总数
					dataCount += row.length;
					//清空list
					list.clear();
				}
			}// end 遍历所有行
			
		}// end sheet遍历
		if( !list.isEmpty() ){
			// 更新到数据库
			int row[] = incomeDao.batchUpdate(sql, list);
			// 计算更新总数
			dataCount += row.length;
			//清空list
			list.clear();
		}
		
		return dataCount;
	}

	//查询上游数据
	@Override
	public String queryUpstreamData(String pageNum, String pageSize, String startMonth, String endMonth, String company, String advertiser,String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select statis_month,");                                                      
		sb.append(" sige_company,");                                                      
		sb.append(" advertiser,");                                                      
		sb.append(" b_type,");                                                      
		sb.append(" business,");                                                      
		sb.append(" info_fee,");                                                      
		sb.append(" unit_price,");                                                      
		sb.append(" final_amount,");                                                      
		sb.append(" remark"); 
		sb.append(" from tb_upstream_fee where 1=1 "); 
		
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt from tb_upstream_fee where 1=1 ");
		
		if(StringUtil.isNoEmpty(startMonth)){
			sb.append(" and statis_month >= '"+startMonth+"' ");
			sbCount.append(" and statis_month >= '"+startMonth+"' ");
		}
		if(StringUtil.isNoEmpty(endMonth)){
			sb.append(" and statis_month <= '"+endMonth+"' ");
			sbCount.append(" and statis_month <= '"+endMonth+"' ");
		}
		if(StringUtil.isNoEmpty(company)){
			sb.append(" and sige_company like '%"+company+"%' ");
			sbCount.append(" and sige_company like '%"+company+"%' ");
		}
		if(StringUtil.isNoEmpty(advertiser)){
			sb.append(" and advertiser like '%"+advertiser+"%' ");
			sbCount.append(" and advertiser like '%"+advertiser+"%' ");
		}
		
		sb.append(" limit ?,? ");
		List<Map<String,Object>> result = incomeDao.queryData(sb.toString(),new Object[]{Integer.valueOf(pageNum),Integer.valueOf(pageSize)});
		List<Map<String,Object>> resultCount = incomeDao.queryData(sbCount.toString(),null);
		int total = Integer.valueOf(String.valueOf(resultCount.get(0).get("cnt")));
		DataBean<Map<String,Object>> data = new DataBean<Map<String,Object>>();
		data.setDraw(Integer.valueOf(draw));
		data.setRecordsTotal(total);
		data.setRecordsFiltered(total);
		data.setData(result);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}

	//查询渠道数据
	@Override
	public String queryChannelData(String pageNum, String pageSize, String startMonth, String endMonth, String company,String channelCompany, String draw) {
		StringBuffer sb = new StringBuffer();
		sb.append("select statis_month,");                                                      
		sb.append(" sige_company,");                                                      
		sb.append(" channel_company,");                                                      
		sb.append(" b_type,");                                                      
		sb.append(" business,");                                                      
		sb.append(" info_fee,");                                                      
		sb.append(" unit_price,");                                                      
		sb.append(" final_amount,");                                                      
		sb.append(" remark"); 
		sb.append(" from tb_channel_fee where 1=1 "); 
		
		StringBuffer sbCount = new StringBuffer();
		sbCount.append(" select count(1) cnt from tb_channel_fee where 1=1 ");
		
		if(StringUtil.isNoEmpty(startMonth)){
			sb.append(" and statis_month >= '"+startMonth+"' ");
			sbCount.append(" and statis_month >= '"+startMonth+"' ");
		}
		if(StringUtil.isNoEmpty(endMonth)){
			sb.append(" and statis_month <= '"+endMonth+"' ");
			sbCount.append(" and statis_month <= '"+endMonth+"' ");
		}
		if(StringUtil.isNoEmpty(company)){
			sb.append(" and sige_company like '%"+company+"%' ");
			sbCount.append(" and sige_company like '%"+company+"%' ");
		}
		if(StringUtil.isNoEmpty(channelCompany)){
			sb.append(" and channel_company like '%"+channelCompany+"%' ");
			sbCount.append(" and channel_company like '%"+channelCompany+"%' ");
		}
		
		sb.append(" limit ?,? ");
		List<Map<String,Object>> result = incomeDao.queryData(sb.toString(),new Object[]{Integer.valueOf(pageNum),Integer.valueOf(pageSize)});
		List<Map<String,Object>> resultCount = incomeDao.queryData(sbCount.toString(),null);
		int total = Integer.valueOf(String.valueOf(resultCount.get(0).get("cnt")));
		DataBean<Map<String,Object>> data = new DataBean<Map<String,Object>>();
		data.setDraw(Integer.valueOf(draw));
		data.setRecordsTotal(total);
		data.setRecordsFiltered(total);
		data.setData(result);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}

}
