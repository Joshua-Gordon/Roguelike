package Entity;

import Entity.Item.RangedWeapon;
import Entity.Item.Weapon;
import Entity.Stats.Statistical;
import Entity.Stats.Stats;
import Environment.Tile;
import Environment.TilesStatic;
import Menu.Inventory;
import Menu.EquipMenu;
import Sprites.Sprite;
import Test.Game;

import java.awt.*;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

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

    @Override
    public void attack(Statistical enemy){
        Stats s = getStats();

        EquipMenu em = getEquipment();
        Weapon w = Weapon.unarmed();
        if(em != null){
            w = em.getWeapon();
        }
        if(!(s.getRange()+w.getRange() >= TilesStatic.distance(getTile(),enemy.getTile()))){
            return;
        }
        if(w instanceof RangedWeapon){
            String ammo = ((RangedWeapon) w).getAmmo();
            int idx = -1;
            for(int i = 0; i < inv.items.size(); ++i) {
                if(inv.items.get(i).getName().equals(ammo)){
                    idx = i;
                }
            }
            if(idx == -1) {
                Game.addText("Out of ammo!");
                return;
            } else {
                inv.items.get(idx).changeAmount(-1);
                if(inv.items.get(idx).getAmount() <= 0) {
                    inv.removeItem(inv.items.get(idx));
                }
            }
        }

        Stats e = enemy.getStats();
        int damage = s.getAttack() - (s.getAttack()/(e.getDefense()*2));

        damage += w.getDamageRange().get((new Random()).nextInt(w.getDamageRange().size()));

        Game.addText("The " + getName() + " attacks the " + enemy.getName() + " for " + damage + " damage!");
        e.getHp().change(-damage);
    }
}
