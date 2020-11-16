package de.uol.snakeinc.vizualizer;

import de.uol.snakeinc.vizualizer.parser.Game;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;

public class Board extends GridPane {

     Game game;

    public Board (File file) {
         this.setMinWidth(1000);
         this.setMinHeight(1000);
         this.setVgap(3);
         this.setHgap(3);
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
         initializeCells();
    }

     public void initializeCells() {
          for (int i=0; i< game.getWidth(); i++) {
               for (int j=0; j< game.getHeight(); j++) {
                    Cell cell = new Cell();
                    this.add(cell,i,j);
               }
          }
     }

}
