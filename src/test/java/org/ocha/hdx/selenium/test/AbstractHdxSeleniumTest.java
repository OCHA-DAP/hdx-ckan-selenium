/**
 * 
 */
package org.ocha.hdx.selenium.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;

/**
 * @author alexandru-m-g
 *
 */
public class AbstractHdxSeleniumTest {
	private static WebDriver driver = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		final String profileName = Config.getFirefoxProfileName();
		if ( profileName != null && !"".equals(profileName.trim()) ) {
			final ProfilesIni allProfiles = new ProfilesIni();
			driver = new FirefoxDriver(allProfiles.getProfile(profileName.trim()));
		} else {
			driver = new FirefoxDriver();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	protected Map<String,Object> instantiateContext() { 
		final Map<String, Object> context = new HashMap<>();
		context.put(ContextConstants.DRIVER, driver);
		return context;
	}

}
