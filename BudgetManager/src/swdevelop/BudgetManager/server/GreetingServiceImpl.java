package swdevelop.BudgetManager.server;

import swdevelop.BudgetManager.client.GreetingService;
import swdevelop.BudgetManager.shared.FieldVerifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public int greetServer(String username,String password) throws IOException {
		String excelFilePath = "C:\\Users\\Tocki\\workspace\\BudgetManager\\war\\user_database.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        Row row;
        Cell cell;
        /* 
        while (iterator.hasNext()) 
        {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) 
            {
            	int user_id = 0;
                Cell cell = cellIterator.next();
                if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
                {
                	user_id=(int) cell.getNumericCellValue();
                }
                if(cell.toString().equalsIgnoreCase(username))
                {
                	if(cellIterator.hasNext()) //in case of it is null
                	{
	                	if(cellIterator.next().toString().equalsIgnoreCase(password))
	                	{
	                		return user_id;
	                	}
                	}
                }
            }
        }
        */
        for(int r = 0; r < firstSheet.getPhysicalNumberOfRows(); r++) {
            row = firstSheet.getRow(r);
            if(row != null) 
            {
            	int user_id=0;
                for(int c = 0; c < firstSheet.getRow(0).getPhysicalNumberOfCells(); c++) 
                {
                    cell = row.getCell((short)c);
                    if(cell != null) 
                    {
                    	if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
                        {
                        	user_id=(int) cell.getNumericCellValue();
                        }
                    	if(cell.toString().equalsIgnoreCase(username))
                    	{
                    		//a cell to the right
                    		//if the username matches
                    		//we go to the next cell to the right
                    		//to se if the password matches
                    		if(row.getCell(c+1).toString().equalsIgnoreCase(password))
                    		{
                    			return user_id;
                    		}
                    	}
                    }
                }
            }
        }
        workbook.close();
		//throw new IllegalArgumentException("Name must be at least 4 characters long");
		return 0;
	}
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	@Override
	public boolean registerUser(String username, String password) throws IOException {
		String excelFilePath = "C:\\Users\\Tocki\\workspace\\BudgetManager\\war\\user_database.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);       
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        Cell cell;
        for(int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
            row = sheet.getRow(r);
            if(row != null) {
                for(int c = 1; c < 2; c++) {
                    cell = row.getCell((short)c);
                    if(cell != null) {
                       if(cell.toString().equalsIgnoreCase(username))
                       {
                    	   return false;
                       }
                    }
                }
            }
        }
        Object[][] bookData = {
        		{username, password}
        };
 
        int rowCount = sheet.getLastRowNum();
 
        for (Object[] aBook : bookData) {
	        Row row1 = sheet.createRow(++rowCount);
	 
	        int columnCount = 0;
	                 
	        Cell cell1 = row1.createCell(columnCount);
	        cell1.setCellValue(rowCount);
	                 
	        for (Object field : aBook) {
	        	cell = row1.createCell(++columnCount);
	            if (field instanceof String) {
	            	cell.setCellValue((String) field);
	            }
	        }
 
        }
 
        inputStream.close();
 
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Tocki\\workspace\\BudgetManager\\war\\user_database.xlsx");
        workbook.write(outputStream);
         workbook.close();
        outputStream.close();
		return true;
	}
}
