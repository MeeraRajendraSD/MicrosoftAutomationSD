package microsoft.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import microsoft.qa.base.TestBase;

public class SearchResultPage extends TestBase{
	
	// Page Factory - Object 
	
	@FindBy (xpath = "//a[@name='tab' and (contains(@aria-label,'buy') or contains(@aria-label,'comprar') )]")
	WebElement comprarLink;
	
	@FindBy (xpath= "//div[@id='R1MarketRedirect-1']/button[contains(@class,'cancel')]")
	WebElement translatePopupCancelButton;
	
	@FindBy (xpath="//a[contains(@aria-label,'Aplicaciones')]")
	WebElement aplicacionesLink;
	
	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void openComprarTab() {
		comprarLink.click();
		if (translatePopupCancelButton.isDisplayed()) {
			translatePopupCancelButton.click();
		}
	}
	
	public AplicacionesPage clickOnAplicacionesbutton() {
		aplicacionesLink.click();
		if (translatePopupCancelButton.isDisplayed()) {
			translatePopupCancelButton.click();
		}
		return new AplicacionesPage();
	}

}
