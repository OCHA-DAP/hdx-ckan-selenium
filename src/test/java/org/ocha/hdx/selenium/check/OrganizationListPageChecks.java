package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class OrganizationListPageChecks {
	public static ICheckAction onlyOneQParamInUrlAfterSearchCheck = context -> {
		final String url = WD(context).getCurrentUrl();
		final int firstIndex = url.indexOf("q=");
		final int lastIndex = url.lastIndexOf("q=");
		assertEquals(firstIndex, lastIndex);
	};

	public static ICheckAction searchTermInUrlCheck = context -> {
		final String orgName = Config.getOrgNameForNewUser().toLowerCase();
		context.put(Constants.URL_CONTAINS, "q="+orgName);
		BasicChecks.urlContainsCheck.doAction(context);
	};


	public static ICheckAction orgOnPageCheck = context -> {
		final String orgName = Config.getOrgNameForNewUser();
		try{
			final WebElement orgEl = WD(context).findElement(By.partialLinkText(orgName));
			assertNotNull(String.format("%s should be in the org list", orgName), orgEl);
		}
		catch (final NoSuchElementException e) {
			assertTrue(String.format("%s should be in the org list", orgName), false);
		}
	};
}
