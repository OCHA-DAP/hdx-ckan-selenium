/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.PreselectOrgPageInteraction;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;

/**
 * @author alexandru-m-g
 *
 */
public class PreselectOrgPageActions {
	public static IAction selectOrgNomemberUserFromConfigAction = context -> {
		final String orgName = Config.getOrgNameForNomemberUser().toLowerCase();
		context.put(ContextConstants.ORG_NAME, orgName);

		PreselectOrgPageInteraction.clickOnOrgInteraction.doAction(context);
	};

	public static IAction selectOrgForEditorUserFromConfigAction = context -> {
		final String orgName = Config.getOrgNameForEditorUser().toLowerCase();
		context.put(ContextConstants.ORG_NAME, orgName);

		PreselectOrgPageInteraction.clickOnOrgInteraction.doAction(context);
	};

	public static IAction requestEditorRightsAction = context -> 
	PreselectOrgPageInteraction.requestEditorRightInteraction.doAction(context);
}
