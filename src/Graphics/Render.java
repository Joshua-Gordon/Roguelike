package Graphics;

import java.awt.image.BufferedImage;

public class Render {

    public Render(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public BufferedImage image;
    public int x,y;
}
