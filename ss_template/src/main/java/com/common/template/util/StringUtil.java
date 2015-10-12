package com.common.template.util;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 字符串工具类
 * @author qiulongjie
 *
 */
public class StringUtil {

	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNoEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}

	/**
	 * 组装where in条件的字符串
	 * @param string
	 * @return
	 */
	public static String joinStrForSql(List<String> list) {
		if( list != null && list.size() > 0 ){
			StringBuffer sb = new StringBuffer();
			for( String str : list ){
				sb.append("'").append(str).append("',");
			}
			sb.deleteCharAt(sb.length()-1);
			return sb.toString();
		}
		return "''";
	}

	/**
	 * 链接字符串
	 * @param string
	 * @return
	 */
	public static Object joinString(String[] arr , String joinStr) {
		if( arr == null || arr.length <= 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(String str : arr){
			sb.append(str).append(joinStr);
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	/**
	 * 把double转成string 如果double的小数为零则转成不带小数的string
	 * @param d
	 * @return
	 */
	public static String doubleToStr(double d){
		long l = new Long((long)d);
		if((d-l) > 0){
			return String.valueOf(d);
		}
		return String.valueOf(l);
	}
	
	/**
	 * 保留两位小数
	 * @param d
	 * @return
	 */
	public static String double2Format(double d){
		DecimalFormat df=new DecimalFormat("0.00");
		return df.format(d);
	}
	
	/**
	 * 判断文件名是不是excel文件名
	 * @param fileName
	 * @return
	 */
	public static boolean isExcelFileName(String fileName){
		if(!isNoEmpty(fileName)){
			return false;
		}
		if( fileName.trim().endsWith(".xls") || 
			fileName.trim().endsWith(".XLS") ||
			fileName.trim().endsWith(".xlsx") ||
			fileName.trim().endsWith(".XLSX") 
		  ){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为2003版本的excel
	 * @param fileName
	 * @return
	 */
	public static boolean isExcel2003File(String fileName){
		if(!isNoEmpty(fileName)){
			return false;
		}
		if( fileName.trim().endsWith(".xls") || fileName.trim().endsWith(".XLS")){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为2007版本的excel
	 * @param fileName
	 * @return
	 */
	public static boolean isExcel2007File(String fileName){
		if(!isNoEmpty(fileName)){
			return false;
		}
		if(fileName.trim().endsWith(".xlsx") || fileName.trim().endsWith(".XLSX") ){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String cellvalue = "201401010.00";
		System.out.println(cellvalue.substring(0, cellvalue.lastIndexOf(".")));
	}
}
