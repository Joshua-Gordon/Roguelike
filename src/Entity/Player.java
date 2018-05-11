package Entity;

import Entity.Stats.Statistical;
import Entity.Stats.Stats;
import Environment.Tile;
import Graphics.Render;
import Sprites.Sprite;
import Test.Game;

import java.awt.*;
import java.util.function.Consumer;

public class Player extends Entity implements Statistical {

    public boolean outOfBounds;
    Stats stats;

    public Player() {
        super(5,5,Sprite.loadSprite("res//bird.jpg"),Game.currentScreen);
        outOfBounds = false;
        stats = Stats.defaultStats();
    }

    public Sprite getSprite(){
        return super.s;
    }

    @Override
    public void update() {

    }

    @Override
    public Consumer<Integer> onClick() {
        return e->{
            Game.addText("That's you!");
        };
    }

    @Override
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
        Game.getRenderer().update();
    }

    @Override
    public Stats getStats() {
        return stats;
    }

}
