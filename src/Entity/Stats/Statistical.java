package Entity.Stats;

public interface Statistical {
    Stats getStats();
    default void attack(Statistical enemy){
        Stats s = getStats();
        Stats e = enemy.getStats();
        int damage = s.attack - (s.attack/(e.defense*2));
        e.hp.change(-damage);
    }
}
