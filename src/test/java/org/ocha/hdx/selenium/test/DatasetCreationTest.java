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
import org.ocha.hdx.selenium.action.LoginActions;
import org.ocha.hdx.selenium.action.PreselectOrgPageActions;
import org.ocha.hdx.selenium.check.DatasetFormPageChecks;

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
		logger.info("Create dataset page ...");

		//LoginActions.logoutAction.doAction(context);

	}


}
