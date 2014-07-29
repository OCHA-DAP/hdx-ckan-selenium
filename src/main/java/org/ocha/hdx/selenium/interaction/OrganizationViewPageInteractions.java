/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.Constants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author alexandru-m-g
 *
 */
public class OrganizationViewPageInteractions {
	public static IInteraction requestMembershipInteraction = context -> {
		WD(context).findElement(By.partialLinkText("REQUEST MEMBERSHIP")).click();
		WD(context).findElement(By.id("message")).sendKeys("This is a test membership request. Please ignore.");
		//		WD(context).findElement(By.cssSelector(".dataset-form .hdx-submit-btn")).click();
	};

	public static IInteraction addMemberInteraction = context -> {
		final String username = REMOVE(context, Constants.USERNAME, String.class);
		final String role = REMOVE(context, Constants.ROLE, String.class);

		WD(context).findElement(By.partialLinkText("ADD MEMBER")).click();

		WD(context).findElement(By.cssSelector("#s2id_username a")).click();
		FF(context, GenericFind.class).byCSSSelectorAndDisplayed("input.select2-input").sendKeys(username.substring(0, 4));
		new WebDriverWait(WD(context),5).until( 
				(ExpectedCondition<Boolean>) d -> FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining("div.select2-result-label", "data-value", username)!=null
				);
		FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining("div.select2-result-label", "data-value", username).click();

		WD(context).findElement(By.cssSelector("#s2id_role a")).click();
		FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining("div.select2-result-label", "data-value", role).click();

		WD(context).findElement(By.cssSelector(".dataset-form .hdx-submit-btn")).click();
	};
}
