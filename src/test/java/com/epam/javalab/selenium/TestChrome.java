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

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestChrome {
    private final static String login = "olenkatestepam@gmail.com";
    private final static String Password = "testepam";
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
        WebElement loginElement = driver.findElement(By.xpath("//input[@type='email']"));
        loginElement.sendKeys(login);
        driver.findElement(By.id("identifierNext")).click();
    }

    @Test(priority = 2)
    public void testPassword() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));

        password.sendKeys(Password);
        driver.findElement(By.id("passwordNext")).click();
    }

    @Test(priority = 3)
    public void testImportantLetters() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='checkbox']")));

        List<WebElement> checkboxs = driver.findElements(By.xpath("//div[@role='checkbox']"));
        for (int i = 0; i < 3; i++) {
            checkboxs.get(i).click();
        }
        List<String>idList=(checkboxs.stream().map(x->x.getAttribute("id")).collect(Collectors.toList()));

        driver.findElement(By.className("bjy")).click();
        driver.findElement(By.xpath("//div[text()='Add star']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.className("nU")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='checkbox']")));
        List<WebElement> stared = driver.findElements(By.xpath("//div[@role='checkbox']"));
        for (int i = 0; i < 3; i++) {
            stared.get(i).click();
        }
        List<String>idStared=(stared.stream().map(x->x.getAttribute("id")).collect(Collectors.toList()));
        Assert.assertEquals(idStared, idList);
    }

    @Test(priority = 4)
    public void testDelete() {
        List<WebElement> cToDelete = driver.findElements(By.xpath("//div[@role='checkbox']"));
        for (int i = 0; i < 3; i++) {
            cToDelete.get(i).click();
        }
        //List<String>toDelete=(cToDelete.stream().map(x->x.getAttribute("id")).collect(Collectors.toList()));
        driver.findElement(By.xpath("//div[@act='10']")).click();

        driver.findElement(By.className("CJ")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://mail.google.com/mail/#trash']"))).click();
/*
        List<WebElement> deleted = driver.findElements(By.xpath("//div[@role='checkbox']"));
        for (int i = 0; i < 3; i++) {
            deleted.get(i).click();
        }
        List<String>idDeleted=(deleted.stream().map(x->x.getAttribute("id")).collect(Collectors.toList()));
        Assert.assertEquals(idDeleted, toDelete);*/
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}