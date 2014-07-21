/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.ocha.hdx.selenium.entities.SearchResultInfo;
import org.ocha.hdx.selenium.interaction.DatasetListPageInteractions;
import org.ocha.hdx.selenium.util.Constants;
import org.ocha.hdx.selenium.util.FindInDatasetListPage;
import org.ocha.hdx.selenium.util.FindInDatasetListPage.DatasetInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author alexandru-m-g
 *
 */
public class DatasetListPageChecks {



	private static ICheckAction selectedSortIsSomethingCheck = context -> {
		final String something = (String) context.remove(Constants.LABEL_CONTAINS);

		final FindInDatasetListPage find = FF(context, FindInDatasetListPage.class);
		final WebElement dropDownLabel = find.sortDropdownLabel();
		assertNotNull(dropDownLabel);
		assertNotNull(dropDownLabel.getText());
		assertTrue("Selection Label should contain: " + something, dropDownLabel.getText().toLowerCase().contains(something.toLowerCase()));

	};

	private static ICheckAction sortedByCheck = context -> {
		final FindInDatasetListPage find = FF(context, FindInDatasetListPage.class);
		final Function<DatasetInfo, Object> getter = (Function<DatasetInfo, Object>) context.remove(Constants.GETTER);
		final Comparator<DatasetInfo> comparator = (Comparator<DatasetInfo>) context.remove(Constants.COMPARATOR);


		final List<DatasetInfo> datasetInfoList	= find.allItems();
		if (datasetInfoList.size() >= 2) {
			for (int i=0; i<datasetInfoList.size()-1; i++) {
				final DatasetInfo dInfo = datasetInfoList.get(i);
				final DatasetInfo dInfoNext = datasetInfoList.get(i+1);
				assertNotNull(getter.apply(dInfo));
				assertNotNull(getter.apply(dInfoNext));
				assertTrue(String.format("%s should be the same or after %s", getter.apply(dInfo), getter.apply(dInfoNext)), 
						comparator.compare(dInfo, dInfoNext) >= 0);
			}
		}

	};

	public static ICheckAction urlShowsSortByMetadataCheck = context -> {
		context.put(Constants.URL_CONTAINS, "metadata_modified");
		BasicChecks.urlContainsCheck.doAction(context);
	};


	public static ICheckAction urlShowsSortByNameAscCheck = context -> {
		context.put(Constants.URL_CONTAINS, "title_string+asc");
		BasicChecks.urlContainsCheck.doAction(context);

	};

	public static ICheckAction urlShowsSortByNameDescCheck = context -> {
		context.put(Constants.URL_CONTAINS, "title_string+desc");
		BasicChecks.urlContainsCheck.doAction(context);

	};


	public static ICheckAction selectedSortIsModifiedCheck = context -> {
		context.put(Constants.LABEL_CONTAINS, "modified");
		selectedSortIsSomethingCheck.doAction(context);
	};

	public static ICheckAction selectedSortIsNameAscCheck = context -> {
		context.put(Constants.LABEL_CONTAINS, "Name Ascending");
		selectedSortIsSomethingCheck.doAction(context);
	};

	public static ICheckAction selectedSortIsNameDescCheck = context -> {
		context.put(Constants.LABEL_CONTAINS, "Name Descending");
		selectedSortIsSomethingCheck.doAction(context);
	};




	public static ICheckAction sortedByDateDescCheck = context -> {
		context.put(Constants.GETTER, (Function<DatasetInfo, Object>) d -> d.getDate() );
		context.put(Constants.COMPARATOR, (Comparator<DatasetInfo>) (d,dNext)-> d.getDate().compareTo(dNext.getDate()) );

		sortedByCheck.doAction(context);

	};

	public static ICheckAction sortedByNameAscCheck = context -> {
		context.put(Constants.GETTER, (Function<DatasetInfo, Object>) d -> d.getName() );
		context.put(Constants.COMPARATOR, (Comparator<DatasetInfo>) (d,dNext)-> dNext.getName().compareTo(d.getName()) );

		sortedByCheck.doAction(context);

	};

	public static ICheckAction sortedByNameDescCheck = context -> {
		context.put(Constants.GETTER, (Function<DatasetInfo, Object>) d -> d.getName() );
		context.put(Constants.COMPARATOR, (Comparator<DatasetInfo>) (d,dNext)-> d.getName().compareTo(dNext.getName()) );

		sortedByCheck.doAction(context);

	};

	public static ICheckAction beforeSearchOrFilterResultInfoSaveCheck = context -> {
		DatasetListPageInteractions.readSearchResultInfo.doAction(context);
		final SearchResultInfo searchResultInfo = REMOVE(context, Constants.SEARCH_RESULTS_INFO, SearchResultInfo.class);
		assertNull(searchResultInfo.getSearchText());
	};

	public static ICheckAction compareNumbersInSearchResultInfoCheck = context -> {
		final SearchResultInfo searchResultInfo = REMOVE(context, Constants.SEARCH_RESULTS_INFO, SearchResultInfo.class);
		final SearchResultInfo prevSearchResultInfo = REMOVE(context, Constants.PREV_SEARCH_RESULTS_INFO, SearchResultInfo.class);

		assertTrue("Number of results after search needs to be smaller", 
				prevSearchResultInfo.getResultsNum() > searchResultInfo.getResultsNum() );
		assertTrue("Number of result pages after search needs to be smaller or equal", 
				prevSearchResultInfo.getPagesNum() > searchResultInfo.getPagesNum() );
	};

	public static ICheckAction somethingSearchResultInfoCheck = context -> {
		DatasetListPageInteractions.readSearchResultInfo.doAction(context);
		final String searchText = REMOVE(context, Constants.TEXT_TO_WRITE, String.class);
		final SearchResultInfo searchResultInfo = REMOVE(context, Constants.SEARCH_RESULTS_INFO, SearchResultInfo.class);

		assertEquals(searchText.toLowerCase(), searchResultInfo.getSearchText().toLowerCase() );

		context.put(Constants.SEARCH_RESULTS_INFO, searchResultInfo);
		compareNumbersInSearchResultInfoCheck.doAction(context);

	};

	public static ICheckAction healthSearchResultInfoCheck = context -> {
		context.put(Constants.TEXT_TO_WRITE, "health");
		somethingSearchResultInfoCheck.doAction(context);
	};

	public static ICheckAction filteredResultInfoCheck = context -> {
		DatasetListPageInteractions.readSearchResultInfo.doAction(context);
		compareNumbersInSearchResultInfoCheck.doAction(context);
	};

	public static ICheckAction tenTagsAppearInFacetCheck = context -> {
		final List<WebElement> elements = 
				WD(context).findElements(By.xpath("//h2[contains(.,'Tags')]/following-sibling::nav/ul/li"));
		assertEquals(10, elements.size());
	};

	public static ICheckAction moreThanTenTagsAppearInFacetCheck = context -> {
		final List<WebElement> elements = 
				WD(context).findElements(By.xpath("//h2[contains(.,'Tags')]/following-sibling::nav/ul/li"));
		assertTrue("There should be more than 10 items in the facet now", elements.size() > 10 );
	};

}
