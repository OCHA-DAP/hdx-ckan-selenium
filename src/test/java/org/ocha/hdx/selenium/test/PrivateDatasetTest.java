package org.ocha.hdx.selenium.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.DatasetListPageActions;
import org.ocha.hdx.selenium.action.LoginActions;
import org.ocha.hdx.selenium.action.OrganizationListPageActions;
import org.ocha.hdx.selenium.check.DatasetCreationChecks;
import org.ocha.hdx.selenium.interaction.BasicInteractions;
import org.ocha.hdx.selenium.util.ContextConstants;
import org.ocha.hdx.selenium.util.DatasetConstants;

import java.util.Map;

/**
 * @author Alexandru Artimon
 * @since 22/10/14
 */
public class PrivateDatasetTest extends AbstractHdxSeleniumTest {
    private static Logger logger = Logger.getLogger(PrivateDatasetTest.class);

    @Test
    public void test() {
        final Map<String,Object> context = this.instantiateContext();

        logger.info("User requesting membership in org");

        BasicActions.goToHomePageAction.doAction(context);

        BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
        LoginActions.loginAsExistingUserAction.doAction(context);

        DatasetCreationUtil.createDataset(context);

        String title = (String) context.get(DatasetConstants.TITLE);
        String url = (String) context.get(DatasetConstants.URL);
        String fileUrl = (String) context.get(DatasetConstants.FILE_URL);
        String previewUrl = (String) context.get(DatasetConstants.PREVIEW_URL);

        testTitle(context, title, url);

        testDatasetVisibleOrgPage(context);

        LoginActions.logoutAction.doAction(context);
        //Now the dataset is created by the "editor" user and is private

        /**
         * Test 1 - We shouldn't be able to view it from the public side
         */
        BasicActions.goToHomePageAction.doAction(context);
        testAccess(context, url);

        testFileAccess(context, fileUrl);

        testPreview(context, previewUrl);

        testSearch(context, title);

        testDatasetNotVisibileOrgPage(context);


        /**
         * Test 2 - New user tries to access the dataset - shouldn't be able to do it
         */
        BasicActions.goToHomePageAction.doAction(context);
        BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
        LoginActions.loginAsNewUserAction.doAction(context);

        testAccess(context, url);

        testFileAccess(context, fileUrl);

        testPreview(context, previewUrl);

        testSearch(context, title);

        testDatasetNotVisibileOrgPage(context);

        BasicActions.goToHomePageAction.doAction(context);
        LoginActions.logoutAction.doAction(context);

        /**
         * Test 3 - Admin tries to access the dataset - should be able to do it
         */

        BasicActions.goToHomePageAction.doAction(context);
        BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
        LoginActions.loginAsAdminAction.doAction(context);

        testTitle(context, title, url);

        testSearch(context, title);

        testDatasetVisibleOrgPage(context);

        LoginActions.logoutAction.doAction(context);
    }

    private void testDatasetVisibleOrgPage(Map<String, Object> context) {
        OrganizationListPageActions.viewEditorOrgFromConfigByUrlAction.doAction(context);
        DatasetCreationChecks.checkDatasetVisibleOnOrgPage.doAction(context);
    }

    private void testDatasetNotVisibileOrgPage(Map<String, Object> context) {
        OrganizationListPageActions.viewEditorOrgFromConfigByUrlAction.doAction(context);
        DatasetCreationChecks.checkDatasetNotVisibleOnOrgPage.doAction(context);
    }

    private void testTitle(Map<String, Object> context, String title, String url) {
        context.put(ContextConstants.DESTINATION_URL, "dataset/" + url);
        BasicActions.goToURLAddBaseURL.doAction(context);
        context.put(DatasetConstants.TITLE, title);
        DatasetCreationChecks.checkTitleIsCorrect.doAction(context);
    }

    private void testAccess(Map<String, Object> context, String url) {
        context.put(ContextConstants.DESTINATION_URL, "dataset/" + url);
        BasicActions.goToURLAddBaseURL.doAction(context);
        DatasetCreationChecks.unauthorizedDatasetAccess.doAction(context);
    }

    private void testFileAccess(Map<String, Object> context, String fileUrl) {
        context.put(ContextConstants.DESTINATION_URL, fileUrl);
        BasicInteractions.navigateToUrlInteraction.doAction(context);
        DatasetCreationChecks.unauthorizedDatasetFileAccess.doAction(context);
    }

    private void testPreview(Map<String, Object> context, String previewUrl) {
        context.put(ContextConstants.DESTINATION_URL, previewUrl);
        BasicInteractions.navigateToUrlInteraction.doAction(context);
        DatasetCreationChecks.unauthorizedDatasetPreviewAccess.doAction(context);
    }

    private void testSearch(Map<String, Object> context, String title) {
        context.put(ContextConstants.TEXT_TO_WRITE, title);
        BasicActions.goToHomePageAction.doAction(context);
        DatasetListPageActions.searchForAction.doAction(context);
        DatasetCreationChecks.unauthorizedDatasetSearchAccess.doAction(context);
    }

}
