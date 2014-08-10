/**
 * 
 */
package org.ocha.hdx.selenium.check;

import org.ocha.hdx.selenium.util.ContextConstants;

/**
 * @author alexandru-m-g
 *
 */
public class DatasetFormPageChecks {
	public static ICheckAction newDatasetFormRenderedCheck = context -> {
		context.put(ContextConstants.URL_CONTAINS, "dataset/new");
		BasicChecks.urlContainsCheck.doAction(context);

	};
}
