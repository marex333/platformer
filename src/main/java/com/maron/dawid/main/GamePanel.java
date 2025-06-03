package com.maron.dawid.main;

import com.maron.dawid.inputs.KeyboardInputs;
import com.maron.dawid.inputs.MouseInputs;
import com.maron.dawid.utils.Constants.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static com.maron.dawid.utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 60;
    private PlayerAction playerAction = PlayerAction.RUN;
    private Direction playerDirection;
    private boolean moving = false;

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
        animations = new BufferedImage[9][8];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[0].length; i++) {
                animations[j][i] = img.getSubimage(i * xSize, j * ySize, xSize, ySize);

            }
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
            } catch (IOException ioException) {

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

    public void setDirection(Direction direction) {
        playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setAnimation() {
        if (moving) {
            playerAction = PlayerAction.RUN;
        } else {
            playerAction = PlayerAction.IDLE;
        }
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= playerAction.getAnimationsNumber()) {
                animationIndex = 0;
            }
        }
    }

    private void updatePosition() {
        if(moving) {
            switch(playerDirection) {
                case LEFT -> xDelta -=5;
                case UP -> yDelta -= 5;
                case RIGHT -> xDelta += 5;
                case DOWN -> yDelta += 5;
            }
        }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(animations[playerAction.getActionIndex()][animationIndex], (int) xDelta, (int) yDelta, 120, 120, null);
    }
}
