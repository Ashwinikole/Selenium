package com.Datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelsheet_getdata {

	public String getadatafromSheet(String Sheetname, int row, int cell)
	{
		String returnvalue = null;
		
		try {
			FileInputStream fs = new FileInputStream("C:\\Users\\Arnav\\Desktop\\selenium\\SpeedwayTech Automation testing class\\documents\\Selenium\\DataDrivensheets\\Login_password_saucedemo.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		  Sheet s= wb.getSheet(Sheetname);   
		  Row r = s.getRow(row);
		  Cell c =r.getCell(cell) ;   
		  returnvalue = c.getStringCellValue();
		// System.out.println(returnvalue);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnvalue;
	}
}
