package pong;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import util.FullScreen;

public class GameFrame extends JFrame {

	private final GameRenderer render;

	public GameFrame(GameRenderer render) {
		super("game");
		setTitle("Game");
		this.render = render;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		setUndecorated(true);
		setResizable(false);
		setSize(800, 600);
	}

	@Override
	public void paint(Graphics g) {
		render.paint((Graphics2D) g);
	}

	public void showWindowed() {
		setVisible(true);
	}

	public void showFullScreen() {
		FullScreen.showFullScreen(this);
	}
}
