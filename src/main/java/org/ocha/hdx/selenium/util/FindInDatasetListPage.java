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

	private final WebDriver driver;

	private final GenericFind find;

	public FindInDatasetListPage(final WebDriver driver) {
		super();
		this.driver = driver;
		this.find = new GenericFind(driver);
	}


	public List<DatasetInfo> allItems() {

		final List<DatasetInfo> retList = new ArrayList<DatasetInfo>();

		final List<WebElement> elements = this.driver.findElements(By.cssSelector("li.dataset-item .dataset-content"));
		for (final WebElement webElement : elements) {
			String name = null;
			LocalDate date = null;
			try{
				name = webElement.findElement(By.cssSelector(".dataset-heading a")).getText().trim();
			}
			catch (final Exception e) {
				logger.warn(e.getMessage());
			}


			try{
				final String text = webElement.findElement(By.cssSelector("div")).getText();
				final int dashIdx = text.lastIndexOf("-");
				if (dashIdx > 0) {
					int endIdx = text.indexOf("\n", dashIdx);
					endIdx = endIdx>0 ? endIdx : text.length(); 
					final String dateText = text.substring(dashIdx+1, endIdx).trim();
					date = dateTimeFormatter.parseLocalDate(dateText);
				}
			}
			catch (final Exception e) {
				logger.warn(e.getMessage());
			}

			final DatasetInfo datasetInfo = new DatasetInfo(name, date);
			retList.add(datasetInfo);
		}
		return retList;

	}

	public WebElement sortOptionByVal(final String val) {
		final List<WebElement> elements = this.driver.findElements(By.cssSelector(".dropdown-menu.dropdown-inverse a"));
		return elements.stream().filter(el -> el.getAttribute("val") != null ? el.getAttribute("val").contains(val):false ).findFirst().get();
	}

	public WebElement sortDropdownLabel() {
		return this.find.byCSSSelectorAndAttributeContaining("#field-order-by span", "class", "dropdown" );
	}

	public class DatasetInfo {
		String name;
		LocalDate date;

		public DatasetInfo(final String name, final LocalDate date) {
			super();
			this.name = name;
			this.date = date;
		}
		public String getName() {
			return this.name;
		}
		public void setName(final String name) {
			this.name = name;
		}
		public LocalDate getDate() {
			return this.date;
		}
		public void setDate(final LocalDate date) {
			this.date = date;
		}


	}

}
