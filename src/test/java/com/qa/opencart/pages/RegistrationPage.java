package com.qa.opencart.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.WebDriverUtils;

public class RegistrationPage extends WebDriverUtils {
	private WebDriver driver;
	private static Logger log = LogManager.getLogger(RegistrationPage.class.getName());

	public RegistrationPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	
	}
@FindBy(css="h1")
private WebElement registerAccountHeader;

@FindBy(css="#content>p")
private WebElement registerAccountStaticTxt;

@FindBy(css="#content>p>a")
private WebElement registerAccountLoginPageLink;
//personalDetails elements
@FindBy(css="fieldset#account>legend")
private  WebElement personalDetailsLegendTxt;
@FindBy(id="input-firstname")
private WebElement firstNameEditbox;
@FindBy(name="lastname")
private WebElement lastNameEditbox;
@FindBy(id="input-email")
private WebElement emailEditbox;
@FindBy(id="input-telephone")
private WebElement telephoneEditbox;
@FindBy(id="input-fax")
private WebElement faxEditbox;
//your address legend elements
@FindBy(css="fieldset#address>legend")
private WebElement addressLegendTxt;
@FindBy(id="input-company")
private WebElement companyEditbox;
@FindBy(id="input-address-1")
private WebElement address1Editbox;
@FindBy(id="input-address-2")
private WebElement address2Editbox;
@FindBy(id="input-city")
private WebElement cityEditbox;
@FindBy(id="input-postcode")
private WebElement postcodeEditbox;
@FindBy(id="input-country")
private WebElement countryDropdown;
@FindBy(id="input-zone")
private WebElement stateDropdown;

@FindBy(css="fieldset:nth-child(1) legend:nth-child(1)")
private WebElement passwordLegendTxt;

@FindBy(css="#input-password")
private WebElement passwordEditbox;

@FindBy(css="#input-confirm")
private WebElement confirmPassowrdEditbox;

@FindBy(css="input[value='1'][name='newsletter']")
private WebElement subscribeYesRadioBtn;

@FindBy(css="input[value='0']")
private WebElement subscribeNoRadioBtn;

@FindBy(css="input[value='1'][name='agree']")
private WebElement privacypolicyCheckbox;

@FindBy(css="input[value='Continue']")
private WebElement continueBtn;

@FindBy(css="body > div:nth-child(4) > div.alert.alert-danger")
private WebElement agreeErrMsg;

@FindBy(css="body div[class='container'] ul[class='breadcrumb'] li:nth-child(3) a:nth-child(1)")
private WebElement registerBreadCrumb;

@FindBy(css="div#content>h1")
private WebElement accntCreatedHeader;

@FindBy(css="div#content>p")
private WebElement accntCreatedSuccMsg;

@FindBy(css="a.btn.btn-primary")
private WebElement accntCreatedContinueBtn;

@FindBy(css="body > div:nth-child(4) > ul > li:nth-child(3) > a")
private WebElement accntCreatedBreadCrumbSuccessLink;

@FindBy(xpath = "//a[contains(.,'contact us')]")
private WebElement contactUsLink;

@FindBy(css=".fa.fa-home")
private WebElement breadCrumbHomeIcon;

public boolean isRegisterBreadCrumbPresent() {
	return isDisplayed(registerBreadCrumb);
}

public String getAccntCreatedHeader() throws InterruptedException {
	return getText(accntCreatedHeader);
}
public String getAccntCreatedSuccMsg() throws InterruptedException {
	return getText(accntCreatedSuccMsg);
}

public boolean isAccntCreatedSuccMsgPresent() {
	return isDisplayed(accntCreatedSuccMsg);
}
public boolean isAccntCreatedBreadCrumbSuccessLinkPresent() {
	return isDisplayed(accntCreatedBreadCrumbSuccessLink);
}

public void clickOnBreadCrumbHomeIcon() throws InterruptedException {
	log.info("clicking on BreadCrumbHomeIcon");
	click(breadCrumbHomeIcon);
}
public void clickOnContactUsLink() throws InterruptedException {
	log.info("clicking on BreadCrumbHomeIcon");
	click(contactUsLink);
}

public void clickAccntHasBeenCreatedContinueBtn() throws InterruptedException {
	log.info("clicking on accntCreatedContinueBtn");
	click(accntCreatedContinueBtn);
}

public String getFirstNameEditbox() {
	return firstNameEditbox.getAttribute("value");
}

public void setFirstNameEditbox(String firstName) throws InterruptedException {
	log.info("Type the first name value");
	sendData(firstNameEditbox, firstName);
}

