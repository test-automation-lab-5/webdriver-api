package com.epam.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

import com.epam.control.AbstractElement;

public class CustomElementLocatorFactory extends DefaultElementLocator{

	public CustomElementLocatorFactory(SearchContext searchContext, AbstractAnnotations annotations) {
		super(searchContext, annotations);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public AbstractElement findElement() {
		// TODO Auto-generated method stub
		return null;
	}

}
