package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Triangle extends CenteredGraphicalElement {
    private Color drawColor;
    private Color fillColor;
    private int height;
    private int baseWidth;
    private double angle; // Angle d'inclinaison en radians

    public Triangle(int x, int y, Color drawColor, Color fillColor, int height, int baseWidth, double angleInRadian) {
        super(x, y);
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.height = height;
        this.baseWidth = baseWidth;
        this.angle = -(angleInRadian + Math.PI/2); // Ajustement de 90 degrés
    }

    public void paint(Graphics2D g2d) {
        Stroke originalStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.0F));
        Color originalColor = g2d.getColor();

        int halfBase = baseWidth / 2;

        int[] xPoints = {
                getX(),
                (int) (getX() + halfBase),
                (int) (getX() - halfBase)
        };
        int[] yPoints = {
                getY(),
                getY() - height,
                getY() - height
        };

        // Rotation du triangle autour du point A dans le sens trigonométrique
        rotateTriangle(xPoints, yPoints, getX(), getY(), angle);

        if (fillColor != null) {
            g2d.setColor(fillColor);
            g2d.fillPolygon(xPoints, yPoints, 3);
        }

        g2d.setColor(drawColor);
        g2d.drawPolygon(xPoints, yPoints, 3);

        g2d.setColor(originalColor);
        g2d.setStroke(originalStroke);
    }

    // Méthode pour faire tourner les points du triangle autour du point d'origine (A)
    private void rotateTriangle(int[] xPoints, int[] yPoints, int originX, int originY, double angle) {
        for (int i = 0; i < 3; i++) {
            int x = xPoints[i] - originX;
            int y = yPoints[i] - originY;
            xPoints[i] = (int) (x * Math.cos(angle) - y * Math.sin(angle) + originX);
            yPoints[i] = (int) (x * Math.sin(angle) + y * Math.cos(angle) + originY);
        }
    }

    @Override
    public String toString() {
        return drawColor.toString() + " triangle";
    }
}
