package com.qa.opencart.listeners;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.opencart.factory.WebDriverFactory;


public class ExtentReportListener implements ITestListener {


	private static ExtentReports extent = ExtentManager.init();
	public static ExtentTest test;
	
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
		
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		/*
		 * try { URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 * 
		 * // Create the email message 
		 * ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com"); 
		 * email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("rameshqaonline@gmail.com","password")); 
		 * email.setSSLOnConnect(true);
		 * email.setFrom("rameshqaonline@gmail.com"); //Sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find Attached Report....");
		 * email.addTo("rameshn3@gmail.com"); //Receiver 
		 * email.attach(url, "extent report", "please check report..."); 
		 * email.send(); // send the email 
		 * }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
	}

	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(methodName,result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());

		extentTest.assignCategory(className);
		
	}

	public synchronized void onTestSuccess(ITestResult result) {
		 String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " passed!"));
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
		
		try {
			String imgPath = WebDriverFactory.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public synchronized void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " failed!"));
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

		
		  try { String imgPath = WebDriverFactory.getScreenshot(result.getName());
		  test.addScreenCaptureFromPath(imgPath); } catch (IOException e1) {
		  e1.printStackTrace(); }
		 

		/*
		 * try { test.fail(result.getThrowable(),
		 * MediaEntityBuilder.createScreenCaptureFromPath(WebDriverFactory.getScreenshot
		 * (methodName)).build()); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		test.getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " skipped!"));
		
		try {
			String imgPath = WebDriverFactory.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		test.getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}