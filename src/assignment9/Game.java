package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;
    private int score;

    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
        score = 0;
    }

    public void play() {
		boolean hasStarted = false; // Track if the game has started

		while (snake.isInbounds()) {
			// Handle input
			int dir = getKeypress();
			if (dir != -1) {
				snake.changeDirection(dir);
				hasStarted = true; // Game starts on first keypress
			}

			// Only move if the game has started
			if (hasStarted) {
				snake.move();
				if (snake.eatFood(food)) {
					food = new Food();
					score += 10;
				}
				snake.update();
			}

			// Draw
			updateDrawing();
			StdDraw.pause(50);
		}

        // Game over
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(0.5, 0.5, "Game Over! Score: " + score);
        StdDraw.show();
        StdDraw.pause(2000);
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) return 1;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) return 2;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) return 3;
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) return 4;
        return -1;
    }

    private void updateDrawing() {
        StdDraw.clear();
        snake.draw();
        food.draw();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.1, 0.95, "Score: " + score);
        StdDraw.show();
    }

    public static void main(String[] args) {
        new Game().play();
    }
}