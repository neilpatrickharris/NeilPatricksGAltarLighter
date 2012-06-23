package org.neilpatrick.galterlighter.tasks;

import org.neilpatrick.galterlighter.util.GAlterIDCache;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;

public class PrelimTask extends Strategy implements Task {

	public static Timer runTime = new Timer(-1);
	
	private boolean hasChecked = false;
	private boolean hasNormalMarrentill = false;
	private boolean hasNotedMarrentill = false;

	@Override
	public boolean validate() {
		return !hasChecked;
	}

	@Override
	public void run() {
		Item[] invItems = Inventory.getItems();
		for (Item i : invItems) {
			if (i.getId() == GAlterIDCache.CLEAN_MARRENTILL) {
				hasNormalMarrentill = true;
			} else if (i.getId() == GAlterIDCache.NOTED_MARRENTILL) {
				hasNotedMarrentill = true;
			}
		}

		if (hasNormalMarrentill) {
			System.out.println("Normal herb detected");
		}

		if (hasNotedMarrentill) {
			System.out.println("Noted herb detected");
		}

		if (!hasNormalMarrentill && !hasNotedMarrentill) {
			System.out
					.println("No herb detected! Get sum dat herb and try again.");
			System.exit(1);
		}

		hasChecked = true;
	}

}
