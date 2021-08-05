package util.input;

import java.util.concurrent.atomic.AtomicBoolean;

public class KeyAction {

	private final String id;
	private final AtomicBoolean active = new AtomicBoolean(false);
	private final AtomicBoolean once = new AtomicBoolean(false);
	private final Runnable invokeImmediate;

	public KeyAction(String id) {
		this(id, null);
	}

	public KeyAction(String id, Runnable invokeImmediate) {
		this.id = id;
		this.invokeImmediate = invokeImmediate;
	}

	public String getId() {
		return id;
	}

	public void setActive() {
		active.set(true);

		if (invokeImmediate != null) {
			invokeImmediate.run();
		}
	}

	public void setInvokeOnce() {
		once.set(true);
	}

	public boolean getAndReset() {
		boolean result = active.get();

		if (once.get()) {
			once.set(false);
			active.set(false);
		}
		return result;
	}
}
