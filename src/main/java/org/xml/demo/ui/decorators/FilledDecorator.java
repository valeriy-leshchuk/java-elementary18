package org.xml.demo.ui.decorators;

import org.xml.demo.ui.Figure;

import java.awt.*;

public class FilledDecorator implements IDecorator {

    @Override
    public void doDecorate(Figure target, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        target.draw(g, true);
    }

}
