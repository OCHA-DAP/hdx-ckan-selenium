package org.ocha.hdx.selenium.action;

import java.util.Map;

import org.ocha.hdx.selenium.interaction.DashboardPageInteractions;

public class DashboardPageActions {
	public static IAction joinOrganizationAction = 
			context -> DashboardPageInteractions.joinOrganizationInteraction.doAction(context);
			
	public static IAction goToMyOrganizationsAction = new IAction() {
		
		@Override
		public void doAction(Map<String, Object> context) {
			DashboardPageInteractions.goToMyOrganizationsInteraction.doAction(context);
			
		}
	};
}
