package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumExample {

    public static void main(String[] args) {

        // Set path to drivers executable
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer1.exe");

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        List<WebElement> element = driver.findElements(By.name("fsjkdhfjksdhjkh"));

        // Enter something to search for
        element.get(0).sendKeys("KIA!");

        // Now submit the form. WebDriver will find the form for us from the
        // element
        element.get(0).submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(
                (ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("cheese!"));

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        // Close the browser
        driver.quit();

        ((JavascriptExecutor) driver).executeScript("agruments[0].click();", element.get(0));

        new Actions(driver).clickAndHold(element.get(0)).click().contextClick().doubleClick().release().perform();


    }

}
