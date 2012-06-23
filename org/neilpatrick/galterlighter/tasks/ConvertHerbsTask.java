package org.neilpatrick.galterlighter.tasks;

import org.neilpatrick.galterlighter.util.ActionTimer;
import org.neilpatrick.galterlighter.util.GAlterWidgetCache;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;

public class ConvertHerbsTask extends Strategy implements Task {

	@Override
	public boolean validate() {
		return Widgets.get(GAlterWidgetCache.UNCERT_WIDGET).validate()
				&& ActionTimer.isStopped();
	}

	@Override
	public void run() {
		Widgets.get(GAlterWidgetCache.UNCERT_WIDGET)
				.getChild(GAlterWidgetCache.UNCERT_OPTION).click(true);
		ActionTimer.reset();
	}

}
