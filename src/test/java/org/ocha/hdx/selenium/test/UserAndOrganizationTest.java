package org.ocha.hdx.selenium.test;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.DashboardPageActions;
import org.ocha.hdx.selenium.action.LoginActions;
import org.ocha.hdx.selenium.action.OrganizationListPageActions;
import org.ocha.hdx.selenium.action.OrganizationViewPageActions;
import org.ocha.hdx.selenium.action.PreselectOrgPageActions;
import org.ocha.hdx.selenium.check.BasicChecks;
import org.ocha.hdx.selenium.check.DatasetFormPageChecks;
import org.ocha.hdx.selenium.check.OrganizationListPageChecks;
import org.ocha.hdx.selenium.check.OrganizationViewPageChecks;

public class UserAndOrganizationTest extends AbstractHdxSeleniumTest{
	private static Logger logger = Logger.getLogger(DatasetListPageSortingTest.class);


	@Test
	public void test() {
		final Map<String,Object> context = this.instantiateContext();

		this.requestMembershipInOrg(context);

		this.adminMakesUserMemberOfTheOrg(context);

		this.userRequestsEditorRights(context);

		this.adminGrantsEditorRights(context);

		this.userGoesToDatasetForm(context);

		this.adminRemovesUserFromOrg(context);

	}

	private void requestMembershipInOrg(final Map<String, Object> context) {
		/** 
		 * request membership in org
		 */
		logger.info("User requesting membership in org");

		BasicActions.goToHomePageAction.doAction(context);

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsNewUserAction.doAction(context);

		DashboardPageActions.joinOrganizationAction.doAction(context);

		OrganizationListPageActions.searchForOrgFromConfigAction.doAction(context);
		OrganizationListPageChecks.searchTermInUrlCheck.doAction(context);
		OrganizationListPageChecks.onlyOneQParamInUrlAfterSearchCheck.doAction(context);
		OrganizationListPageChecks.orgOnPageCheck.doAction(context);

		OrganizationListPageActions.viewOrgFromConfigAction.doAction(context);

		//OrganizationListPageActions.viewOrgFromConfigByUrlAction.doAction(context);
		OrganizationViewPageActions.requestMembershipAction.doAction(context);
		BasicChecks.successfulMessageCheck.doAction(context);

		LoginActions.logoutAction.doAction(context);
	}

	private void adminMakesUserMemberOfTheOrg(final Map<String, Object> context) {
		/**
		 *  Admin makes user a member of the org
		 */
		logger.info("admin making user a member of the org");

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsAdminAction.doAction(context);
		BasicActions.goToOrgListUsingMainMenuAction.doAction(context);
		//		OrganizationListPageActions.searchForOrgFromConfigAction.doAction(context);
		OrganizationListPageActions.viewOrgFromConfigByUrlAction.doAction(context);
		OrganizationViewPageActions.addUserFromConfigToOrgAsMember.doAction(context);

		OrganizationViewPageChecks.userFromConfigInMemberListAsMemberCheck.doAction(context);


		LoginActions.logoutAction.doAction(context);
	}


	private void userRequestsEditorRights(final Map<String, Object> context) {
		/**
		 * User requests editor rights
		 */
		logger.info("user requestion editor rights");

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsNewUserAction.doAction(context);
		BasicActions.goToSubmitPageUsingMainMenuAction.doAction(context);

		PreselectOrgPageActions.selectOrgFromConfigAction.doAction(context);
		PreselectOrgPageActions.requestEditorRightsAction.doAction(context);
		BasicChecks.successfulMessageCheck.doAction(context);

		LoginActions.logoutAction.doAction(context);
	}


	private void adminGrantsEditorRights(final Map<String, Object> context) {
		/**
		 * Admin grants editor rights
		 */
		logger.info("admin granting editor rights");

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsAdminAction.doAction(context);
		BasicActions.goToOrgListUsingMainMenuAction.doAction(context);
		OrganizationListPageActions.searchForOrgFromConfigAction.doAction(context);
		OrganizationListPageActions.viewOrgFromConfigAction.doAction(context);
		OrganizationViewPageActions.viewMembersAction.doAction(context);
		OrganizationViewPageActions.addUserFromConfigToOrgAsEditor.doAction(context);
		OrganizationViewPageChecks.userFromConfigInMemberListAsEditorCheck.doAction(context);
		LoginActions.logoutAction.doAction(context);
	}


	private void userGoesToDatasetForm(final Map<String, Object> context) {
		/**
		 * User goes to dataset form
		 */
		logger.info("user going to datset form");

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsNewUserAction.doAction(context);
		BasicActions.goToSubmitPageUsingMainMenuAction.doAction(context);

		PreselectOrgPageActions.selectOrgFromConfigAction.doAction(context);
		DatasetFormPageChecks.newDatasetFormRenderedCheck.doAction(context);
		LoginActions.logoutAction.doAction(context);
	}


	private void adminRemovesUserFromOrg(final Map<String, Object> context) {
		/**
		 * Admin removes user from org
		 */
		logger.info("admin removing user from org");

		BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
		LoginActions.loginAsAdminAction.doAction(context);
		BasicActions.goToOrgListUsingMainMenuAction.doAction(context);
		OrganizationListPageActions.searchForOrgFromConfigAction.doAction(context);
		OrganizationListPageActions.viewOrgFromConfigAction.doAction(context);
		OrganizationViewPageActions.viewMembersAction.doAction(context);
		OrganizationViewPageActions.removeConfigMemberFromOrg.doAction(context);
		LoginActions.logoutAction.doAction(context);
	}

}
