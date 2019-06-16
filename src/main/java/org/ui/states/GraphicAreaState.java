package org.ui.states;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ui.figures.Figure;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
public class GraphicAreaState implements Serializable
{
    @Getter private List<Figure> figures;
}
