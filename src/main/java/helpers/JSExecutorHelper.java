package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import baseFramework.BaseClass;

public class JSExecutorHelper extends BaseClass {
	
	/*
	 * Scrolls the WebElement into view and clicks it using JavascriptExecutor
	 * @param	WebElement
	 * */
	public void scrollIntoViewAndClick(WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}

}
