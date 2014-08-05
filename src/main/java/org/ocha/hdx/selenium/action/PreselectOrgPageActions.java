/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.PreselectOrgPageInteraction;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.Constants;

/**
 * @author alexandru-m-g
 *
 */
public class PreselectOrgPageActions {
	public static IAction selectOrgFromConfig = context -> {
		final String orgName = Config.getOrgNameForNewUser().toLowerCase();
		context.put(Constants.ORG_NAME, orgName);

		PreselectOrgPageInteraction.clickOnOrg.doAction(context);
	};
}
