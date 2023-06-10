package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.WebDriverUtils;

public class ForgotPasswordPage extends WebDriverUtils{
	private WebDriver driver;
	private static Logger log=LogManager.getLogger(ForgotPasswordPage.class.getName());
		
		public ForgotPasswordPage(WebDriver driver) {
			super(driver);
			
		}

		@FindBy(css="#content>form>fieldset>legend")
		private WebElement yourEmailAdressLegendTxt;
		
		@FindBy(css="#content>h1")
		private WebElement forgotPasswordHeader;

		@FindBy(css="#input-email")
		private WebElement emailAddressEditbox;
		
		@FindBy(css="a.btn.btn-default")
		private WebElement BackBtn;
		
		@FindBy(css="input[type='submit'][value='Continue']")
		private WebElement continueBtn;
		
		public boolean isyourEmailAdressLegendTxtExists() {
			return isDisplayed(yourEmailAdressLegendTxt);
		}
		public boolean isforgotPasswordHeaderExists() {
			return isDisplayed(forgotPasswordHeader);
		}
		public void setEmailAddress(String email) throws InterruptedException {
			log.info("Entering the email address");
			sendData(emailAddressEditbox, email);
		}
		
		public void navigateToLoginPage() {
			log.info("Click on Back page");
			try {
				click(BackBtn);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void clickForgotPasswordContinueBtn() {
			log.info("Click on Back page");
			try {
				click(continueBtn);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
