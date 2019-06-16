package org.ui.figures;

import lombok.Data;

import java.awt.*;

@Data
public class Line extends Figure {

    private final int startX, startY, endX, endY;

    @Override
    public void draw(Graphics g, boolean filled) {
        g.drawLine(startX, startY, endX, endY);
    }
}
