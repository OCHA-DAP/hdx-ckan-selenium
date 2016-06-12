package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.BasicInteractions;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;

public class MapExplorerActions {
	public static IAction goToMapExplorerAction = context -> {
		context.put(ContextConstants.DESTINATION_URL, Config.getMapExplorerUrl());
		BasicInteractions.navigateToUrlInteraction.doAction(context);
	};
	public static IAction selectFirstSliceFromListActions = context -> {
		context.put(ContextConstants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK, ".legend-list .btn i.caret");
		BasicInteractions.clickOnElementWithCssSelector.doAction(context);
		
		context.put(ContextConstants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK, "ul.ui-select-choices a.ui-select-choices-row-inner");
		BasicInteractions.clickOnElementWithCssSelector.doAction(context);
	};
	public static IAction removeFirstLoadedSliceAction = context -> {
		context.put(ContextConstants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK, ".legend-list .legend-actions i.glyphicon-remove");
		BasicInteractions.clickOnElementWithCssSelector.doAction(context);
	};

}
