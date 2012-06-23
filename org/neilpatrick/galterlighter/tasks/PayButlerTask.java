package org.neilpatrick.galterlighter.tasks;

import org.neilpatrick.galterlighter.util.GAlterWidgetCache;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.Widget;

public class PayButlerTask extends Strategy implements Task {

	@Override
	public boolean validate() {
		Widget butlerWidget = Widgets.get(GAlterWidgetCache.BUTLER_WIDGET);
		if (butlerWidget.validate()) {
			if (butlerWidget.validate()
					|| butlerWidget.getChild(GAlterWidgetCache.PAY_COINS)
							.validate()) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void run() {
		Widget butlerWidget = Widgets.get(GAlterWidgetCache.BUTLER_WIDGET);
		if (butlerWidget.validate()) {
			butlerWidget.getChild(19).click(true);
		} else if (Widgets.get(1188).getChild(GAlterWidgetCache.PAY_COINS)
				.validate()) {
			Widgets.get(1188).getChild(GAlterWidgetCache.PAY_COINS).click(true);
		} else {
			
		}
	}

}
