package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import tests.BaseClass;

public class ProductPage extends BaseClass {

	public ProductPage(AppiumDriver<MobileElement> driver)
{
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
	@AndroidFindBy(xpath="//*[@resource-id='title_feature_div']//*[@class='android.view.View']")
	   public WebElement ProductName;

	@AndroidFindBy(xpath="//*[contains(@resource-id,'add-to-cart-button')]")
	public WebElement AddToCartButton;
	
	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/action_bar_cart_count")
	public WebElement CartIcon;
	
	public String getProductDetails()
	{ 
		
		String Product=ProductName.getText();
		scrollNClick(AddToCartButton);
		return Product;
	}
	
	public void NavigateToCart()
	{
		CartIcon.click();
	}
}

