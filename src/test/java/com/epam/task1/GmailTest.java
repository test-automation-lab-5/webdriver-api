package com.epam.task1;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class GmailTest {
    private static final Logger log = Logger.getLogger(GmailTest.class);

    private final static String USER_MAIL = "mixer.log4j@gmail.com";
    private final static String USER_PASSWORD = "Kids12345a";
    private final static int ELEMENTS_COUT = 3;

    /*
    Common:
    1. Create Maven project
    2. Use TestNG & Selenium to develop tests
    3. One task == one test method
    4. Use xml as data source for data
    5. Use property file(s) for properties

    Task 4
    1. Open gmain & login                                       +
    2. Select 3 messages from inbox using checkboxes
    3. Click on “delete” button
    4. Click on undo button
    5. Verify that messages are not deleted
    mixer.log4j@gmail.com
    Kids12345a
    */
    @Test
    public void LoginCheckDeleteUndo() {
        log.info("LoginCheckDeleteUndo start");
        WebDriver driver = null;
        try {
            log.info("Init driver");
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            log.info("Open browser page");
            driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
            log.info("Type mail");
            driver.findElement(By.xpath("//input[@type='email']")).sendKeys(USER_MAIL + "\n");
            log.info("Type password");
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']"))).sendKeys(USER_PASSWORD + "\n");
            log.info("Select 3 messages from inbox using checkboxes");
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='checkbox']")));
            List<WebElement> checkboxs = driver.findElements(By.xpath("//div[@role='checkbox']"));
            List<String> verifyIdList = new ArrayList<>();
            for (int i = 0; i < ELEMENTS_COUT && i < checkboxs.size(); i++) {
                checkboxs.get(i).click();
                verifyIdList.add(checkboxs.get(i).getAttribute("id"));
            }
            log.info("Click on delete button");
            driver.findElement(By.xpath("//div[@act='10']")).click();

            log.info("Click on undo button");
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("link_undo"))).click();
            log.info("Verify that messages are not deleted");
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                            "//div[@aria-live='assertive'][@aria-atomic='true']/div/div/span")));
            List<WebElement> checkboxsVerify = driver.findElements(By.xpath("//div[@role='checkbox']"));
            for (int i = 0; i < ELEMENTS_COUT && i < checkboxs.size(); i++)
                if (!verifyIdList.get(i).equals(checkboxsVerify.get(i).getAttribute("id")))
                    throw new AssertionError("Restored Elements not found");
        } finally {
            if (!Objects.isNull(driver)) {
                log.info("Close driver");
                driver.close();
            }
        }
    }
}