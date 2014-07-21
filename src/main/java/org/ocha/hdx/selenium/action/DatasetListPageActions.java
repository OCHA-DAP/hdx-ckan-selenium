/**
 * 
 */
package org.ocha.hdx.selenium.action;

import static org.ocha.hdx.selenium.util.Util.REMOVE;

import org.ocha.hdx.selenium.entities.SearchResultInfo;
import org.ocha.hdx.selenium.interaction.DatasetListPageInteractions;
import org.ocha.hdx.selenium.util.Constants;

/**
 * @author alexandru-m-g
 *
 */
public class DatasetListPageActions {
	public static IAction changeSortingToNameAscAction = context -> DatasetListPageInteractions.changeSortingToNameAscInteraction.doAction(context);
	public static IAction changeSortingToNameDescAction = context -> DatasetListPageInteractions.changeSortingToNameDescInteraction.doAction(context);

	public static IAction searchForHealthAction = context -> {
		context.put(Constants.TEXT_TO_WRITE, "health");
		DatasetListPageInteractions.searchForTextInteraction.doAction(context);

	};
	public static IAction openTagsFacetAction = context -> {
		context.put(Constants.FACET_NAME, "Tags");
		DatasetListPageInteractions.openFacetInteraction.doAction(context);

	};
	public static IAction showMoreTagsFacetAction = context -> {
		context.put(Constants.FACET_NAME, "Tags");
		DatasetListPageInteractions.showMoreFacetValuesInteraction.doAction(context);

	};
	public static IAction filterByTagDeathsAction = context -> {
		context.put(Constants.FACET_NAME, "Tags");
		context.put(Constants.FACET_VALUE, "deaths");
		DatasetListPageInteractions.filterByFacetValueInteraction.doAction(context);

	};

	public static IAction storeSearchResultsInfoAsPrevAction = context -> {
		DatasetListPageInteractions.readSearchResultInfo.doAction(context);
		final SearchResultInfo searchResultInfo = REMOVE(context, Constants.SEARCH_RESULTS_INFO, SearchResultInfo.class);
		context.put(Constants.PREV_SEARCH_RESULTS_INFO, searchResultInfo);
	};

	public static IAction storeSearchResultsInfoAsOriginalAction = context -> {
		DatasetListPageInteractions.readSearchResultInfo.doAction(context);
		final SearchResultInfo searchResultInfo = REMOVE(context, Constants.SEARCH_RESULTS_INFO, SearchResultInfo.class);
		context.put(Constants.ORIGINAL_SEARCH_RESULTS_INFO, searchResultInfo);
	};
}
