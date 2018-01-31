import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GmailTask6Test {
        private static WebDriver driver;

        @BeforeClass
        public static void setUp(){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            driver = new ChromeDriver();
        }

        @Test
        public void testChromeSelenium() {

            // 1. Open gmail & login

            driver.manage().window().maximize();
            String url = "https://accounts.google.com/signin";
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
            email_phone.sendKeys("olenayurkiv2017@gmail.com");
            driver.findElement(By.id("identifierNext")).click();
            WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(password));
            password.sendKeys("09667505Mm");
            driver.findElement(By.id("passwordNext")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("a[class='WaidBe']")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            String emailsubject="Email subject";
            String tomailidWrong ="ole";
            String tomailid ="olenayurkiv2017@gmail.com";
            String mailbody ="Mailbody";

            // 2. Click on compose button

            driver.findElement(By.xpath("//div[@class='z0']/div")).click();

            // 3. Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button

            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//td//img[2]")).click();
            driver.findElement(By.className("vO")).sendKeys(tomailidWrong);
            driver.findElement(By.className("aoT")).sendKeys(emailsubject);
            driver.findElement(By.cssSelector("div[class='Am Al editable LW-avf']")).sendKeys(mailbody);
            driver.findElement(By.xpath("//div[text()='Отправить']")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // 4. Verify that warning message appears

            String expectedError= "Проверьте правильность ввода всех адресов.";
            String actualError= driver.findElement(By.cssSelector("div[class='Kj-JD-Jz']")).getAttribute("innerHTML");
            System.out.println(String.format("Error message: %s", actualError));
            Assert.assertTrue(actualError.contains(expectedError));

            // 5. Click “OK” & enter correct email address & click send

            driver.findElement(By.cssSelector("button[name='ok']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            WebElement element = driver.findElement(By.xpath("//div[@class='oh J-Z-I J-J5-Ji T-I-ax7']/div"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click()", element);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//div[@class='z0']/div")).click();

            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//td//img[2]")).click();
            driver.findElement(By.className("vO")).sendKeys(tomailid);
            driver.findElement(By.className("aoT")).sendKeys(emailsubject);
            driver.findElement(By.cssSelector("div[class='Am Al editable LW-avf']")).sendKeys(mailbody);
            driver.findElement(By.xpath("//div[text()='Отправить']")).click();


            // 6. Verify that message is moved to “Sent mail” folder

            driver.findElement(By.xpath("//a[@title='Отправленные']")).click();
            if (driver.findElement(By.xpath("//div[@class='y6']//b[text()='"+emailsubject+"']")) != null)
            {
                System.out.println("Email was sent successfully!");
            }
            else
            {
                System.out.println("Failed to send email");
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            
        }

        @AfterClass
        public static void cleanUp(){
            if (driver != null) {
                driver.close();
                driver.quit();
            }

        }
}
