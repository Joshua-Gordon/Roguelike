package Sprites;

import Graphics.GameObject;
import Graphics.Render;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {

    BufferedImage bi;

    public Sprite(BufferedImage bi) {
        this.bi = bi;
    }

    public BufferedImage getBi() {
        return bi;
    }

    public static Sprite loadSprite(String fp) {
        BufferedImage bi = null;
        try{
            File f = new File(fp);
            bi = ImageIO.read(f);
        }catch(IOException ioe) {
            System.err.println("Problem loading sprite!");
            ioe.printStackTrace();
        }
        return new Sprite(bi);
    }

}