public String getLastNameEditbox() {
	return lastNameEditbox.getAttribute("value");
}

public void setLastNameEditbox(String lastName) throws InterruptedException {
	log.info("Type the last name value");
	sendData(lastNameEditbox, lastName);
}

public String getEmailEditbox() {
	return emailEditbox.getAttribute("value");
}

public void setEmailEditbox(String email) throws InterruptedException {
	log.info("Type the email value");
	sendData(emailEditbox ,email);
}

public String getTelephoneEditbox() {
	return telephoneEditbox.getAttribute("value");
}

public void setTelephoneEditbox(String telephone) throws InterruptedException {
	log.info("Type the telephone value");
	sendData(telephoneEditbox, telephone);
}

public String getFaxEditbox() {
	return faxEditbox.getAttribute("value");
}

public void setFaxEditbox(String fax) throws InterruptedException {
	log.info("Type the email value");
	sendData(faxEditbox,fax);
}

public String getCompanyEditbox() {
	return companyEditbox.getAttribute("value");
}

public void setCompanyEditbox(String company) throws InterruptedException {
	log.info("Type the Company value");
	sendData(companyEditbox,company);
}

public String getAddress1Editbox() {
	return address1Editbox.getAttribute("value");
}

public void setAddress1Editbox(String address1) throws InterruptedException {
	log.info("Type the address1 value");
	sendData(address1Editbox,address1);
}

public String getAddress2Editbox() {
	return address2Editbox.getAttribute("value");
}

public void setAddress2Editbox(String address2) throws InterruptedException {
	log.info("Type the address2 value");
	sendData(address2Editbox, address2);
}

public String getCityEditbox() {
	return cityEditbox.getAttribute("value");
}

public void setCityEditbox(String city) throws InterruptedException {
	log.info("Type the City value");
	sendData(cityEditbox,city);
}

public String getPostCodeEditbox() {
	return postcodeEditbox.getAttribute("value");
}

public void setPostCodeEditbox(String postCode) throws InterruptedException {
	log.info("Enter the postCode value:");
	sendData(postcodeEditbox, postCode);
}

public String getPasswordEditbox() {
	return passwordEditbox.getAttribute("value");
}

public void setPasswordEditbox(String password) throws InterruptedException {
	log.info("Enter the password value:");
	sendData(passwordEditbox,password);
}

public String getConfirmPassowrdEditbox() {
	return confirmPassowrdEditbox.getAttribute("value");
}

public void setConfirmPassowrdEditbox(String confirmPassowrd) throws InterruptedException {
	log.info("Enter the email value:");
	sendData(confirmPassowrdEditbox,confirmPassowrd);
}

public void selectCountry(String optionTxt) {
	log.info("select an option from country dropdown by label");
	selectOptionByVisibleText(countryDropdown, optionTxt);
	waitForPageLoad(1000);
}
public void selectState(String optionTxt) {
	log.info("select an option from State dropdown by label");
	selectOptionByVisibleText(stateDropdown, optionTxt);
	
}

public void selectSubscribe(String subscribe) throws InterruptedException {
	log.info("select newsletter subscription value");
	if(subscribe.equalsIgnoreCase("Yes")) {
		log.info("Select Yes radio button");
		click(subscribeYesRadioBtn);
	}else {
		log.info("Select No radio button");
		click(subscribeNoRadioBtn);
	}
}

public void checkAgreeCheckbox() throws InterruptedException {
	log.info("check Agree checkbox");
	check(privacypolicyCheckbox);
}

public void clickContinueBtn() throws InterruptedException {
	log.info("click Continue button");
	click(continueBtn);
}

public void clickLoginPageLink() throws InterruptedException {
	log.info("click LoginPage Link");
	click(registerAccountLoginPageLink);
}

public void setPersonalDetails(String fname,String lname,String email,String tel,String fax) throws InterruptedException {
	setFirstNameEditbox(fname);
	setLastNameEditbox(lname);
	setEmailEditbox(email);
	setTelephoneEditbox(tel);
	setFaxEditbox(fax);
}

public void setAddressDetails(String company,String addr1,String addr2,String city,String postCode,String country,String state) throws InterruptedException {
	setCompanyEditbox(company);
	setAddress1Editbox(addr1);
	setAddress2Editbox(addr2);
	setCityEditbox(city);
	setPostCodeEditbox(postCode);
	selectCountry(country);
	selectState(state);
}

public void setPasswordDetails(String pwd,String confirmPwd) throws InterruptedException {
	setPasswordEditbox(pwd);
	setConfirmPassowrdEditbox(confirmPwd);
}


}
