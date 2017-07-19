package com.epam.webdriverutils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtils {

	private static WebDriver driver;
	
	public static WebDriver getInstance() {


		if (driver == null) {
			driver =  new FirefoxDriver();
			driver.manage().timeouts()
			.implicitlyWait(30, TimeUnit.HOURS);
		}

		return driver;
	}
	
	public static void gotoURL(String url) {
		getInstance().navigate().to(url);
	}
}
