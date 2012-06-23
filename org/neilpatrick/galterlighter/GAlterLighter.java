package org.neilpatrick.galterlighter;

import java.awt.Graphics;

import javax.swing.SwingUtilities;

import org.neilpatrick.galterlighter.tasks.ConvertHerbsTask;
import org.neilpatrick.galterlighter.tasks.EnterAmountTask;
import org.neilpatrick.galterlighter.tasks.LightAltar;
import org.neilpatrick.galterlighter.tasks.PayButlerTask;
import org.neilpatrick.galterlighter.tasks.PrelimTask;
import org.neilpatrick.galterlighter.tasks.ProfitCheckerTask;
import org.neilpatrick.galterlighter.tasks.UseHerbOnButlerTask;
import org.neilpatrick.galterlighter.ui.PaintHandler;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(authors = { "NeilPatrick-Nebula-RoadProphet" }, description = "Neil Patricks G Altar Lighter", name = "G Altar Insence Burner", version = 1.01)
public class GAlterLighter extends ActiveScript implements PaintListener {
	
	@Override
	protected void setup() {
		provide(new PrelimTask());
		provide(new ProfitCheckerTask());
		provide(new PayButlerTask());
		provide(new UseHerbOnButlerTask());
		provide(new ConvertHerbsTask());
		provide(new EnterAmountTask());
		provide(new LightAltar());
	}

	@Override
	public void onRepaint(Graphics g) {
		PaintHandler.render(g);
	}
}