package test.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class IntermediateTests {

	WebDriver driver;

	ExtentReports report;
	ExtentTest test;

	@Before
	public void setup() {
		driver = chooseBrowser(Browser.CHROME);

	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void seeQA() throws InterruptedException {
		driver.get("http://www.qa.com");
		assertEquals("https://www.qa.com/", driver.getCurrentUrl());
		Thread.sleep(1000);
	}

	@Test
	public void seeDemoSite() throws InterruptedException {

		driver.get("http://thedemosite.co.uk");
		Thread.sleep(2000);

		driver.findElement(By.linkText("3. Add a User")).click();

		Thread.sleep(1500);

		driver.findElement(By.name("username")).sendKeys("John");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[2]/small/a"))
				.click();

		driver.findElement(By.name("username")).sendKeys("John");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("FormsButton2")).click();

		assertEquals("**Successful Login**",
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"))
						.getText());
		Thread.sleep(2000);
	}

	@Test
	public void demoSiteReport() {

		report = new ExtentReports("C:/Users/JB227/Desktop/Selenium/report.html", true);

		test = report.startTest("Logging in to demoSite");

		test.log(LogStatus.INFO, "Browser started");

		driver.get("http://thedemosite.co.uk");

		test.log(LogStatus.INFO, "On page");

		driver.findElement(By.linkText("3. Add a User")).click();
		test.log(LogStatus.INFO, "On register page");

		driver.findElement(By.name("username")).sendKeys("John");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("FormsButton2")).click();
		test.log(LogStatus.INFO, "Registered account");

		driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[2]/small/a"))
				.click();

		test.log(LogStatus.INFO, "On login page");
		driver.findElement(By.name("username")).sendKeys("John");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("FormsButton2")).click();
		test.log(LogStatus.INFO, "Logging in");

		if ("**Successful Login**".equals(
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"))
						.getText())) {

			test.log(LogStatus.PASS, "Login successful");
		} else {

			test.log(LogStatus.FAIL, "Login unsuccessful");
		}

		report.endTest(test);

		report.flush();
	}

	@Test
	public void demoSiteReportExcel() {

		// close driver created by @Before
		driver.quit();

		ExcelReader reader = new ExcelReader("C:/Users/JB227/Desktop/Selenium/info.xlsx");

		while (!reader.endFile()) {


			List<String> info = reader.readRow();

			if (info.size() < 3)
				fail("Bad excel file");

			String uName = info.get(0);
			String password = info.get(1);
			String browser = info.get(2);

			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			report = new ExtentReports("C:/Users/JB227/Desktop/Selenium/report_excel_"+ browser + "_" + timeStamp +".html", true);

			test = report.startTest("Logging in to demoSite");
			
			try {
				runTests(uName, password, makeBrowser(browser));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				driver.quit();
			}
		
			report.endTest(test);
	
			report.flush();
		}
	}
	
	private void runTests(String uName, String password, Browser browser) throws InterruptedException, IOException {
		test.log(LogStatus.INFO, "Starting browser " + browser);
		driver = chooseBrowser(browser);

		test.log(LogStatus.INFO, "Going to website");
		driver.get("http://thedemosite.co.uk");

		test.log(LogStatus.INFO, "Navigating to register page");
		driver.findElement(By.linkText("3. Add a User")).click();

		test.log(LogStatus.INFO, "Registering account");
		driver.findElement(By.name("username")).sendKeys(uName);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(250);
		driver.findElement(By.name("FormsButton2")).click();

		test.log(LogStatus.INFO, "Navigating to login page");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[2]/small/a"))
				.click();

		test.log(LogStatus.INFO, "Logging in");
		driver.findElement(By.name("username")).sendKeys(uName);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(250);
		driver.findElement(By.name("FormsButton2")).click();


		if (driver
				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"))
				.getText().equals("**Successful Login**")) {
			test.log(LogStatus.PASS, "Login successful");
		}
		else {
			test.log(LogStatus.FAIL, "Login unsuccessful");
		}
		
		takeScreenShot(browser);
	}
	
	private void takeScreenShot(Browser browser) throws IOException {
		TakesScreenshot cam = (TakesScreenshot) driver;
		
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		File screenshot = cam.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\JB227\\Desktop\\Selenium\\screenshots\\" + browser + "_" + timeStamp +".png");
		
		FileUtils.copyFile(screenshot, dest);
		
		String image = test.addScreenCapture(dest.getAbsolutePath());
		
		test.log(LogStatus.INFO, "End screen", image);
	}
	
	private Browser makeBrowser(String browser) {
		try {
			return Browser.valueOf(browser.toUpperCase());
		} catch(Exception e) {
			return Browser.UNKNOWN;
		}
	}

	private static WebDriver chooseBrowser(Browser browser) {

		WebDriver driver;
		
		switch(browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case FIREFOX:
		default:
			System.setProperty("webdriver.gecko.driver", "C:/Driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		
		}

		return driver;

	}

}