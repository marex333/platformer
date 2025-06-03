package com.maron.dawid.entities;

import com.maron.dawid.utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {
    private final int CHARACTER_SPEED = 1;
    private int animationTick, animationIndex, animationSpeed = 60;
    private Constants.PlayerConstants.PlayerAction playerAction = Constants.PlayerConstants.PlayerAction.RUN;
    private boolean up, down, left, right;
    private boolean moving = false;
    private BufferedImage[][] animations;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction.getActionIndex()][animationIndex], (int) x, (int) y, 120, 120, null);

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
        moving = false;

        if (left && !right) {
            x -= CHARACTER_SPEED;
            moving = true;
        } else if (right && !left) {
            x += CHARACTER_SPEED;
            moving = true;
        }

        if (up && !down) {
            y -= CHARACTER_SPEED;
            moving = true;
        } else if (down && !up) {
            y += CHARACTER_SPEED;
            moving = true;
        }
    }

    public void setAnimation() {
        if (moving) {
            playerAction = Constants.PlayerConstants.PlayerAction.RUN;
        } else {
            playerAction = Constants.PlayerConstants.PlayerAction.IDLE;
        }
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/char_hood.png");
        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[9][8];
            int xSize = 32, ySize = 32;
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[0].length; i++) {
                    animations[j][i] = img.getSubimage(i * xSize, j * ySize, xSize, ySize);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ioException) {

            }
        }
    }

    public void resetDirBooleans() {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
