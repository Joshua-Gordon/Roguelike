package Graphics;

import java.awt.image.BufferedImage;

public class Render {

    public boolean blank;

    public Render(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.blank = false;
    }

    public Render(boolean blank) {
        this.image = null;
        this.x = -1;
        this.y = -1;
        this.blank = true;
    }

    public BufferedImage image;
    public int x,y;

    public static Render blank() {
        return new Render(false);
    }
}
