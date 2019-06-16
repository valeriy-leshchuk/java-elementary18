package org.ui.states;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
@AllArgsConstructor
public class ApplicationWindowState {

    private  ApplicationMode mode;

    private  Color color;
}
