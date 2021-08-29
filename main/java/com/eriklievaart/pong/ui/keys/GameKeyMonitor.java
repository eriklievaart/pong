package com.eriklievaart.pong.ui.keys;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eriklievaart.toolkit.lang.api.check.CheckCollection;
import com.google.inject.Singleton;

@Singleton
public class GameKeyMonitor {

	private final Map<String, KeyAction> keys = new ConcurrentHashMap<>();
	private final Map<String, KeyAction> actions = new ConcurrentHashMap<>();

	public void press(String key) {
		if (keys.containsKey(key)) {
			keys.get(key).setActive();
		}
	}

	public void release(String key) {
		if (keys.containsKey(key)) {
			keys.get(key).setInvokeOnce();
		}
	}

	public boolean process(String action) {
		CheckCollection.isPresent(actions, action);
		return actions.get(action).getAndReset();
	}

	public void register(KeyMapping mapping) {
		keys.put(mapping.getKeyString(), mapping.getKeyAction());
		actions.put(mapping.getActionId(), mapping.getKeyAction());
	}
}
