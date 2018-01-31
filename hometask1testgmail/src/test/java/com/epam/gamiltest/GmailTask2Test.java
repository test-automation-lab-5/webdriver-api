package com.epam.gamiltest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;





public class GmailTask2Test {

	private static final String DRIVER_TYPE = "webdriver.chrome.driver";
	private static final String DRIVER_PATH = "src/main/resources/chromedriver.exe";
	private static final String WEB_SITE_URL = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
	private static final String MY_LOGIN = "ForHometaskTest";
	private static final String MY_PASSWORD = "12345aKids";
	private static final String SENT_TO = "ForHometaskTest@gmail.com";
	private static WebDriver driver;
	
	@org.junit.Rule
	public TestWatcher watchman = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			System.out.println(String.format("Method: \"%s\" - failed", description.getMethodName()));
		}

		@Override
		protected void succeeded(Description description) {
			System.out.println(String.format("Method: \"%s\" - succeeded", description.getMethodName()));
		}
	};

	@Before
	public void setUp() throws Exception {
		System.setProperty(DRIVER_TYPE, DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(WEB_SITE_URL);
	}

	@Test
	public void testGmailDrafts() {
		driver.findElement(By.xpath("//input[@type='email'][@name='identifier']")).sendKeys(MY_LOGIN);
		driver.findElement(By.xpath("//div[@role='button']//span[contains(text(),'Next')]")).click();
		WebElement myElement = driver.findElement(By.xpath("//input[@type='password'][@name='password']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("elem=arguments[0]; elem.setAttribute('type','text'); ", myElement);
		myElement.sendKeys(MY_PASSWORD);
		driver.findElement(By.id("passwordNext")).click();
		driver.findElement(By.xpath("//div[@role='button'][contains(text(),'COMPOSE')]")).click();
		WebElement elementToSent = driver
				.findElement(By.xpath("//form[@enctype='multipart/form-data']/div[1]/table[1]//textarea"));
		elementToSent.sendKeys(SENT_TO);
		elementToSent.click();
		driver.findElement(By.xpath("//div[@aria-label='Message Body']")).click();
		WebElement elementSubject = driver
				.findElement(By.xpath("//form[@enctype='multipart/form-data']//input[@name='subjectbox']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(elementSubject).build().perform();
		elementSubject.sendKeys("Test");
		WebElement elementMessage = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
		builder.moveToElement(elementMessage).click().sendKeys("Message text. Massage text").perform();
		WebElement elementCloser = driver.findElement(By.xpath("//img[@aria-label='Save & Close']"));
		elementCloser.click();
		driver.get("https://mail.google.com/mail/u/0/#drafts");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.findElement(By.xpath("//div[contains(text(),'Message text. Massage text')]"));
		WebElement elementTableTr = driver
				.findElement(By.xpath("//div[contains(text(),'Message text. Massage text')]/parent::td/parent::tr"));
		WebElement elementLinkDiv = driver
				.findElement(By.xpath("//div[@role='link'][contains(.,'Message text. Massage text')]"));
		builder.moveToElement(elementLinkDiv).click().perform();

		WebElement elementSentButton = driver.findElement(By.xpath(
				"//form[@enctype='multipart/form-data']/following-sibling::table//div[@role='button'][contains(text(),'Send')]"));
		elementSentButton.click();
		driver.get("https://mail.google.com/mail/u/0/#sent");
		List<WebElement> elementsDrafts = driver
				.findElements(By.xpath("//div[contains(text(),'Message text. Massage text')]"));
		assertTrue("Draft has been sended",(elementsDrafts.size()> 0));
	}
	
	 @After
     public void tearDown(){
         if (driver != null) {
             driver.close();
             driver.quit();
}
	 }

}
