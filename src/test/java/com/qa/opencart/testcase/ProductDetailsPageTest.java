package com.qa.opencart.testcase;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class ProductDetailsPageTest extends TestBase{
  private Logger log=LogManager.getLogger(ProductDetailsPageTest.class.getName());
	
	@BeforeClass
	  public void beforeClass() throws InterruptedException {
		log.info("Initializing the page class objects");
		homePg=new HomePage(driver);
		loginPg=new LoginPage(driver);
		myaccountPg=new MyAccountPage(driver);
		logoutPg=new LogoutPage(driver);
		resultPg=new ResultsPage(driver);
		productDetailPg=new ProductDetailsPage(driver);
		homePg.navigateToLoginPage();
		log.info("Perform the login ");
		loginPg.doLogin(rb.getString("username"), rb.getString("pwd"));
	  }
	

  @BeforeMethod
  public void beforeMethod() {
	  myaccountPg.waitForPageLoad(2000);
	 
  }

  @DataProvider
  public Object[][] productImageTestData(){
	  
	  return new Object[][] {
		  {"Macbook","MacBook Pro", 4},
		  {"Macbook","MacBook Air", 4},
		  {"iMac","iMac",3},
		  {"Samsung","Samsung SyncMaster 941BW",1},
		  {"Apple","Apple Cinema 30\"",6}
	  };
  }

	
@Test(description="TC_01_Verifying the product images test",dataProvider="productImageTestData")
public void verifyProductImageTest(String searchkeyword,String productName,int imgCount) throws InterruptedException {
	log.info("Perform the product search");
	myaccountPg.waitForPageLoad(2000);
	resultPg=myaccountPg.doProductSearch(searchkeyword);
	resultPg.waitForPageLoad(2000);
	log.info("select the product from search results");
	productDetailPg=resultPg.selectProduct(productName);
	log.info("fetch the product image count for each product");
	int actProductImageCount=productDetailPg.getProductImageCount();
	Assert.assertEquals(actProductImageCount, imgCount);
}


@Test(description="TC_02_Perform product MetadataTest",priority=2)
public void performProductMetadataTest() throws InterruptedException {
	log.info("TC_02_Perform product Metadata test:");
	myaccountPg.waitForPageLoad(2000);
	resultPg=myaccountPg.doProductSearch("MacBook");
	resultPg.waitForPageLoad(2000);
	log.info("select the product from search results");
	productDetailPg=resultPg.selectProduct("MacBook");
	Map<String,String>productInfoMap=productDetailPg.getProductInformation();
	SoftAssert sfobj=new SoftAssert();
	sfobj.assertEquals(productInfoMap.get("Brand"), "Apple");
	sfobj.assertEquals(productInfoMap.get("Product Code"), "Product 16");
	sfobj.assertEquals(productInfoMap.get("Reward Points"), "600");
	sfobj.assertEquals(productInfoMap.get("Availability"), "In Stock");
	sfobj.assertEquals(productInfoMap.get("actualprice"), "$602.00");
	sfobj.assertAll();
}


@Test(dependsOnMethods={"performProductMetadataTest"})
public void doLogout() throws InterruptedException {
	log.info("click on logout link in my account menu");
	myaccountPg.clickLogoutLink();
	logoutPg.waitForPageLoad(2000);
	Assert.assertEquals(logoutPg.getTitle(), Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
	log.info("click on Continue button in logout page");
	logoutPg.clickContinueBtn();
	homePg.waitForPageLoad(2000);
	Assert.assertEquals(homePg.getTitle(), Constants.HOME_PAGE_TITLE);
}


}
