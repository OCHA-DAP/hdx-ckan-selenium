package org.ocha.hdx.selenium.test;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.DashboardPageActions;
import org.ocha.hdx.selenium.action.LoginActions;
import org.ocha.hdx.selenium.action.OrganizationListPageActions;
import org.ocha.hdx.selenium.action.OrganizationViewPageActions;
import org.ocha.hdx.selenium.check.OrganizationListPageChecks;

public class UserAndOrganizationTest extends AbstractHdxSeleniumTest{
	private static Logger logger = Logger.getLogger(DatasetListPageSortingTest.class);


	@Test
	public void test() {
		final Map<String,Object> context = this.instantiateContext();

		BasicActions.goToHomePageAction.doAction(context);;

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsNewUserAction.doAction(context);

		DashboardPageActions.joinOrganizationAction.doAction(context);

		OrganizationListPageActions.searchForOrgFromConfigAction.doAction(context);
		OrganizationListPageChecks.searchTermInUrlCheck.doAction(context);
		//OrganizationListPageChecks.onlyOneQParamInUrlAfterSearchCheck.doAction(context);
		//OrganizationListPageChecks.checkOrgOnPage.doAction(context);

		//OrganizationListPageActions.viewOrgFromConfigAction.doAction(context);

		OrganizationListPageActions.viewOrgFromConfigByUrlAction.doAction(context);
		OrganizationViewPageActions.requestMembershipAction.doAction(context);

		LoginActions.logoutAction.doAction(context);

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsAdminAction.doAction(context);
		BasicActions.goToOrgListUsingMainMenuAction.doAction(context);
		//		OrganizationListPageActions.searchForOrgFromConfigAction.doAction(context);
		OrganizationListPageActions.viewOrgFromConfigByUrlAction.doAction(context);
		OrganizationViewPageActions.addMemberFromConfigToOrgAsMember.doAction(context);

	}


}
