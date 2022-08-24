package microsoft.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import microsoft.qa.base.TestBase;

public class HomePage extends TestBase {
	
	// Page Factory - Object 

	@FindBy (id = "shellmenu_2")
	WebElement windowsLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public WindowsPage clickOnWindows() {
		
		windowsLink.click();
		return new WindowsPage();
		
	}

}
