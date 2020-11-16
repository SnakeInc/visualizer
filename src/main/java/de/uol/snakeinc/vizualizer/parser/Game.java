package de.uol.snakeinc.vizualizer.parser;

import java.io.File;
import java.util.HashMap;

public class Game {

    private int rounds;
    private HashMap<Integer, String> players;
    private int us;

    private int width;
    private int height;

    private HashMap<Integer, Integer[]> boards;

    public Game(int rounds, HashMap<Integer, String> players, int us, int width, int height, HashMap<Integer, Integer[]> boards) {
        this.rounds = rounds;
        this.players = players;
        this.us = us;

        this.width = width;
        this.height = height;
        this.boards = boards;
    }

    public int getRounds() {
        return this.rounds;
    }

    public HashMap<Integer, String> getPlayers() {
        return this.players;
    }

    public int getUs() {
        return this.us;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public HashMap<Integer, Integer[]> getBoards() {
        return this.boards;
    }

    public static Game getGame(File file) {

        return null;
    }
}
