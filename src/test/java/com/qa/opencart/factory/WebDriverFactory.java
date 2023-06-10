package com.qa.opencart.factory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class WebDriverFactory {

	private static final Logger log = LogManager.getLogger(WebDriverFactory.class.getName());
	public ResourceBundle rb;// to read config.properties
	public static String highlight;
	// Singleton

	// Only one instance of the class can exist at a time

	private static final WebDriverFactory instance = new WebDriverFactory();

	private WebDriverFactory() {

	}

	public static WebDriverFactory getInstance() {

		return instance;

	}

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/***
	 * 
	 * Get driver instance based on the browser type
	 * 
	 * @param browser
	 * 
	 * @return
	 * @throws IOException
	 * 
	 */

	public WebDriver getDriver(String browser) throws IOException {
		rb = ResourceBundle.getBundle("config");// Load config.properties
		WebDriver driver = null;
		// highlight = readPropertyValue("highlight");
		highlight = rb.getString("highlight");
		if (tlDriver.get() == null) {

			try {

				if (browser.equalsIgnoreCase(Constants.FIREFOX)) {

					FirefoxOptions ffOptions = setFFOptions();

					driver = new FirefoxDriver(ffOptions);

					tlDriver.set(driver);

				} else if (browser.equalsIgnoreCase(Constants.CHROME)) {

					ChromeOptions chromeOptions = setChromeOptions();

					driver = new ChromeDriver(chromeOptions);

					tlDriver.set(driver);

				} else if (browser.equalsIgnoreCase(Constants.IE)) {

					InternetExplorerOptions ieOptions = setIEOptions();

					driver = new InternetExplorerDriver(ieOptions);

					tlDriver.set(driver);

				} else if (browser.equalsIgnoreCase(Constants.EDGE)) {

					EdgeOptions edgeopt = setEdgeOptions();

					driver = new EdgeDriver(edgeopt);

				} else if (browser.equalsIgnoreCase("headless")) {
					ChromeOptions coptions = getChromeOptions();
					driver = new ChromeDriver(coptions);
					tlDriver.set(driver);
				} else if (browser.equalsIgnoreCase("incognito")) {
					ChromeOptions coptions = getChromeOptions();
					driver = new ChromeDriver(coptions);
					tlDriver.set(driver);
				} else {
					ChromeOptions chromeOptions = setChromeOptions();

					driver = new ChromeDriver(chromeOptions);

					tlDriver.set(driver);
				}

			} catch (Exception e) {

				e.printStackTrace();

			}
			log.info("clear the cache/cookies ");
			getDriver().manage().deleteAllCookies();
			log.info("adding implicit wait ");
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
			log.info("maximize the window");
			getDriver().manage().window().maximize();

		}

		return getDriver();

	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	/***
	 * 
	 * Quit driver instance
	 * 
	 */

	public void quitDriver() {

		getDriver().quit();

		tlDriver.set(null);

	}

	/***
	 * 
	 * Set Chrome Options
	 * 
	 * @return options
	 * 
	 */

	private ChromeOptions setChromeOptions() {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("disable-infobars");

		options.setAcceptInsecureCerts(true);

		return options;

	}

	/***
	 * 
	 * Set Firefox Options
	 * 
	 * @return options
	 * 
	 */

	private FirefoxOptions setFFOptions() {

		FirefoxOptions options = new FirefoxOptions();

		options.addArguments("disable-infobars");
		
		options.setAcceptInsecureCerts(true);

		return options;

	}

	/***
	 * 
	 * Set EdgeOptions
	 * 
	 * @return options
	 * 
	 */

	private EdgeOptions setEdgeOptions() {

		EdgeOptions options = new EdgeOptions();

		options.addArguments("disable-infobars");

		options.setAcceptInsecureCerts(true);

		return options;

	}

	/***
	 * 
	 * Set Internet Explorer Options
	 * 
	 * @return options
	 * 
	 */

	private InternetExplorerOptions setIEOptions() {

		InternetExplorerOptions options = new InternetExplorerOptions();

		options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);

		options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);

		options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

		options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

		options.setCapability(InternetExplorerDriver.

				INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		return options;

	}

	public ChromeOptions getChromeOptions() throws IOException {
		ChromeOptions coptions = new ChromeOptions();
		if (Boolean.parseBoolean(readPropertyValue("headless"))) {
			System.out.println(".....Running the test in Headless mode.......");
			coptions.addArguments("--headless=new");
		}
		if (Boolean.parseBoolean(readPropertyValue("incognito"))) {
			System.out.println(".....Running the test in Incognito mode.......");
			coptions.addArguments("--incognito");
		}
		return coptions;
	}

	public FirefoxOptions getFirefoxOptions() throws IOException {
		FirefoxOptions ffoptions = new FirefoxOptions();
		if (Boolean.parseBoolean(readPropertyValue("headless"))) {
			System.out.println(".....Running the test in Headless mode.......");
			ffoptions.addArguments("--headless=new");
			
		}
		if (Boolean.parseBoolean(readPropertyValue("incognito"))) {
			System.out.println(".....Running the test in Incognito mode.......");
			ffoptions.addArguments("--incognito");
		}
		return ffoptions;
	}

	/**
	 * 
	 * This method reads the property value from properties file
	 * 
	 * 
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 */

	public static String readPropertyValue(String key) throws IOException {

		log.info("Create Object for Properties class");

		Properties prop = new Properties();

		log.info("Read the properties file");

		try {

			FileInputStream fis = new FileInputStream(new File(Constants.CONFIG_DIRECTORY));

			prop.load(fis);

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return prop.getProperty(key);

	}

	public static String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public static String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
/**
 * generate the random email
 * 
 */
	public static String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);

		return (st + "@" + num);
	}

	public static String getScreenshot(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = Constants.USER_DIRECTORY + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}

}