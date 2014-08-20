/**
 * 
 */
package org.ocha.hdx.selenium.check;


/**
 * @author Dan Mihaila
 *
 */
public class DatasetCreationChecks {


	public static ICheckAction countryErrorCheck = context -> {
		BasicChecks.errorMessageCheck.doAction(context);
	};
}
