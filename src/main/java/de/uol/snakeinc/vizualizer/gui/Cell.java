package de.uol.snakeinc.vizualizer.gui;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    public  Cell () {
        super.setHeight(11);
        super.setWidth(11);
        this.setFill(Paint.valueOf("BLACK"));
    }
}
