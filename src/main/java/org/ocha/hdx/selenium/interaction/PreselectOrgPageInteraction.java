/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author alexandru-m-g
 *
 */
public class PreselectOrgPageInteraction {
	public static IInteraction clickOnOrgInteraction = context -> {
		final String orgName = REMOVE(context, ContextConstants.ORG_NAME, String.class);
		WD(context).findElement(By.id(SelectorConstants.ORG_ITEM_PREFIX + orgName)).click();
	};

	public static IInteraction requestEditorRightInteraction = context -> {
		if ( Config.getNonReversableActions() ) {
			final String selector = ".modal-footer button.hdx-submit-btn";
			new WebDriverWait(WD(context),5).until( 
					(ExpectedCondition<Boolean>) d -> 
					FF(context, GenericFind.class).byCSSSelectorAndDisplayed(selector)!=null
					);
			FF(context, GenericFind.class).byCSSSelectorAndDisplayed(selector).click();
		}

	};
}
