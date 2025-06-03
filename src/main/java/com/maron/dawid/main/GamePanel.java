package com.maron.dawid.main;

import com.maron.dawid.inputs.KeyboardInputs;
import com.maron.dawid.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta = 0;
    private BufferedImage img, subImg;

    public GamePanel() {
        importImg();

        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/char_hood.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        var image1 = img.getSubimage(0 * 32,0 * 32, 32, 32);
        g.drawImage(image1, (int)xDelta, (int)yDelta, 120, 120, null);
    }
}
