package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import tests.BaseClass;

public class LoginPage extends BaseClass{
	public LoginPage(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
  
@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/sso_continue")
   public WebElement Continue;
 public void login()
 {   waitForElementToBeClickable(Continue);
	 Continue.click();
 }
}
