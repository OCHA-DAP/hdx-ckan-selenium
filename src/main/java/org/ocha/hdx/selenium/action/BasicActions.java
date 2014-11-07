/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.BasicInteractions;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;

/**
 * @author alexandru-m-g
 *
 */
public class BasicActions {

	public static IAction goToHomePageAction = context -> {
		context.put(ContextConstants.DESTINATION_URL, Config.getDomainWithHttp());
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

	public static IAction goToURLPageAction = context -> {
		context.put(ContextConstants.DESTINATION_URL, Config.getURLTest());
		BasicInteractions.navigateToUrlInteraction.doAction(context);
	};

    public static IAction goToURLAddBaseURL = context -> {
        String url = (String) context.get(ContextConstants.DESTINATION_URL);
        String base = Config.getDomainWithHttp();
        context.put(ContextConstants.DESTINATION_URL, base+url);
        BasicInteractions.navigateToUrlInteraction.doAction(context);
    };


}
