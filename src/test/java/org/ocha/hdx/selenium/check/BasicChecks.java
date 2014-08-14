package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.BasicFind;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class BasicChecks {
	public static ICheckAction datasetMainMenuItemExistsCheck = context -> {
		final BasicFind basicFind = FF(context, BasicFind.class);
		assertNotNull(basicFind.datasetListMainMenuItem());
	};

	public static ICheckAction urlContainsCheck = context -> {
		final String str = (String) context.remove(ContextConstants.URL_CONTAINS);
		final String currentUrl = WD(context).getCurrentUrl();
		assertTrue("It needs to be the url for creating new dataset",WD(context).getCurrentUrl().contains(str));

	};


	public static ICheckAction successfulMessageCheck = context -> {
		if ( Config.getNonReversableActions()) {
			try{
				WD(context).findElement(By.cssSelector(".alert-success"));
				assertTrue("There needs to be a succesful message on the page",true);
			}
			catch(final NoSuchElementException e){
				assertTrue("There needs to be a succesful message on the page",false);
			}
		}

	};
}
