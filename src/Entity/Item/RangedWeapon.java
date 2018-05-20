package Entity.Item;

import Sprites.Sprite;

public class RangedWeapon extends Weapon {

    int range;
    String ammo;

    public RangedWeapon(String name, String description, double weight, Sprite sprite, int amount, boolean twoHanded, int damageMean, int damageStdDev, int range, String ammo) {
        super(name, description, weight, sprite, amount, twoHanded, damageMean, damageStdDev);
        this.range = range;
        this.ammo = ammo;
    }

    @Override
    public int getRange(){
        return this.range;
    }

    public String getAmmo() {
        return this.ammo;
    }
}
