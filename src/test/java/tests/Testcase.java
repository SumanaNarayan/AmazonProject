package tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class Testcase extends BaseClass{
	
	@BeforeTest
	public void intiateDriver()
	{
		setup();
	}
	
	@Test
	@Parameters({"ProductName","CartCount"})
	public void testcase(String ProductName,String CartCount)
	
	{ 
		HomePage home= new HomePage(driver);
		ProductPage Prdt= new ProductPage(driver);
		CartPage cart= new CartPage(driver);
		LoginPage log= new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.login();
	    home.searchProduct(ProductName);
	    String Product= Prdt.getProductDetails();
	    Prdt.NavigateToCart();
	    
	    cart.ValidateProductDetails(Product, CartCount);
	    cart.NavigateCheckOut();
	    
	    
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	

}
