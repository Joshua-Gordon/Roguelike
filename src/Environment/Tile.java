package Environment;

import Control.Clickable;
import Graphics.GameObject;
import Graphics.Render;
import Sprites.Sprite;
import Entity.Entity;
import Test.Game;

import java.awt.*;
import java.util.function.Consumer;

public class Tile implements GameObject, Clickable {

    int x,y; //Pixels
    Sprite s;
    String info;

    boolean blocking;

    public Tile(Sprite s, int x, int y, boolean b) {
        this.s = s;
        this.x = x;
        this.y = y;
        this.blocking = b;
        info = "A tile. Very generic, in an ad-hoc sort of way.";
    }

    @Override
    public Render render() {
        //System.out.println("Tile rendering at: (" +x+","+y+")");
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


    @Override
    public Consumer<Integer> onClick() {
        return e-> Game.addText(info);
    }

    @Override
    public Rectangle clickBox() {
        return new Rectangle(x,y,32,32);
    }
}
