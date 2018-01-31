package edu.epam.seleniumexample;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GMailImportentLetter{

    @Test
    public void markImportentLettersInGMailTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//      System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        WebDriver driver = new ChromeDriver();
//      WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/gmail/about");
        driver.findElement(By.cssSelector(".gmail-nav__nav-link.gmail-nav__nav-link__sign-in")).click();
        driver.findElement(By.xpath("//*[@name='identifier']")).sendKeys("chkirchyk");

        driver.findElement(By.cssSelector(".RveJvd.snByac")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")))
                .sendKeys("strilka27");
        driver.findElement(By.id("passwordNext")).click();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        List<WebElement> elementsArr = driver.findElements(By.xpath("//div[contains(@class,'bnj')]"));
        for (int i = 0; i < 3; i++) {
            elementsArr.get(i).click();
        }
        driver.findElement(By.xpath("//a[@title='Важливі']")).click();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.quit();
    }
}
