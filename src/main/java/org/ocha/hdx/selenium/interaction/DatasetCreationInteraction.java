/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.DatasetConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.ocha.hdx.selenium.util.SelectorConstants;
import org.ocha.hdx.selenium.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public static IInteraction clickOnSaveInteraction = context -> {
        WD(context).findElement(By.id(SelectorConstants.SAVE_DATASET)).click();
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
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector, attrName, attrValue, DatasetConstants.DELAY);
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
		final String title = (String) context.get(DatasetConstants.TITLE);
		WD(context).findElement(By.id("field-title")).sendKeys(title);
	};

    public static IInteraction datasetURLInteraction = context -> {
        String url = WD(context).findElement(By.cssSelector(".slug-preview .slug-preview-value")).getText();
        context.put(DatasetConstants.URL, url);

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


//	public static IInteraction datasetCheckOrganisationInteraction = context -> {
//		DatasetCreationChecks.datasetSelectedOrganisationCheck.doAction(context);
//
//		logger.info("checking Organisation");
//	};

	public static IInteraction datasetDatesInteraction = context -> {
		WD(context).findElement(By.id("ui_date_range2")).click();
		WD(context).findElement(By.id("ui_date_range1")).click();

		final String selector = "#ui-datepicker-div table.ui-datepicker-calendar tbody tr td a.ui-state-default";

        new WebDriverWait(WD(context),5).until(
                (ExpectedCondition<Boolean>) d -> {
                    final WebElement element = d.findElement(By.cssSelector(selector));
                    return element != null && element.isDisplayed();
                }
        );
        FF(context, GenericFind.class).byCSSSelectorAndBodyContaining(selector, "15").click();
		WD(context).findElement(By.id("ui_date_range2")).click();

		final String selector2 = "#ui-datepicker-div table.ui-datepicker-calendar tbody tr td a.ui-state-default";

        new WebDriverWait(WD(context),5).until(
                (ExpectedCondition<Boolean>) d -> d.findElement(By.cssSelector(selector2)) != null
        );
		final WebElement endDateEl = FF(context, GenericFind.class).byCSSSelectorAndBodyContaining(selector2, "10");
		assertNull(endDateEl);

		FF(context, GenericFind.class).byCSSSelectorAndBodyContaining(selector2, "20").click();

		logger.info("filled in the caveats");
	};

	//datasetResourceURLAction

	public static IInteraction datasetResourceURLInteraction = context -> {

		String selector = "mx-type-url";
		WD(context).findElement(By.id(selector)).click();

		selector = "field-url";
		WD(context).findElement(By.id(selector)).sendKeys("http://ourairports.com/countries/VE/airports.csv");

		selector = "mx-save-another";
		WD(context).findElement(By.id(selector)).click();

		selector = "error-license";
		assertNotNull(WD(context).findElement(By.id(selector)));

		selector = "field-name";
		WD(context).findElement(By.id(selector)).sendKeys("Airports in Venezuela");

		selector = "field-description";
		WD(context).findElement(By.id(selector)).sendKeys(DatasetConstants.TEXT_LONG);

		selector = "mx-save-another";
		WD(context).findElement(By.id(selector)).click();

		logger.info("adding an url as resource");
	};

	public static IInteraction datasetResourceFileInteraction = context -> {


		String selector = "#mx-type-file";
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector, 10);
		selector = "mx-type-file";
		WD(context).findElement(By.id(selector)).click();

		selector = "mx-file";
		WD(context).findElement(By.id(selector)).sendKeys(Config.getURLFile());

		selector="#field-name";
		Util.checkAndWaitIsLoadedByCSSSelector(context, selector, "value", null, 10);

		selector="field-description";
		WD(context).findElement(By.id(selector)).sendKeys(DatasetConstants.TEXT_LONG);

        new WebDriverWait(WD(context),5).until(
                (ExpectedCondition<Boolean>) d -> {
                    final WebElement element = d.findElement(By.id("field-url"));
                    return element != null && element.getAttribute("value").startsWith(Config.getDomainWithHttp());
                }
        );

        String fileUrl = WD(context).findElement(By.id("field-url")).getAttribute("value");
        context.put(DatasetConstants.FILE_URL, fileUrl);

//		selector = "mx-save-dataset";
//		WD(context).findElement(By.id(selector)).click();

		logger.info("uploading a file");
	};

}
