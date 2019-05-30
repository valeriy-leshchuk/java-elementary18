package org.xml.demo.ui.states;

public interface IApplicationWindowStateManager {

    ApplicationWindowState provideState();

    void changeState(ApplicationWindowState state);
}
