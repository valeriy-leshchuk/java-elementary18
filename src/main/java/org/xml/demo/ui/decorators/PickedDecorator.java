package org.xml.demo.ui.decorators;

import lombok.Data;
import org.xml.demo.ui.figures.Figure;

import java.awt.*;

@Data
public class PickedDecorator extends FilledDecorator {

    private final int x1, y1, x2, y2;

    @Override
    public void doDecorate(Figure target, Graphics g) {
        System.out.println("--> " + target.isInArea(x2, y2));
        if (target.isInArea(x2, y2)) {
            execute(target);
        }
        super.doDecorate(target, g);
    }

    public void execute(Figure target) {
        target.moveTo(x1, y1, x2, y2);
    }
}
