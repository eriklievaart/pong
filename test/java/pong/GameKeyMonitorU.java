package pong;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

import com.eriklievaart.toolkit.lang.api.check.Check;

import util.input.GameKeyMonitor;
import util.input.KeyAction;
import util.input.KeyMapping;

public class GameKeyMonitorU {

	private final AtomicBoolean invokeMe = new AtomicBoolean(false);

	@Test
	public void invokeImmediate() {
		GameKeyMonitor monitor = new GameKeyMonitor();
		monitor.register(new KeyMapping("pause", new KeyAction("test", new Runnable() {
			@Override
			public void run() {
				invokeMe.set(true);
			}
		})));

		Check.isFalse(invokeMe.get());
		monitor.press("pause");
		Check.isTrue(invokeMe.get());
	}
}
