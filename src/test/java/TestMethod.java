import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestMethod {
    private static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void SomeStrangeActionsWithGmail() {
        driver.get("https://gmail.com");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement emailInput = driver.findElement(By.id("identifierId"));
        emailInput.sendKeys("bread.with.batter@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("qwezxcasd123");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('passwordNext').click()");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//textarea[@name='to']"))).sendKeys("roman.shmandrovskyj.ki.2014@gmail.com");
        driver.findElement(By.xpath("//span[@class='aB gQ pE']")).click();
        driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys("sh-roman@online.ua");
        driver.findElement(By.xpath("//span[@class='aB  gQ pB']")).click();
        driver.findElement(By.xpath("//textarea[@name='bcc']")).sendKeys("bread.with.batter@gmail.com");
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("First selenium automation test!");
        driver.findElement(By.xpath("//div[@class=\"Am Al editable LW-avf\"]")).sendKeys("bla bla bla");
        driver.findElement(By.xpath("//img[@alt='Close']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/#drafts']")).click();
        (new WebDriverWait(driver, 3))
                .until(ExpectedConditions.textToBePresentInElementValue(By
                                .xpath("//input[@name='q']"), "in:draft "));
        driver.findElements(By.xpath("//table[@class='F cf zt']/tbody/tr[@class='zA yO']")).get(1).click();
        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")).click();
    }

}
