/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * @author alexandru-m-g
 *
 */
public class OrganizationViewPageChecks {

	public static ICheckAction userFromConfigInMemberListCheck = context -> {
		final String username = Config.getNewUsername();
		try{
			final WebElement orgEl = WD(context).findElement(By.partialLinkText(username));
			assertNotNull(String.format("%s should be in the members list", username), orgEl);
		}
		catch (final NoSuchElementException e) {
			assertTrue(String.format("%s should be in the members list", username), false);
		}

	};

}
