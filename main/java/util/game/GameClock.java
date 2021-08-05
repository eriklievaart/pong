package util.game;

import java.util.concurrent.atomic.AtomicLong;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import pong.GameFrame;

@Singleton
public class GameClock implements Runnable {
	private final AtomicLong interval = new AtomicLong(20);

	@Inject
	private GameState turn;
	private GameFrame frame;

	@Override
	public void run() {
		while (true) {
			turn.tick();
			frame.repaint();
			sleepUntilNextTick();
		}
	}

	private void sleepUntilNextTick() {
		try {
			long time = System.currentTimeMillis();
			long tickTime = interval.get();
			Thread.sleep(tickTime - time % tickTime);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}
}
