package org.ui;

import lombok.extern.log4j.Log4j;

@Log4j
public class ApplicationDemo
{
    public static final String FILE_FOR_STORING_STATE = "D:\\Hillel\\java-elementary18\\savedState.txt";

    public static void main(String[] args)
    {
        Window window = Window
            .builder()
            .windowHeight(700)
            .windowWidth(700)
            .windowTitle("Test")
            .build();
        window.init();

        window.setVisible(true);
    }
}
