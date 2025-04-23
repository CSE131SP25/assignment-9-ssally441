package assignment9;

import java.awt.Color;
import java.util.LinkedList;

public class Snake {
    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX, deltaY;
    private Color baseColor = Color.GRAY;
    private Color flashColor;
    private int flashFrames = 0;
    private static final int FLASH_DURATION = 30;

	public Snake() {
		segments = new LinkedList<>();
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE)); // Initial head at center
		deltaX = 0; // Start stationary (no movement)
		deltaY = 0;
	}

	public void changeDirection(int direction) {
		// Only change direction if the input is valid
		if (direction == 1) { // Up
			deltaX = 0;
			deltaY = MOVEMENT_SIZE;
		} else if (direction == 2) { // Down
			deltaX = 0;
			deltaY = -MOVEMENT_SIZE;
		} else if (direction == 3) { // Left
			deltaX = -MOVEMENT_SIZE;
			deltaY = 0;
		} else if (direction == 4) { // Right
			deltaX = MOVEMENT_SIZE;
			deltaY = 0;
		}
	}

    public void move() {
        // Move body segments (tail to head)
        for (int i = segments.size() - 1; i > 0; i--) {
            BodySegment current = segments.get(i);
            BodySegment prev = segments.get(i - 1);
            current.setPosition(prev.getX(), prev.getY());
        }
        // Move head
        BodySegment head = segments.getFirst();
        head.setPosition(head.getX() + deltaX, head.getY() + deltaY);
    }

    public boolean eatFood(Food food) {
        BodySegment head = segments.getFirst();
        double dx = head.getX() - food.getX();
        double dy = head.getY() - food.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < SEGMENT_SIZE + Food.FOOD_SIZE) {
            // Grow snake
            BodySegment tail = segments.getLast();
            segments.add(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
            // Set flash color
            flashColor = new Color(
                (int)(Math.random() * 256),
                (int)(Math.random() * 256),
                (int)(Math.random() * 256)
            );
            flashFrames = FLASH_DURATION;
            return true;
        }
        return false;
    }

    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        return head.getX() >= SEGMENT_SIZE && head.getX() <= 1 - SEGMENT_SIZE &&
               head.getY() >= SEGMENT_SIZE && head.getY() <= 1 - SEGMENT_SIZE;
    }

    public void update() {
        if (flashFrames > 0) flashFrames--;
    }

    public void draw() {
        Color color = (flashFrames > 0) ? flashColor : baseColor;
        for (BodySegment segment : segments) {
            segment.draw(color);
        }
    }
}