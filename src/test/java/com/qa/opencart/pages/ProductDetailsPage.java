package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.WebDriverUtils;

public class ProductDetailsPage extends WebDriverUtils {
	private WebDriver driver;
	private static Logger log = LogManager.getLogger(ProductDetailsPage.class.getName());
	
	
	public ProductDetailsPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	
	}
@FindBy(xpath="//div[@id='content']/div[1]/div[2]//h1")
private WebElement productHeader;

@FindBy(css="a.thumbnail")
private List<WebElement> productImageList;

@FindBy(xpath="(//div[@id='content']/div/div[2]//ul[@class='list-unstyled'])[position()=1]/li")
private List<WebElement> productMetaDataList;

@FindBy(xpath="(//div[@id='content']/div/div[2]//ul[@class='list-unstyled'])[position()=2]/li")
private  List<WebElement> productPriceList;

@FindBy(id="button-cart")
private WebElement addToCartBtn;

public String getProductName() {
	return productHeader.getText();
}

public int getProductImageCount() {
	return productImageList.size();
}

private Map<String,String>productMap;

public void getProductMetaData() {
	log.info("Product metadata count is:"+productMetaDataList.size());
	for(WebElement pmd:productMetaDataList) {
		String metaTxt=pmd.getText();//Brand: Apple
		log.info("split the metatext based on :");
		String[] metaData=metaTxt.split(":");
		log.info("fetch meta key and value");
		String metaKey=metaData[0].trim();
		String metaValue=metaData[1].trim();
		log.info("Store meta key and value in the map");
		productMap.put(metaKey, metaValue);
	}
}
public void getProductPriceData() {
	log.info("Product meta price count is:"+productPriceList.size());
	String price=productPriceList.get(0).getText().trim();
	String exTaxprice=productPriceList.get(1).getText().trim();
	log.info("store the price values in the map");
	productMap.put("actualprice", price);
	productMap.put("actualtaxprice", exTaxprice);
	
}

public Map<String,String> getProductInformation(){
	productMap=new HashMap<String,String>();
	getProductMetaData();
	getProductPriceData();
	return productMap;
}

public void clickAddToCartBtn() throws InterruptedException {
	click(addToCartBtn);
}

}
