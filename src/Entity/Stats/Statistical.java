package Entity.Stats;

import Test.Game;

public interface Statistical {
    Stats getStats();
    String getName();
    default void attack(Statistical enemy){
        Stats s = getStats();
        Stats e = enemy.getStats();
        int damage = s.attack - (s.attack/(e.defense*2));
        Game.addText("The " + getName() + " attacks the " + enemy.getName() + " for " + damage + " damage!");
        e.hp.change(-damage);
    }
}
