package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.google.inject.Inject;

import util.game.GameState;

public class GameRenderer {

	@Inject
	private GameState state;

	public void paint(Graphics2D g) {
		BufferedImage buffer = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
		paintDoubleBuffer(buffer.createGraphics());

		g.drawImage(buffer, 0, 0, null);
		g.dispose();
	}

	private void paintDoubleBuffer(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);

		g.setColor(Color.WHITE);
		g.setFont(new Font("courier", Font.BOLD, 20));
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString("" + state.getScore(), 10, 30);

		g.drawLine(50, 10, 50, 590);
		g.drawLine(750, 10, 750, 590);
		g.fillRect(state.getPlayerX(), state.getPlayerY(), state.getPlayerW(), state.getPlayerH());
		g.fillRect(state.getOpponentX(), state.getOpponentY(), state.getOpponentW(), state.getOpponentH());
		g.fillOval(state.getBallX(), state.getBallY(), state.getBallW(), state.getBallH());

		if (!state.isAlive()) {
			g.setColor(Color.RED);
			g.setFont(new Font("courier", Font.BOLD, 40));
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawString("Ready?", 300, 300);
		}

		g.dispose();
	}
}
