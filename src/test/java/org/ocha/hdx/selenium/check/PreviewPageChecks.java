/**
 * 
 */
package org.ocha.hdx.selenium.check;

import static org.junit.Assert.assertEquals;
import static org.ocha.hdx.selenium.util.Util.WD;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author alexandru-m-g
 *
 */
public class PreviewPageChecks {
	public static IPreviewCheckAction onlyThreeItemsAppearCheck = context -> {
		final List<WebElement> rows = 
				WD(context).findElements(By.cssSelector(".slick-viewport .slick-row"));
		assertEquals(3, rows.size());
	};
	
	public static IPreviewCheckAction threeMarkersAppearOnMapCheck = context -> {
		final List<WebElement> markers = WD(context).findElements(By.cssSelector(".leaflet-marker-icon"));
		assertEquals(3, markers.size());
		
	};
}
