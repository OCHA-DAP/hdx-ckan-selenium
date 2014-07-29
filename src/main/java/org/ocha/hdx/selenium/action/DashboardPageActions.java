package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.DashboardPageInteractions;

public class DashboardPageActions {
	public static IAction joinOrganizationAction = 
			context -> DashboardPageInteractions.joinOrganizationInteraction.doAction(context);
}
