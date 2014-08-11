/**
 * 
 */
package org.ocha.hdx.selenium.test;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.DatasetListPageActions;
import org.ocha.hdx.selenium.check.BasicChecks;
import org.ocha.hdx.selenium.check.DatasetListPageChecks;

/**
 * @author alexandru-m-g
 *
 */

public class DatasetListPageSortingTest extends AbstractHdxSeleniumTest {
	private static Logger logger = Logger.getLogger(DatasetListPageSortingTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		final Map<String,Object> context = this.instantiateContext();

		BasicActions.goToHomePageAction.doAction(context);

		BasicChecks.datasetMainMenuItemExistsCheck.doAction(context);

		BasicActions.goToDatasetListUsingMainMenuAction.doAction(context);

		DatasetListPageChecks.urlShowsSortByMetadataCheck.doAction(context);
		DatasetListPageChecks.selectedSortIsModifiedCheck.doAction(context);
		DatasetListPageChecks.sortedByDateDescCheck.doAction(context);

		DatasetListPageActions.changeSortingToNameAscAction.doAction(context);

		DatasetListPageChecks.urlShowsSortByNameAscCheck.doAction(context);
		DatasetListPageChecks.selectedSortIsNameAscCheck.doAction(context);
		DatasetListPageChecks.sortedByNameAscCheck.doAction(context);



		DatasetListPageActions.changeSortingToNameDescAction.doAction(context);

		DatasetListPageChecks.urlShowsSortByNameDescCheck.doAction(context);
		DatasetListPageChecks.selectedSortIsNameDescCheck.doAction(context);
		DatasetListPageChecks.sortedByNameDescCheck.doAction(context);


	}

}
