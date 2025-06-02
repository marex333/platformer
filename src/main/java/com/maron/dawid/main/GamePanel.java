package com.maron.dawid.main;

import com.maron.dawid.inputs.KeyboardInputs;
import com.maron.dawid.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xDelta = 100;
    private int yDelta = 100;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(xDelta, yDelta, 200, 50);
    }
}
