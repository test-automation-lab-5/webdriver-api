package com.epam.businessobject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.model.LetterModel;
import com.epam.pages.GmailHomePage;

public class GmailSearchBO {

	
	public List<LetterModel> searchMessages(String searchText) {
	
		GmailHomePage gmailHomePage = new GmailHomePage();
		
		gmailHomePage.typeSearchBoxText(searchText);
		
		gmailHomePage.clickSubmitSearchButton();
		
		List<String> messageSubjects = gmailHomePage.getMessageSubjects();
		
		if (!messageSubjects.isEmpty())
		{
			return messageSubjects.stream().map(sub -> {
				LetterModel lm = new LetterModel();
				lm.setSubject(sub);
				return lm;
			}).collect(Collectors.toList());
		}
		
		else {
			return Collections.emptyList();
		}
	}
}
