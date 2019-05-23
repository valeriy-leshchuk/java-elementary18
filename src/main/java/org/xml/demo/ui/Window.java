package org.xml.demo.ui;

import javax.swing.*;

public class Window extends JFrame {

    public static void main(String[] args) {
        Window window = new Window();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Graphic editor v.1");
        window.add(new GraphicArea());

        window.setSize(700, 700);
        window.setVisible(true);
    }
}
