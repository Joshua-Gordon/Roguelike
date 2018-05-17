package Entity;

import Entity.Stats.Statistical;
import Entity.Stats.Stats;
import Environment.Tile;
import Menu.Inventory;
import Menu.EquipMenu;
import Sprites.Sprite;
import Test.Game;

import java.awt.*;
import java.util.function.Consumer;

public class Player extends Entity implements Statistical {

    public boolean outOfBounds;
    Stats stats;

    public Inventory inv;
    public EquipMenu equip;

    public Player() {
        super(5,5,Sprite.loadSprite("res//bird.jpg"),Game.currentScreen);
        outOfBounds = false;
        stats = Stats.defaultStats();
        inv = new Inventory(50);
        equip = new EquipMenu();
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
    @Override
    public String getName() { return "player";}

    @Override
    public Tile getTile() {
        return t;
    }

    @Override
    public EquipMenu getEquipment() {
        return equip;
    }
}
