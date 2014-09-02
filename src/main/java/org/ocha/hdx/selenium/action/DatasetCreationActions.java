/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.interaction.DatasetCreationInteraction;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.DatasetConstants;

/**
 * @author Dan Mihaila
 *
 */
public class DatasetCreationActions {
	private static Logger logger = Logger.getLogger(DatasetCreationActions.class);

	public static IAction nextAddDataAction = context -> {
		DatasetCreationInteraction.clickOnNextAddDataInteraction.doAction(context);
	};

	public static IAction addCountryAction = context -> {
		DatasetCreationInteraction.clickSelectorCountryInteraction.doAction(context);
	};

	public static IAction removeCountryAction = context -> {
		DatasetCreationInteraction.clickRemoveCountryInteraction.doAction(context);
	};

	public static IAction fillFormFieldsAction = context -> {
		DatasetCreationActions.datasetTitleAction.doAction(context);
		DatasetCreationActions.datasetSourceAction.doAction(context);
		DatasetCreationActions.datasetDescriptionAction.doAction(context);
		DatasetCreationActions.datasetLicenseAction.doAction(context);
		DatasetCreationActions.datasetCheckOrganisationAction.doAction(context);
		DatasetCreationActions.datasetVisibilityAction.doAction(context);
		DatasetCreationActions.datasetMethodologyAction.doAction(context);
		//		DatasetCreationActions.datasetTagsAction.doAction(context);
		DatasetCreationActions.datasetCaveatsAction.doAction(context);
		DatasetCreationActions.datasetDatesAction.doAction(context);
	};

	public static IAction datasetTitleAction = context -> {
		final String title = Config.getEditorDatasetTitle() + System.currentTimeMillis();
		DatasetCreationInteraction.datasetTitleInteraction.doAction(context, DatasetConstants.TITLE, title);
	};

	public static IAction datasetSourceAction = context -> {
		final String src = "United Nations Office for the Coordination of Humanitarian Affairs";
		DatasetCreationInteraction.datasetSourceInteraction.doAction(context, DatasetConstants.SOURCE, src);
	};

	public static IAction datasetDescriptionAction = context -> {
		DatasetCreationInteraction.datasetDescriptionInteraction.doAction(context, DatasetConstants.DESCRIPTION, DatasetConstants.TEXT_LONG);
	};

	public static IAction datasetLicenseAction = context -> {
		DatasetCreationInteraction.datasetLicenseInteraction.doAction(context, DatasetConstants.LICENSE, DatasetConstants.TEXT_SHORT);
	};

	public static IAction datasetVisibilityAction = context -> {
		DatasetCreationInteraction.datasetVisibilityInteraction.doAction(context);
	};

	public static IAction datasetMethodologyAction = context -> {
		DatasetCreationInteraction.datasetMethodologyInteraction.doAction(context, DatasetConstants.METHODOLOGY, DatasetConstants.TEXT_SHORT);
	};

	public static IAction datasetCaveatsAction = context -> {
		DatasetCreationInteraction.datasetCaveatsInteraction.doAction(context, DatasetConstants.CAVEATS, DatasetConstants.TEXT_SHORT);
	};

	public static IAction datasetCheckOrganisationAction = context -> {
		DatasetCreationInteraction.datasetCheckOrganisationInteraction.doAction(context);
	};

	public static IAction datasetDatesAction = context -> {
		DatasetCreationInteraction.datasetDatesInteraction.doAction(context);
	};


	public static IAction fillFormResourcesAction = context -> {
		DatasetCreationActions.datasetResourceURLAction.doAction(context);
		DatasetCreationActions.datasetResourceFileAction.doAction(context);
	};

	public static IAction datasetResourceURLAction = context -> {
		DatasetCreationInteraction.datasetResourceURLInteraction.doAction(context);
	};


	public static IAction datasetResourceFileAction = context -> {
		DatasetCreationInteraction.datasetResourceFileInteraction.doAction(context);
	};
}
