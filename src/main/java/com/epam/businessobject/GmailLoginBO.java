package com.epam.businessobject;

import com.epam.model.UserModel;
import com.epam.pages.GmailHomePage;
import com.epam.pages.GmailLoginPage;

public class GmailLoginBO {

	public boolean login(UserModel user) {

		// Gmail Login PageObject
		GmailLoginPage gmailLoginPage = new GmailLoginPage();

		// Send login to login input and submit form
		gmailLoginPage.typeLoginAndSubmit(user.getEmail());

		// Gmail Home PageObject
		GmailHomePage gmailHomePage = gmailLoginPage.typePasswordAndSubmit(user.getPassword());

		return gmailHomePage.isMyAccountMessageVisible();
	}

}
