package org.ui.states;

public interface IApplicationWindowStateManager {

    ApplicationWindowState provideState();

    void changeState(ApplicationWindowState state);
}
