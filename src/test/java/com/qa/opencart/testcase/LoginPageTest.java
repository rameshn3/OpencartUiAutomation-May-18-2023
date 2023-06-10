package com.qa.opencart.testcase;

import org.testng.annotations.Test;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utils.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class LoginPageTest extends TestBase{
  private Logger log=LogManager.getLogger(LoginPageTest.class.getName());
	
	@BeforeClass
	  public void beforeClass() throws InterruptedException {
		log.info("Initializing the page class objects");
		homePg=new HomePage(driver);
		regPg=new RegistrationPage(driver);
		loginPg=new LoginPage(driver);
		myaccountPg=new MyAccountPage(driver);
		logoutPg=new LogoutPage(driver);
		forgotpwdPg=new ForgotPasswordPage(driver);
		homePg.navigateToLoginPage();
	  }
	

  @BeforeMethod
  public void beforeMethod() {
	  loginPg.waitForPageLoad(2000);
  }


	
@Test(description="TC_01_Verifying the Login page Url",priority=1)
public void verifyLoginPageUrlTest() {
	log.info("Verify the login page url");
	Assert.assertTrue(loginPg.getLoginPageUrl().contains(Constants.LOGIN_PAGE_FRACTION_URL));
}

@Test(description="TC_02_Verifying the login page title",priority=2)
public void verifyLoginPageTitleTest() {
	log.info("Verify the login page title test");
	Assert.assertEquals(loginPg.getTitle(),Constants.LOGIN_PAGE_TITLE);
}

@Test(description="TC_03_Verifying the Login page elements",priority=3)
public void verifyLoginPageElementsTest() {
	log.info("Verify Login page elements test");
	Assert.assertTrue(loginPg.isNewCustomerHeaderExists());
	Assert.assertTrue(loginPg.isRegisterAccountTextExists());
	Assert.assertTrue(loginPg.isreturningCustomerHeaderExists());
	Assert.assertTrue(loginPg.isLoginBreadCrumbExists());
}

@Test(description="TC_04_Navigate to registration page from Login page ",priority=4)
public void navigateToRegistrationPageTest() throws InterruptedException {
	log.info("Navigate to registration page ");
	loginPg.clickNewCustomerContinueBtn();
	regPg.waitForPageLoad(2000);
	log.info("Assert the Registration page title");
	Assert.assertEquals(regPg.getTitle(),Constants.REGISTRATION_PAGE_TITLE);
	log.info("click on browser back button in registration page");
	regPg.navigateBrowserBack();
}

@Test(description="TC_05_navigateTo forgot password page",priority=5)
public void navigateToForgotPasswordTest() throws InterruptedException {
	log.info("Navigate to forgot password page ");
	loginPg.navigateToForgotPasswordPage();
	forgotpwdPg.waitForPageLoad(2000);
	log.info("Assert the forgotpassword page title");
	Assert.assertEquals(forgotpwdPg.getTitle(),Constants.FORGOT_PWD_PAGE_TITLE);
	log.info("Click on browser back button in forgot password page");
	forgotpwdPg.navigateBrowserBack();
}

@Test(description="TC_06_Happy path flow login to opencart app",priority=7)
public void happyPathLoginTest() throws InterruptedException {
	log.info("TC_06_Happy path flow login to opencart app ");
	loginPg.doLogin(rb.getString("username"), rb.getString("pwd"));
	myaccountPg.waitForPageLoad(2000);
	log.info("Assert the MyAccount page title");
	Assert.assertEquals(myaccountPg.getTitle(),Constants.MY_ACCOUNT_PAGE_TITLE);
	
}

@Test(description="TC_07_Empty Credentials login to opencart app",priority=6)
public void emptyCredsLoginTest() throws InterruptedException {
	log.info("TC_07_Empty Credentials login to opencart app ");
	loginPg.doLogin("", "");
	loginPg.waitForPageLoad(2000);
	log.info("Assert the empty creds error message in login page ");
	Assert.assertTrue(loginPg.getEmptyCredsErrMessage().contains(Constants.LOGIN_NOMATCH_ERR_MSG));
	
}

@Test(dependsOnMethods={"happyPathLoginTest"})
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
