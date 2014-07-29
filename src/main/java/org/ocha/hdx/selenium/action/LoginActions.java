/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.LoginInteractions;
import org.ocha.hdx.selenium.util.Config;
import org.ocha.hdx.selenium.util.Constants;

/**
 * @author alexandru-m-g
 *
 */
public class LoginActions {

	public static IAction loginAsNewUserAction = context -> {
		final String newUser = Config.getNewUsername();
		final String password = Config.getNewUserPassword();
		context.put(Constants.USERNAME, newUser);
		context.put(Constants.PASSWORD, password);
		LoginInteractions.loginInteraction.doAction(context);

	};

	public static IAction logoutAction = context -> LoginInteractions.logoutInteraction.doAction(context);

	public static IAction loginAsAdminAction = context -> {
		final String sysadmin = Config.getSysadminUsername();
		final String password = Config.getSysadminPassword();

		context.put(Constants.USERNAME, sysadmin);
		context.put(Constants.PASSWORD, password);
		LoginInteractions.loginInteraction.doAction(context);

	};
}
