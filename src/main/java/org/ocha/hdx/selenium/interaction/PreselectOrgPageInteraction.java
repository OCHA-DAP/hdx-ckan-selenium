/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author alexandru-m-g
 *
 */
public class PreselectOrgPageInteraction {
	private static Logger logger = Logger.getLogger(PreselectOrgPageInteraction.class);

	public static IInteraction clickOnOrgInteraction = context -> {
		final String orgName = REMOVE(context, ContextConstants.ORG_NAME, String.class);

		new WebDriverWait(WD(context),5).until((ExpectedCondition<Boolean>) d -> 
		{
			logger.info("check if js is completely loaded");
			final String selector = ".mx-init-complete";
			final WebElement searchedEl = FF(context, GenericFind.class).byCSSSelectorAndDisplayed(selector);
			return searchedEl!=null? true: false;
		});

		WD(context).findElement(By.id(SelectorConstants.ORG_ITEM_PREFIX + orgName)).click();
		logger.info("click on organization interaction");
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
