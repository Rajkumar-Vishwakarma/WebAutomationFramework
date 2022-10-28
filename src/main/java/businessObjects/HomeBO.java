package businessObjects;

import org.testng.Assert;

import baseFramework.BaseClass;
import baseFramework.ConstantsFile;
import dataModels.HomePage;
import pageObjects.HomePO;

public class HomeBO extends BaseClass {
	
	public String MSG_INVALID_COMBINATION = "Invalid combination of Country: %1$s and Language: %2$s selected";
	public HomePO homePO;
	
	public HomeBO(HomePO homePO)
	{
		this.homePO = homePO;
	}

	public void validateTestAction(HomePage countryLanguage)
	{
		
		homePO.clickCountryDropdown();
		homePO.selectCountryValue(countryLanguage.country);
		homePO.clickLanguageDropdown();
		homePO.selectLanguageValue(countryLanguage.language);
		homePO.clickSubmitButton();
		
		if (homePO.isAcceptCookieBtnDisplayed())
		{
			homePO.clickAcceptCookieBtn();
		}
		
		if (countryLanguage.country.equals(readProperty(ConstantsFile.countryUS)) &&
				countryLanguage.language.equals(readProperty(ConstantsFile.languageEng)))
		{
			Assert.assertTrue(homePO.isPatientsLinkDisplayed() && homePO.isProfessionalsLinkDisplayed());
		}
		else if (countryLanguage.country.equals(readProperty(ConstantsFile.countryFrance)) &&
				countryLanguage.language.equals(readProperty(ConstantsFile.languageFrench)))
		{
			Assert.assertFalse(homePO.isPatientsLinkDisplayed() && homePO.isProfessionalsLinkDisplayed());
		}
		else
		{
			Assert.fail(String.format(MSG_INVALID_COMBINATION, countryLanguage.country, countryLanguage.language));
		}
	}
	
	
	
	
	
	

}
