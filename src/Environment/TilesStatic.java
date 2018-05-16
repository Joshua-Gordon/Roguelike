package Environment;

import Sprites.Sprite;

public class TilesStatic {

    public static Tile grass(int x, int y){
        GrassFactory gf = new GrassFactory();
        return new Tile(gf.grass(),x*32,y*32,false);
    }

    public static Tile loadTile(String name, int x, int y) {
        boolean blocking = name.equals("wall");
        Tile t = new Tile(Sprite.loadSprite("res//"+name+".png"),x*32,y*32,blocking);
        if(name.equals("water")) {
            t.setInfo("water");
        }
        return t;
    }

    public static Tile loadTileExplicitLocation(String name, int x, int y) {
        boolean blocking = name.equals("wall");
        return new Tile(Sprite.loadSprite("res//"+name+".png"),x,y,blocking);
    }

    public static int distance(Tile t1, Tile t2) {
        return (int)Math.round(Math.sqrt((t1.x-t2.x)*(t1.x-t2.x) + ((t1.y-t2.y)*(t1.y-t2.y))));
    }
}
