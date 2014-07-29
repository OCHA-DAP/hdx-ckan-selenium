/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;

import org.ocha.hdx.selenium.util.GenericFind;

/**
 * @author alexandru-m-g
 *
 */
public class DashboardPageInteractions {
	public static IInteraction joinOrganizationInteraction = 
			context -> FF(context, GenericFind.class).
			byCSSSelectorAndAttributeContaining(".btn-primary", "href", "organization").click();
}
