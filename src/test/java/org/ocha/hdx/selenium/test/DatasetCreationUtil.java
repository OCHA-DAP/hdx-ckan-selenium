/**
 * 
 */
package org.ocha.hdx.selenium.test;

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

import java.util.Map;

/**
 * @author Dan Mihaila
 *
 */

public class DatasetCreationUtil {
	private static Logger logger = Logger.getLogger(DatasetCreationUtil.class);

	public static void createDataset(Map<String,Object> context) {
		createDatasetStep1(context);
		createDatasetStep2(context);
	}

	private static void createDatasetStep1(final Map<String, Object> context) {
		/** 
		 * create Dataset Step 1
		 */
		logger.info("Create dataset step 1");

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

//		DatasetCreationActions.fillFormFieldsAction.doAction(context);

        DatasetCreationActions.datasetTitleAction.doAction(context);

        DatasetCreationActions.datasetSourceAction.doAction(context);
        DatasetCreationActions.datasetDescriptionAction.doAction(context);
        DatasetCreationActions.datasetLicenseAction.doAction(context);
//        DatasetCreationActions.datasetCheckOrganisationAction.doAction(context);
        DatasetCreationChecks.datasetSelectedOrganisationCheck.doAction(context);
        logger.info("checking Organisation");
        DatasetCreationActions.datasetVisibilityAction.doAction(context);
        DatasetCreationActions.datasetMethodologyAction.doAction(context);
        //		DatasetCreationActions.datasetTagsAction.doAction(context);
        DatasetCreationActions.datasetCaveatsAction.doAction(context);
        DatasetCreationActions.datasetDatesAction.doAction(context);

		DatasetCreationActions.nextAddDataAction.doAction(context);

		logger.info("Create dataset END step 1");
	}

	private static void createDatasetStep2(final Map<String, Object> context) {
		/** 
		 * create Dataset Step 2
		 */
		logger.info("Create dataset step 2");

		DatasetCreationActions.fillFormResourcesAction.doAction(context);
        DatasetCreationActions.saveAction.doAction(context);
        DatasetCreationActions.getPreviewLinkAction.doAction(context);

        logger.info("Create dataset step 2");
	}

}
