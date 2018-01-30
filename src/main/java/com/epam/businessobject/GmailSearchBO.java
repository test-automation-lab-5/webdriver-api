package com.epam.businessobject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
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
	
	public static void main(String[] args) {
		
		EnvProperties envProperties =  new EnvProperties();
		
		envProperties.getBaseUrl();
	}
}


class EnvProperties {
	
	private Properties properties;
	
	public EnvProperties() {
		
		properties = new Properties();
		
		try {
			properties.load(new FileInputStream(new File("/path/env.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getBaseUrl () {
		return properties.getProperty("base.url");
	}
}



