package de.uol.snakeinc.vizualizer;

public class RoundTimer extends Thread {

    private int time;
    private boolean stopped = false;
    private final Object lock = new Object();
    private Board board;

    public RoundTimer(double time, Board board) {
        this.board = board;
        this.time = Integer.parseInt(Double.toString(time));
        this.setDaemon(true);
    }

    /**
     * Force-End Timer.
     */
    public void end() {
        this.stopped = true;
        synchronized (lock) {
            lock.notify();
        }
    }

    /**
     * Main-Runner-Thread.
     */
    @Override
    public void run() {
        synchronized (lock) {
            while (!stopped) {
                try {
                    lock.wait(time * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        board.nextRound();
            }
        }
    }
}
