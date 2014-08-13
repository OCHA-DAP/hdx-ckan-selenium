package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.DatasetViewPageInteraction;

public class DatasetViewPageActions {
	public static IAction previewTheFirstResourceAction = context -> DatasetViewPageInteraction.previewTheFirstResourceInteraction.doAction(context);
	
	public static IAction viewCountryPageAction = context -> DatasetViewPageInteraction.clickOnCountryLinkInteraction.doAction(context);
}
