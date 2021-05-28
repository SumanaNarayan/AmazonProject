package tests;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass {
	
	public static AppiumDriver<MobileElement> driver;
	DesiredCapabilities cap = new DesiredCapabilities();
	
	public void setup()
	{
		try {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "SM-N960F");
		cap.setCapability(MobileCapabilityType.UDID, "25e83868d61c7ece");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		cap.setCapability(MobileCapabilityType.APP, "C:\\Users\\hp\\Amazon_shopping.apk");
		cap.setCapability("appPackage","com.amazon.mShop.android.shopping");
		cap.setCapability("appActivity","com.amazon.mShop.home.HomeActivity");
		
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver= new AppiumDriver<MobileElement>(url,cap);
		driver= new AndroidDriver<MobileElement>(url,cap);
		
		
	}
	catch(Exception exp) {
	System.out.println("Cause is:"+exp.getCause());
	System.out.println("Message is:"+exp.getMessage());
	exp.printStackTrace();
	}
	}
	
	public void waitForElementToBeClickable(WebElement identifier)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			  wait.until(ExpectedConditions.elementToBeClickable(identifier));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public static void scrollDown(){
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction((PerformsTouchActions)driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }
	
	public static void scrollNClick(WebElement el){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                break;
            }catch (org.openqa.selenium.NoSuchElementException e){
                scrollDown();
                retry++;
            }
        }
    }
	

	


}
