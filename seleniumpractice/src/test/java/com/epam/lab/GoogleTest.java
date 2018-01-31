package com.epam.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleTest {
    private WebDriver webDriver;

    @Test
    public void gmailTest() {
        System.setProperty( "webdriver.chrome.driver", "src/resourses/chromedriver.exe" );
        webDriver = new ChromeDriver();
        webDriver.get( "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false&flowName=GlifWebSignIn&flowEntry=ServiceLogin" );
        webDriver.manage().timeouts().implicitlyWait( 45, TimeUnit.SECONDS );
        webDriver.manage().window().maximize();
        WebElement enterEmail = webDriver.findElement( By.xpath( "//*[@id='identifierId']" ) );
        enterEmail.sendKeys( "annrusnaktest12345@gmail.com" );
        webDriver.findElement( By.xpath( "//*[@id='identifierNext']/content/span" ) ).click();
        WebElement password = webDriver.findElement( By.xpath( ".//*[@id='password']//descendant::input" ) );
        WebDriverWait wait = new WebDriverWait( webDriver, 20 );
        password.sendKeys( "annatest123" );
        wait.until( ExpectedConditions.elementToBeClickable( password ) );
        WebElement nextButton = webDriver.findElement( By.xpath( ".//*[@id='passwordNext']//descendant::span" ) );
        Actions builder = new Actions( webDriver );
        builder.click( nextButton ).build().perform();
        List<WebElement> checkbox = webDriver.findElements( By.xpath( ".//*[@role='checkbox']" ) );
        for (int i = 1; i < 4; i++) {
            if (!checkbox.get( i ).isSelected()) {
                checkbox.get( i ).click();
            }
        }
        WebElement delete = webDriver.findElement( By.xpath( "//div[@act='10']" ) );
        delete.click();
        WebElement undo = webDriver.findElement( By.xpath( ".//*[@id='link_undo']" ) );
        wait.until( ExpectedConditions.elementToBeClickable( undo ) );
        undo.click();
        WebElement canceled = webDriver.findElement( By.xpath( ".//*[@class='bofITb']" ) );
        wait.until( ExpectedConditions.elementToBeClickable( canceled )  );
        int expectedResult = 0;
        List<WebElement> sub = webDriver.findElements( By.xpath( "//*[@class='bog']" ) );
        for (WebElement el: sub) {
            if (el.getText().equals( "HomePod. The new sound of home." )) {
                expectedResult += 1;
            }
        }
        Assert.assertNotNull(  expectedResult );
    }
    @AfterMethod
    public final void tearDown() {
        webDriver.quit();
    }
}
