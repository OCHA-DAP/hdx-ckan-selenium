/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.util.DatasetConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


/**
 * @author Dan Mihaila
 *
 */
public class DatasetCreationChecks {

	private static Logger logger = Logger.getLogger(DatasetCreationChecks.class);

	public static ICheckAction countryErrorCheck = context -> {
		BasicChecks.errorMessageCheck.doAction(context);
	};

	public static ICheckAction addedCountryCheck = context -> {
		final String countryID = (String) context.remove(DatasetConstants.EL_COUNTRY_ID);
		final WebElement addedCountry = WD(context).findElement(By.id(countryID));
		assertNotNull(addedCountry);
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
