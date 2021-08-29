package com.eriklievaart.pong.ui.keys;

import com.eriklievaart.toolkit.lang.api.ToString;

public class KeyMapping {

	private final KeyAction action;
	private final String keyString;

	public KeyMapping(String key, KeyAction action) {
		this.keyString = key;
		this.action = action;
	}

	public KeyAction getKeyAction() {
		return action;
	}

	public String getKeyString() {
		return keyString;
	}

	public String getActionId() {
		return action.getId();
	}

	@Override
	public String toString() {
		return ToString.simple(this, "$[$, $]", action, keyString);
	}
}
