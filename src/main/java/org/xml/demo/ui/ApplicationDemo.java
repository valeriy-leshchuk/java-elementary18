package org.xml.demo.ui;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.LogManager;

@Slf4j
public class ApplicationDemo {

    static {
        try {
            LogManager.getLogManager().readConfiguration(
                    ApplicationDemo.class.getClassLoader().getResourceAsStream("log-ext.properties"));
        } catch (IOException io) {
            throw new RuntimeException(io);
        }
    }

    public static void main(String[] args) {
        int i = 1;
        log.info("Variable i = {}", i);
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
