package com.maron.dawid.main;

import com.maron.dawid.inputs.KeyboardInputs;
import com.maron.dawid.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;

    public GamePanel() {
        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setPreferredSize(size);
    }

    // for keyboard
    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    // for keyboard
    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    // for mouse
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
