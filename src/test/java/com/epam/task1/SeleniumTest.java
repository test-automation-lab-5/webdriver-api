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

public class SeleniumTest {
    private static WebDriver driver;
    private static String URL = "https://accounts.google.com/signin";
    private static String DRIVER_URL = "src/main/resources/chromedriver.exe";
    private static String LOGIN = "jamesdaw11101993@gmail.com";
    private static String PASSWORD = "7483145okokokokok";
    private static String RECEIVER = "jamesdaw11101993@gmail.com";
    private static String SUBJECT = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    private static String MESSAGE = "test message";

    @BeforeClass
    public static void launch() {
        System.setProperty("webdriver.chrome.driver", DRIVER_URL);
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void writeLetter() {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#sent']")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='main']//div[@role='checkbox']")));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertEquals(SUBJECT, driver.findElement(By.xpath("//*[@class='bog']//*[text()='" + SUBJECT + "']")).getText());

        List<WebElement> checkboxs = driver.findElements(By.xpath("//div[@role='main']//div[@role='checkbox']"));
        checkboxs.get(0).click();

        driver.findElement(By.xpath("//div[@gh='mtb']//div[@act='10']")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='alertdialog']//button[@name='ok']"))).click();
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}
