package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import baseFramework.BaseClass;
import helpers.JSExecutorHelper;
import helpers.WebDriverHelper;

public class HomePO extends BaseClass {
	public WebDriverHelper driverHelper;
	public JSExecutorHelper jsExecutorHelper;
	
	By countryDropdown = By.cssSelector("button[id='countryDropdownBtn']");
	By languageDropdown = By.cssSelector("button[id='languageDropdownBtn']");
	By submitBtn = By.cssSelector("button[id='modalSubmit']");
	By patientsLink = By.linkText("Patients");
	By professionalsLink = By.linkText("Professionals");
	By acceptCookieBtn = By.cssSelector("button[id='truste-consent-button']");
	//ul[@id='countryDropdown']//a[text()='Argentina (AR)']
	//ul[@id='languageDropdown']//a[text()='%s']
	public String countryValueXpath = "//ul[@id='countryDropdown']//a[text()='%s']";
	public String languageValueXpath = "//ul[@id='languageDropdown']//a[text()='%s']";
	
	
	
	public HomePO()
	{
		this.driverHelper = new WebDriverHelper();
		this.jsExecutorHelper = new JSExecutorHelper();
	}
	
	public void clickCountryDropdown()
	{
		this.driverHelper.clickWebElement(countryDropdown);
	}
	
	public void selectCountryValue(String country)
	{
		By countryValueLocator = By.xpath(String.format(countryValueXpath, country));
		this.driverHelper.waitForElementToLoad(countryValueLocator);
		WebElement countryValueElement = driver.findElement(countryValueLocator);
		this.jsExecutorHelper.scrollIntoViewAndClick(countryValueElement);
		
	}
	
	public void clickLanguageDropdown()
	{
		this.driverHelper.clickWebElement(languageDropdown);
	}
	
	public void selectLanguageValue(String language)
	{
		By languageValueLocator = By.xpath(String.format(languageValueXpath, language));
		this.driverHelper.waitForElementToLoad(languageValueLocator);
		WebElement languageValueElement = driver.findElement(languageValueLocator);
		this.jsExecutorHelper.scrollIntoViewAndClick(languageValueElement);
	}
	
	public void clickSubmitButton()
	{
		this.driverHelper.clickWebElement(submitBtn);
	}

	public boolean isPatientsLinkDisplayed() 
	{
		boolean isDisplayed = false;
		try 
		{
			isDisplayed = this.driverHelper.isElementDisplayed(patientsLink);
		}
		catch (NoSuchElementException ex)
		{
			System.out.println(ex);
		}
		return isDisplayed;
	}

	public boolean isProfessionalsLinkDisplayed() 
	{
		boolean isDisplayed = false;
		try 
		{
			isDisplayed = this.driverHelper.isElementDisplayed(professionalsLink);
		}
		catch (NoSuchElementException ex)
		{
			System.out.println(ex);
		}
		return isDisplayed;
	}
	
	public boolean isAcceptCookieBtnDisplayed()
	{
		return this.driverHelper.isElementDisplayed(acceptCookieBtn);
	}
	
	public void clickAcceptCookieBtn()
	{
		this.driverHelper.clickWebElement(acceptCookieBtn);
	}

}
