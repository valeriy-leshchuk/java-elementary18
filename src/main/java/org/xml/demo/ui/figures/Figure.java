package org.xml.demo.ui.figures;

import lombok.Getter;
import lombok.Setter;
import org.xml.demo.ui.states.ApplicationWindowState;

import java.awt.*;

@Setter
@Getter
public abstract class  Figure {

    protected ApplicationWindowState windowState;

    public abstract void draw(Graphics g, boolean filled);

    public boolean isInArea(int x, int y) {
        //
        return false;
    }

    public void moveTo(int startX, int startY, int currentX, int currentY) {
        //do nothing
    }

    public void endMove() {
        //do nothing
    }
}
