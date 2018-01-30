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
    private static String YOUR_EMAIL = "dovbysh.bohdan@gmail.com";
    private static String PASSWORD = "FuckOff666666719113";
    private static String SEND_TO = "dovbysh.bohdan@gmail.com";
    private static String SUBJECT = "Test";
    private static String MESSAGE = "Test message";

    @BeforeClass
    public static void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", DRIVER_URL);
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void openLogin() {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email_phone.sendKeys(YOUR_EMAIL);
        driver.findElement(By.xpath("//content[@class='CwaK9']")).click();
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys(PASSWORD);
        driver.findElement(By.className("CwaK9")).click();
        driver.findElement(By.className("WaidBe")).click();
    }

    @Test
    public void fillToMessage() {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement send = driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        send.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//textarea[@id=':mp']")).sendKeys(SEND_TO);
        driver.findElement(By.xpath("//input[@class='aoT']")).sendKeys(SUBJECT);
        driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys(MESSAGE);
    }
    @Test
    public void clickSendButton(){
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement sendButton =  driver.findElement(By.xpath("//div[@class='J-J5-Ji btA']"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        sendButton.click();
    }
//    @Test
//    public void removeFromSend(){
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//        WebElement sendFolder = driver.findElement(By.xpath("//div[@class='aio UKr6le']"));
//        sendFolder.click();
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//        WebElement myMessage = driver.findElement(By.linkText("мені"));
//        myMessage.click();
//    }
}
