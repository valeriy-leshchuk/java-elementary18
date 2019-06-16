package org.ui.figures;

import java.awt.*;
import lombok.ToString;
@ToString(callSuper = true)
public class Rectangle extends Figure {

    private  int initialX, initialY, startX, startY;

    private  int width, height, initialWidth, initialHeight;

    public Rectangle(int startX, int startY, int currentX, int currentY) {
        this.initialX = this.startX = Integer.min(startX, currentX);
        this.initialY = this.startY = Integer.min(startY, currentY);
        initialWidth = this.width = Math.abs(startX - currentX);
        initialWidth = this.height = Math.abs(startY - currentY);
    }

    @Override
    public void draw(Graphics g, boolean filled) {
        if (filled) {
            g.fillRect(startX, startY, width, height);
        } else {
            g.drawRect(startX, startY, width, height);
        }
    }

    @Override
    public boolean isInArea(int x, int y) {
        return x >= startX
                && x <= startX + width
                && y >= startY
                && y <= startY + height;
    }

    @Override
    public void moveTo(int startX, int startY, int currentX, int currentY) {
        this.startX = this.initialX + (currentX - startX);
        this.startY = this.initialY  + (currentY - startY);
    }

    @Override
    public void endMove() {
        this.initialX = startX;
        this.initialY = startY;
    }

    @Override
    public void endResize() {
        this.initialHeight = this.height;
        this.initialWidth = this.width;
    }

    @Override
    public void resizeStart(int startX, int startY, int currentX, int currentY) {
        this.width = this.initialWidth + (currentX - startX);
        this.height = this.initialHeight  + (currentY - startY);
    }    
    
}
