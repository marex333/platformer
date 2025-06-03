package com.maron.dawid.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "char_hood.png";

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
