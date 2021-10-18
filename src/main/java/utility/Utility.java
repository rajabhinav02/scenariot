package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {

	FileInputStream fis;
	XSSFWorkbook wb;
	File src;
	
	public Utility(String path) throws IOException {
		src = new File (path);
		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
	}
	
//	public void updateexcel(String sheetname, int rowcount, int columncount) throws IOException {
//		
//		String path = System.getProperty("user.dir")+"\\ExcelData.xlsx";
//		FileInputStream fis = new FileInputStream(path);
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
//		
//		int sheetnumbers= wb.getNumberOfSheets();
//		Set<String> sn = new HashSet<>();
//		for (int i=0; i<sheetnumbers; i++) {
//		String sheetn=	wb.getSheetAt(i).getSheetName(); 
//		sn.add(sheetn);
//		if (!sn.contains(sheetname)) {
//			XSSFSheet sheet=wb.createSheet(sheetname);
//			for (int k=0; k<columncount; k++) {
//			sheet.createRow(1).createCell(k);
//			
//			}
//		}
//			}
//		
//		
//		}
		
		
	public Object[][] getArrayofinput(String sheetname) {
		XSSFSheet sheet=wb.getSheet(sheetname);
		
		Object[][]data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i=0; i<sheet.getLastRowNum(); i++) {
			for (int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
				
				data[i][j]= sheet.getRow(i+1).getCell(j).getStringCellValue();
				
			}
						
		}
		
		return data;
	}
	
	public List<String> getArrayListinput(String sheetname, String TCName) {
		
		int sheetnumber= wb.getNumberOfSheets();
		int k=0;
		int column = 0;
		List<String>al = new ArrayList<>();
		
		for (int i=0; i<sheetnumber; i++) {
			if (wb.getSheetAt(i).getSheetName().equals(sheetname)) {
				XSSFSheet sheet=wb.getSheetAt(i);
				Iterator<Row>rows=sheet.rowIterator();
				
				while (rows.hasNext()) {
					Row fr=rows.next();
					
					Iterator<Cell>cells=fr.cellIterator();
					
					while(cells.hasNext()) {
						Cell cell = cells.next();
						if (cell.getStringCellValue().equals("Test Case")) {
							column=k;
							break;
						}k++;
					}
					
					
					while (rows.hasNext()) {
						Row fg= rows.next();
						if (fg.getCell(column).getStringCellValue().equals(TCName)){
							Iterator<Cell>cellsval=fg.cellIterator();
							while (cellsval.hasNext()) {
								Cell fc = cellsval.next();
								if (fc.getCellType().equals(CellType.STRING)) {
									al.add(fc.getStringCellValue());
								}else {
									String value=NumberToTextConverter.toText(fc.getNumericCellValue());
									al.add(value);
								}
							}
						}
					}
				}
			}
		}
		return al;
	}
	
	public void createsheet(String sheetname) {
		
//		int sheetnumber = wb.getNumberOfSheets();
//		
//		for (int i=0; i<sheetnumber; i++) {
//			if(!wb.getSheetAt(i).getSheetName().equals(sheetname)) {
//				XSSFSheet sheet=wb.createSheet();
//				
//			}
//		}
		
		wb.createSheet();
		
	}
	
	public void addcolumn(int maxrow, String sheetname, int maxcoll, String celldata) throws IOException {
		XSSFSheet sheet= wb.getSheet(sheetname);
		
		for (int row=0; row<maxrow; row++) {
			XSSFRow myrow=sheet.getRow(row);
			if (myrow==null) {
				myrow=sheet.createRow(row);
				XSSFCell mycell =myrow.getCell(row);
				if (mycell==null) {
					for (int cell=0; cell<maxcoll; cell++ ) {
						myrow.createCell(cell).setCellValue(celldata);
					}
				}
			}
		}
		FileOutputStream fos = new FileOutputStream(src);
		wb.write(fos);
	}
}
