package boot;

import com.eriklievaart.toolkit.swing.api.SwingThread;
import com.google.inject.Guice;
import com.google.inject.Injector;

import pong.GameFrame;
import pong.GameRenderer;
import util.game.GameClock;
import util.input.GameKeyListener;

public class Main {

	public static void main(String[] args) {
		SwingThread.invokeAndWaitUnchecked(new Runnable() {

			@Override
			public void run() {
				Injector injector = Guice.createInjector();
				GameKeyListener listener = injector.getInstance(GameKeyListener.class);
				GameRenderer renderer = injector.getInstance(GameRenderer.class);
				GameClock clock = injector.getInstance(GameClock.class);
				GameActions actions = injector.getInstance(GameActions.class);

				actions.register();

				GameFrame frame = new GameFrame(renderer);
				frame.addKeyListener(listener);
				frame.showWindowed();
				frame.repaint();
				clock.setFrame(frame);

				Thread thread = new Thread(clock);
				thread.setDaemon(true);
				thread.start();
			}
		});
	}
}
