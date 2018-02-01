package com.epam.lab;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.lab.GlobalConfiguration.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*Task 6

1.Open gmail & login
2.Click on compose button
3.Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button
4.Verify that warning message appears
5.Click “OK” & enter correct email address & click send
6.Verify that message is moved to “Sent mail” folder*/
public class GmailTest {
    private static WebDriver driver;


    @BeforeClass
    public static void open() {
        driver = new ChromeDriver();
        loadProperties();
        loadData();
    }

    @Test
    public void chromeTest() {
        //  1.Open gmail & login
        System.setProperty(NAME_DRIVER, PATH_DRIVER);
        driver.navigate().to(URL);
        WebElement element = driver.findElement(By.xpath("//input[@type='email']"));
        element.sendKeys(MAIL);
        WebDriverWait wait = new WebDriverWait(driver, 40);
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Next']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", el);
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));
        element.sendKeys(PASSWORD);
        el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Next']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", el);

        //   2.Click on compose button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='z0']"))).click();

        //3.Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("to")));
        element.sendKeys(INCORRECT_MAIL);
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("subjectbox")));
        element.sendKeys(SUBJECT);
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@role=\"textbox\"]")));
        element.sendKeys(MESSAGE);
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='J-J5-Ji btA']")));
        element.click();

        //4.Verify that warning message appears
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='OK']")));
        assertTrue(element.isEnabled());

        //5.Click “OK” & enter correct email address & click send
        element.click();
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='aoD hl']")));
        element.click();
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='vM']")));
        element.click();
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("to")));
        element.sendKeys(TARGET_MAIL);
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='J-J5-Ji btA']")));
        element.click();

        // 6.Verify that message is moved to “Sent mail” folder
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href=\"https://mail.google.com/mail/#sent\"]")));
        element.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='BltHke nH oy8Mbf'][@role='main']"))).click();
        el = element.findElement(By.xpath("//span[@class='g2']"));
        assertEquals(TARGET_MAIL, el.getAttribute("email"));
    }

    @AfterClass
    public static void close() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}