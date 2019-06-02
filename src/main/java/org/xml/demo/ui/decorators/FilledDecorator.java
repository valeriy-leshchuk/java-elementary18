package org.xml.demo.ui.decorators;

import org.xml.demo.ui.figures.Figure;

import java.awt.*;

public class FilledDecorator implements IDecorator {

    @Override
    public void doDecorate(Figure target, Graphics g) {
        System.out.println("Created simple decorator");
        g.setColor(target.getWindowState().getColor());
        target.draw(g, true);
    }

}
