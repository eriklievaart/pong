package util;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.eriklievaart.toolkit.lang.api.str.Str;

public class FullScreen {

	public static void showFullScreen(JFrame frame) {
		DisplayMode mode = getAvailableDisplayMode(frame.getWidth(), frame.getHeight());
		if (mode == null) {
			System.err.println(Str.sub("No Display mode for resolution $x$", frame.getWidth(), frame.getHeight()));
			return;
		}
		GraphicsDevice gd = getGraphicsDevice();
		gd.setFullScreenWindow(frame);
		gd.setDisplayMode(mode);
	}

	public static DisplayMode getAvailableDisplayMode(int width, int height) {
		List<DisplayMode> available = new ArrayList<>();

		for (DisplayMode mode : getGraphicsDevice().getDisplayModes()) {
			if (mode.getWidth() == width && mode.getHeight() == height) {
				available.add(mode);
			}
		}
		DisplayMode best = null;
		for (DisplayMode mode : available) {
			if (best == null || best.getRefreshRate() < mode.getRefreshRate()) {
				best = mode;
			}
		}
		return best;
	}

	public static void endFullScreen() {
		GraphicsDevice gd = getGraphicsDevice();
		gd.setFullScreenWindow(null);
	}

	public static void dumpDisplayModes() {
		DisplayMode[] modes = getGraphicsDevice().getDisplayModes();
		for (DisplayMode mode : modes) {
			int color = mode.getBitDepth();
			int hz = mode.getRefreshRate();
			System.err.println(Str.sub("$x$:$ ($hz)", mode.getWidth(), mode.getHeight(), color, hz));
		}
	}

	private static GraphicsDevice getGraphicsDevice() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	}
}
