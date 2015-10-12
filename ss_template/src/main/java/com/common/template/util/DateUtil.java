package com.common.template.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author qiulongjie
 *
 */
public final class DateUtil {
	/** 日期格式 yyyyMMdd **/
	public static final String YYYYMMDD = "yyyyMMdd";

	/** 日期格式 yyyy-MM-dd **/
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	/** 日期格式 yyyy-MM-dd HH:mm:ss **/
	public static final String YYYY_MM_DD_HH_MM_SS = YYYY_MM_DD + " HH:mm:ss";

	/** 日期格式 yyyyMMddHHmmss **/
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * 获取现时
	 */
	public static Date currentTimeMillis() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 格式化日期  yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		return new SimpleDateFormat(YYYYMMDD).format(date);
	}
	
	/**
	 * 格式化日期  yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatDate2(Date date){
		return new SimpleDateFormat(YYYY_MM_DD).format(date);
	}
	
	/**
	 * 格式化日期  yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date){
		return new SimpleDateFormat(YYYYMMDDHHMMSS).format(date);
	}
	
	/**
	 * 格式化日期  yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDateTime2(Date date){
		return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(date);
	}
}
