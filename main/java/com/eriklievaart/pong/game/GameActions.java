package com.eriklievaart.pong.game;

import com.eriklievaart.pong.ui.keys.GameKeyMonitor;
import com.eriklievaart.pong.ui.keys.KeyAction;
import com.eriklievaart.pong.ui.keys.KeyMapping;
import com.google.inject.Inject;

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
