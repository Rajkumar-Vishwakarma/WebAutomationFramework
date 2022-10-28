package helpers;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseFramework.BaseClass;

public class WebDriverHelper extends BaseClass {
	
	public void clickWebElement(By elementLocator)
	{
		waitForElementToLoad(elementLocator);
		
		driver.findElement(elementLocator).click();
		
	}
	
	public boolean isElementDisplayed(By elementLocator)
	{
		return driver.findElement(elementLocator).isDisplayed();
	}
	
	public void waitForElementToLoad(By elementLocator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
	}

}
