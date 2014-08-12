package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.WD;

import org.openqa.selenium.By;

public class DatasetViewPageInteraction {
	public static IInteraction previewTheFirstResourceInteraction = 
			context -> WD(context).findElement(By.partialLinkText("PREVIEW")).click(); 
}
