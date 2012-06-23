package org.neilpatrick.galterlighter.tasks;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;

public class ProfitCheckerTask extends Strategy implements Task {

	private int initAmount;
	public static int profit = 0;
	
	private boolean toggle = false;
	
	private int convert(String amount) {
		if(amount.endsWith("M")) {
			return Integer.parseInt(amount.replaceAll("M", ""))  * 1000000;
		} else if(amount.endsWith("K")) {
			return Integer.parseInt(amount.replaceAll("K", ""))  * 1000;
		}
		return Integer.parseInt(amount);
	}
	
	@Override
	public boolean validate() {
		if(!toggle) {
			initAmount = convert(Widgets.get(548, 196).getText());
			System.out.println(initAmount);
			toggle = true;
		}
		
		return true;
	}
	
	@Override
	public void run() {
		profit = (convert(Widgets.get(548, 196).getText()) - initAmount);
	}
}
