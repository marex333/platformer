package com.maron.dawid.util;

import com.maron.dawid.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "character/char_hood.png";
    // TODO add license
    public static final String LEVEL_ATLAS = "level/outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level/level_one_data.png";

    public static BufferedImage getSpriteAtlas(String sprite) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + sprite);
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
        return img;
    }

    public static int[][] getLevelData() {
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if (value > 48) {
                    value = 0;
                }
                levelData[i][j] = value;
            }
        }
        return levelData;

    }
}
