package org.ocha.hdx.selenium.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasicFind {
	private final WebDriver driver;
	private final GenericFind generalFind;

	public BasicFind(final WebDriver driver) {
		super();
		this.driver = driver;
		this.generalFind = new GenericFind(driver);
	}

	public WebElement datasetListMainMenuItem() {
		return this.generalFind.byCSSSelectorAndAttributeContaining(".mainLinks li a", "href", "dataset");
	}

	public WebElement lastPaginationItem() {
		final List<WebElement> paginationItems = this.driver.findElements(By.cssSelector(".pagination li a"));
		final int listSize = paginationItems.size();
		if (listSize == 0) {
			return null;
		}
		else if ( paginationItems.get(listSize-1).getText().matches(".*\\d+.*") ) {
			return paginationItems.get(listSize-1);
		} else {
			return paginationItems.get(listSize-2);
		}
	}


}
