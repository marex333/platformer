package com.maron.dawid.level;

import com.maron.dawid.main.Game;
import com.maron.dawid.util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.maron.dawid.main.Game.TILES_SIZE;

public class LevelHandler {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelHandler(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprites() {
        int spriteVerticalSize = 4;
        int spriteHorizontalSize = 12;
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[spriteVerticalSize * spriteHorizontalSize];
        for (int i = 0; i < spriteVerticalSize; i++) {
            for (int j = 0; j < spriteHorizontalSize; j++) {
                int index = i * spriteHorizontalSize + j;
                levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        for(int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                int index = levelOne.getSpriteIndex(j, i);
                g.drawImage(levelSprite[index], j * TILES_SIZE, i * TILES_SIZE, TILES_SIZE, TILES_SIZE, null);
            }
        }

    }

    public void update() {

    }

    public Level getLevel() {
        return levelOne;
    }
}
