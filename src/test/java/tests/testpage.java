package tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;
import page.tablepage;
import utility.Utility;

public class testpage extends Base {

	tablepage tp;
	Utility ut;
	
	@BeforeTest
	public void setup() throws IOException {
		String path = System.getProperty("user.dir")+"\\ExcelData.xlsx";
		driver=basemethod();
		tp = new tablepage(driver);
		ut = new Utility(path);
	}
	
	@Test
	public void testrun() throws IOException {
		List<WebElement>rows=tp.getrows();
		List<WebElement>columns= tp.getcolumns();
		
		int rowcount = rows.size();
		System.out.println(rowcount);
		int columncount= columns.size();
		System.out.println(columncount);	
		
		
		List<String> values = tp.gettext();
		
		
		ut.createsheet("data");
		
		for (String value:values) {
			ut.addcolumn(rowcount, "data", columncount, value);
		}
	}
	
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
