package GUI;

import javax.swing.*;
import java.awt.*;

public class TriangleLabel extends JComponent {
    public enum Direction {
        TOP_LEFT,   // Normal right triangle
        BOTTOM_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT
    }

    private Color backgroundColor;
    private Direction direction;

    public TriangleLabel( Color backgroundColor, Direction direction) {
        this.backgroundColor = backgroundColor;
        this.direction = direction;
    }

    //Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Enable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        int[] xPoints;
        int[] yPoints;

        // Select points based on direction
        switch (direction) {
            case TOP_LEFT:
                xPoints = new int[]{0, w, 0};
                yPoints = new int[]{0, 0, h};
                break;
            case BOTTOM_LEFT:
                xPoints = new int[]{0, w, 0};
                yPoints = new int[]{h, h, 0};
                break;
            case TOP_RIGHT:
                xPoints = new int[]{w, 0, w};
                yPoints = new int[]{0, 0, h};
                break;
            case BOTTOM_RIGHT:
                xPoints = new int[]{w, 0, w};
                yPoints = new int[]{h, h, 0};
                break;
            default:
                xPoints = new int[]{0, w, 0};
                yPoints = new int[]{0, 0, h};
        }

        // Draw triangle
        g2.setColor(backgroundColor);
        g2.fillPolygon(xPoints, yPoints, 3);
    }
}
