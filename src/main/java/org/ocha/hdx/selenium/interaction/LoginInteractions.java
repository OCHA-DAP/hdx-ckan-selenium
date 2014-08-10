/**
 * 
 */
package org.ocha.hdx.selenium.interaction;

import static org.ocha.hdx.selenium.util.Util.FF;
import static org.ocha.hdx.selenium.util.Util.REMOVE;
import static org.ocha.hdx.selenium.util.Util.WD;

import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.GenericFind;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author alexandru-m-g
 *
 */
public class LoginInteractions {

	public static final IInteraction loginInteraction = context -> {
		final String newUser = REMOVE(context, ContextConstants.USERNAME, String.class);
		final String password = REMOVE(context, ContextConstants.PASSWORD, String.class);

		new WebDriverWait(WD(context),5).until( 
				(ExpectedCondition<Boolean>) d -> d.findElement(By.id("field-login"))!=null
				);
		WD(context).findElement(By.id("field-login")).sendKeys(newUser);
		WD(context).findElement(By.id("field-password")).sendKeys(password);
		FF(context, GenericFind.class).byCSSSelectorAndBodyContaining("button.btn-primary", "Login").click();

	};

	public static final IInteraction logoutInteraction = context -> {
		WD(context).findElement(By.cssSelector(".gravatar")).click();
		FF(context, GenericFind.class).byCSSSelectorAndAttributeContaining(".dropdown-menu a", "href", "logout").click();
	};

}
