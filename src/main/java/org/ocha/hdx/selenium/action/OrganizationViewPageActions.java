package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.OrganizationViewPageInteractions;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;

public class OrganizationViewPageActions {
	public static IAction requestMembershipAction = 
			context -> OrganizationViewPageInteractions.requestMembershipInteraction.doAction(context);

			public static IAction addUserFromConfigToOrgAsMember = context -> {
				final String username = Config.getNomemberUsername();
				context.put(ContextConstants.USERNAME, username);
				context.put(ContextConstants.ROLE, "member");
				OrganizationViewPageInteractions.addMemberInteraction.doAction(context);
			};

			public static IAction addUserFromConfigToOrgAsEditor = context -> {
				final String username = Config.getNomemberUsername();
				context.put(ContextConstants.USERNAME, username);
				context.put(ContextConstants.ROLE, "editor");
				OrganizationViewPageInteractions.addMemberInteraction.doAction(context);
			};

			public static IAction removeConfigMemberFromOrg = context -> {
				final String username = Config.getNomemberUsername();
				context.put(ContextConstants.USERNAME, username);
				context.put(ContextConstants.ROLE, "member");
				OrganizationViewPageInteractions.removeMemberInteraction.doAction(context);
			};

			public static IAction viewMembersAction = context -> 
			OrganizationViewPageInteractions.viewMembersInteraction.doAction(context);
}
