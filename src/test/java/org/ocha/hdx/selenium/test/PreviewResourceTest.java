/**
 * 
 */
package org.ocha.hdx.selenium.test;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.DatasetListPageActions;
import org.ocha.hdx.selenium.action.DatasetViewPageActions;
import org.ocha.hdx.selenium.action.PreviewPageActions;
import org.ocha.hdx.selenium.check.PreviewPageChecks;

/**
 * @author alexandru-m-g
 *
 */
public class PreviewResourceTest extends AbstractHdxSeleniumTest {
	private static Logger logger = Logger.getLogger(PreviewResourceTest.class);
	
	@Test
	public void test() {
		final Map<String,Object> context = this.instantiateContext();

		BasicActions.goToHomePageAction.doAction(context);
		BasicActions.goToDatasetListUsingMainMenuAction.doAction(context);

		DatasetListPageActions.searchForAirportsInZambiaAction.doAction(context);
		DatasetListPageActions.viewAirportsInZambiaDatasetAction.doAction(context);
		DatasetViewPageActions.previewTheFirstResourceAction.doAction(context);

		PreviewPageActions.showOnlyFirst3RowsAction.doAction(context);
		PreviewPageChecks.onlyThreeItemsAppearCheck.doAction(context);

		PreviewPageActions.switchToMapViewAction.doAction(context);
		PreviewPageActions.selectLatitudeAndLongitudeColsAction.doAction(context);
		
		PreviewPageChecks.threeMarkersAppearOnMapCheck.doAction(context);
		
		logger.info("Preview Resource Test End ");
	}
}
