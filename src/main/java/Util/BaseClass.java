package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

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
	Properties prop=new Properties();
	
	public void setup()
	{
		try {
		DesiredCapabilities cap = new DesiredCapabilities();
		prop=loadPropertyFile(System.getProperty("user.dir")+"\\src\\main\\resources\\PropertyFiles\\capability.properties");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("platformName"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
		cap.setCapability(MobileCapabilityType.APP, prop.getProperty("app"));
		cap.setCapability("appPackage",prop.getProperty("appPackage"));
		cap.setCapability("appActivity",prop.getProperty("appActivity"));
		
		
		URL url = new URL(prop.getProperty("hubUrl"));
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
	
	public static void scrollNClick(WebElement el,Reporting report){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                report.extentReportPass(el+ "is clicked");
                break;
            }catch (org.openqa.selenium.NoSuchElementException e){
                scrollDown();
                retry++;
            }
        }
    }
	
	public Properties loadPropertyFile(String path)
	{
		try
		{
			FileInputStream fs=new FileInputStream(path);
			prop.load(fs);
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return prop;
	}
	public void Click(WebElement el,Reporting report) 
	{
		try
		{
			
			el.click();
			report.extentReportPass(el+ "is clicked");
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}
	


}