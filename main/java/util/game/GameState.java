package util.game;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import util.input.GameKeyMonitor;

@Singleton
public class GameState {

	private boolean alive = false;

	private int score = 0;
	private int barSpeed = 4;

	private double ballH = 8;
	private double ballW = 8;
	private double ballX = 400 - ballW / 2;
	private double ballY = 300 - ballH / 2;
	private double ballV = 2;
	private double ballDX = 0;
	private double ballDY = ballV;

	private int playerX = 350;
	private int playerY = 580;
	private int playerH = 10;
	private int playerW = 100;

	private int opponentX = 350;
	private int opponentY = 10;
	private int opponentH = 10;
	private int opponentW = 100;

	@Inject
	private GameKeyMonitor monitor;

	public int getScore() {
		return score;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public int getPlayerW() {
		return playerW;
	}

	public int getPlayerH() {
		return playerH;
	}

	public int getOpponentX() {
		return opponentX;
	}

	public int getOpponentY() {
		return opponentY;
	}

	public int getOpponentH() {
		return opponentH;
	}

	public int getOpponentW() {
		return opponentW;
	}

	public int getBallX() {
		return (int) ballX;
	}

	public int getBallY() {
		return (int) ballY;
	}

	public int getBallH() {
		return (int) ballH;
	}

	public int getBallW() {
		return (int) ballW;
	}

	public GameKeyMonitor getMonitor() {
		return monitor;
	}

	private void movePlayer() {
		if (monitor.process("left")) {
			playerX -= barSpeed;
			playerX = playerX < 50 ? 50 : playerX;
		}
		if (monitor.process("right")) {
			playerX += barSpeed;
			playerX = playerX + playerW > 750 ? 750 - playerW : playerX;
		}
	}

	private void moveOpponent() {
		int move = 0;
		if (ballX - opponentX - opponentW / 2 > ballW) {
			if (opponentX + opponentW < 750) {
				move += barSpeed;
			}
		}
		if (opponentX + opponentW / 2 - ballX > ballW) {
			if (opponentX - barSpeed >= 50) {
				move -= barSpeed;
			}
		}
		opponentX += move;
	}

	private void moveBall() {
		ballX = ballX + ballDX;
		ballY = ballY + ballDY;

		// player side collision
		if (ballY + ballH >= playerY) {
			if (ballX > playerX && ballX < playerX + playerW) {
				ballV = ballV + 1;

				int over = (int) (ballY + ballH - playerY);
				ballY = playerY - over - ballH;

				double offset = playerX + playerW / 2 - ballX - ballW / 2 - ballDX / 2;
				double factor = playerW / 2;
				ballDX = Math.sin(1.5 * offset / factor) * ballV * -1;
				ballDY = Math.cos(1.5 * offset / factor) * ballV * -1;
			}
		}
		// opponent side collision
		if (ballY <= opponentY + opponentH) {
			if (ballX >= opponentX && ballX < opponentX + opponentW) {
				ballV = ballV + 1;

				int over = (int) (opponentY + opponentH - ballY);
				ballY = opponentY + opponentH + over;

				double offset = opponentX + opponentW / 2 - ballX - ballW / 2 - ballDX / 2;
				double factor = opponentW / 2;
				ballDX = Math.sin(1.5 * offset / factor) * ballV * -1;
				ballDY = Math.cos(1.5 * offset / factor) * ballV;
			}
		}
		// left wall
		if (ballX < 50) {
			ballX = 100 - ballX;
			ballDX = -ballDX;
		}
		// right wall
		if (ballX + ballW > 750) {
			ballX = 1500 - (ballX + ballW) - ballW;
			ballDX = -ballDX;
		}
		if (ballY < opponentY + opponentH / 2) {
			System.out.println("score!");
			score++;
			ballX = opponentX + opponentW / 2;
			ballY = opponentY + opponentH;
			ballV = 2;
			ballDX = 0;
			ballDY = 2;
		}
		if (ballY > playerY + playerH / 2) {
			System.out.println("lose!");
			alive = false;
		}
	}

	public void tick() {
		if (!alive) {
			return;
		}
		movePlayer();
		moveOpponent();
		moveBall();
	}

	public boolean isAlive() {
		return alive;
	}

	public void play() {
		if (alive) {
			return;
		}
		alive = true;

		ballX = 400 - ballW / 2;
		ballY = 300 - ballH / 2;
		ballV = 2;
		ballDX = 0;
		ballDY = ballV;
	}
}
