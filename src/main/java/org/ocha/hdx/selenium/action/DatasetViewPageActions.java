package org.ocha.hdx.selenium.action;

import java.util.Map;

import org.ocha.hdx.selenium.interaction.DatasetViewPageInteraction;

public class DatasetViewPageActions {
	public static IAction previewTheFirstResourceAction = new IAction() {

		@Override
		public void doAction(final Map<String, Object> context) {
			DatasetViewPageInteraction.previewTheFirstResourceInteraction.doAction(context);

		}
	};
}
