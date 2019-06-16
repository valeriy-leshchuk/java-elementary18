package org.ui;

import javax.swing.*;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.ui.states.ApplicationMode;
import org.ui.states.ApplicationWindowState;
import org.ui.states.GraphicAreaState;
import org.ui.states.IApplicationWindowStateManager;

import java.awt.*;
import java.io.*;

@Builder
@Getter
@Log4j
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
        GraphicAreaState graphicAreaState = readGraphicAreaStateFromFile();
        if (graphicAreaState!=null)
        {
            graphicArea.setGraphicAreaState(graphicAreaState);
        }
        graphicArea.setManager(this);
        initWithButtons();
        add(graphicArea, BorderLayout.CENTER);
    }

    private void initWithButtons() {
        JPanel buttonPanel = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();

        JToggleButton button = new JToggleButton("Rectangle");

        button.addActionListener(e -> {
            ApplicationWindowState previous = provideState();
            changeState(new ApplicationWindowState(ApplicationMode.DRAW_RECTANGLE, previous.getColor()));
        });
        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.PAGE_START);

        JToggleButton button2 = new JToggleButton("Circle");
        button2.addActionListener(e -> {
            ApplicationWindowState previous = provideState();
            changeState(new ApplicationWindowState(ApplicationMode.DRAW_CIRCLE, previous.getColor()));
        });
        buttonPanel.add(button2);
        add(buttonPanel, BorderLayout.PAGE_START);

        JToggleButton button3 = new JToggleButton("Line");
        button3.addActionListener(e -> {
            ApplicationWindowState previous = provideState();
            changeState(new ApplicationWindowState(ApplicationMode.DRAW_LINE, previous.getColor()));
        });
        buttonPanel.add(button3);

        //color chooser button
        JToggleButton pickButton = new JToggleButton("Pick");
        pickButton.addActionListener((e) -> {
            //get previous state
            ApplicationWindowState previous = provideState();
            //change current state
            changeState(new ApplicationWindowState(ApplicationMode.SELECT_ELEMENT, previous.getColor()));
        });
        buttonPanel.add(pickButton);

        //color chooser button
        JButton colorButton = new JButton("Color");
        colorButton.addActionListener((e) -> {
            ApplicationWindowState previous = provideState();
            Color c = JColorChooser.showDialog(
                    (Component) e.getSource(),
                    "Choose Background Color",
                    provideState().getColor());
            //change current state
            changeState(new ApplicationWindowState(previous.getMode(), c));
        });
        buttonPanel.add(colorButton);

        //color chooser button
        JToggleButton fill = new JToggleButton("FillColor");
        fill.addActionListener((e) -> {
            //get previous state
            ApplicationWindowState previous = provideState();
            //change current state
            changeState(new ApplicationWindowState(ApplicationMode.FILL_ELEMENT, previous.getColor()));
        });
        buttonPanel.add(fill);

        //color chooser button
        JToggleButton resize = new JToggleButton("Resize");
        resize.addActionListener((e) -> {
            //get previous state
            ApplicationWindowState previous = provideState();
            //change current state
            changeState(new ApplicationWindowState(ApplicationMode.RESIZE, previous.getColor()));
        });
        buttonPanel.add(resize);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e-> saveGraphicAreaStateToFile());
        buttonPanel.add(saveBtn);

        add(buttonPanel, BorderLayout.PAGE_START);
        buttonGroup.add(button);
        buttonGroup.add(button2);
        buttonGroup.add(button3);
        buttonGroup.add(pickButton);
        buttonGroup.add(fill);
        buttonGroup.add(resize);
    }

    @Override
    public ApplicationWindowState provideState() {
        return applicationState == null ? INITIAL_STATE : applicationState;
    }

    @Override
    public void changeState(ApplicationWindowState state) {
        applicationState = state;
    }

    private GraphicAreaState readGraphicAreaStateFromFile()
    {
        GraphicAreaState graphicAreaState = null;
        try
        {
            FileInputStream fin = new FileInputStream(ApplicationDemo.FILE_FOR_STORING_STATE);
            ObjectInputStream ois = new ObjectInputStream(fin);
            graphicAreaState = (GraphicAreaState) ois.readObject();

        }
        catch (IOException | ClassNotFoundException ex)
        {
            log.error("Couldn't load saved state");
            log.error(ex);
        }
        return graphicAreaState;
    }

    private void saveGraphicAreaStateToFile()
    {
        graphicArea.getGraphicAreaState();
        try
        {
            FileOutputStream fileOut = new FileOutputStream(ApplicationDemo.FILE_FOR_STORING_STATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(graphicArea.getGraphicAreaState());
            objectOut.close();
        }
        catch (IOException ex)
        {
            log.error("Couldn't save state to file");
            log.error(ex);
        }
    }
}
