/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.DatasetConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


/**
 * @author Dan Mihaila
 *
 */
public class DatasetCreationChecks {


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

}
