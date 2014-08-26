/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.util.DatasetConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.ocha.hdx.selenium.util.Util;
import org.openqa.selenium.By;

/**
 * @author Dan Mihaila
 *
 */
public class DatasetCreationInteraction {


	private static Logger logger = Logger.getLogger(DatasetCreationInteraction.class);


	public static IInteraction clickOnNextAddDataInteraction = context -> {

		WD(context).findElement(By.id(SelectorConstants.NEXT_STEP_DATASET_CREATION)).click();
		logger.info("click on Next Add Data");
	};

	public static IInteraction clickSelectorCountryInteraction = context -> {

		String selector = "div.select2-container a.select2-choice";
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector, null, null);

		//country selector
		WD(context).findElement(By.cssSelector(selector)).click();
		selector = "div.select2-result-label";
		final String attrName = "data-value";
		//adding one country, which has the code in context
		final String attrValue = REMOVE(context, DatasetConstants.COUNTRY_ID, String.class);
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector, attrName, attrValue);
		FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(selector, attrName, attrValue).click();
		logger.info("clicked on country selector");
	};


	public static IInteraction clickRemoveCountryInteraction = context -> {
		final String countryID = REMOVE(context, DatasetConstants.COUNTRY_ID, String.class);
		final String elID = DatasetConstants.ADDED_COUNTRY_ITEM_PREFIX+countryID;
		WD(context).findElement(By.id(elID)).click();
		logger.info("clicked on remove country link");
	};

	public static IInteraction datasetTitleInteraction = context -> {
		final String title = REMOVE(context, DatasetConstants.TITLE, String.class);
		WD(context).findElement(By.id("field-title")).sendKeys(title);
	};

	public static IInteraction datasetSourceInteraction= context -> {
		final String src = REMOVE(context, DatasetConstants.SOURCE, String.class);
		WD(context).findElement(By.id("field-dataset_source")).sendKeys(src);
	};

	public static IInteraction datasetDescriptionInteraction= context -> {
		final String desc = REMOVE(context, DatasetConstants.DESCRIPTION, String.class);
		WD(context).findElement(By.id("field-notes")).sendKeys(desc);
	};

	public static IInteraction datasetLicenseInteraction= context -> {
		//final String desc = REMOVE(context, DatasetConstants.DESCRIPTION, String.class);
		//WD(context).findElement(By.id("field-notes")).sendKeys(desc);
	};


}
