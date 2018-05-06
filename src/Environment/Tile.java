package Environment;

import Graphics.GameObject;
import Graphics.Render;
import Sprites.Sprite;
import Entity.Entity;

public class Tile implements GameObject{

    int x,y; //Grid indices
    Sprite s;
    String info;

    Entity e;

    public Tile(Sprite s, int x, int y) {
        this.s = s;
        this.x = x;
        this.y = y;
        info = "A tile. Very generic, in an ad-hoc sort of way.";
    }

    @Override
    public Render render() {
        return new Render(s.getBi(),x,y);
    }

    @Override
    public void update() {
        //void void
    }

    @Override
    public int X() {
        return x;
    }

    @Override
    public int Y() {
        return y;
    }

    @Override
    public int layer() {
        return 0;
    }

    //No move function yet. Just screens.

    public String getInfo() {
        return info;
    }

    public void setInfo(String t) {
        info = t;
    }

    public void setEntity(Entity ent) {
        e = ent;
    }

    public Entity getEntity() {
        return e;
    }

}
