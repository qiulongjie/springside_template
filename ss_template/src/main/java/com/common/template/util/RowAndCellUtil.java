package com.common.template.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * excel 操作工具类
 * @author qiulongjie
 *
 */
public class RowAndCellUtil {
	
	/**
	 * 判断一行中，所有单元格内容是否都为空 如果为空返回真，否则返回假
	 * 
	 * @param rowline
	 * @return
	 */
	public static boolean isAllEmpty(Row rowline) {

		boolean flag = false;
		int count = 0;
		for (int i = 0; i < rowline.getLastCellNum(); i++) {
			//HSSFCell cell = rowline.getCell(i);
			Cell cell = rowline.getCell(i);
			String valuecell = getCellContent(cell).trim();
			if (valuecell.equals("")) {
				count++;
			}
		}
		if (count == rowline.getLastCellNum()) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 读取单元格中内容
	 * 
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCellContent(Cell cell) {
		String cellvalue = "";

		// 读取每个单元格的内容
		if (cell != null) {
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMBERIC
			case HSSFCell.CELL_TYPE_NUMERIC: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果Data类型，取得该Cell的Date值
					Date date = cell.getDateCellValue();
					// 把Date转换字符串
					//cellvalue = DateUtil.formatDateTime(date).trim();
					cellvalue = new SimpleDateFormat("yyyyMM").format(date);
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的值
					cellvalue = StringUtil.double2Format(cell.getNumericCellValue());
				}
				break;
			}
			
			case HSSFCell.CELL_TYPE_FORMULA: // 公式
				try {  
					cellvalue = StringUtil.double2Format(cell.getNumericCellValue());  
				} catch (IllegalStateException e) {  
					cellvalue = String.valueOf(cell.getStringCellValue());  
				}  
				break;

			// 如果当前Cell的Type为String
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getStringCellValue();
				break;
				
			case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
				cellvalue = cell.getBooleanCellValue() + "";
				break;
				
			// 默认的Cell值
			default:
				cellvalue = "";
			}
		}
		// 如果为空
		else {
			cellvalue = "";
		}

		return cellvalue.equals("") ? " " : cellvalue;
	}
	
}