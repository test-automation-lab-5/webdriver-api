package com.epam.lab5;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

/*
Task 2
1. Open gmail & login
2. Click “Compose” button
3. Fill “To”, “Subject” & “message” fields
4. Close “new message” window
5. Verify that message is saved as draft
6. Open message from the draft folder & send

* */

public class TestDriverEmail {

    private final static String MAIL = "horinvan@gmail.com";
    private final static String PASSWORD = "fromrome12";
    private final static String MESSAGE_SUBJECT="kkllmm1";

    private static final Logger log = Logger.getLogger(TestDriverEmail.class);

    @Test
    public void gmailLoginVerifySendTest() {

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        log.info("Driver was initialized.");

        driver.get("http://www.gmail.com");
        log.info("Web Page was opened.");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement emailInput = driver.findElement(By.name("identifier"));
        emailInput.sendKeys(MAIL);
        log.info("eMail was entered");
        driver.findElement(By.id("identifierNext")).click();
        log.info("eMail was submited");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(PASSWORD);
        log.info("Password was entered");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext")));
        driver.findElement(By.id("passwordNext")).click();
        log.info("Password was submited");

        driver.findElement(By.cssSelector(".aic .z0 div")).click();
        log.info("Compose button was clicked.");
        WebElement toField = driver.findElement(By.cssSelector(".oj div textarea"));
        toField.sendKeys("horinvan@gmail.com");
        log.info("'To' field was filled in.");
        WebElement subjectBox=driver.findElement(By.name("subjectbox"));
        subjectBox.sendKeys(MESSAGE_SUBJECT);
        log.info("'Subject' field was filled in.");
        WebElement messageField=driver.findElement(By.cssSelector(".Ar.Au div"));
        messageField.sendKeys("Some Text...");
        log.info("'Mail Text' field was filled in.");

        driver.findElement(By.cssSelector(".Ha")).click();
        log.info("'Compose' window was closed.");

        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".TN.GLujEb.aHS-bnq .aio.UKr6le span a")));
        driver.findElement(By.cssSelector(".TN.GLujEb.aHS-bnq .aio.UKr6le span a")).click();
        log.info("'Drafts' folder was opened.");

        assertEquals(MESSAGE_SUBJECT,driver.findElement(By.xpath(String.format("//span[@class='bog'][text()='%s']",
                MESSAGE_SUBJECT))).getText());
        log.info("Test was passed successfully.");

        driver.findElement(By.xpath(String.format("//span[@class='bog'][text()='%s']",MESSAGE_SUBJECT))).click();
        log.info("Mail from Draft was opened.");

        driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys(Keys.CONTROL,Keys.ENTER);
        log.info("Mail from Draft was sent.");

        driver.close();
        log.info("Driver was closed.");

    }
}
