/**
 * 
 */
package org.ocha.hdx.selenium.action;

import org.ocha.hdx.selenium.interaction.PreviewPageInteractions;
import org.ocha.hdx.selenium.util.ContextConstants;

/**
 * @author alexandru-m-g
 *
 */
public class PreviewPageActions {
	public static IAction showOnlyFirst3RowsAction = context -> {
		context.put(ContextConstants.FROM, 0);
		context.put(ContextConstants.TO, 3);
		PreviewPageInteractions.showOnlyRowsInteraction.doAction(context);
	};

	public static IAction switchToMapViewAction = 
			context -> PreviewPageInteractions.switchToMapViewInteraction.doAction(context);

			
	public static IAction selectLatitudeAndLongitudeColsAction = 
			context -> PreviewPageInteractions.selectLatitudeAndLongitudeInteraction.doAction(context);
			
//	public static IAction downloadCsvFileAction = context -> {
//		PreviewPageInteractions.downloadCsvFileInteraction.doAction(context);
//		BasicInteractions.cancelFileDownloadDialogInteraction.doAction(context);
//	};
			
}
