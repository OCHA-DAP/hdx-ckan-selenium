package org.ocha.hdx.selenium.util;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericFind {

	WebDriver driver;


	public GenericFind(final WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement byCSSSelectorAndAttributeContaining(final String selector, final String attributeName, final String containedValue) {
		final List<WebElement> menuItems = this.driver.findElements(By.cssSelector(selector));
		final Stream<WebElement> dataStream = menuItems.stream()
				.filter(item -> item.isDisplayed())
				.filter(
						item ->  {
							final String attr = item.getAttribute(attributeName);
							if ( attr != null ) {
								System.out.println("Contained value is " + containedValue + " result is " + attr.contains(containedValue) );
								return attr.contains(containedValue);
							}
							return false;
						}
						); 
		return dataStream.findFirst().orElse(null);
	}

	public WebElement byCSSSelectorAndDisplayed(final String selector) {
		final List<WebElement> menuItems = this.driver.findElements(By.cssSelector(selector));
		return menuItems.stream()
				.filter(item -> item.isDisplayed())
				.findFirst().orElse(null);
	}

	public WebElement byCSSSelectorAndBodyContaining(final String selector, final String containedValue) {
		final List<WebElement> menuItems = this.driver.findElements(By.cssSelector(selector));
		final Stream<WebElement> dataStream = menuItems.stream().filter(
				item ->  {
					final String body = item.getText().trim();
					if ( body != null ) {
						return body.toLowerCase().contains(containedValue.toLowerCase());
					}
					return false;
				}
				); 
		return dataStream.findFirst().orElse(null);
	}
}
