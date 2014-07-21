/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.BasicFind;
import org.ocha.hdx.selenium.util.Constants;
import org.openqa.selenium.By;


/**
 * @author alexandru-m-g
 *
 */
public class BasicInteractions {

	public static IInteraction navigateToUrlInteraction = context -> {
		final String destinationUrl = (String) context.remove(Constants.DESTINATION_URL);
		WD(context).get(destinationUrl);

	}; 

	public static IInteraction clickOnDatasetInMainMenuInteraction = context -> {
		final BasicFind basicFind = FF(context, BasicFind.class);
		basicFind.datasetListMainMenuItem().click();
	};

	public static IInteraction clickOnElementWithId = context -> {
		final String id = (String) context.remove(Constants.ID_OF_EL_TO_CLICK);
		if (id == null) {
			throw new NullPointerException(Constants.ID_OF_EL_TO_CLICK + " should not be null in context");
		}
		WD(context).findElement(By.id(id)).click();

	};

	public static IInteraction clickOnElementWithCssSelector = context -> {
		final String selector = (String) context.remove(Constants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK);
		if (selector == null) {
			throw new NullPointerException(Constants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK + " should not be null in context");
		}
		WD(context).findElement(By.cssSelector(selector)).click();

	};

	public static IInteraction writeInElementWithId = context -> {
		final String id = (String) context.remove(Constants.ID_OF_EL_TO_WRITE);
		final String text = (String) context.remove(Constants.TEXT_TO_WRITE);
		if (id == null) {
			throw new NullPointerException(Constants.ID_OF_EL_TO_CLICK + " should not be null in context");
		}
		WD(context).findElement(By.id(id)).sendKeys(text);

	};

	public static IInteraction writeInElementWithCssSelector = context -> {
		final String selector = (String) context.remove(Constants.CSS_SELECTOR_OF_EL_TO_WRITE);
		final String text = (String) context.remove(Constants.TEXT_TO_WRITE);
		if (selector == null) {
			throw new NullPointerException(Constants.CSS_SELECTOR_OF_ELEMENT_TO_CLICK + " should not be null in context");
		}
		WD(context).findElement(By.cssSelector(selector)).sendKeys(text);

	};

}
