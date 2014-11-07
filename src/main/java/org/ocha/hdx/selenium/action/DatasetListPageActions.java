/**
 * 
 */
package org.ocha.hdx.selenium.action;

import static org.ocha.hdx.selenium.util.Util.REMOVE;

import org.ocha.hdx.selenium.entities.SearchResultInfo;
import org.ocha.hdx.selenium.interaction.DatasetListPageInteractions;
import org.ocha.hdx.selenium.util.ContextConstants;

/**
 * @author alexandru-m-g
 *
 */
public class DatasetListPageActions {
	public static IAction changeSortingToNameAscAction = context -> DatasetListPageInteractions.changeSortingToNameAscInteraction.doAction(context);
	public static IAction changeSortingToNameDescAction = context -> DatasetListPageInteractions.changeSortingToNameDescInteraction.doAction(context);

	public static IAction searchForAction = context -> {
		DatasetListPageInteractions.searchForTextInteraction.doAction(context);
	};

    public static IAction searchForHealthAction = context -> {
        context.put(ContextConstants.TEXT_TO_WRITE, "health");
        DatasetListPageInteractions.searchForTextInteraction.doAction(context);

    };

    public static IAction searchForAirportsInZambiaAction = context -> {
		context.put(ContextConstants.TEXT_TO_WRITE, "Airports in Zambia");
		DatasetListPageInteractions.searchForTextInteraction.doAction(context);
	};
	
	public static IAction searchForPakistanBaselineAction = context -> {
		context.put(ContextConstants.TEXT_TO_WRITE, "Pakistan Baseline");
		DatasetListPageInteractions.searchForTextInteraction.doAction(context);

	};

	public static IAction openTagsFacetAction = context -> {
		context.put(ContextConstants.FACET_NAME, "Tags");
		DatasetListPageInteractions.openFacetInteraction.doAction(context);

	};
	public static IAction showMoreTagsFacetAction = context -> {
		context.put(ContextConstants.FACET_NAME, "Tags");
		DatasetListPageInteractions.showMoreFacetValuesInteraction.doAction(context);

	};
	public static IAction filterByTagDeathsAction = context -> {
		context.put(ContextConstants.FACET_NAME, "Tags");
		context.put(ContextConstants.FACET_VALUE, "deaths");
		DatasetListPageInteractions.filterByFacetValueInteraction.doAction(context);

	};

	public static IAction storeSearchResultsInfoAsPrevAction = context -> {
		DatasetListPageInteractions.readSearchResultInfo.doAction(context);
		final SearchResultInfo searchResultInfo = REMOVE(context, ContextConstants.SEARCH_RESULTS_INFO, SearchResultInfo.class);
		context.put(ContextConstants.PREV_SEARCH_RESULTS_INFO, searchResultInfo);
	};

	public static IAction storeSearchResultsInfoAsOriginalAction = context -> {
		DatasetListPageInteractions.readSearchResultInfo.doAction(context);
		final SearchResultInfo searchResultInfo = REMOVE(context, ContextConstants.SEARCH_RESULTS_INFO, SearchResultInfo.class);
		context.put(ContextConstants.ORIGINAL_SEARCH_RESULTS_INFO, searchResultInfo);
	};

	public static IAction viewAirportsInZambiaDatasetAction = context -> {
		context.put(ContextConstants.DATASET_DISPLAY_NAME, "Airports in Zambia");
		DatasetListPageInteractions.clickOnDatasetInteraction.doAction(context);

	};
	
	public static IAction viewPakistanBaselineDatasetAction = context -> {
		context.put(ContextConstants.DATASET_DISPLAY_NAME, "Pakistan Baseline");
		DatasetListPageInteractions.clickOnDatasetInteraction.doAction(context);

	};
}
