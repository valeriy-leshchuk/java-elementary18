package org.xml.demo.ui.decorators;

import org.xml.demo.ui.figures.Figure;

import java.awt.*;

public interface IDecorator {

    public void doDecorate(Figure target, Graphics g);

}
