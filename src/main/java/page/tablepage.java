package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class tablepage {
	
	WebDriver driver;
	
	public tablepage(WebDriver driver) {
		this.driver = driver;
	}

	//Locator
	int rownum;
	int columnnum;
	private By rows = By.xpath("//table//tr");
	private By columns = By.xpath("//table//tr[2]/td");
	private By data = By.xpath("//table//tr["+rownum+"]//td["+columnnum+"]");
	
	
	public List<WebElement> getrows() {
		List<WebElement>row=driver.findElements(rows);
		return row;
	}
	
	public List<WebElement> getcolumns() {
		List<WebElement>column= driver.findElements(columns);
		return column;
	}
	
	public List<String> gettext() {
		List<WebElement>row=driver.findElements(rows);
		List<WebElement>column= driver.findElements(columns);
		int rowcount = row.size();
		System.out.println(rowcount);
		int columncount= column.size();
		System.out.println(columncount);
		List<String>al = new ArrayList<>();
		
		for (int i=2; i<=row.size(); i++) {
			for (int j=1; j<column.size(); j++) {
				String value=driver.findElement(By.xpath("//table//tr["+i+"]//td["+j+"]")).getText();
				al.add(value);
			}
		}
		
		return al;
		
	}
	
}
