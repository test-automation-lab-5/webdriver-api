package com.epam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.control.Button;
import com.epam.control.Label;
import com.epam.control.TextInput;

import java.util.List;

public class GmailLoginPage extends PageObject {

	@FindBy(id = "identifierId")
	private TextInput loginInput;

	@FindBy(xpath = "//*[text()='Forgot email?']")
	private Label forgotEmailLink;

	@FindBy(xpath = "//*[text()='More options']")
	private Label moreOptionsLink;

	@FindBy(id = "identifierNext")
	private Button nextButton;

	private List<WebElement> webElementList;

	public GmailLoginPage typeLoginAndSubmit(String login) {
		loginInput.sendKeys(login);
		nextButton.click();
		return this;
	}

	public GmailHomePage typePasswordAndSubmit(String login) {
		loginInput.sendKeys(login);
		nextButton.click();
		return new GmailHomePage();
	}
}
