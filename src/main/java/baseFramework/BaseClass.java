package baseFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static File file;
	public static Properties prop;
	public static FileInputStream fis;
	
	public static WebDriver driver;

	/*
	 * Initializes properties to perform the test actions
	 * @param	filePath as String
	 * */
	public static void initializeProps(String filePath) {
		file = new File(filePath);
		try {
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {

			System.out.println("FileNotFoundException occurred in InitializeProps: " + e.getMessage());
		} catch (IOException e) {

			System.out.println("IOException occurred in InitializeProps: " + e.getMessage());
		}

	}
	
	/*
	 * Reads the property passed as parameter from the properties file
	 * @param	property as String
	 * @return 	property value
	 * */
	public static String readProperty(String property)
	{
		return prop.getProperty(property);
	}
	
	/*
	 * Launches the browser instance
	 * @param	browserType as String
	 * */
	public static void launchBrowser(String browserType)
	{
		switch (browserType) 
		{
		case ConstantsFile.chrome:
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("window-size=1200x600", 
					"--disable-web-security",
					"--disable-gpu");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			break;
			
		case ConstantsFile.edge:
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("window-size=1200x600", 
					"--disable-web-security",
					"--disable-gpu");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

}
