package com.maron.dawid.main;

public class Game implements Runnable {

    private final int FPS_SET = 120;

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }

    @Override
    public void run() {
        // nano-second
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while(true) {

            now = System.nanoTime();
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
}
