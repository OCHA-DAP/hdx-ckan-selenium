package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.OrganizationListPageInteraction;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;


public class OrganizationListPageActions {
	public static IAction searchForOrgFromConfigAction  = context -> {
		final String orgName = Config.getOrgNameForNewUser().toLowerCase();
		context.put(ContextConstants.ORG_NAME, orgName);
		OrganizationListPageInteraction.searchForOrgInteraction.doAction(context);

	};
	public static IAction viewOrgFromConfigAction = context -> {
		final String orgName = Config.getOrgNameForNewUser();
		context.put(ContextConstants.ORG_NAME, orgName.toLowerCase());
		OrganizationListPageInteraction.viewOrgInteraction.doAction(context);

	};

	public static IAction viewOrgFromConfigByUrlAction = context -> {
		final String orgName = Config.getOrgNameForNewUser().toLowerCase();
		context.put(ContextConstants.ORG_NAME, orgName);

		final String url = Config.getDomainWithHttp();
		context.put(ContextConstants.DESTINATION_URL, url);

		OrganizationListPageInteraction.viewOrgByUrlInteraction.doAction(context);
	};
}
