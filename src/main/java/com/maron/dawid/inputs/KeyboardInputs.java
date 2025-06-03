package com.maron.dawid.inputs;

import com.maron.dawid.main.GamePanel;
import com.maron.dawid.utils.Constants.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                gamePanel.changeYDelta(-5);
                gamePanel.setDirection(Direction.UP);
            }
            case KeyEvent.VK_S -> {
                gamePanel.changeYDelta(5);
                gamePanel.setDirection(Direction.DOWN);
            }
            case KeyEvent.VK_A -> {
                gamePanel.changeXDelta(-5);
                gamePanel.setDirection(Direction.LEFT);
            }
            case KeyEvent.VK_D -> {
                gamePanel.changeXDelta(5);
                gamePanel.setDirection(Direction.RIGHT);
            }
        }
        gamePanel.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> {}
            case KeyEvent.VK_S -> {}
            case KeyEvent.VK_A -> {}
            case KeyEvent.VK_D -> {}
        }
        gamePanel.setMoving(false);
    }
}
