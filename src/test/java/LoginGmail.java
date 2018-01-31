import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginGmail {
    private static WebDriver driver;
    private static String DRIVER_URL = "C:\\Users\\admin\\eclipse-workspace\\GmailTest\\src\\main\\resources\\chromedriver.exe";
    private static String URL = "https://accounts.google.com/signin";
    private static String YOUR_EMAIL = "homeworkbohdan@gmail.com";
    private static String PASSWORD = "719113719113";
    private static String SEND_TO = "homeworkbohdan@gmail.com";
    private static String SUBJECT = "TestTwo";
    private static String MESSAGE = "Test message";

    @BeforeClass
    public static void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", DRIVER_URL);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void openLogin() {
        WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email_phone.sendKeys(YOUR_EMAIL);
        driver.findElement(By.xpath("//content[@class='CwaK9']")).click();
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys(PASSWORD);
        driver.findElement(By.className("CwaK9")).click();
        driver.findElement(By.className("WaidBe")).click();

        WebElement send = driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']"));
        send.click();
        driver.findElement(By.xpath("//textarea[@class='vO']")).sendKeys(SEND_TO);
        driver.findElement(By.xpath("//input[@class='aoT']")).sendKeys(SUBJECT);
        driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys(MESSAGE);

        WebElement sendButton = driver.findElement(By.xpath("//div[@class='J-J5-Ji btA']"));
        sendButton.click();

        driver.findElement(By.linkText("Sent Mail")).click();

        String messageElement = driver.findElement(By.xpath("//*[@class='bog']//*[text()='TestTwo']")).getText();
        Assert.assertEquals("TestTwo", messageElement);
        
        driver.findElement(By.xpath("//td[@class='oZ-x3 xY']")).click();
        driver.findElement(By.xpath("//div[@gh='mtb']//div[@act='10']")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='alertdialog']//button[@name='ok']"))).click();
    }
}
