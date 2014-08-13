/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.BasicInteractions;

/**
 * @author alexandru-m-g
 *
 */
public class GroupViewPageActions {
	public static IAction viewActivityStream = 
			context -> BasicInteractions.clickOnActivityStreamInteraction.doAction(context);
}
