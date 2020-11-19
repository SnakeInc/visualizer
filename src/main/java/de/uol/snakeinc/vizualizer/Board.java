package de.uol.snakeinc.vizualizer;

import de.uol.snakeinc.vizualizer.parser.Game;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;

public class Board extends GridPane {

     Game game;
     int rounds;
     RoundTimer roundTimer;

    public Board (File file) {
        super();

        this.setStyle("-fx-background-color: white; -fx-border-color: black");
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

     public void setSpeed (int speed) {
        if (roundTimer != null) {
            roundTimer.end();
        }

         roundTimer = new RoundTimer(speed, this);
     }

     public void nextRound () {
        ObservableList<Node> childrens = this.getChildren();

        System.out.println(childrens.size());
        for (Node node : childrens) {
            int i = this.getRowIndex(node);
            int j = this.getColumnIndex(node);

            ((Cell) node).setFill(Visualizer.colorBag(game.getBoards().get(game.getRounds()-rounds)[i][j]));
        }
        if (rounds == 0) {
             roundTimer.end();
        } else {
             rounds--;
        }
     }

     public void startGame() {
         ObservableList<Node> childrens = this.getChildren();
         rounds--;
         roundTimer.start();
     }

     public Game getGame () {
        return game;
     }

}
