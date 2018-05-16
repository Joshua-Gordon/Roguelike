package Entity.Item;

import Sprites.Sprite;

public class MeleeWeapon extends Weapon {
    public MeleeWeapon(String name, String description, double weight, Sprite sprite, boolean twoHanded, int damageMean, int damageStdDev) {
        super(name, description, weight, sprite, twoHanded, damageMean, damageStdDev);
    }
}
