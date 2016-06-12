/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.ocha.hdx.selenium.util.Util.WD;

import java.util.List;

import org.ocha.hdx.selenium.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/**
 * @author alexandru-m-g
 *
 */
public class MapExplorerChecks {

	public static ICheckAction slicesDropdownListNotEmptyCheck = context -> {
		List<WebElement> elements = WD(context).findElements(By.cssSelector(".legend-list .selector li"));
		assertTrue(elements!=null && elements.size()>0);
	};
	public static ICheckAction mapIsLoadedCheck = context -> {
		Util.checkAndWaitIsLoadedByCSSSelector(context, "#map svg g");
		List<WebElement> elements = WD(context).findElements(By.cssSelector("#map svg g"));
		assertTrue(elements!=null && elements.size()>0);
	};
	public static ICheckAction chartIsLoadedCheck = context -> {
		Util.checkAndWaitIsLoadedByCSSSelector(context, ".chart-item-wrapper svg g");
		List<WebElement> elements = WD(context).findElements(By.cssSelector(".chart-item-wrapper svg g"));
		assertTrue(elements!=null && elements.size()>0);
	};
	
	public static ICheckAction mapIsNotLoadedCheck = context -> {
		try {
			Util.checkAndWaitIsLoadedByCSSSelector(context, "#map svg g", 2);
		} catch (TimeoutException e) {
			return;
		}
		fail("The map should not be found");
	};
	public static ICheckAction chartIsNotLoadedCheck = context -> {
		try {
			Util.checkAndWaitIsLoadedByCSSSelector(context, ".chart-item-wrapper svg g", 2);
		} catch (TimeoutException e) {
			return;
		}
		fail("The chart should not be found");
	};

}
