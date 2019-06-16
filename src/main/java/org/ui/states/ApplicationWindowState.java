package org.ui.states;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApplicationWindowState implements Serializable
{

    private  ApplicationMode mode;

    private  Color color;
}
