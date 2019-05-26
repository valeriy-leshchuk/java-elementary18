package org.xml.demo.ui.decorators;

import org.xml.demo.ui.Figure;

import java.awt.*;

public interface IDecorator {

    public void doDecorate(Figure target, Graphics g);

}
