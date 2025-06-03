package com.maron.dawid.level;

import com.maron.dawid.main.Game;
import com.maron.dawid.util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelHandler {

    private Game game;
    private BufferedImage levelSprite;

    public LevelHandler(Game game) {
        this.game = game;
        levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
    }

    public void draw(Graphics g) {
        g.drawImage(levelSprite, 0, 0, null);
    }

    public void update() {

    }
}
