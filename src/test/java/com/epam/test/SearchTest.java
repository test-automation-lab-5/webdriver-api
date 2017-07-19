package com.epam.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.epam.businessobject.GmailLoginBO;
import com.epam.businessobject.GmailSearchBO;
import com.epam.model.LetterModel;
import com.epam.model.UserModel;

public class SearchTest {

	// Business objects
	private final GmailLoginBO loginBO = new GmailLoginBO();

	private final GmailSearchBO gmailSearchBO = new GmailSearchBO();

	@Test
	public void testSearch() {

		boolean isLoggedIn = loginBO.login(new UserModel());

		// Verify that user is logged in
		Assert.assertTrue("User is not located on GmailHomePage", isLoggedIn);
		
		// Search for text and collect search results
		List<LetterModel> serchResults = gmailSearchBO.searchMessages("TomJ");
		
		// Verify search results contain expected letter(s)
		Assert.assertTrue(serchResults.contains(new LetterModel()));
	}
}
