/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.WD;

import org.apache.log4j.Logger;
import org.ocha.hdx.selenium.util.SelectorConstants;
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

		//WD(context).findElement(By.id(SelectorConstants.NEXT_STEP_DATASET_CREATION)).click();

		//FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining("div.select2-container a.select2-choice", "href", "javascript").click();

		try{
			Thread.sleep(1000);
		}catch(final Exception ex){
			ex.printStackTrace();
		}
		final WebElement countrySelector = WD(context).findElement(By.cssSelector("div.select2-container a.select2-choice"));
		if(countrySelector == null){
			logger.info("element is null");
		}

		logger.info("click on country selector");
	};

}
