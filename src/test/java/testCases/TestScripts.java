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
	
	/*
	 * Executes actions before suite execution is started
	 * */
	@BeforeSuite
	public void beforeSuite()
	{
		String filePath = System.getProperty(ConstantsFile.userDir)+"\\src\\main\\java\\config\\config.properties";
		initializeProps(filePath);
	}
	
	/*
	 * Executes actions before method execution is started
	 * */
	@BeforeMethod
	public void beforeMethod()
	{
		launchBrowser(ConstantsFile.chrome);
		driver.get(readProperty(ConstantsFile.applicationUrl));
	}
	
	/*
	 * Test method in which test actions are performed
	 * @param	country and language as an object of HomePage data model
	 * */
	@Test (dataProvider = "countryLanguageData")
	public void patientsProfessionalsTest(HomePage countryLanguage)
	{
		homeBO.validateTestAction(countryLanguage);
	}
	
	/*
	 * Data provider method for test method
	 * @return	country and language as objects of HomePage data model
	 * */
	@DataProvider(name="countryLanguageData")
	public Object[][] dataProviderMethod()
	{
		return new Object[][] {
			{new HomePage(readProperty(ConstantsFile.countryUS),readProperty(ConstantsFile.languageEng))},
			{new HomePage(readProperty(ConstantsFile.countryFrance),readProperty(ConstantsFile.languageFrench))}
		};
	}
	
	/*
	 * Executes actions after test method execution is completed
	 * */
	@AfterMethod
	public void afterMethod()
	{
		driver.quit();
	}
	
	

}
