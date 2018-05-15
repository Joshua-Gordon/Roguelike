package Entity.Stats;

import Entity.Item.Weapon;
import Test.Game;

import java.util.Random;

public interface Statistical {
    Stats getStats();
    String getName();
    default void attack(Statistical enemy){
        Stats s = getStats();
        Stats e = enemy.getStats();
        int damage = s.attack - (s.attack/(e.defense*2));

        Weapon w = getWeapon();
        damage += w.getDamageRange().get((new Random()).nextInt(w.getDamageRange().size()));

        Game.addText("The " + getName() + " attacks the " + enemy.getName() + " for " + damage + " damage!");
        e.hp.change(-damage);
    }
    default Weapon getWeapon(){
        return Weapon.unarmed();
    }
}
