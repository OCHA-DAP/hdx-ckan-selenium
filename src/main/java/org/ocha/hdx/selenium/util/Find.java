package org.ocha.hdx.selenium.util;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Find {
	
	WebDriver driver;
	
	
	public Find(WebDriver driver) {
		super();
		this.driver = driver;
	}



	public WebElement byCSSSelectorAndAttributeContaining(String selector, String attributeName, String containedValue) {
		List<WebElement> menuItems = this.driver.findElements(By.cssSelector(selector));
		Stream<WebElement> dataStream = menuItems.stream().filter(
				item ->  {
						String attr = item.getAttribute(attributeName);
						if ( attr != null )
							return attr.contains(containedValue);
						return false;
					}
				); 
		return dataStream.findFirst().orElse(null);
	}
}
