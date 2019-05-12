package utility;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class XlsDP 
{
	public static String[][] getDataFromXLSUsingJXL(String xlFilePath, String sheetName, String tableName)
	{
		String[][] tabArray=null;
	   try
	    {
		   System.out.println("File Path is " + xlFilePath + " Sheet Name " + sheetName);
	          Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
	         System.out.println(" Workbook open "+workbook);
	          Sheet sheet = workbook.getSheet(sheetName);
	          System.out.println(" Sheet open "+sheet);
	          
	          Cell tableStart=sheet.findCell(tableName);
	          
	          int startRow,startCol, endRow, endCol,ci,cj;
	          
	          startRow=tableStart.getRow();
	          startCol=tableStart.getColumn();

	          Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

	          endRow=tableEnd.getRow();
	          endCol=tableEnd.getColumn();
	          System.out.println("startRow="+startRow+", endRow="+endRow+", " +
	                  "startCol="+startCol+", endCol="+endCol);
	          tabArray=new String[endRow-startRow-1][endCol-startCol-1];
	          ci=0; 
	          
	          for (int i=startRow+1;i<endRow;i++,ci++)
	          {
	              cj=0;
	              for (int j=startCol+1;j<endCol;j++,cj++)
	              {
	                  tabArray[ci][cj]=sheet.getCell(j,i).getContents();
	              }
	          }
	    }     
	    catch (Exception e)    
	    {
	       System.out.println("Please check if file path, sheet name and tag name are correct");
	    }

	    return(tabArray);
	}
	
}	


