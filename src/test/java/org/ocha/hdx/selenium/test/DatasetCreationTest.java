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
import org.ocha.hdx.selenium.action.DatasetCreationActions;
import org.ocha.hdx.selenium.action.LoginActions;
import org.ocha.hdx.selenium.action.PreselectOrgPageActions;
import org.ocha.hdx.selenium.check.DatasetCreationChecks;
import org.ocha.hdx.selenium.check.DatasetFormPageChecks;
import org.ocha.hdx.selenium.util.DatasetConstants;

/**
 * @author Dan Mihaila
 *
 */

public class DatasetCreationTest extends AbstractHdxSeleniumTest {
	private static Logger logger = Logger.getLogger(DatasetCreationTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * This test will create a dataset using an user that belongs to an organization. It will start with step 1 and in step 2
	 * will submit a resource: url and/or file
	 */
	@Test
	public void test() {

		final Map<String,Object> context = this.instantiateContext();

		this.createDatasetStep1(context);

		this.createDatasetStep2(context);
	}

	private void createDatasetStep1(final Map<String, Object> context) {
		/** 
		 * create Dataset Step 1
		 */
		logger.info("Create dataset step 1");

		BasicActions.goToHomePageAction.doAction(context);

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsExistingUserAction.doAction(context);

		BasicActions.goToSubmitPageUsingMainMenuAction.doAction(context);

		PreselectOrgPageActions.selectOrgForEditorUserFromConfigAction.doAction(context);
		DatasetFormPageChecks.newDatasetFormRenderedCheck.doAction(context);
		DatasetCreationActions.nextAddDataAction.doAction(context);
		DatasetCreationChecks.countryErrorCheck.doAction(context);

		//add&check country Peru
		DatasetCreationActions.addCountryAction.doAction(context, DatasetConstants.COUNTRY_ID, "per");
		DatasetCreationChecks.addedCountryCheck.doAction(context, DatasetConstants.EL_COUNTRY_ID, DatasetConstants.ADDED_COUNTRY_ITEM_PREFIX+"per");

		//remove&check country Peru
		DatasetCreationActions.removeCountryAction.doAction(context, DatasetConstants.COUNTRY_ID, "per");
		DatasetCreationChecks.removedCountryCheck.doAction(context, DatasetConstants.EL_COUNTRY_ID, DatasetConstants.ADDED_COUNTRY_ITEM_PREFIX+"per");

		//add&check country Bolivia
		DatasetCreationActions.addCountryAction.doAction(context, DatasetConstants.COUNTRY_ID, "bol");
		DatasetCreationChecks.addedCountryCheck.doAction(context, DatasetConstants.EL_COUNTRY_ID, DatasetConstants.ADDED_COUNTRY_ITEM_PREFIX+"bol");

		//add&check country Peru
		DatasetCreationActions.addCountryAction.doAction(context, DatasetConstants.COUNTRY_ID, "per");
		DatasetCreationChecks.addedCountryCheck.doAction(context, DatasetConstants.EL_COUNTRY_ID, DatasetConstants.ADDED_COUNTRY_ITEM_PREFIX+"per");

		DatasetCreationActions.fillFormFieldsAction.doAction(context);
		DatasetCreationActions.nextAddDataAction.doAction(context);

		logger.info("Create dataset END step 1");
	}

	private void createDatasetStep2(final Map<String, Object> context) {
		/** 
		 * create Dataset Step 2
		 */
		logger.info("Create dataset step 2");

		DatasetCreationActions.fillFormResourcesAction.doAction(context);

		logger.info("Create dataset step 2");
		LoginActions.logoutAction.doAction(context);

	}

}
