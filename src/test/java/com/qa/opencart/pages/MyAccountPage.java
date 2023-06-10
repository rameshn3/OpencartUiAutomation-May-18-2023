package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.WebDriverUtils;

public class MyAccountPage extends WebDriverUtils {
	private WebDriver driver;
	private static Logger log = LogManager.getLogger(MyAccountPage.class.getName());

	public MyAccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='top-links']/ul/li[2]/a")
	private WebElement myAccountMenu;

	@FindBy(name = "search")
	private WebElement searchEditbox;

	@FindBy(css = "button[class='btn btn-default btn-lg']")
	private WebElement searchTorchIcon;

	@FindBy(linkText = "Logout")
	private WebElement logoutLink;

	@FindBy(css = "body div[class='container'] ul[class='breadcrumb'] li:nth-child(1) a:nth-child(1)")
	private WebElement accountBreadCrumb;

	@FindBy(xpath = "//div[@id='top-links']/ul/li[2]/ul/li/a")
	private List<WebElement> myAccountMenuOptionList;

	@FindBy(xpath = "//div[@id='content']/h2")
	private List<WebElement> myAccountHeaderList;

	@FindBy(xpath = "//div[@id='content']/ul[1]/li/a")
	private List<WebElement> myAccountHeaderOptionList;

	@FindBy(xpath = "//div[@id='content']/ul[2]/li/a")
	private List<WebElement> myOrderHeaderOptionList;

	public void clickMyAccountMenuLink() throws InterruptedException {
		log.info("click on my account menu link");
		click(myAccountMenu);
	}

	public String getMyAccountPageUrl() {
		log.info("fetch my Account page url");
		return waitForUrlContains(Constants.ACC_PAGE_FRACTION_URL);
	}

	public boolean isSearchEditboxExists() {
		return isDisplayed(searchEditbox);
	}

	public boolean isLogoutExists() {
		return isDisplayed(logoutLink);
	}

	public void clickLogoutLink() throws InterruptedException {
		clickMyAccountMenuLink();
		waitForElementVisible(By.linkText("Logout"));
		click(logoutLink);
	}

	public ResultsPage doProductSearch(String productName) throws InterruptedException {
		log.info("Search for Product:" + productName);
		if (isSearchEditboxExists()) {
			log.info("perform the type action on search editbox");
			sendData(searchEditbox, productName);
			log.info("click on search torch icon");
			click(searchTorchIcon);
			return new ResultsPage(driver);
		}
		return null;
	}

	public List<String> getMyAccountMenuOptionList() throws InterruptedException {
		clickMyAccountMenuLink();
		List<String> myAccntMenuOptionTxtList = new ArrayList<String>();

		// iterate the myAccountMenuOptionList
		for (WebElement ele : myAccountMenuOptionList) {
			// get each element text
			String txt = ele.getText();
			// add the element text to myAccntMenuOptionTxtList
			myAccntMenuOptionTxtList.add(txt);

		}
		return myAccntMenuOptionTxtList;
	}

	public List<String> getMyAccountHeaderList() throws InterruptedException {
		clickMyAccountMenuLink();
		List<String> myAccntHeaderTxtList = new ArrayList<String>();

		// iterate the myAccountMenuOptionList
		for (WebElement ele : myAccountHeaderList) {
			// get each element text
			String txt = ele.getText();
			// add the element text to myAccntMenuOptionTxtList
			myAccntHeaderTxtList.add(txt);

		}
		return myAccntHeaderTxtList;
	}

	public List<String> getMyAccountHeaderOptionsList() throws InterruptedException {
		clickMyAccountMenuLink();
		List<String> myAccntHeaderOptionsTxtList = new ArrayList<String>();

		// iterate the myAccountMenuOptionList
		for (WebElement ele : myAccountHeaderOptionList) {
			// get each element text
			String txt = ele.getText();
			// add the element text to myAccntMenuOptionTxtList
			myAccntHeaderOptionsTxtList.add(txt);

		}
		return myAccntHeaderOptionsTxtList;
	}

	public List<String> getmyOrderHeaderOptionList() throws InterruptedException {
		clickMyAccountMenuLink();
		List<String> myOrdersHeaderOptionsTxtList = new ArrayList<String>();

		// iterate the myAccountMenuOptionList
		for (WebElement ele : myOrderHeaderOptionList) {
			// get each element text
			String txt = ele.getText();
			// add the element text to myAccntMenuOptionTxtList
			myOrdersHeaderOptionsTxtList.add(txt);

		}
		return myOrdersHeaderOptionsTxtList;
	}

}
