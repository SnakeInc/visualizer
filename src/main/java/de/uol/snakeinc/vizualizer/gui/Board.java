package de.uol.snakeinc.vizualizer.gui;

import de.uol.snakeinc.vizualizer.ClientApp;
import de.uol.snakeinc.vizualizer.parser.Game;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
        roundTimer.start();
     }

     public void nextRound () {
        ObservableList<Node> childrens = this.getChildren();

         if (rounds == 0) {
             roundTimer.end();
         } else {
             rounds--;
             for (Node node : childrens) {
                 int i = this.getRowIndex(node);
                 int j = this.getColumnIndex(node);

                 ((Cell) node).setFill(ClientApp.colorBag(game.getBoards().get(game.getRounds() - rounds)[i][j]));
             }
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
