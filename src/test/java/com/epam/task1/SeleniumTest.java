package com.epam.task1;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTest implements Variables{
    private static WebDriver driver;

    @BeforeClass
    public static void launch() {
        System.setProperty("webdriver.chrome.driver", DRIVER_URL);
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void writeLetter() {

        driver.manage().window().maximize();

        WebElement email = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email.clear();
        email.sendKeys(LOGIN);
        driver.findElement(By.id("identifierNext")).click();

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));

        password.clear();
        password.sendKeys(PASSWORD);
        driver.findElement(By.id("passwordNext")).click();

        WebElement gmail = driver.findElement(By.className("WaidBe"));
        wait.until(ExpectedConditions.elementToBeClickable(gmail));
        gmail.click();


        WebElement compose = driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']"));
        wait.until(ExpectedConditions.elementToBeClickable(compose));
        compose.click();

        driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(RECEIVER);
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(SUBJECT);
        driver.findElement(By.xpath("//div[@aria-multiline='true']")).sendKeys(MESSAGE);


        driver.findElement(By.cssSelector("div[data-tooltip*='Enter']")).click();

        driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#sent']")).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='main']//div[@role='checkbox']")));


        List<WebElement> checkboxs = driver.findElements(By.xpath("//div[@role='main']//div[@role='checkbox']"));
        checkboxs.get(0).click();

        driver.findElement(By.xpath("//div[@gh='mtb']//div[@act='10']")).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='alertdialog']//button[@name='ok']"))).click();

        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@role='button']"))).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#trash']"))).click();
        Assert.assertEquals(SUBJECT, driver.findElement(By.xpath("//*[@class='bog']//*[text()='" + SUBJECT + "']")).getText());
    }
    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
    
}
