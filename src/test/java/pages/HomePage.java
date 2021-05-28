package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import tests.BaseClass;

public class HomePage extends BaseClass {
	
	public HomePage(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
  
@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
   public WebElement SearchField;

@AndroidFindBy(xpath="(//*[@resource-id='com.amazon.mShop.android.shopping:id/list_product_linear_layout'])[1]")
public WebElement SearchResult;
   
   public void searchProduct(String ProductName)
   {   waitForElementToBeClickable(SearchField);
	   SearchField.sendKeys(ProductName,Keys.ENTER);
	   waitForElementToBeClickable(SearchResult);
	   SearchResult.click();
   }
}
