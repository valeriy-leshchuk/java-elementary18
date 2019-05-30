package org.xml.demo.ui;

import javax.swing.*;

import lombok.Builder;
import lombok.Getter;
import org.xml.demo.ui.states.ApplicationMode;
import org.xml.demo.ui.states.ApplicationWindowState;
import org.xml.demo.ui.states.IApplicationWindowStateManager;

import java.awt.*;

@Builder
@Getter
public class Window extends JFrame implements IApplicationWindowStateManager {

    private static ApplicationWindowState INITIAL_STATE = new ApplicationWindowState(
            ApplicationMode.DRAW_RECTANGLE,
            Color.BLUE);

    private String windowTitle;

    private int windowHeight;

    private int windowWidth;

    private String workingDirectory;

    private GraphicArea graphicArea;

    private ApplicationWindowState applicationState;

    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(windowTitle);
        setSize(windowWidth, windowHeight);
        graphicArea = new GraphicArea();
        graphicArea.setManager(this);
        initWithButtons();
        add(graphicArea, BorderLayout.CENTER);
    }

    private void initWithButtons() {
        JPanel buttonPanel = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();

        JToggleButton button = new JToggleButton("Rectangle");
        ApplicationWindowState previous = provideState();
        button.addActionListener(e -> {
            changeState(new ApplicationWindowState(ApplicationMode.DRAW_RECTANGLE, previous.getColor()));
        });
        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.PAGE_START);

        JToggleButton button2 = new JToggleButton("Circle");
        button2.addActionListener(e -> {
            changeState(new ApplicationWindowState(ApplicationMode.DRAW_CIRCLE, previous.getColor()));
        });
        buttonPanel.add(button2);
        add(buttonPanel, BorderLayout.PAGE_START);

        JToggleButton button3 = new JToggleButton("Line");
        button3.addActionListener(e -> {
            changeState(new ApplicationWindowState(ApplicationMode.DRAW_LINE, previous.getColor()));
        });
        buttonPanel.add(button3);
        add(buttonPanel, BorderLayout.PAGE_START);
        buttonGroup.add(button);
        buttonGroup.add(button2);
        buttonGroup.add(button3);
    }

    @Override
    public ApplicationWindowState provideState() {
        return applicationState == null ? INITIAL_STATE : applicationState;
    }

    @Override
    public void changeState(ApplicationWindowState state) {
        applicationState = state;
    }
}
