package com.maron.dawid.main;

import com.maron.dawid.inputs.KeyboardInputs;
import com.maron.dawid.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static com.maron.dawid.main.Game.GAME_HEIGHT;
import static com.maron.dawid.main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {
    private Game game;
    private MouseInputs mouseInputs;

    public GamePanel(Game game) {
        this.game = game;
        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.printf("size: %s : %s\n", GAME_WIDTH, GAME_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }

}
