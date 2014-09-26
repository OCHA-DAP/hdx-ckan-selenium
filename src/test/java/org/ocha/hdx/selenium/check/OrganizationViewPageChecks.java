/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import java.util.Map;

import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	public static ICheckAction wrongUsernameCheck = new ICheckAction() {
		
		@Override
		public void doAction(Map<String, Object> context) {
			String username = "xz12-non-existing-username";
			WD(context).findElement(By.partialLinkText("ADD MEMBER")).click();

			new WebDriverWait(WD(context),5).until( 
					(ExpectedCondition<Boolean>) d -> d.findElement(By.cssSelector("#s2id_username a"))!=null
					);
			WD(context).findElement(By.cssSelector("#s2id_username a")).click();
			WebElement usernameInputBox = FF(context, GenericFind.class).byCSSSelectorAndDisplayed("input.select2-input"); 
			usernameInputBox.sendKeys(username);
			new WebDriverWait(WD(context),5).until( 
					(ExpectedCondition<Boolean>) d -> !usernameInputBox.getAttribute("class").contains("select2-active")
					);
			
			try {
				WebElement noResult = WD(context).findElement(By.cssSelector(".select2-no-results"));
				assertNotNull(noResult);
			}
			catch (NoSuchElementException e) {
				fail("The 'no-result' message should appear as the search-as-you-type result");
			}
			
			WD(context).findElement(By.cssSelector(".dataset-form .hdx-submit-btn")).click();
			
			try {
				WebElement errorMsg = WD(context).findElement(By.cssSelector("div.flash-messages div.alert"));
				assertNotNull(errorMsg);
			}
			catch (NoSuchElementException e) {
				fail("An error message should be displayed when the user clicks submit without writing a username");
			}
		}
	};

}
