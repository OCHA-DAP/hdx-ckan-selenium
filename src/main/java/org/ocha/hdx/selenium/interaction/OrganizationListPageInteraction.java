/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.openqa.selenium.By;

/**
 * @author alexandru-m-g
 *
 */
public class OrganizationListPageInteraction {
	public static IInteraction searchForOrgInteraction = context -> {
		final String orgName = REMOVE(context, ContextConstants.ORG_NAME, String.class);
		WD(context).findElement(By.cssSelector(".search-form input.search"))
		.sendKeys(orgName);
		WD(context).findElement(By.cssSelector(".search-form button")).click();

	};
	public static IInteraction viewOrgInteraction = context -> {
		final String orgName = REMOVE(context, ContextConstants.ORG_NAME, String.class);
		final String selector = String.format("#%s h3 a", SelectorConstants.ORG_ITEM_PREFIX + orgName);
		WD(context).findElement(By.cssSelector(selector)).click();

	};

	public static IInteraction viewOrgByUrlInteraction = context -> {
		final String destinationUrl = REMOVE(context, ContextConstants.DESTINATION_URL, String.class);
		final String orgName = REMOVE(context, ContextConstants.ORG_NAME, String.class);
		WD(context).get(destinationUrl+"organization/"+orgName);

	};

}
