package org.neilpatrick.galterlighter.tasks;

import org.neilpatrick.galterlighter.util.ActionTimer;
import org.neilpatrick.galterlighter.util.GAlterIDCache;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class LightAltar extends Strategy implements Task {

	
	private SceneObject lastAltar = null;// In Development
	private Filter<SceneObject> oppositeBurner = new Filter<SceneObject>() {
		public boolean accept(SceneObject o) {
			if (o.getId() == GAlterIDCache.INCENSE_BURNER_LIT
					|| o.getId() == GAlterIDCache.INCENSE_BURNER_UNLIT) {
				return o != lastAltar;
			}
			return false;
		}
	};

	@Override
	public boolean validate() {
		if (Inventory.getCount(GAlterIDCache.CLEAN_MARRENTILL) > 0
				&& ActionTimer.isStopped()) {

			return true;
		}
		return false;
	}

	@Override
	public void run() {
		if (SceneEntities.getNearest(GAlterIDCache.INCENSE_BURNER_UNLIT) != null) {
			SceneObject o = SceneEntities
					.getNearest(GAlterIDCache.INCENSE_BURNER_UNLIT);

			if (!o.isOnScreen()) {
				Camera.turnTo(o, 30);//Turns camera if not able to see burner
			}
			
			if(o.interact("Light")) {
			}

			ActionTimer.reset();
		} else {
			ActionTimer.reset();
		}
	}

}
