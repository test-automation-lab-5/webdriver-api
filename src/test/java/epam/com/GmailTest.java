package epam.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GmailTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mail.google.com");
    }

    @Test
    public void manipulateGmail() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement login = driver.findElement(By.xpath("//input[contains(@type,'email')]"));

        login.sendKeys("ForHometaskTest@gmail.com");

        driver.findElement(By.id("identifierNext")).click();

        WebElement password = driver.findElement(By.xpath("//input[contains(@name,'password')]"));

        password.sendKeys("12345aKids");

        driver.findElement(By.id("passwordNext")).click();

        driver.findElement(By.xpath("//div[contains(@class, 'UI')]//table//tr[1]//td[4]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'UI')]//table//tr[2]//td[4]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'UI')]//table//tr[3]//td[4]")).click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'UI')]//div[contains(@role, 'tabpanel')]//span[contains(@class, 'bog')]//b")));

        List<String> titleList = new ArrayList<String>();
        List<WebElement> titles = driver.findElements(By.xpath("//div[contains(@class, 'UI')]//div[contains(@role, 'tabpanel')]//span[contains(@class, 'bog')]//b"));
        titleList.add(titles.get(0).getText());
        titleList.add(titles.get(1).getText());
        titleList.add(titles.get(2).getText());

        Thread.sleep(5000);

        driver.findElement(By.cssSelector("div.TK a[title='Important']")).click();


        Thread.sleep(2000);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'AO')]//div[contains(@role, 'main')]//div[@role='checkbox']")));

        List<WebElement> checkeds = driver.findElements(By.xpath("//div[contains(@class, 'AO')]//div[contains(@role, 'main')]//div[@role='checkbox']"));
        checkeds.get(0).click();
        checkeds.get(1).click();
        checkeds.get(2).click();

        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'AO')]//div[contains(@role, 'main')]//span[contains(@class, 'bog')]")));

        List<String> verifyList = new ArrayList<String>();
        List<WebElement> verifies = driver.findElements(By.xpath("//div[contains(@class, 'AO')]//div[contains(@role, 'main')]//span[contains(@class, 'bog')]"));
        verifyList.add(verifies.get(0).getText());
        verifyList.add(verifies.get(1).getText());
        verifyList.add(verifies.get(2).getText());

        Assert.assertEquals(titleList, verifyList, "Letters are present in Important.");

        driver.findElement(By.xpath("//div[@gh='mtb']//div[@act='10']")).click();

//        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[contains(@role, 'button')]")).click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.TK a[title='Bin']")));
        driver.findElement(By.cssSelector("div.TK a[title='Bin']")).click();

        List<String> deletedList = new ArrayList<String>();
        List<WebElement> deleted = driver.findElements(By.xpath("//div[contains(@class, 'AO')]//div[contains(@role, 'main')]//span[contains(@class, 'bog')]//b"));
        deletedList.add(deleted.get(0).getText());
        deletedList.add(deleted.get(1).getText());
        deletedList.add(deleted.get(2).getText());

        Assert.assertEquals(titleList, deletedList,"Was deleted.");
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}
