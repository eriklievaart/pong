package com.eriklievaart.pong.ui.keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.google.inject.Inject;

public class GameKeyListener implements KeyListener {

	@Inject
	private GameKeyMonitor monitor;

	@Override
	public void keyPressed(KeyEvent event) {
		monitor.press(KeyString.toKeyString(event));
	}

	@Override
	public void keyReleased(KeyEvent event) {
		monitor.release(KeyString.toKeyString(event));
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
