/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.BasicInteractions;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.Constants;

/**
 * @author alexandru-m-g
 *
 */
public class BasicActions {

	public static IAction goToHomePageAction = context -> {
		context.put(Constants.DESTINATION_URL, Config.getDomainWithHttp());
		BasicInteractions.navigateToUrlInteraction.doAction(context);
	};

	public static IAction goToDatasetListUsingMainMenuAction = context -> BasicInteractions.clickOnDatasetInMainMenuInteraction
			.doAction(context);

	public static IAction goToOrgListUsingMainMenuAction = context -> BasicInteractions.clickOnOrganizationsInMainMenuInteraction
			.doAction(context);

	public static IAction goToLoginPageUsingMainMenuAction = context -> BasicInteractions.clickOnLoginInMainMenuInteraction
			.doAction(context);

	public static IAction goToSubmitPageUsingMainMenuAction = context -> BasicInteractions.clickOnSubmitInMainMenuInteraction
			.doAction(context);

}
