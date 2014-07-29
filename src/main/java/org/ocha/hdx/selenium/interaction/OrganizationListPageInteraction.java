/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.Constants;
import org.openqa.selenium.By;

/**
 * @author alexandru-m-g
 *
 */
public class OrganizationListPageInteraction {
	public static IInteraction searchForOrgInteraction = context -> {
		final String orgName = REMOVE(context, Constants.ORG_NAME, String.class);
		WD(context).findElement(By.cssSelector(".search-form input.search"))
		.sendKeys(orgName);
		WD(context).findElement(By.cssSelector(".search-form button")).click();

	};
	public static IInteraction viewOrgInteraction = context -> {
		final String orgName = REMOVE(context, Constants.ORG_NAME, String.class);
		WD(context).findElement(By.partialLinkText(orgName)).click();

	};

	public static IInteraction viewOrgByUrlInteraction = context -> {
		final String destinationUrl = REMOVE(context, Constants.DESTINATION_URL, String.class);
		final String orgName = REMOVE(context, Constants.ORG_NAME, String.class);
		WD(context).get(destinationUrl+"/organization/"+orgName);

	};

}
