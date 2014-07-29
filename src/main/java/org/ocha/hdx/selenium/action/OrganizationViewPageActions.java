package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.OrganizationViewPageInteractions;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.Constants;

public class OrganizationViewPageActions {
	public static IAction requestMembershipAction = 
			context -> OrganizationViewPageInteractions.requestMembershipInteraction.doAction(context);

	public static IAction addMemberFromConfigToOrgAsMember = context -> {
		final String username = Config.getNewUsername();
		context.put(Constants.USERNAME, username);
		context.put(Constants.ROLE, "member");
		OrganizationViewPageInteractions.addMemberInteraction.doAction(context);
	};
}
