/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.junit.Assert.assertNotNull;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.entities.SearchResultInfo;
import org.ocha.hdx.selenium.test.DatasetListPageSortingTest;
import org.ocha.hdx.selenium.util.BasicFind;
import org.ocha.hdx.selenium.util.Constants;
import org.ocha.hdx.selenium.util.FindInDatasetListPage;
import org.ocha.hdx.selenium.util.GenericFind;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author alexandru-m-g
 *
 */
public class DatasetListPageInteractions {

	private static Logger logger = Logger.getLogger(DatasetListPageSortingTest.class);

	public static IInteraction changeSortingToSomethingInteraction = context -> {
		final String selectedLabelVal = REMOVE(context, Constants.SELECTED_LABEL_VAL, String.class);
		final FindInDatasetListPage findInDatasetListPage = FF(context, FindInDatasetListPage.class);

		context.put(Constants.ID_OF_EL_TO_CLICK, "field-order-by");
		BasicInteractions.clickOnElementWithId.doAction(context);

		final WebElement sortOptionEl = findInDatasetListPage.sortOptionByVal(selectedLabelVal);
		assertNotNull(sortOptionEl);
		sortOptionEl.click();
		logger.info("Sorted dataset by " + selectedLabelVal);

	};

	public static IInteraction changeSortingToNameAscInteraction = context -> {
		context.put(Constants.SELECTED_LABEL_VAL, "title_string asc");
		changeSortingToSomethingInteraction.doAction(context);
	};

	public static IInteraction changeSortingToNameDescInteraction = context -> {
		context.put(Constants.SELECTED_LABEL_VAL, "title_string desc");
		changeSortingToSomethingInteraction.doAction(context);
	};

	public static IInteraction searchForTextInteraction = context -> {
		context.put(Constants.CSS_SELECTOR_OF_EL_TO_WRITE, ".form-search input.searchInput");
		BasicInteractions.writeInElementWithCssSelector.doAction(context);
		context.put(Constants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK, ".form-search button.button");
		BasicInteractions.clickOnElementWithCssSelector.doAction(context);
	};

	public static IInteraction readSearchResultInfo = new IInteraction() {
		Pattern pattern = Pattern.compile("([0-9,.]+) datasets found( for \\\"(.*)\\\")?", Pattern.CASE_INSENSITIVE);
		@Override
		public void doAction(final Map<String, Object> context) {

			final BasicFind basicFind = FF(context,BasicFind.class);			

			final WebElement searchResultMsgEl = WD(context).findElement(By.cssSelector("form.search-form h2"));
			final String totalNumberOfDatasetsMsg = searchResultMsgEl.getText().trim();
			final Matcher matcher = this.pattern.matcher(totalNumberOfDatasetsMsg);
			matcher.matches();
			final String numFoundStr = matcher.group(1).replace(".", "").replace(",", "");
			final int numFound = Integer.parseInt(numFoundStr);
			final String searchText = matcher.group(3);

			final WebElement lastPaginationItem = basicFind.lastPaginationItem();

			int lastPageNum;
			if (lastPaginationItem != null) {
				final String lastPageNumStr = lastPaginationItem.getText().trim();
				lastPageNum = Integer.parseInt(lastPageNumStr);
			} else {
				lastPageNum = 1;
			}

			final SearchResultInfo searchResultInfo = new SearchResultInfo(numFound, lastPageNum, searchText);

			context.put(Constants.SEARCH_RESULTS_INFO, searchResultInfo);

		}

	};

	public static IInteraction openFacetInteraction = context -> {
		final GenericFind find = FF(context, GenericFind.class);
		final String facetName = REMOVE(context, Constants.FACET_NAME, String.class);

		final WebElement facetEl = find.byCSSSelectorAndBodyContaining(".facet-title", facetName);
		facetEl.click();


	};

	public static IInteraction showMoreFacetValuesInteraction = context -> {
		final GenericFind find = FF(context, GenericFind.class);
		final String facetName = REMOVE(context, Constants.FACET_NAME, String.class);

		final WebElement facetEl = find.byCSSSelectorAndBodyContaining(".facet-url", facetName);
		facetEl.click();
	};

	public static IInteraction filterByFacetValueInteraction = context -> {
		final String facetName = REMOVE(context, Constants.FACET_NAME, String.class);
		final String facetValue = REMOVE(context, Constants.FACET_VALUE, String.class);

		final String xpath = String.format("//h2[contains(.,'%s')]/following-sibling::nav/ul/li/a[contains(@href,'%s')]", 
				facetName, facetValue);
		WD(context).findElement(By.xpath(xpath)).click();
	};
}
