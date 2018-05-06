package Environment;

import Sprites.Sprite;

public class TilesStatic {

    public static Tile grass(int x, int y){
        GrassFactory gf = new GrassFactory();
        return new Tile(gf.grass(),x*32,y*32,false);
    }

    public static Tile loadTile(String name, int x, int y) {
        boolean blocking = name.equals("wall");
        return new Tile(Sprite.loadSprite("res//"+name+".png"),x*32,y*32,blocking);
    }

    public static Tile loadTileExplicitLocation(String name, int x, int y) {
        boolean blocking = name.equals("wall");
        return new Tile(Sprite.loadSprite("res//"+name+".png"),x,y,blocking);
    }
}
