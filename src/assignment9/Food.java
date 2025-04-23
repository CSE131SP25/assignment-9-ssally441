package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {
    public static final double FOOD_SIZE = 0.02;
    private double x, y;

    public Food() {
        respawn();
    }

    public void respawn() {
        x = FOOD_SIZE + Math.random() * (1.0 - 2 * FOOD_SIZE);
        y = FOOD_SIZE + Math.random() * (1.0 - 2 * FOOD_SIZE);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void draw() {
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledCircle(x, y, FOOD_SIZE);
    }
}