package Sprites;

import Graphics.GameObject;
import Graphics.Render;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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

    public void rotate(boolean positive) {
        AffineTransform at = new AffineTransform();
        at.rotate(positive ? Math.PI/2 : -Math.PI/2,bi.getWidth()/2,bi.getHeight()/2);
        //Graphics2D g2d = (Graphics2D)bi.getGraphics();
        AffineTransformOp ato = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
        bi = ato.filter(bi,null);
    }

}
