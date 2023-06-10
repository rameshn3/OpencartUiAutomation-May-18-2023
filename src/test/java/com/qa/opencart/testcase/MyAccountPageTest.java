package com.qa.opencart.testcase;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;

import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class MyAccountPageTest extends TestBase{
  private Logger log=LogManager.getLogger(MyAccountPageTest.class.getName());
	
	@BeforeClass
	  public void beforeClass() throws InterruptedException {
		log.info("Initializing the page class objects");
		homePg=new HomePage(driver);
		loginPg=new LoginPage(driver);
		myaccountPg=new MyAccountPage(driver);
		logoutPg=new LogoutPage(driver);
		resultPg=new ResultsPage(driver);
		homePg.navigateToLoginPage();
		log.info("Perform the login ");
		loginPg.doLogin(rb.getString("username"), rb.getString("pwd"));
	  }
	

  @BeforeMethod
  public void beforeMethod() {
	  myaccountPg.waitForPageLoad(2000);
	 
  }


	
@Test(description="TC_01_Verifying the myAccount page Url",priority=1)
public void verifyMyAccountPageUrlTest() {
	log.info("Verify the MyAccount page url");
	Assert.assertTrue(myaccountPg.getMyAccountPageUrl().contains(Constants.ACC_PAGE_FRACTION_URL));
}

@Test(description="TC_02_Verifying the My Account page title",priority=2)
public void verifyMyAccountPageTitleTest() {
	
	 log.info("Assert the MyAccount page title");
		Assert.assertEquals(myaccountPg.getTitle(),Constants.MY_ACCOUNT_PAGE_TITLE);
}

@Test(description="TC_03_Verifying My Account page elements",priority=3)
public void verifyMyAccountPageElementsTest() {
	log.info("Verify My Account page elements test");
	Assert.assertTrue(myaccountPg.isSearchEditboxExists());
	Assert.assertTrue(myaccountPg.isLogoutExists());
	
}

@Test(description="TC_04_Verify My Account Menu options test ",priority=4)
public void VerifyMyAccountMenuOptionsTest() throws InterruptedException {
	log.info("TC_04_Verify My Account Menu options test ");
	Assert.assertEquals(myaccountPg.getMyAccountMenuOptionList(), Constants.EXPECTED_MYACC_MENU_OPTS_LIST);
}

@Test(description="TC_05_Verify My Account Headers List",priority=5)
public void verifyMyAccountHeadersListTest() throws InterruptedException {
	log.info("TC_05_Verify My Account Headers List ");
	
	Assert.assertEquals(myaccountPg.getMyAccountHeaderList(),Constants.EXPECTED_MYACC_HEADER_LIST);
	
}

@Test(description="TC_06_Verify the broken links in my account page",priority=6)
public void verifyBrokenLinksTest() throws InterruptedException, IOException {
	log.info("TC_06_Verify the broken links in my account page ");
	List<WebElement>myAccPageLinksList=driver.findElements(By.tagName("a"));
	for(WebElement el:myAccPageLinksList) {
		String eleUrl=el.getAttribute("href");
		verifyUrls(eleUrl);
	}
}

@Test(description="TC_07_Perform product search",priority=7,dataProvider="productData")
public void performProductSearchTest(String productName) throws InterruptedException {
	log.info("TC_07_Perform product search for:"+productName);
	myaccountPg.waitForPageLoad(2000);
	resultPg=myaccountPg.doProductSearch(productName);
	resultPg.waitForPageLoad(2000);
	String actResultsPgTitle=resultPg.getTitle();
	log.info("Search results page title:"+actResultsPgTitle);
	new SoftAssert().assertEquals(actResultsPgTitle, "Search - "+productName);
	Assert.assertTrue(resultPg.getSearchProductListSize()>0);
	resultPg.waitForPageLoad(2000);
	resultPg.navigateToMyAccountPage();
}

@DataProvider
public Object[][] productData(){
	
	return new Object[][] {
		{"MacBook"},
		{"iMac"},
		{"Samsung"}
	};
}

@Test(dependsOnMethods={"performProductSearchTest"})
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

public static void verifyUrls(String url) throws IOException {
	//step1) Create Object for URL class
	URL ul=new URL(url);
	//step2 open the connection
	HttpURLConnection hc=(HttpURLConnection) ul.openConnection();
	//step3: connect to the url
	hc.connect();
	
	//fetch the status code
	int respStatusCode=hc.getResponseCode();
	//fetch the response message
	String respMsg=hc.getResponseMessage();
	
	if(respStatusCode==200) {
		System.out.println(url+" is working fine:"+respStatusCode+" - "+respMsg);
	}else if(respStatusCode==404) {
		System.out.println(url+" is working broken:"+respStatusCode+" - "+respMsg);
	}
	
}
}
