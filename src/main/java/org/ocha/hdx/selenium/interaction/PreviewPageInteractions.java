package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PreviewPageInteractions {
	public static IPreviewInteraction showOnlyRowsInteraction = context -> {

		final Integer from = REMOVE(context, ContextConstants.FROM, Integer.class);
		final Integer to = REMOVE(context, ContextConstants.TO, Integer.class);
		final WebElement fromEl = FF(context, GenericFind.class).
				byCSSSelectorAndAttributeContaining(".pagination input", "name", "from");
		final WebElement toEl = FF(context, GenericFind.class).
				byCSSSelectorAndAttributeContaining(".pagination input", "name", "to");

		fromEl.sendKeys("\b\b\b" + from);
		toEl.sendKeys("\b\b\b" + to);

		fromEl.click();
	};

	public static IPreviewInteraction switchToMapViewInteraction = 
			context -> FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(".navigation a.btn", "href", "map").click();

	public static IPreviewInteraction selectLatitudeAndLongitudeInteraction = context -> {
//		final WebElement latSelectEl = WD(context).findElement(By.cssSelector(".editor-lat-field select"));
		final WebElement latOptionEl = FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(".editor-lat-field select option", 
				"value", "latitude");

//		final WebElement longSelectEl = WD(context).findElement(By.cssSelector(".editor-lon-field select"));
		final WebElement longOptionEl = FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(".editor-lon-field select option", 
				"value", "longitude");
		
		latOptionEl.click();
		longOptionEl.click();

		WD(context).findElement(By.cssSelector(".editor-update-map")).click();

	};
}
