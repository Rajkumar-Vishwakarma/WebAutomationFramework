package helpers;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseFramework.BaseClass;

public class WebDriverHelper extends BaseClass {
	
	/*
	 * Clicks WebElement
	 * @param	ElementLocator as By object
	 * */
	public void clickWebElement(By elementLocator)
	{
		waitForElementToLoad(elementLocator);
		
		driver.findElement(elementLocator).click();
		
	}
	
	/*
	 * Checks if element is displayed or not
	 * @param	ElementLocator as By object
	 * @return	true if displayed else false
	 * */
	public boolean isElementDisplayed(By elementLocator)
	{
		return driver.findElement(elementLocator).isDisplayed();
	}
	
	/*
	 * Waits for element to load
	 * @param	ElementLocator as By object
	 * */
	public void waitForElementToLoad(By elementLocator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
	}

}
