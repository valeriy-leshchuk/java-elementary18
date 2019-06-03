package org.xml.demo.ui.decorators;

import org.xml.demo.ui.figures.Figure;

import java.awt.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilledDecorator implements IDecorator {

    @Override
    public void doDecorate(Figure target, Graphics g) {
        log.info("Do decorate method of simple decorator");
        g.setColor(target.getWindowState().getColor());
        target.draw(g, true);
    }

}
