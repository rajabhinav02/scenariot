package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import getproperty.GetProperty;
import initdriver.DriverInIt;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base extends DriverInIt {

	public static WebDriver driver;
	private GetProperty gp;
	private DriverInIt di;
	private Properties pro;
	
	
	public WebDriver basemethod() {
		
		gp= new GetProperty();
		pro= gp.getpro();
		
		String browsername = pro.getProperty("browser");
		String envi = pro.getProperty("env");
		String url=null;
		
		if (envi.equals("prod")) {
			url = pro.getProperty("purl");
		}else if (envi.equals("ete")) {
			url = pro.getProperty("eurl");
		}else {
			System.out.println("Enter correct environment");
		}
		
if (browsername.equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		//driver=di.driverinit(browsername);
		
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
}
