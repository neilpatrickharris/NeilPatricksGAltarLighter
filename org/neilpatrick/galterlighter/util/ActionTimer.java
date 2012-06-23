package org.neilpatrick.galterlighter.util;

import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

/**
 * Provides an easy way of "sleeping" between actions, so they are not spammed
 * 
 * @author RoadProphet
 * 
 */
public class ActionTimer {

	/** last sys time since reset */
	private static long sysTimeAtReset = 0;

	/** Expected sys time at end */
	private static long expectedSysTimeAtReset = 0;

	/** Milliseconds to be idle */
	public static long millisTillIdle = 10 * 60 * 1000; // Ten minutes

	/** Our timer */
	private static Timer actionTimer = new Timer(0);

	public static void reset() {
		long period = Random.nextInt(1500, 2100);
		actionTimer.setEndIn(period);
		sysTimeAtReset = System.currentTimeMillis();
		expectedSysTimeAtReset = sysTimeAtReset + period;
	}

	public static void reset(long period) {
		actionTimer.setEndIn(period);
		sysTimeAtReset = System.currentTimeMillis();
		expectedSysTimeAtReset = sysTimeAtReset + period;
	}

	public static boolean isStopped() {
		return !actionTimer.isRunning();
	}

	public static boolean isIdle() {
		return System.currentTimeMillis() - expectedSysTimeAtReset >= millisTillIdle;
	}
}
