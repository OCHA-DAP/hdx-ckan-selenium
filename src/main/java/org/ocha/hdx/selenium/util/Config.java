/**
 * 
 */
package org.ocha.hdx.selenium.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author alexandru-m-g
 *
 */
public class Config {
	private static Logger logger = Logger.getLogger(FindInDatasetListPage.class);

	private static Properties prop = new Properties();

	static {

		try(InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				logger.error("Problem with config properties file");
			} else {
				prop.load(input);
			}
		}
		catch (final Exception e) {
			logger.error(e.getMessage());
		}

	}

	public static String getDomainWithHttp(){
		return prop.getProperty("base.url") ;
	}

	public static String getOrgNameForNewUser(){
		return prop.getProperty("user.new.org") ;

	}

	public static String getNewUsername() {
		return prop.getProperty("user.new.username") ;
	}

	public static String getNewUserPassword() {
		return prop.getProperty("user.new.password") ;
	}

	public static String getSysadminUsername() {
		return prop.getProperty("sysadmin.username") ;
	}


	public static String getSysadminPassword() {
		return prop.getProperty("sysadmin.password") ;
	}

	public static boolean getNonReversableActions() {
		final String reversableStr =  prop.getProperty("base.non_reversable_actions");
		if ( reversableStr!=null && "true".equals(reversableStr.trim()) ) {
			return true;
		} else {
			return false;
		}
	}

}