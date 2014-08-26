package org.ocha.hdx.selenium.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	public static WebDriver WD (final Map<String,Object> context) {
		return (WebDriver)context.get(ContextConstants.DRIVER);
	}

	public static <T> T FF(final Map<String,Object> context, final Class<T> findClass) {
		T findInstance = (T) context.get(findClass.getName());
		if ( findInstance == null ) {
			try {
				final WebDriver driver = WD(context);
				if (driver == null) {
					throw new NullPointerException("The context has no driver although it MUST have one !");
				}
				final Constructor<T> constructor = findClass.getConstructor(WebDriver.class);
				findInstance = constructor.newInstance(driver);
				context.put(findClass.getName(), findInstance);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return findInstance;
	}

	public static<T> T REMOVE(final Map<String,Object> context, final String key, final Class<T> targetClass) {
		final T result = (T) context.remove(key);
		if ( result == null ) {
			throw new NullPointerException(key + " must appear in the context !");
		}
		return result;

	}
		
	public static String REMOVE_STRING(final Map<String,Object> context, final String key) {
		return REMOVE(context, key, String.class);
	}

	/**
	 * Method that waits for 5 seconds or until a specific element is loaded and displayed.
	 * @param context
	 * @param selector
	 * @param attributeName
	 * @param containedValue
	 */
	public static void checkAndWaitIsLoadedByCSSSelector(final Map<String, Object> context, final String selector, final String attributeName, final String containedValue) {
		new WebDriverWait(WD(context),5).until((ExpectedCondition<Boolean>) d -> 
		{
			logger.info("check if js is completely loaded");
			WebElement searchedEl = null;
			if(attributeName == null) {
				searchedEl = FF(context, GenericFind.class).byCSSSelectorAndDisplayed(selector);
			} else {
				searchedEl = FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(selector, attributeName, containedValue);
			}
			return searchedEl!=null? true: false;
		});
	}

	/**
	 * Method that waits for 5 seconds or until a specific element is loaded and displayed.
	 * @param context
	 * @param selector
	 */
	public static void checkAndWaitIsLoadedByCSSSelector(final Map<String, Object> context, final String selector) {
		checkAndWaitIsLoadedByCSSSelector(context, selector, null, null);
	}

}
