package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.WebDriverUtils;

public class LoginPage extends WebDriverUtils{
	private WebDriver driver;
	private static Logger log=LogManager.getLogger(LoginPage.class.getName());
		
		public LoginPage(WebDriver driver) {
			super(driver);
			
		}

		@FindBy(xpath="//ul[@class='breadcrumb']/li[3]/a[text()='Login']")
		private WebElement loginBreadCrumb;
		
		@FindBy(css="div.well>h2")
		private WebElement newCustomerHeader;

		@FindBy(css="a.btn.btn-primary")
		private WebElement newCustomerContinueBtn;
		
		@FindBy(xpath="//strong[contains(text(),'Register Account')]")
		private WebElement registerAccountTxt;
		
		@FindBy(xpath="//h2[normalize-space()='Returning Customer']")
		private WebElement returningCustomerHeader;

		@FindBy(id="input-email")
		private WebElement emailAddressEditbox;
		
		@FindBy(name="password")
		private WebElement passwordEditbox;
		
		@FindBy(linkText="Forgotten Password")
		private WebElement forgottenPasswordLink;
		
		@FindBy(xpath="//input[@type='submit' and @value='Login']")
		private WebElement loginBtn;
		
		@FindBy(xpath="//i[contains(@class,'fa fa-home')]")
		private WebElement loginHomeIcon;
		
		@FindBy(css="div.alert.alert-danger")
		private WebElement emptycredsErrMsg;
		
		
		public String getEmptyCredsErrMessage() {
			return emptycredsErrMsg.getText();
		}
		
		public void clickHomeIcon() throws InterruptedException {
			log.info("Click on Breadcrumb home icon");
			click(loginHomeIcon);
		}
		
		public String getLoginPageUrl() {
			return waitForUrlContains(Constants.LOGIN_PAGE_FRACTION_URL);
		}
		
		public void doLogin(String email,String pwd) throws InterruptedException {
			log.info("Performing the login operation");
			sendData(emailAddressEditbox, email);
			sendData(passwordEditbox, pwd);
			log.info("Click on Login button");
			click(loginBtn);
		}
		
		public void navigateToForgotPasswordPage() throws InterruptedException {
			log.info("Click on Forgot password link");
			click(forgottenPasswordLink);
		}
		
		public void clickNewCustomerContinueBtn() {
			log.info("click on new customer continue button");
			try {
				click(newCustomerContinueBtn);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	public boolean isNewCustomerHeaderExists() {
		return isDisplayed(newCustomerHeader);
	}
	public boolean isRegisterAccountTextExists() {
		return isDisplayed(registerAccountTxt);
	}
	public boolean isreturningCustomerHeaderExists() {
		return isDisplayed(returningCustomerHeader);
	}
	
	public boolean isLoginBreadCrumbExists() {
		return isDisplayed(loginBreadCrumb);
	}
}
