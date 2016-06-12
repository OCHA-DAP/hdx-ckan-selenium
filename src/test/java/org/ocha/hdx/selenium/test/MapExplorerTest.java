/**
 * 
 */
package org.ocha.hdx.selenium.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ocha.hdx.selenium.action.BasicActions;
import org.ocha.hdx.selenium.action.MapExplorerActions;
import org.ocha.hdx.selenium.check.MapExplorerChecks;

/**
 * @author alexandru-m-g
 *
 */
public class MapExplorerTest extends AbstractHdxSeleniumTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		final Map<String,Object> context = this.instantiateContext();

		MapExplorerActions.goToMapExplorerAction.doAction(context);
		MapExplorerChecks.slicesDropdownListNotEmptyCheck.doAction(context);
		
		MapExplorerActions.selectFirstSliceFromListActions.doAction(context);
		MapExplorerChecks.mapIsLoadedCheck.doAction(context);
		MapExplorerChecks.chartIsLoadedCheck.doAction(context);
		
		MapExplorerActions.removeFirstLoadedSliceAction.doAction(context);
		MapExplorerChecks.mapIsNotLoadedCheck.doAction(context);
		MapExplorerChecks.chartIsNotLoadedCheck.doAction(context);
		
	}

}
