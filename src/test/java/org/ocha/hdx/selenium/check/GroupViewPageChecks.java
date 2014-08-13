/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.WD;

import java.util.List;

import org.ocha.hdx.selenium.util.ContextConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author alexandru-m-g
 *
 */
public class GroupViewPageChecks {
	public static ICheckAction onPakistanPageCheck = context -> {
		context.put(ContextConstants.URL_CONTAINS, "group/pak");
		BasicChecks.urlContainsCheck.doAction(context);
	};
	public static ICheckAction listOfActivitiesAppearsCheck = context -> {
		final List<WebElement> items = WD(context).findElements(By.cssSelector("ul.activity li"));
		assertTrue("There should be at least 5 itmes in the activity stream", items.size() >= 5);
		
	};
}
