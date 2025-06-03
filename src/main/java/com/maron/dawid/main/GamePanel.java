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
    private BufferedImage img;
    private BufferedImage[] idleAnimation;
    private int animationTick, animationIndex, animationSpeed = 60;

    public GamePanel() {
        importImg();
        loadAnimations();

        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        int xSize = 32, ySize = 32;
        idleAnimation = new BufferedImage[2];
        for (int i = 0; i < idleAnimation.length; i++) {
            idleAnimation[i] = img.getSubimage(i * xSize, 0 * ySize, xSize, ySize);

        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/char_hood.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch(IOException ioException) {

            }
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

        updateAnimationTick();
        g.drawImage(idleAnimation[animationIndex], (int)xDelta, (int)yDelta, 120, 120, null);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= idleAnimation.length) {
                animationIndex = 0;
            }
        }
    }
}
