/**
 * 
 */
package org.ocha.hdx.selenium.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.Find;
import org.ocha.hdx.selenium.util.FindInDatasetListPage;
import org.ocha.hdx.selenium.util.FindInDatasetListPage.DatasetInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author alexandru-m-g
 *
 */

public class DatasetListPageTest {
	private static Logger logger = Logger.getLogger(DatasetListPageTest.class);
	private static WebDriver driver = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDatasetListPage() {
		goToHomepage();
		
		Find findUtil = new Find(driver);
		goToDatsetUrlWithCheck(findUtil);
		
		checkUrlAndSelectedSort(findUtil, "metadata_modified", "modified" );
		
		FindInDatasetListPage findInDatasetListPage = new FindInDatasetListPage(driver);
		checkSorted(findInDatasetListPage, (d,dNext)-> d.getDate().compareTo(dNext.getDate()), d -> d.getDate());
		
		changeSelection(findInDatasetListPage, "title_string asc");
		checkUrlAndSelectedSort(findUtil, "title_string+asc", "Name Ascending" );
		checkSorted(findInDatasetListPage, (d,dNext)-> dNext.getName().compareTo(d.getName()), d -> d.getName() );
		
		changeSelection(findInDatasetListPage, "title_string desc");
		checkUrlAndSelectedSort(findUtil, "title_string+desc", "Name Descending" );
		checkSorted(findInDatasetListPage, (d,dNext)-> d.getName().compareTo(dNext.getName()), d -> d.getName() );
		
	}

	private void changeSelection(FindInDatasetListPage findInDatasetListPage, String selectedLabelVal) {
		WebElement sortOptionEl = findInDatasetListPage.sortOptionByVal(selectedLabelVal);
		assertNotNull(sortOptionEl);
		sortOptionEl.click();
		logger.info("Sorted dataset by " + selectedLabelVal);
	}

	private void checkSorted(FindInDatasetListPage findInDatasetListPage, Comparator<DatasetInfo> comparator, Function<DatasetInfo, Object> getter) {
		
		List<DatasetInfo> datasetInfoList = findInDatasetListPage.allItems();
		if (datasetInfoList.size() >= 2) {
			for (int i=0; i<datasetInfoList.size()-1; i++) {
				DatasetInfo dInfo = datasetInfoList.get(i);
				DatasetInfo dInfoNext = datasetInfoList.get(i+1);
				assertNotNull(getter.apply(dInfo));
				assertNotNull(getter.apply(dInfoNext));
				assertTrue(String.format("%s should be the same or after %s", getter.apply(dInfo), getter.apply(dInfoNext)), 
						comparator.compare(dInfo, dInfoNext) >= 0);
			}
		}
	}

	private void checkUrlAndSelectedSort(Find findUtil, String urlContained, String selectionLabelContained) {
		String url = driver.getCurrentUrl();
		assertTrue(url.contains(urlContained));
		
		WebElement sortDropdown = findUtil.byCSSSelectorAndAttributeContaining("#field-order-by span", "class", "dropdown" );
		assertNotNull(sortDropdown);
		assertNotNull(sortDropdown.getText());
		assertTrue("Selection Label should contain: " + selectionLabelContained, sortDropdown.getText().toLowerCase().contains(selectionLabelContained.toLowerCase()));
	}

	private void goToDatsetUrlWithCheck(Find findUtil) {
		WebElement dataLink =  findUtil.byCSSSelectorAndAttributeContaining(".mainLinks li a", "href", "dataset");
		assertNotNull(dataLink);
		
		dataLink.click();
	}

	private void goToHomepage() {
		driver.get(Config.getDomainWithHttp());
	}
	
}
