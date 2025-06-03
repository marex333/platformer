package com.maron.dawid.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "character/char_hood.png";
    public static final String LEVEL_ATLAS = "level/TileToon1_0.png";
    public static final String LEVEL_ATLAS_2 = "level/TileToon2.png";
    public static final String BACKGROUND_ATLAS = "level/BG_01.png";
    public static final String SKY = "level/sky_01.png";

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
}
