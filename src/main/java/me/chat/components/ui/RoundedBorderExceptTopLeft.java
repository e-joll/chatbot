package me.chat.components.ui;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorderExceptTopLeft extends AbstractBorder {
    private final int radius;
    private final int thickness;

    public RoundedBorderExceptTopLeft(int radius, int thickness) {
        this.radius = radius;
        this.thickness = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(thickness));

        int arc = radius;
        int x1 = x + thickness / 2;
        int y1 = y + thickness / 2;
        int w = width - thickness;
        int h = height - thickness;

        // Dessiner les lignes droites
        g2.drawLine(x1, y1, x1 + w - radius / 2, y1); // Ligne du haut (jusqu'à l'arrondi)
        g2.drawLine(x1, y1, x1, y1 + h - radius / 2); // Ligne de gauche (jusqu'à l'arrondi)
        g2.drawLine(x1 + radius / 2, y1 + h, x1 + w - radius / 2, y1 + h); // Ligne du bas
        g2.drawLine(x1 + w, y1 + radius / 2, x1 + w, y1 + h - radius / 2); // Ligne de droite

        // Dessiner les coins arrondis
        g2.drawArc(x1 + w - arc, y1, arc, arc, 0, 90); // Coin haut-droit
        g2.drawArc(x1 + w - arc, y1 + h - arc, arc, arc, 270, 90); // Coin bas-droit
        g2.drawArc(x1, y1 + h - arc, arc, arc, 180, 90); // Coin bas-gauche

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, thickness, thickness, thickness);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(thickness, thickness, thickness, thickness);
        return insets;
    }
}
