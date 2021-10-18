package initdriver;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import getproperty.GetProperty;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInIt {

	public static WebDriver driver;
	
	public WebDriver driverinit(String browser) {
		
		if (browser.equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		return driver;
	}
	
}
