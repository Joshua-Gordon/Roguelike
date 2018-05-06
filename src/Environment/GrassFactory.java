package Environment;

import Sprites.Sprite;

import java.awt.image.BufferedImage;
import java.util.Random;

public class GrassFactory {

    Random aynRand;
    BufferedImage grassTemplate;
    BufferedImage tuft;

    public GrassFactory(){
        aynRand = new Random();
        grassTemplate = Sprite.loadSprite("res//grassTemplate.png").getBi();
        tuft = Sprite.loadSprite("res//tuft.png").getBi();
    }

    public Sprite grass(){

        BufferedImage bi = new BufferedImage(grassTemplate.getColorModel(),grassTemplate.copyData(null),grassTemplate.getColorModel().isAlphaPremultiplied(),null);

        int numTufts = aynRand.nextInt(2);

        //System.out.println(numTufts);

        for(int i = 0; i < numTufts; ++i) {
            int x = aynRand.nextInt(32);
            int y = aynRand.nextInt(32);
            bi.getGraphics().drawImage(tuft,x,y,null);
        }
        return new Sprite(bi);
    }

}
