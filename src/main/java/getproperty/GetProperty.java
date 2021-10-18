package getproperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

	private Properties pro;
	
	public Properties getpro() {
	
	try {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		pro=null;
		
		try {
			pro= new Properties();
			pro.load(fis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return pro;
	}
}
