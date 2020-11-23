package de.uol.snakeinc.vizualizer.gui;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    public  Cell () {
        super.setHeight(10);
        super.setWidth(10);
        this.setFill(Paint.valueOf("BLACK"));
    }
}
