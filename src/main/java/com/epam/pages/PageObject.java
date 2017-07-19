package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import com.epam.webdriverutils.WebDriverUtils;

public class PageObject {

	public PageObject(WebDriver driver) {
		WebDriver instance = WebDriverUtils.getInstance();
		PageFactory.initElements(new DefaultElementLocatorFactory(instance), this);
	}

	public PageObject() {
		this(WebDriverUtils.getInstance());
	}
}
