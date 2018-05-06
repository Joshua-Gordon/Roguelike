package Graphics;

import Test.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class InfoBoxRenderer {

    LinkedList<String> text;

    int x = (int)(Game.WIDTH*.8);
    int w = (int)(Game.WIDTH*.2);

    int numLines = 30;

    public InfoBoxRenderer() {
        text = new LinkedList<>();
        for(int i = 0; i < numLines; ++i) {
            text.add("");
        }
    }

    public BufferedImage render(BufferedImage bi) {
        Graphics2D g = (Graphics2D)bi.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(x,0,w,Game.HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        int i = 0;
        for(String s : text) {
            g.drawString(">"+s,x,35*(i++));
            //System.out.println("Drawing text");
        }
        return bi;
    }

    public void addText(String t) {
        text.remove();
        text.add(t);
    }

}
