package de.uol.snakeinc.vizualizer;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    public  Cell (int[][] cells, int i, int j) {
        super.setHeight(20);
        super.setWidth(20);
        this.setFill(Paint.valueOf("BLACK"));
    }

    public void changeTo (Paint colour) {
        this.setFill(colour);
    }

}
