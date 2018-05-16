package Entity.Item;

import Sprites.Sprite;

public class RangedWeapon extends Weapon {

    int range;
    Material ammo;

    public RangedWeapon(String name, String description, double weight, Sprite sprite, int amount, boolean twoHanded, int damageMean, int damageStdDev, int range, Material ammo) {
        super(name, description, weight, sprite, amount, twoHanded, damageMean, damageStdDev);
        this.range = range;
        this.ammo = ammo;
    }
}
