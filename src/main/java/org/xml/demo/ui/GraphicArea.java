package org.xml.demo.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.List;

public class GraphicArea extends JComponent {

    private int gridStep = 10;

    private boolean isMousePressed = false;

    private int startX;

    private int startY;

    private int currentX;

    private int currentY;

    private List<Figure> figures = new LinkedList<>();

    public GraphicArea() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed");
                isMousePressed = true;
                currentX = startX = e.getX();
                currentY = startY = e.getY();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released");
                isMousePressed=false;
                figures.add(new Rectangle(startX, startY, currentX, currentY));
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
        Graphics2D g2 = (Graphics2D) g;
        System.out.println("Graphic area paint method");
        drawGrid(g);

        //draw existing figures
        for (Figure f: figures) {
            f.draw(g);
        }

        if (isMousePressed) {
            g2.setPaint(Color.BLUE);
            g.fillRect(
                    Integer.min(startX, currentX),
                    Integer.min(startY, currentY),
                    Math.abs(startX - currentX),
                    Math.abs(startY - currentY));
        }
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (int i =0 ; i<getHeight(); i+= gridStep) {
            g.drawLine(0, i, getWidth(), i);
        }

        for (int i =0 ; i<getWidth(); i+= gridStep) {
            g.drawLine(i, 0, i, getHeight());
        }


    }
}
