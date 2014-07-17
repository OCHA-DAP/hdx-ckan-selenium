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
			}
			else
				prop.load(input);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	public static String getDomainWithHttp(){
		return prop.getProperty("base_url") ;
	}
}
