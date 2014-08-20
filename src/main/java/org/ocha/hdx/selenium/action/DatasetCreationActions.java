/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.interaction.DatasetCreationInteraction;

/**
 * @author Dan Mihaila
 *
 */
public class DatasetCreationActions {
	private static Logger logger = Logger.getLogger(DatasetCreationActions.class);

	public static IAction nextAddDataAction = context -> {

		DatasetCreationInteraction.clickOnNextAddDataInteraction.doAction(context);
	};

	public static IAction addCountryAction = context -> {

		DatasetCreationInteraction.clickSelectorCountryInteraction.doAction(context);
	};


}
