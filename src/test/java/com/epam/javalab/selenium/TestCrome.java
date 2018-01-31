package com.epam.javalab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCrome {
    private final static String login = "olenkaklyuka@gmail.com";
    private final static String Password = "Summers98@";
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://Program Files//Chrome Driver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://gmail.com");
    }

    @Test(priority = 1)
    public void testLogin() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement loginElement = driver.findElement(By.xpath("//input[@type='email']"));
        loginElement.sendKeys(login);
        driver.findElement(By.id("identifierNext")).click();
    }

    @Test(priority = 2)
    public void testPassword() {
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));

        password.sendKeys(Password);
        driver.findElement(By.id("passwordNext")).click();
    }

    @Test(priority = 3)
    public void testImportantLetters() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='checkbox']")));

        List<WebElement> checkboxs = driver.findElements(By.xpath("//div[@role='checkbox']"));
        List<String> idList = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            checkboxs.get(i).click();
            idList.add(checkboxs.get(i).getAttribute("id"));
        }
        driver.findElement(By.className("bjy")).click();
        driver.findElement(By.xpath("//div[text()='Add star']")).click();
        driver.findElement(By.className("nU")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='checkbox']")));

        List<WebElement> checkboxStared = driver.findElements(By.xpath("//div[@role='checkbox']"));
        List<String> idListStared = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            checkboxStared.get(i).click();
            idListStared.add(checkboxStared.get(i).getAttribute("id"));
        }
        Assert.assertEquals(idList, idListStared);
    }

    @Test(priority = 4)
    public void testDelete() {
        List<WebElement> checkboxDelete = driver.findElements(By.xpath("//div[@role='checkbox']"));
        for (int i = 0; i < 3; i++) {
            checkboxDelete.get(i).click();
        }
        driver.findElement(By.xpath("//div[@act='10']")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}