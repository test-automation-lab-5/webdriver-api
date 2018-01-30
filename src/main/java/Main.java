import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\eclipse-workspace\\GmailTest\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://accounts.google.com/signin";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email_phone.sendKeys("dovbysh.bohdan@gmail.com");
        driver.findElement(By.xpath("//content[@class='CwaK9']")).click();
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys("FuckOff666666719113");
        driver.findElement(By.className("CwaK9")).click();
        driver.findElement(By.className("WaidBe")).click();
        driver.findElement(By.className("z0")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//textarea[@id=':mp']")).sendKeys("dovbysh.bohdan@gmail.com");
        driver.findElement(By.xpath("//input[@class='aoT']")).sendKeys("test");
        driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys("TestMessage");

    }
}
