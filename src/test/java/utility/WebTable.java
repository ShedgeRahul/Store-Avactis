package utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class WebTable 
{
	private WebElement webTable;
	private String tableID;
	
	public WebTable(WebElement webTable) 
		{
		  String tagName = webTable.getTagName();

		    if (null == tagName || !"table".equals(tagName.toLowerCase())) 
		    {
		      throw new UnexpectedTagNameException("table", tagName);
		    }

		    this.webTable = webTable;
		    tableID = webTable.getAttribute("id"); 
		    System.out.println("tableID - "+tableID);
		}

	public WebElement get_webTable() 
	{
		return webTable;
	}

	public void set_webTable(WebElement webTable) {
		this.webTable = webTable;
	}
	
	public List<WebElement> getAllDataRows() {		
		return webTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));		
	}

	public int getRowCount() {
	    List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
	    return tableRows.size();
	}
	
	public int getDataRowCount() {
	    WebElement tbody = webTable.findElement(By.tagName("tbody"));
	    List<WebElement> dataRows = tbody.findElements(By.tagName("tr"));
	    return dataRows.size();
	}
	
	public int getColumnCount() {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		WebElement headerRow = tableRows.get(0);
		List<WebElement> tableCols = headerRow.findElements(By.tagName("td"));
		return tableCols.size();
	}
	
	
	//table.getCellData(2,2)
	public String getCellData(int rowIdx, int colIdx) {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		WebElement currentRow = tableRows.get(rowIdx-1);
		List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
		WebElement cell = tableCols.get(colIdx-1);
		return cell.getText();		
	}


	public String getData(String searchValue, String getValueOfColumn)
	{
		WebElement eachCell;
		int i,j = 0;
		int detailsColumnPosition = 0;
		boolean flag = false;
		System.out.println("using tag Name********************************************");
		List <WebElement> allRows = webTable.findElements(By.tagName("//tbody//tr"));
		//List <WebElement> allRows = webTable.findElements(By.xpath("//table[@id='"+tableID+"']//tbody//tr"));
	//	List <WebElement> allRows = webTable.findElements(By.xpath("//table[@id='datatable_orders']//tbody//tr")); //getting all rows from tbody****
	int	rowSize = allRows.size();
	System.out.println("***********************rowSize "+rowSize);	
	
	//WebElement tHead = webTable.findElement(By.tagName("//table[@id='datatable_orders']//thead//th"));
	
	List<WebElement> allColumns = webTable.findElements(By.xpath("//table[@id='"+tableID+"']//thead//th"));
//	List<WebElement> allColumns = webTable.findElements(By.xpath("//table[@id='datatable_orders']//thead//th"));  // Getting table headers from thead****
	int columnSize = allColumns.size();
	System.out.println("columnSize - "+columnSize);
		
		boolean flag1 = false;
		for(WebElement eachCol : allColumns)
		{
			detailsColumnPosition = detailsColumnPosition+1;
			System.out.println(" "+eachCol.getText());
			if(eachCol.getText().equalsIgnoreCase(getValueOfColumn))
			{
				flag1 = true;
				System.out.println("column whoes value need to fetch found at position - "+detailsColumnPosition);
				break;
			}
			if(flag1==true)
				break;			
		}
		
		for(i=1; i<=rowSize; i++)
		{
			for(j=1; j<columnSize; j++)
			{
			eachCell = webTable.findElement(By.xpath("tbody//tr["+i+"]//td["+j+"]"));
			System.out.println(" "+eachCell.getText());
			String cellData = eachCell.getText();
			if(cellData.equals(searchValue))
				{
					flag = true;
					System.out.println("Order ID found at row - "+i+"  and column at - "+j);
					break;
				}
			}
		if(flag==true)
				break;
		}
		
		
		System.out.println("i value - "+i);
		
		System.out.println("j value - "+j);
		
		List<WebElement> allCellsOfOneRow = webTable.findElements(By.xpath("//table[@id='"+tableID+"']//tbody//tr[1]//td"));
	//	List<WebElement> allCellsOfOneRow = webTable.findElements(By.xpath("//table[@id='datatable_orders']//tbody//tr[1]//td"));   //Geting value of each cell****
		System.out.println("number of Cells found in serchValue row - "+allCellsOfOneRow.size());
		
		for(WebElement oneCell: allCellsOfOneRow)
		{
			System.out.println(" "+oneCell.getText());
		}
	
		System.out.println("Column whoes value is to get is at "+detailsColumnPosition+"th position column");
		
		String value = webTable.findElement(By.xpath("//tbody//tr["+i+"]//td["+detailsColumnPosition+"]")).getText();
		return value;
	}
	
		
}
