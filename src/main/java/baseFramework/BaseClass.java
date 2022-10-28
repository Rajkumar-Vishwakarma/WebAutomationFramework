package baseFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {

	public static File file;
	public static Properties prop;
	public static FileInputStream fis;
	
	public static WebDriver driver;

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
	
	public static String readProperty(String property)
	{
		return prop.getProperty(property);
	}
	
	public static void launchBrowser(String browserType)
	{
		switch (browserType) 
		{
		case ConstantsFile.chrome:
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+prop.getProperty(ConstantsFile.chromePath));
			driver = new ChromeDriver();
			break;
			
		case ConstantsFile.edge:
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+prop.getProperty(ConstantsFile.edgePath));
			driver = new EdgeDriver();
			break;

		default:
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

}
