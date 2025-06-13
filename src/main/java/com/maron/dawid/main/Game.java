package com.maron.dawid.main;

import com.maron.dawid.entity.Player;
import com.maron.dawid.level.LevelHandler;

import java.awt.*;

public class Game implements Runnable {

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final static int CHARACTER_SIZE = 50;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;
    private LevelHandler levelHandler;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.setBackground(Color.GRAY);
        startGameLoop();
    }

    private void initClasses() {
        levelHandler = new LevelHandler(this);
        player = new Player(200, 200, (int)(CHARACTER_SIZE * SCALE), (int)(CHARACTER_SIZE * SCALE));
        player.loadLevelData(levelHandler.getLevel().getLevelData());
    }

    private void update() {
        player.update();
        levelHandler.update();
    }

    public void render(Graphics g) {
        levelHandler.draw(g);
        player.render(g);
    }

    @Override
    public void run() {
        // nano-second
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                update();
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.printf("FPS: %s || UPS: %s%n", frames, updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public void windowLostFocus() {
        player.resetDirBooleans();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public Player getPlayer() {
        return player;
    }
}
