package org.neilpatrick.galterlighter.tasks;

import org.neilpatrick.galterlighter.util.ActionTimer;
import org.neilpatrick.galterlighter.util.GAlterIDCache;
import org.neilpatrick.galterlighter.util.GAlterWidgetCache;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;

public class EnterAmountTask extends Strategy implements Task {

	@Override
	public boolean validate() {
		return Widgets.get(GAlterWidgetCache.ENTER_AMOUNT_WIDGET)
				.getChild(GAlterWidgetCache.ENTER_AMOUNT_LABEL).visible()
				&& ActionTimer.isStopped();
	}

	@Override
	public void run() {

		int amountToSend = Inventory.getCount(true,
				GAlterIDCache.NOTED_MARRENTILL);

		if (amountToSend > 28 - Inventory.getCount()) {
			amountToSend = 28 - Inventory.getCount();
		}
		Keyboard.sendText(String.valueOf(amountToSend), true);
		ActionTimer.reset(Random.nextInt(2300, 3000));
	}

}
