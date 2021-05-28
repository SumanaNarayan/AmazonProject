package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import tests.BaseClass;

public class CartPage extends BaseClass {
	
	public CartPage(AppiumDriver<MobileElement> driver)
{
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
	@AndroidFindBy(xpath="(//*[@resource-id='activeCartViewForm']//*[@class='android.widget.TextView'])[1]")
	   public WebElement ProductName;

	@AndroidFindBy(xpath="(//*[@resource-id='activeCartViewForm']//*[@class='android.widget.TextView'])[2]")
	public WebElement ProductCount;
	
	@AndroidFindBy(xpath="(//*[@resource-id='sc-mini-buy-box']//*[@class='android.widget.Button'])")
	public WebElement CheckOut;
	
	
	public void ValidateProductDetails(String Productname,String Productcount)
	{
		String ActProductName=ProductName.getText();
		Assert.assertTrue(ActProductName.contains(Productname), "Product detail is wrong");
		
		String ActProductCount =ProductCount.getText();
		Assert.assertEquals(ActProductCount, Productcount);
	}
	
	public void NavigateCheckOut()
	{   waitForElementToBeClickable(CheckOut);
		CheckOut.click();
	}

}
