package com.maron.dawid.entity;

import com.maron.dawid.util.Constants.PlayerConstants.PlayerAction;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.maron.dawid.util.LoadSave.*;

public class Player extends Entity {
    private final int CHARACTER_SPEED = 1;
    private int animationTick, animationIndex, animationSpeed = 30;
    private PlayerAction playerAction = PlayerAction.RUN;
    private boolean up, down, left, right;
    private boolean moving = false;
    private boolean attacking = false;
    private BufferedImage[][] animations;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update() {
        updatePosition();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction.getActionIndex()][animationIndex], (int) x, (int) y, width, height, null);
        drawHitbox(g);

    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= playerAction.getAnimationsNumber()) {
                animationIndex = 0;
                attacking = false;
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
        // fix missing subImages for idle
        if (!moving && !attacking && playerAction != PlayerAction.IDLE) {
            resetTick();
        }
    }

    public void setAnimation() {
        if (moving) {
            playerAction = PlayerAction.RUN;
        } else {
            playerAction = PlayerAction.IDLE;
        }

        if (attacking) {
            playerAction = PlayerAction.ATTACK;
        }
    }

    private void resetTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void loadAnimations() {
        BufferedImage img = getSpriteAtlas(PLAYER_ATLAS);
        animations = new BufferedImage[9][8];
        int xSize = 32, ySize = 32;
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * xSize, j * ySize, xSize, ySize);
            }
        }
    }

    public void resetDirBooleans() {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
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
