package com.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	

	Workbook wb;
	
	public ExcelFileUtil() throws Exception	{
			FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"\\TestInput\\OHRMS_Input.xlsx");
		    wb=new XSSFWorkbook(fi);
	}
	
	public int rowCount(String sheetname) {
		   return wb.getSheet(sheetname).getLastRowNum();
	}
	
	public int colCount(String sheetname){
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	
	public String getData(String sheetname,int row,int column) {
		
		String data="";
		
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {	
			
			if (HSSFDateUtil.isCellDateFormatted(wb.getSheet(sheetname).getRow(row).getCell(column))) {
//					data=wb.getSheet(sheetname).getRow(row).getCell(column).getDateCellValue().toString();				
				    Date celldate=wb.getSheet(sheetname).getRow(row).getCell(column).getDateCellValue();			
//					Date date=new Date();				
					SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/YYYY");
					data=sdf.format(celldate);
					
			}else {
					int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
					data=String.valueOf(celldata);
			}
			
		}else if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_STRING){
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		
		return data;	
	}
	
	public void setData(String sheetname,int row,int column,String status) throws Exception {
		
		Cell cell=wb.getSheet(sheetname).getRow(row).createCell(column);
		cell.setCellValue(status);		
		
		if(status.equalsIgnoreCase("Pass")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
		}else if(status.equalsIgnoreCase("Fail")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
		}else if(status.equalsIgnoreCase("Not Executed")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
		}		
		
		FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"\\TestOutput\\output.xlsx");
		wb.write(fos);
		fos.close();
		
	}
	

}
