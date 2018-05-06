package Environment;

import Sprites.Sprite;

public class TilesStatic {

    public static Tile grass(int x, int y){
        GrassFactory gf = new GrassFactory();
        return new Tile(gf.grass(),x,y);
    }

    public static Tile loadTile(String name, int x, int y) {
        return new Tile(Sprite.loadSprite("res//"+name+".png"),x,y);
    }
}
