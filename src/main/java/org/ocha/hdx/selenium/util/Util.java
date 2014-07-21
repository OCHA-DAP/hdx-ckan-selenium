package org.ocha.hdx.selenium.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class Util {


	public static WebDriver WD (final Map<String,Object> context) {
		return (WebDriver)context.get(Constants.DRIVER);
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
}
