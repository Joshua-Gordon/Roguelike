package Entity.Stats;

import Entity.Item.Weapon;
import Environment.Tile;
import Environment.TilesStatic;
import Menu.EquipMenu;
import Test.Game;

import java.util.Random;

public interface Statistical {
    Stats getStats();
    String getName();
    Tile getTile();
    default void attack(Statistical enemy){
        Stats s = getStats();

        EquipMenu em = getEquipment();
        Weapon w = Weapon.unarmed();
        if(em != null){
            //TODO: Get the stuff
        }
        if(!(s.getRange()+w.getRange() >= TilesStatic.distance(getTile(),enemy.getTile()))){
            return;
        }

        Stats e = enemy.getStats();
        int damage = s.attack - (s.attack/(e.defense*2));

        damage += w.getDamageRange().get((new Random()).nextInt(w.getDamageRange().size()));

        Game.addText("The " + getName() + " attacks the " + enemy.getName() + " for " + damage + " damage!");
        e.hp.change(-damage);
    }
    default Weapon getWeapon(){
        return Weapon.unarmed();
    }

    EquipMenu getEquipment();

}
