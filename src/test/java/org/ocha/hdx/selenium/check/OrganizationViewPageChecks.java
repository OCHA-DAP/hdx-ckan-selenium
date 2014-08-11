/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * @author alexandru-m-g
 *
 */
public class OrganizationViewPageChecks {

	private static ICheckAction userFromConfigInMemberListCheck = context -> {
		final String username = Config.getNomemberUsername();
		final String role = REMOVE(context, ContextConstants.ROLE, String.class);
		try{
			final String selector = String.format("#%s .%s", 
					SelectorConstants.MEMBER_ITEM_PREFIX+username, SelectorConstants.MEMBER_ROLE);
			final WebElement orgRoleEl = WD(context).findElement(By.cssSelector(selector));
			assertNotNull(String.format("%s should be in the members list", username), orgRoleEl);
			assertTrue(String.format("User %s should now have role '%s'", username, role), 
					orgRoleEl.getText().toLowerCase().contains(role) );
		}
		catch (final NoSuchElementException e) {
			assertTrue(String.format("%s should be in the members list", username), false);
		}

	};

	public static ICheckAction userFromConfigInMemberListAsMemberCheck = context -> {
		context.put(ContextConstants.ROLE, "member");
		userFromConfigInMemberListCheck.doAction(context);
	};

	public static ICheckAction userFromConfigInMemberListAsEditorCheck = context -> {
		context.put(ContextConstants.ROLE, "editor");
		userFromConfigInMemberListCheck.doAction(context);
	};

}
