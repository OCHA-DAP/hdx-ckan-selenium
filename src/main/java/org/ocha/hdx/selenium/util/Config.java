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

	public static String getOrgNameForNomemberUser(){
		return prop.getProperty("user.nomember.org") ;
	}

	public static String getOrgNameForEditorUser(){
		return prop.getProperty("user.editor.org") ;
	}

	public static String getNomemberUsername() {
		return prop.getProperty("user.nomember.username") ;
	}

	public static String getNomemberUserPassword() {
		return prop.getProperty("user.nomember.password") ;
	}

	public static String getEditorUsername() {
		return prop.getProperty("user.editor.username") ;
	}

	public static String getEditorUserPassword() {
		return prop.getProperty("user.editor.password") ;
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