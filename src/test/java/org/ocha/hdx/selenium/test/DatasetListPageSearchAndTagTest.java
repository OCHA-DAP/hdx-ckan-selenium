/**
 * 
 */
package org.ocha.hdx.selenium.test;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.DatasetListPageActions;
import org.ocha.hdx.selenium.check.DatasetListPageChecks;

/**
 * @author alexandru-m-g
 *
 */
public class DatasetListPageSearchAndTagTest extends AbstractHdxSeleniumTest {

	private static Logger logger = Logger.getLogger(DatasetListPageSearchAndTagTest.class);

	@Test
	public void test() {
		final Map<String,Object> context = this.instantiateContext();

		BasicActions.goToHomePageAction.doAction(context);
		BasicActions.goToDatasetListUsingMainMenuAction.doAction(context);

		DatasetListPageChecks.beforeSearchOrFilterResultInfoSaveCheck.doAction(context);
		DatasetListPageActions.storeSearchResultsInfoAsPrevAction.doAction(context);

		DatasetListPageActions.searchForHealthAction.doAction(context);
		DatasetListPageChecks.healthSearchResultInfoCheck.doAction(context);

		DatasetListPageActions.openTagsFacetAction.doAction(context);

		DatasetListPageChecks.tenTagsAppearInFacetCheck.doAction(context);

		DatasetListPageActions.showMoreTagsFacetAction.doAction(context);

		DatasetListPageChecks.moreThanTenTagsAppearInFacetCheck.doAction(context);

		DatasetListPageActions.storeSearchResultsInfoAsPrevAction.doAction(context);

		DatasetListPageActions.filterByTagDeathsAction.doAction(context);
		DatasetListPageChecks.filteredResultInfoCheck.doAction(context);

		logger.info("Test finished");

	}
}
