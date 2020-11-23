package de.uol.snakeinc.vizualizer.gui;

import de.uol.snakeinc.vizualizer.ClientApp;
import de.uol.snakeinc.vizualizer.parser.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;

public class Board extends GridPane {

     Game game;
     int rounds;
     private IntegerProperty actualRoundProperty = new SimpleIntegerProperty();
     RoundTimer roundTimer;

    public Board (File file) {
        super();
         try {
              game = Game.getGame(file);
              System.out.println("Rounds: " + game.getRounds());
              System.out.println("Width: " + game.getWidth());System.out.println("Height: " + game.getHeight());
              for(int player : game.getPlayers().keySet()) {
              System.out.println("Player " + player + ": " + game.getPlayers().get(player));
              }
         } catch (FileNotFoundException e) {
              e.printStackTrace();
         }
         rounds = game.getRounds();
         actualRoundProperty.setValue(0);
         initializeCells();
    }

     public void initializeCells() {
             for (int i = 0; i < game.getWidth(); i++) {
                 for (int j = 0; j < game.getHeight(); j++) {
                     Cell cell = new Cell();
                     this.add(cell, i, j);
                 }
             }
    }

    public void startGame() {
        ObservableList<Node> childrens = this.getChildren();
        roundTimer.start();
    }

     public void setSpeed (int speed) {
        if (roundTimer != null) {
            roundTimer.end();
            roundTimer = new RoundTimer(speed, this);
            roundTimer.start();
        } else {
            roundTimer = new RoundTimer(speed, this);
        }
     }


     public void nextRound () {
        ObservableList<Node> childrens = this.getChildren();

         if (rounds == 1) {
             roundTimer.end();
         } else {
             rounds--;
             actualizeActualRoundProperty();
             for (Node node : childrens) {
                 int i = this.getRowIndex(node);
                 int j = this.getColumnIndex(node);

                 ((Cell) node).setFill(ClientApp.colorBag(game.getBoards().get(getActualRound())[i][j]));
             }
         }
     }

     public Game getGame () {
        return game;
     }

     public final int getActualRound() {
        return actualRoundProperty.get();
     }

     public IntegerProperty actualRoundProperty() {
        return actualRoundProperty;
     }

     public void actualizeActualRoundProperty() {
         actualRoundProperty.setValue(getActualRound() + 1);
     }
}
