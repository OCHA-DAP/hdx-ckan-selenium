/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.util.BasicFind;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * @author alexandru-m-g
 *
 */
public class BasicInteractions {
	private static Logger logger = Logger.getLogger(BasicInteractions.class);
	public static IInteraction navigateToUrlInteraction = context -> {
		final String destinationUrl = (String) context.remove(ContextConstants.DESTINATION_URL);
		WD(context).get(destinationUrl);

	}; 

	public static IInteraction clickOnLoginInMainMenuInteraction = context -> {
		final WebElement loginLink = FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining("li.newLogin a", "href", "login");
		if (loginLink != null) {
			loginLink.click();
		} else {
			logger.error("Login link cannot be found");
		}
	};

	public static IInteraction clickOnDatasetInMainMenuInteraction = context -> {
		final BasicFind basicFind = FF(context, BasicFind.class);
		basicFind.datasetListMainMenuItem().click();
	};

	public static IInteraction clickOnSubmitInMainMenuInteraction = context -> {
		FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining("a", "href", "/dataset/preselect").click();
	};

	public static IInteraction clickOnOrganizationsInMainMenuInteraction = 
			context ->	FF(context, BasicFind.class).orgListMainMenuItem().click();

			public static IInteraction clickOnElementWithId = context -> {
				final String id = (String) context.remove(ContextConstants.ID_OF_EL_TO_CLICK);
				if (id == null) {
					throw new NullPointerException(ContextConstants.ID_OF_EL_TO_CLICK + " should not be null in context");
				}
				WD(context).findElement(By.id(id)).click();

			};

			public static IInteraction clickOnElementWithCssSelector = context -> {
				final String selector = (String) context.remove(ContextConstants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK);
				if (selector == null) {
					throw new NullPointerException(ContextConstants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK + " should not be null in context");
				}
				WD(context).findElement(By.cssSelector(selector)).click();

			};

			public static IInteraction writeInElementWithId = context -> {
				final String id = (String) context.remove(ContextConstants.ID_OF_EL_TO_WRITE);
				final String text = (String) context.remove(ContextConstants.TEXT_TO_WRITE);
				if (id == null) {
					throw new NullPointerException(ContextConstants.ID_OF_EL_TO_CLICK + " should not be null in context");
				}
				WD(context).findElement(By.id(id)).sendKeys(text);

			};

			public static IInteraction writeInElementWithCssSelector = context -> {
				final String selector = (String) context.remove(ContextConstants.CSS_SELECTOR_OF_EL_TO_WRITE);
				final String text = (String) context.remove(ContextConstants.TEXT_TO_WRITE);
				if (selector == null) {
					throw new NullPointerException(ContextConstants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK + " should not be null in context");
				}
				WD(context).findElement(By.cssSelector(selector)).sendKeys(text);

			};

}
