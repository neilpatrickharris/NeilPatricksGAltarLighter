package org.neilpatrick.galterlighter.tasks;

import org.neilpatrick.galterlighter.util.ActionTimer;
import org.neilpatrick.galterlighter.util.GAlterIDCache;
import org.neilpatrick.galterlighter.util.GAlterWidgetCache;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.Widget;

public class UseHerbOnButlerTask extends Strategy implements Task {

	@Override
	public boolean validate() {
		if (Inventory.getCount(GAlterIDCache.CLEAN_MARRENTILL) < 3
				&& ActionTimer.isStopped()) {
			Widget butlerWidget = Widgets.get(GAlterWidgetCache.UNCERT_WIDGET);
			Widget enterWidget = Widgets
					.get(GAlterWidgetCache.ENTER_AMOUNT_WIDGET);
			if (!butlerWidget.validate()
					&& !enterWidget.getChild(
							GAlterWidgetCache.ENTER_AMOUNT_LABEL).visible()) {
				Widget butlerAlertWidget = Widgets
						.get(GAlterWidgetCache.BUTLER_WIDGET);
				if (!butlerAlertWidget.validate()) {
					if (!butlerAlertWidget.validate()
							|| !Widgets.get(1188)
									.getChild(GAlterWidgetCache.PAY_COINS)
									.validate()) {
						return true;
					}

				}
			}
		}
		return false;
	}

	@Override
	public void run() {
		if (Inventory.getCount(GAlterIDCache.NOTED_MARRENTILL) == 0) {
			System.err.println("Out of herbs! Shutting down.");
			System.exit(1);
		}

		NPC butler = NPCs.getNearest(GAlterIDCache.DEMON_BUTLER);

		if (butler == null) {
		} else {
			Inventory.getItem(GAlterIDCache.NOTED_MARRENTILL).getWidgetChild()
					.interact("Use");
			Time.sleep(Random.nextInt(500, 1000));
			butler.interact("Use");
			ActionTimer.reset();
		}

	}

}
