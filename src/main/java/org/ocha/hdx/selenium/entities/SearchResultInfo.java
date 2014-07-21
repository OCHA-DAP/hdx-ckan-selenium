/**
 * 
 */
package org.ocha.hdx.selenium.entities;

/**
 * @author alexandru-m-g
 *
 */
public class SearchResultInfo {
	private final int resultsNum;
	private final int pagesNum;
	private final String searchText;

	public SearchResultInfo(final int resultsNum, final int pagesNum,
			final String searchText) {
		this.resultsNum = resultsNum;
		this.pagesNum = pagesNum;
		this.searchText = searchText;
	}

	public int getResultsNum() {
		return this.resultsNum;
	}

	public int getPagesNum() {
		return this.pagesNum;
	}

	public String getSearchText() {
		return this.searchText;
	}

}