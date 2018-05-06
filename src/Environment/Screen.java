package Environment;

import Graphics.GameObject;
import Graphics.Render;
import Test.Game;

import java.awt.image.BufferedImage;

public class Screen implements GameObject{

    Tile[][] tiles;

    public Screen(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public static Screen defaultScreen() {
        Tile[][] grassTiles = new Tile[48][33];
        GrassFactory gf = new GrassFactory();
        for(int x = 0; x < 48; ++x) {
            for(int y = 0; y < 33; ++y) {
                grassTiles[x][y] = new Tile(gf.grass(),x*32,y*32,false);
            }
        }
        return new Screen(grassTiles);
    }

    @Override
    public Render render() {
        BufferedImage bi = new BufferedImage((int)(Game.WIDTH*.8),(int)(Game.HEIGHT),BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < 48; ++x) {
            for(int y = 0; y < 33; ++y) {
                bi.getGraphics().drawImage(tiles[x][y].render().image,x*32,y*32,null);
            }
        }
        return new Render(bi,0,0);
    }

    @Override
    public void update() {

    }

    @Override
    public int X() {
        return 0;
    }

    @Override
    public int Y() {
        return 0;
    }

    @Override
    public int layer() {
        return 0;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
