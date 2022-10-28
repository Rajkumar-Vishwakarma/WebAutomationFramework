package testCases;

import baseFramework.BaseClass;
import baseFramework.ConstantsFile;
import businessObjects.HomeBO;
import dataModels.HomePage;
import pageObjects.HomePO;

import org.testng.annotations.*;

public class TestScripts extends BaseClass {
	
	HomePO homePO = new HomePO();
	HomeBO homeBO = new HomeBO(homePO);
	
	@BeforeSuite
	public void beforeSuite()
	{
		String filePath = System.getProperty(ConstantsFile.userDir)+"\\src\\main\\java\\config\\config.properties";
		initializeProps(filePath);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		launchBrowser(ConstantsFile.chrome);
		driver.get(readProperty(ConstantsFile.applicationUrl));
	}
	
	@Test (dataProvider = "countryLanguageData")
	public void patientsProfessionalsTest(HomePage countryLanguage)
	{
		homeBO.validateTestAction(countryLanguage);
	}
	
	@DataProvider(name="countryLanguageData")
	public Object[][] dataProviderMethod()
	{
		return new Object[][] {
			{new HomePage(readProperty(ConstantsFile.countryUS),readProperty(ConstantsFile.languageEng))},
			{new HomePage(readProperty(ConstantsFile.countryFrance),readProperty(ConstantsFile.languageFrench))}
		};
	}
	
	@AfterMethod
	public void afterMethod()
	{
		driver.quit();
	}
	
	

}
