package org.ocha.hdx.selenium.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.DatasetCreationActions;
import org.ocha.hdx.selenium.action.DatasetListPageActions;
import org.ocha.hdx.selenium.action.LoginActions;
import org.ocha.hdx.selenium.check.BasicChecks;
import org.ocha.hdx.selenium.check.DatasetCreationChecks;
import org.ocha.hdx.selenium.interaction.DatasetListPageInteractions;
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


        context.put(ContextConstants.DESTINATION_URL, "dataset/" + url);
        BasicActions.goToURLAddBaseURL.doAction(context);

        context.put(DatasetConstants.TITLE, title);
        DatasetCreationChecks.checkTitleIsCorrect.doAction(context);

        LoginActions.logoutAction.doAction(context);
        //Now the dataset is created by the "editor" user and is private

        /**
         * Test 1 - We shouldn't be able to view it from the public side
         */
        BasicActions.goToHomePageAction.doAction(context);
        context.put(ContextConstants.DESTINATION_URL, "dataset/" + url);
        BasicActions.goToURLAddBaseURL.doAction(context);

        DatasetCreationChecks.unauthorizedDatasetAccess.doAction(context);

        /**
         * Test 2 - New user tries to access the dataset - shouldn't be able to do it
         */
        BasicActions.goToHomePageAction.doAction(context);
        BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
        LoginActions.loginAsNewUserAction.doAction(context);

        context.put(ContextConstants.DESTINATION_URL, "dataset/" + url);
        BasicActions.goToURLAddBaseURL.doAction(context);

        DatasetCreationChecks.unauthorizedDatasetAccess.doAction(context);

        LoginActions.logoutAction.doAction(context);

        /**
         * Test 3 - Admin tries to access the dataset - should be able to do it
         */

        BasicActions.goToHomePageAction.doAction(context);
        BasicActions.goToLoginPageUsingMainMenuAction.doAction(context);
        LoginActions.loginAsAdminAction.doAction(context);

        context.put(ContextConstants.DESTINATION_URL, "dataset/" + url);
        BasicActions.goToURLAddBaseURL.doAction(context);

        context.put(DatasetConstants.TITLE, title);
        DatasetCreationChecks.checkTitleIsCorrect.doAction(context);

        LoginActions.logoutAction.doAction(context);

    }

}
