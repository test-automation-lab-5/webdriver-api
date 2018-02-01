import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestMethod {
    private static final String GMAIL = "https://gmail.com";
    private static final String EMAIL = "bread.with.batter@gmail.com";
    private static final String PASSWORD = "qwezxcasd123";
    private static final String TO = "roman.shmandrovskyj.ki.2014@gmail.com";
    private static final String CC = "sh-roman@online.ua";
    private static final String BCC = "bread.with.batter@gmail.com";
    private static Random random = new Random();
    private static WebDriver driver;
    private static String SUBJECT = "";
    private static String MESSAGE = "";


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        for (int i = 0; i < 20; i++) {
            SUBJECT += (char) (random.nextInt(78) + 48);
        }

        for (int i = 0; i < 50; i++) {
            MESSAGE += (char) (random.nextInt(78) + 48);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void SomeStrangeActionsWithGmail() {
        driver.get(GMAIL);
        driver.findElement(By.xpath("//input[@type='email' and @class='whsOnd zHQkBf']")).sendKeys(EMAIL);
        driver.findElement(By.xpath("//div[@class='O0WRkf zZhnYe e3Duub C0oVfc Zp5qWd Hj2jlf dKVcQ']")).click();
        driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys(PASSWORD);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('passwordNext').click()");

        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//textarea[@name='to']"))).sendKeys(TO);
        
        driver.findElement(By.xpath("//span[@class='aB gQ pE']")).click();
        driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys(CC);
        driver.findElement(By.xpath("//span[@class='aB  gQ pB']")).click();
        driver.findElement(By.xpath("//textarea[@name='bcc']")).sendKeys(BCC);
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(SUBJECT);
        driver.findElement(By.xpath("//div[@class=\"Am Al editable LW-avf\"]")).sendKeys(MESSAGE);
        driver.findElement(By.xpath("//img[@alt='Close']")).click();

        driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/#drafts']")).click();
        (new WebDriverWait(driver, 3))
                .until(ExpectedConditions.textToBePresentInElementValue(By
                        .xpath("//input[@name='q']"), "in:draft "));

        WebElement subject = driver.findElement(By.xpath("//table[@cellpadding='0']/tbody/tr/td[@class='xY a4W']/div/div/div/span[text()='" + SUBJECT + "']"));
        subject.click();

        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")).click();

        Assert.assertEquals(subject.getText(), SUBJECT);
    }

}
