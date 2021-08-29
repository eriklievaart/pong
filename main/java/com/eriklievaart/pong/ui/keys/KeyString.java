package com.eriklievaart.pong.ui.keys;

import java.awt.event.KeyEvent;

public class KeyString {

	public static String toKeyString(KeyEvent event) {
		switch (event.getKeyCode()) {

		case KeyEvent.VK_CONTROL:
			return "ctrl";

		case KeyEvent.VK_ALT:
			return "alt";

		case KeyEvent.VK_SHIFT:
			return "shift";

		}
		return getKeyString(event);
	}

	private static String getKeyString(KeyEvent event) {
		StringBuilder builder = new StringBuilder();
		if (event.isControlDown()) {
			builder.append("ctrl ");
		}
		if (event.isAltDown()) {
			builder.append("alt ");
		}
		if (event.isShiftDown()) {
			builder.append("shift ");
		}
		builder.append(KeyEvent.getKeyText(event.getKeyCode()));
		return builder.toString().toLowerCase();
	}
}
