package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());

		}
		
		
		
	}

	public String getbaseURL() {
		String baseURL = pro.getProperty("baseURL");
		return baseURL;
	}

	public String getChromepath() {
		String chromepath = pro.getProperty("chromepath");
		return chromepath;
	}

	public String getFirefoxpath() {
		String firfoxpath = pro.getProperty("firfoxpath");
		return firfoxpath;
	}

	public String getRPassword() {
		String Password = pro.getProperty("RPassword");
		return Password;
	}

	public String getREmailAddress() {
		String EmailAddress = pro.getProperty("REmailAddress");
		return EmailAddress;
	}
	
	public String getLPassword() {
		String Password = pro.getProperty("LPassword");
		return Password;
	}

	public String getLEmailAddress() {
		String EmailAddress = pro.getProperty("LEmailAddress");
		return EmailAddress;
	}

}
