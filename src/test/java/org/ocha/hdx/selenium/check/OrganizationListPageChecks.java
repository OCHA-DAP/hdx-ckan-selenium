package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.SelectorConstants;
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
		final String orgName = Config.getOrgNameForNomemberUser().toLowerCase();
		context.put(ContextConstants.URL_CONTAINS, "q="+orgName);
		BasicChecks.urlContainsCheck.doAction(context);
	};


	public static ICheckAction orgOnPageCheck = context -> {
		final String orgName = Config.getOrgNameForNomemberUser();
		try{
			final WebElement orgEl = WD(context).findElement(By.id(SelectorConstants.ORG_ITEM_PREFIX+orgName.toLowerCase()));
			assertNotNull(String.format("%s should be in the org list", orgName), orgEl);
		}
		catch (final NoSuchElementException e) {
			assertTrue(String.format("%s should be in the org list", orgName), false);
		}
	};
}
