package com.maron.dawid.main;

import com.maron.dawid.inputs.KeyboardInputs;
import com.maron.dawid.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 0.25f, yDir = 0.25f;
    private Color rectangleColor = new Color(50, 115, 140);
    private Random random;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        random = new Random();
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

        updateRectangle();
        g.setColor(rectangleColor);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);
    }

    private void updateRectangle() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir = -xDir;
            rectangleColor = getRandomColor();
        }
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir = -yDir;
            rectangleColor = getRandomColor();
        }
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
