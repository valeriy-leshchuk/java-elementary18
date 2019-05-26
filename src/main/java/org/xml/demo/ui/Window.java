package org.xml.demo.ui;

import javax.swing.*;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Window extends JFrame {

    private String windowTitle;

    private int windowHeight;

    private int windowWidth;

    private String workingDirectory;

    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(windowTitle);
        setSize(windowWidth, windowHeight);
    }
}
