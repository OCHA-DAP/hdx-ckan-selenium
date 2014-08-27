/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.junit.Assert.assertNull;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.check.DatasetCreationChecks;
import org.ocha.hdx.selenium.util.DatasetConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.ocha.hdx.selenium.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector);

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

	public static IInteraction datasetSourceInteraction = context -> {
		final String src = REMOVE(context, DatasetConstants.SOURCE, String.class);
		WD(context).findElement(By.id("field-dataset_source")).sendKeys(src);
	};

	public static IInteraction datasetDescriptionInteraction = context -> {
		final String desc = REMOVE(context, DatasetConstants.DESCRIPTION, String.class);
		WD(context).findElement(By.id("field-notes")).sendKeys(desc);
	};

	public static IInteraction datasetLicenseInteraction = context -> {

		String selector = "#mx-dataset-license div.select2-container a";
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector);
		//license selector
		WD(context).findElement(By.cssSelector(selector)).click();
		selector = "#field-license option";
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector);
		final String attr = "value";
		final String attrValue = "hdx-other";
		FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(selector, attr, attrValue).click();
		logger.info("clicked on license");

		selector = "#field-license_other";
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector);
		final String licenseOther = REMOVE(context, DatasetConstants.LICENSE, String.class);
		WD(context).findElement(By.id("field-license_other")).sendKeys(licenseOther);

		logger.info("filled the other field for license");

	};

	public static IInteraction datasetVisibilityInteraction = context -> {

		final String selector = "#mx-dataset-visibility div.select2-container a";
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector);
		//visibility selector
		WD(context).findElement(By.cssSelector(selector)).click();
		final String optionSelector = "#field-private option";
		Util.checkAndWaitIsLoadedByCSSSelector(context, optionSelector);
		final String attr = "value";
		final String attrValue = "True";
		FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(optionSelector, attr, attrValue).click();
		//click again to close the dropdown
		WD(context).findElement(By.cssSelector(selector)).click();
		logger.info("clicked on visibility");
	};

	public static IInteraction datasetMethodologyInteraction = context -> {
		final String selector = "#meth_other_radio";
		WD(context).findElement(By.cssSelector(selector)).click();
		final String methTxt = REMOVE(context, DatasetConstants.METHODOLOGY, String.class);
		WD(context).findElement(By.id("method_other")).sendKeys(methTxt);

		logger.info("clicked on methodology other");
	};

	public static IInteraction datasetCaveatsInteraction = context -> {
		final String caveatsTxt = REMOVE(context, DatasetConstants.CAVEATS, String.class);
		WD(context).findElement(By.id("field-caveats")).sendKeys(caveatsTxt);

		logger.info("filled in the caveats");
	};


	public static IInteraction datasetCheckOrganisationInteraction = context -> {
		DatasetCreationChecks.datasetSelectedOrganisationCheck.doAction(context);

		logger.info("checking Organisation");
	};

	public static IInteraction datasetDatesInteraction = context -> {
		WD(context).findElement(By.id("ui_date_range2")).click();
		WD(context).findElement(By.id("ui_date_range1")).click();
		String selector = "#ui-datepicker-div table.ui-datepicker-calendar tbody tr td a.ui-state-default";
		FF(context, GenericFind.class).byCSSSelectorAndBodyContaining(selector, "15").click();

		WD(context).findElement(By.id("ui_date_range2")).click();
		selector = "#ui-datepicker-div table.ui-datepicker-calendar tbody tr td a.ui-state-default";
		final WebElement endDateEl = FF(context, GenericFind.class).byCSSSelectorAndBodyContaining(selector, "10");
		assertNull(endDateEl);

		FF(context, GenericFind.class).byCSSSelectorAndBodyContaining(selector, "20").click();

		logger.info("filled in the caveats");
	};

}
