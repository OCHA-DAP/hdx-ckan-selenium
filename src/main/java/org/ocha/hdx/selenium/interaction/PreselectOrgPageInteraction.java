/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;

import org.ocha.hdx.selenium.util.Constants;
import org.ocha.hdx.selenium.util.GenericFind;

/**
 * @author alexandru-m-g
 *
 */
public class PreselectOrgPageInteraction {
	public static IInteraction clickOnOrg = context -> {
		final String orgName = REMOVE(context, Constants.ORG_NAME, String.class);
		FF(context, GenericFind.class).byCSSSelectorAndBodyContaining(".org-selector-name", orgName).click();
	};
}
