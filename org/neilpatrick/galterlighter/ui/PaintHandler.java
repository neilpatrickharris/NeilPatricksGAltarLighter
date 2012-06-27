package org.neilpatrick.galterlighter.ui;

import java.awt.Color;
import java.awt.Graphics;

import org.neilpatrick.galterlighter.tasks.LightAltar;
import org.neilpatrick.galterlighter.tasks.PrelimTask;
import org.neilpatrick.galterlighter.tasks.ProfitCheckerTask;
import org.neilpatrick.galterlighter.util.GAlterIDCache;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class PaintHandler {

	private static int startFactor = 1;
	private static int arcWidth = 40;
	private static int radius = 20;
	private static int rotationFactor = 4;

	public static void render(Graphics g) {

		// ////////////////////////////////

		// ///////////STATUS///////////////

		// ////////////////////////////////

		g.setColor(Color.red);
		g.drawString("NeilPatricks G Altar Lighter", 10, 40);
		g.drawString("Time Ran: " + Time.format(PrelimTask.runTime.getElapsed()), 10, 60);
		g.drawString("Profit: " + ProfitCheckerTask.profit, 10, 75);
		g.drawString("Only Demon Butler - Marble Burner Supported.", 10, 90);
		// ////////////////////////////////

		// ///////////TILE/////////////////

		// ////////////////////////////////

		SceneObject[] litBurner = SceneEntities
				.getLoaded(GAlterIDCache.INCENSE_BURNER_LIT);
		SceneObject[] unlitBurner = SceneEntities
				.getLoaded(GAlterIDCache.INCENSE_BURNER_UNLIT);

		for (SceneObject o : unlitBurner) {
			o.getLocation().draw(g);
		}

		g.setColor(Color.green);

		for (SceneObject o : litBurner) {
			o.getLocation().draw(g);
		}

		// /////////////////////////////////

		// ////////////DRAW MOUSE///////////

		// /////////////////////////////////

		g.setColor(Color.GREEN);
		g.fillOval(Mouse.getPressX(), Mouse.getPressY(), 3, 3);

		startFactor += rotationFactor;
		if (startFactor >= 360) {
			startFactor = startFactor - 360;
		}
		g.setColor(Mouse.isPressed() ? Color.GREEN : Color.RED);
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor, arcWidth);
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor + 90, arcWidth);
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor + 180, arcWidth);
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor + 270, arcWidth);

		startFactor *= -1;
		rotationFactor *= -1;
		radius /= 2;
		// Inner circle
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor, arcWidth);
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor + 90, arcWidth);
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor + 180, arcWidth);
		g.drawArc(Mouse.getX() - (radius / 2), Mouse.getY() - (radius / 2),
				radius, radius, startFactor + 270, arcWidth);

		startFactor *= -1;
		rotationFactor *= -1;
		radius *= 2;
	}
}
