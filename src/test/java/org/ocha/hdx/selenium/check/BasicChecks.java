package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertNotNull;
import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.BasicFind;
import org.ocha.hdx.selenium.util.Constants;


public class BasicChecks {
	public static ICheckAction datasetMainMenuItemExistsCheck = context -> {
		final BasicFind basicFind = FF(context, BasicFind.class);
		assertNotNull(basicFind.datasetListMainMenuItem());
	};

	public static ICheckAction urlContainsCheck = context -> {
		final String str = (String) context.remove(Constants.URL_CONTAINS);
		WD(context).getCurrentUrl().contains(str);

	};
}
