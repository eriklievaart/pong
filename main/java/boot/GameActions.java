package boot;

import com.google.inject.Inject;

import util.game.GameState;
import util.input.GameKeyMonitor;
import util.input.KeyAction;
import util.input.KeyMapping;

public class GameActions {

	@Inject
	public GameActions(final GameKeyMonitor monitor, final GameState state) {
		Runnable play = new Runnable() {
			@Override
			public void run() {
				state.play();
			}
		};
		monitor.register(new KeyMapping("space", new KeyAction("play", play)));
		monitor.register(new KeyMapping("left", new KeyAction("left")));
		monitor.register(new KeyMapping("right", new KeyAction("right")));
	}

	public void register() {
		// just make sure the constructor is run through Guice
	}
}
