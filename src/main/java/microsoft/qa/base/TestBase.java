package microsoft.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	
	public static WebDriver driver;
	static Properties prop;
	
	
	
	public TestBase () {
		
		try {
			prop= new Properties();
			FileInputStream ip= new FileInputStream("/Users/kashyapbhatt/eclipse-Selenium-Java-workspace/MicrosoftProject/src/main/java/microsoft/qa/config/config.properties");
			prop.load(ip);		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void initialize() {
		String browser= prop.getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/kashyapbhatt/Downloads/chromedriver");
			driver= new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}

}
