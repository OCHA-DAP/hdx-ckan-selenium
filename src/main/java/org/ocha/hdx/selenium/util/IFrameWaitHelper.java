/**
 * 
 */
package org.ocha.hdx.selenium.util;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.WD;

import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author alexandru-m-g
 *
 */
public interface IFrameWaitHelper {
	default void wrapperDoAction (final Map<String,Object> context) {
		WD(context).switchTo().frame(0);
		new WebDriverWait(WD(context),5).until( 
				(ExpectedCondition<Boolean>) d -> FF(context, GenericFind.class).
				byCSSSelectorAndAttributeContaining(".pagination input", "name", "from")!=null
				);

		this.innerDoAction(context);
		WD(context).switchTo().defaultContent();
	}

	void innerDoAction(final Map<String,Object> context);
}
