package org.ocha.hdx.selenium.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindInDatasetListPage {
	private static Logger logger = Logger.getLogger(FindInDatasetListPage.class);
	
	private static DateTimeFormatter dateTimeFormatter =  DateTimeFormat.forPattern("MMMM d, yyyy");
	
	private WebDriver driver;

	public FindInDatasetListPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	
	public List<DatasetInfo> allItems() {
		
		List<DatasetInfo> retList = new ArrayList<DatasetInfo>();
		
		List<WebElement> elements = this.driver.findElements(By.cssSelector("li.dataset-item .dataset-content"));
		for (WebElement webElement : elements) {
			String name = null;
			LocalDate date = null;
			try{
				name = webElement.findElement(By.cssSelector(".dataset-heading a")).getText().trim();
			}
			catch (Exception e) {
				logger.warn(e.getMessage());
			}
			
			
			try{
				String text = webElement.findElement(By.cssSelector("div")).getText();
				int dashIdx = text.lastIndexOf("-");
				if (dashIdx > 0) {
					int endIdx = text.indexOf("\n", dashIdx);
					endIdx = endIdx>0 ? endIdx : text.length(); 
					String dateText = text.substring(dashIdx+1, endIdx).trim();
					date = dateTimeFormatter.parseLocalDate(dateText);
				}
			}
			catch (Exception e) {
				logger.warn(e.getMessage());
			}
			
			DatasetInfo datasetInfo = new DatasetInfo(name, date);
			retList.add(datasetInfo);
		}
		return retList;
		
	}
	
	public WebElement sortOptionByVal(String val) {
		this.driver.findElement(By.id("field-order-by")).click();
		List<WebElement> elements = this.driver.findElements(By.cssSelector(".dropdown-menu.dropdown-inverse a"));
		return elements.stream().filter(el -> el.getAttribute("val") != null ? el.getAttribute("val").contains(val):false ).findFirst().get();
	}
	
	public class DatasetInfo {
		String name;
		LocalDate date;
		
		public DatasetInfo(String name, LocalDate date) {
			super();
			this.name = name;
			this.date = date;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		
		
	}
	
}
