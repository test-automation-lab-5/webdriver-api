package Selenium;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static junit.framework.TestCase.assertNotNull;

public class Test {
    private static WebDriver driver;
    private static String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    private static String URL = "http://mail.google.com";
    private static String EMAIL = "hnatko2@gmail.com";
    private static String PASSWORD = "hnatko222";
    private static String ADDRESSE = "kristinabilokura@gmail.com";
    private static String SUBJECT = "Selenium";
    private static String MESSAGE = "Test[0]";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void open() {
        System.setProperty("webdriver.chrome.driver",DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(URL);
    }
    @org.junit.Test
    public void Test() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.name("identifier"));
        element.sendKeys(EMAIL);
        element.sendKeys(Keys.ENTER);

        WebElement password = driver.findElement(By.name("password"));
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(password)).sendKeys(PASSWORD);
        password.sendKeys(Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();

        driver.findElement(By.className("vO")).sendKeys(ADDRESSE);
        driver.findElement(By.xpath("//input[@class='aoT']")).sendKeys(SUBJECT);
        driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys(MESSAGE);
        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")).click();
        driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#sent']")).click();
       // driver.findElement(By.linkText("Надіслані")).click();
        //Assert.assertFalse(null== driver.findElement(By.name("Kristina Bilokura")));
        assertNotNull(driver.findElement(By.xpath("//*[@email='" + String.format("%s", ADDRESSE) + "']")));

        Thread.sleep(1000);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='main']//div[@role='checkbox']"))).click();
        driver.findElement(By.xpath("//div[@gh='tm']//div[@act='10']")).click();
        driver.findElement(By.name("ok")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            driver.findElement(By.xpath("//*[@email='" + String.format("%s", ADDRESSE) + "']"));
            Assert.fail("expecting NoSuchElementException here");
        } catch (Exception e) {
        }
    }
    @AfterClass
    public static void quit(){
        driver.quit();
    }
}
