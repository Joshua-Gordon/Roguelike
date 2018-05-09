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
    Screen sc;

    public Entity(int x, int y, Sprite s, Screen sc){
        this.x = x;
        this.y = y;
        this.s = s;
        this.t = Game.currentScreen.getTiles()[x][y];
        this.sc = sc;
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
            if(t.isBlocking()) {
                throw new NullPointerException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if(x+dx<0){
                Game.getRenderer().setScreen(Game.map.screens[--Game.screenX][Game.screenY]);
                x = 47;
            }
            else if(y+dy<0){
                Game.getRenderer().setScreen(Game.map.screens[Game.screenX][--Game.screenY]);
                y = 33;
            } else if(x+dx>47){
                Game.getRenderer().setScreen(Game.map.screens[++Game.screenX][Game.screenY]);
                x = 0;
            }
            else if(y+dy>33){
                Game.getRenderer().setScreen(Game.map.screens[Game.screenX][++Game.screenY]);
                y = 0;
            }
            Tile tNew = Game.currentScreen.getTiles()[x][y];
            if(tNew.isBlocking()) {
                move(-dx,-dy);
            } else {
                this.t = tNew;
            }
        } catch (NullPointerException e) {
            move(-dx,-dy);
        }
        this.sc = Game.currentScreen;
    }

    public void moveTo(Tile t) {
        this.x = t.X();
        this.y = t.Y();
        this.t = t;
    }

    public Render render() {
        if(Game.currentScreen.equals(sc))
            return new Render(s.getBi(),t.X(),t.Y());
        else
            return Render.blank();
    }

    @Override
    public Rectangle clickBox() {
        return t.clickBox();
    }

    public void setScreen(Screen sc) {
        this.sc = sc;
    }
}
