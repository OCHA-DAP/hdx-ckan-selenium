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
		final String desc = "Note: This is a test dataset. Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro.\n"
				+"**This should be bold.** De carne lumbering animata corpora quaeritis. Summus brains sit​​, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris.\n"
				+"*This should be italics.* Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium.\n"
				+"1. This\n"
				+"1. should\n"
				+"1. be\n"
				+"1. a numbered list\n"
				+"\n"
				+"* This\n"
				+"* should\n"
				+"* be\n"
				+"* a bulleted list\n"
				+"\n"
				+"Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead.";
		DatasetCreationInteraction.datasetDescriptionInteraction.doAction(context, DatasetConstants.DESCRIPTION, desc);
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

}
