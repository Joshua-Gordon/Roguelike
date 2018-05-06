package Entity;

import Control.Clickable;
import Environment.Collidable;
import Environment.Screen;
import Environment.Tile;
import Graphics.GameObject;
import Graphics.Render;
import Sprites.Sprite;
import Test.Game;

import java.awt.*;

public abstract class Entity implements GameObject, Clickable {
    int x, y;
    Sprite s;
    Tile t;

    public Entity(int x, int y, Sprite s){
        this.x = x;
        this.y = y;
        this.s = s;
        this.t = Game.currentScreen.getTiles()[x][y];
    }

    @Override
    public int X() {
        return t.X();
    }

    @Override
    public int Y() {
        return t.Y();
    }

    @Override
    public int layer() {
        return 2;
    }

    public void move(int dx, int dy) {
        x= (x+dx);
        y= (y+dy);
        try {
            this.t = Game.currentScreen.getTiles()[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            if(x+dx<0){
                Game.getRenderer().setScreen(Game.map.screens[--Game.screenX][Game.screenY]);
            }
            else if(y+dy<0){
                Game.getRenderer().setScreen(Game.map.screens[Game.screenX][--Game.screenY]);
            } else if(x+dx>47){
                Game.getRenderer().setScreen(Game.map.screens[++Game.screenX][Game.screenY]);
            }
            else if(y+dy>33){
                Game.getRenderer().setScreen(Game.map.screens[Game.screenX][++Game.screenY]);
            }
            x= (x+47) % 47;
            y= (y+33) % 33;
            this.t = Game.currentScreen.getTiles()[x][y];
        }
        /*if(x+dx<0){
            x+=dx;
        }
        if(y+dy<0){
            y+=dy;
        }
        if(!checkOccupied((x+dx)/32,(y+dy)/32)) {
            try {
                Game.currentScreen.getTiles()[x/32][y/32].setEntity(null);
                x += dx;
                y += dy;
                Game.currentScreen.getTiles()[x / 32][y / 32].setEntity(this);
            } catch(ArrayIndexOutOfBoundsException e) {
                try {
                    if (dx < 0) {
                        Game.getRenderer().setScreen(Game.map.screens[--Game.screenX][Game.screenY]);
                        x += 47 * 32;
                    } else if (dx > 0) {
                        Game.getRenderer().setScreen(Game.map.screens[++Game.screenX][Game.screenY]);
                        x -= 47 * 32;
                    } else if (dy < 0) {
                        Game.getRenderer().setScreen(Game.map.screens[Game.screenX][--Game.screenY]);
                        y += 33 * 32;
                    } else if (dy > 0) {
                        Game.getRenderer().setScreen(Game.map.screens[Game.screenX][++Game.screenY]);
                        y -= 33 * 32;
                    }
                } catch(ArrayIndexOutOfBoundsException a){}
            }
        } else {
//            Collidable c = (Collidable)Game.currentScreen.getTiles()[(x+dx)/32][(y+dy)/32].getEntity();
//            c.onCollide().accept(this);
        }*/
    }

    public void moveTo(Tile t) {
        this.x = t.X();
        this.y = t.Y();
        this.t = t;
    }

    public Render render() {
        return new Render(s.getBi(),t.X(),t.Y());
    }

    @Override
    public Rectangle clickBox() {
        return t.clickBox();
    }
}
