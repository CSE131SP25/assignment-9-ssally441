package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class BodySegment {
    private double x, y, size;

    public BodySegment(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Color color) {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, size);
    }
}