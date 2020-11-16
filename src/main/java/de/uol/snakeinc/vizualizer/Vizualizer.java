package de.uol.snakeinc.vizualizer;

import de.uol.snakeinc.vizualizer.parser.Game;

import java.io.File;
import java.io.FileNotFoundException;

public class Vizualizer {

    public static void main(String[] args) {
        File file = new File("logs", "test.json");
        try {
            Game game = Game.getGame(file);
            System.out.println("Rounds: " + game.getRounds());
            System.out.println("Width: " + game.getWidth());
            System.out.println("Height: " + game.getHeight());
            for(int player : game.getPlayers().keySet()) {
                System.out.println("Player " + player + ": " + game.getPlayers().get(player));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
