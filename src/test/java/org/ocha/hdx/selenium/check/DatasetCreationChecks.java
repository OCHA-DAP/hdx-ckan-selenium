/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.*;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.util.DatasetConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;


/**
 * @author Dan Mihaila
 *
 */
public class DatasetCreationChecks {

	private static Logger logger = Logger.getLogger(DatasetCreationChecks.class);

	public static ICheckAction countryErrorCheck = context -> {
		BasicChecks.errorMessageCheck.doAction(context);
	};

    public static ICheckAction unauthorizedDatasetAccess = context -> {
        final WebElement alert = WD(context).findElement(By.cssSelector(DatasetConstants.ALERTS_ON_PAGE));
        assertNotNull(alert);
        assertNotNull(alert.getText());
        assertTrue(alert.getText().startsWith("Unauthorized to read package"));
    };

    public static ICheckAction unauthorizedDatasetFileAccess = context -> {
        final WebElement error = WD(context).findElement(By.cssSelector("code.main-exception"));
        assertNotNull(error);
        assertNotNull(error.getText());
        assertTrue(error.getText().startsWith("NotAuthorized"));
    };

    public static ICheckAction unauthorizedDatasetPreviewAccess = context -> {
        final WebElement login = WD(context).findElement(By.cssSelector("h1.page-heading"));
        assertNotNull(login);
        assertNotNull(login.getText());
        assertTrue(login.getText().startsWith("Login"));
    };

    public static ICheckAction unauthorizedDatasetSearchAccess = context -> {
        final WebElement heading = WD(context).findElement(By.cssSelector("h2"));
        assertNotNull(heading);
        assertNotNull(heading.getText());
        assertTrue(heading.getText().trim().startsWith("Sorry no datasets found for"));
    };

    public static ICheckAction addedCountryCheck = context -> {
        final String countryID = (String) context.remove(DatasetConstants.EL_COUNTRY_ID);
        final WebElement addedCountry = WD(context).findElement(By.id(countryID));
        assertNotNull(addedCountry);
    };

    public static ICheckAction checkTitleIsCorrect = context -> {
        final String title = (String) context.get(DatasetConstants.TITLE);
        final WebElement addedCountry = WD(context).findElement(By.cssSelector(DatasetConstants.DATASET_TITLE_CSS_SELECTOR));
        assertNotNull(addedCountry);
        String existingTitle = addedCountry.getText();
        assertEquals(title, existingTitle);
    };

    public static ICheckAction checkDatasetVisibleOnOrgPage = context -> {
        final String title = (String) context.get(DatasetConstants.TITLE);
        final List<WebElement> links = WD(context).findElements(By.cssSelector(".dataset-heading a"));
        assertTrue(links.size() > 0);
        WebElement link = links.get(0);
        assertNotNull(link);
        String existingTitle = link.getText().trim();
        assertEquals(title, existingTitle);
    };

    public static ICheckAction checkDatasetNotVisibleOnOrgPage = context -> {
        final String title = (String) context.get(DatasetConstants.TITLE);
        final List<WebElement> links = WD(context).findElements(By.cssSelector(".dataset-heading a"));
        assertTrue(links.size() > 0);
        WebElement link = links.get(0);
        assertNotNull(link);
        String existingTitle = link.getText().trim();
        assertNotEquals(title, existingTitle);
    };


    public static ICheckAction removedCountryCheck = context -> {
		final String countryID = (String) context.remove(DatasetConstants.EL_COUNTRY_ID);
		WebElement addedCountry = null;

		try{
			addedCountry = WD(context).findElement(By.id(countryID));
			//the country should be removed and this should throw exception
			assertFalse(false);
		}
		catch (final NoSuchElementException e) {
			//added country was removed
			assertTrue(true);
		}
		//assertNull(addedCountry);
	};

	public static ICheckAction datasetSelectedOrganisationCheck = context -> {

		final String url = WD(context).getCurrentUrl();
		final int firstIndex = url.indexOf("organization_id=");
		final int lastIndex = url.lastIndexOf("organization_id=");
		final int orgLength = "organization_id=".length();
		assertEquals(firstIndex, lastIndex);

		final String orgID = url.substring(lastIndex+orgLength, url.length());
		logger.info("Organization id found in URL: " + orgID);
		assertTrue(orgID!=null && "".compareTo(orgID)!=0);

		final String selector = "#mx-dataset-organisation select option";
		final WebElement country = FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(selector, "value", orgID);
		assertNotNull(country);

		final String attr = country.getAttribute("selected");
		assertTrue(attr!=null && "true".compareTo(attr)==0);

		logger.info("Organization found in url is the selected in dropdown");

	};






}
