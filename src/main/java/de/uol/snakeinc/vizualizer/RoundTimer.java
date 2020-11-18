package de.uol.snakeinc.vizualizer;

public class RoundTimer extends Thread {

    private int time;
    private boolean stopped = false;
    private final Object lock = new Object();
    private Board board;

    public RoundTimer(int time, Board board) {
        this.board = board;
        this.time = time;
        this.setDaemon(true);
    }

    /**
     * Force-End Timer.
     */
    public void end() {
        this.stopped = true;
    }

    /**
     * Main-Runner-Thread.
     */
    @Override
    public void run() {
            while (!stopped) {
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.nextRound();
        }
    }
}
