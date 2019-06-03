package org.xml.demo.ui;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.xml.demo.ui.decorators.FilledDecorator;
import org.xml.demo.ui.decorators.IDecorator;
import org.xml.demo.ui.decorators.PickedDecorator;
import org.xml.demo.ui.figures.Figure;
import org.xml.demo.ui.figures.Line;
import org.xml.demo.ui.figures.Rectangle;
import org.xml.demo.ui.states.ApplicationMode;
import org.xml.demo.ui.states.ApplicationWindowState;
import org.xml.demo.ui.states.IApplicationWindowStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class GraphicArea extends JComponent {

    private int gridStep = 10;

    private boolean isMousePressed = false;

    private int startX, startY, currentX, currentY;

    private List<Figure> figures = new LinkedList<>();

    @Setter
    private IApplicationWindowStateManager manager;

    public GraphicArea() {
        log.debug("Graphic area message");
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                isMousePressed = true;
                currentX = startX = e.getX();
                currentY = startY = e.getY();
                setActiveLatestFigure(figures, p -> p.isInArea(currentX, currentY), true);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMousePressed = false;
                Figure f = drawFromState(null, false);
                if (f != null) {
                    figures.add(f);
                }
                figures.forEach(Figure::endMove);
                figures.forEach(ff -> ff.setActive(false));
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        drawGrid(g);

        //draw existing figures
        for (Figure f : figures) {
            IDecorator decorator = createDecorator(manager.provideState());
            decorator.doDecorate(f, g);
        }

        if (isMousePressed) {
            drawFromState(g, true);
        }
    }

    private void setActiveLatestFigure(List<Figure> figures, Predicate<Figure> p, boolean value) {
        //set isActive only for latest figure in list,
        //which contains current cursor
        Figure tmpFigure = null;
        for (Figure f : figures) {            
            if (p.test(f)) {      
                if (tmpFigure != null) {
                    tmpFigure.setActive(false);
                }                
                f.setActive(value);
                tmpFigure = f;
            }            
        }
    }

    private Figure drawFromState(Graphics g, boolean doDecorate) {
        ApplicationWindowState state = manager.provideState();
        IDecorator decorator = createDecorator(state);
        Figure f = null;
        switch (state.getMode()) {
            case DRAW_RECTANGLE:
                f = createRectangle();
                break;
            case DRAW_LINE:
                f = createLine();
                break;
            case DRAW_CIRCLE:
                break;
        }
        if (f != null) {
            f.setWindowState(state);
        }
        if (f != null && decorator != null && doDecorate) {
            decorator.doDecorate(f, g);
        }
        return f;
    }

    private Figure createRectangle() {
        return new Rectangle(startX, startY, currentX, currentY);
    }

    private Figure createLine() {
        return new Line(startX, startY, currentX, currentY);
    }

    private IDecorator createDecorator(ApplicationWindowState state) {
        if ((state.getMode() != ApplicationMode.SELECT_ELEMENT
                && state.getMode() != ApplicationMode.FILL_ELEMENT) || !isMousePressed) {
            return new FilledDecorator();
        } else if (state.getMode() == ApplicationMode.SELECT_ELEMENT && isMousePressed) {
            log.info("Created Picked decorator");
            return new PickedDecorator(startX, startY, currentX, currentY);
        } else if (state.getMode() == ApplicationMode.FILL_ELEMENT && isMousePressed) {
            log.info("Created Picked decorator Ext (Filled decorator)");
            return new PickedDecorator(startX, startY, currentX, currentY) {
                @Override
                public void execute(Figure target) {
                    target.getWindowState().setColor(state.getColor());
                }
            };
        } else {
            return null;
        }
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < getHeight(); i += gridStep) {
            g.drawLine(0, i, getWidth(), i);
        }

        for (int i = 0; i < getWidth(); i += gridStep) {
            g.drawLine(i, 0, i, getHeight());
        }

    }
}
