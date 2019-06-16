package org.ui.decorators;

import org.ui.figures.Figure;

import java.awt.*;

public interface IDecorator {

    public void doDecorate(Figure target, Graphics g);

}
