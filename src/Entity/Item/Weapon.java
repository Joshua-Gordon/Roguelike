package Entity.Item;

import Environment.Screen;
import Sprites.Sprite;
import Test.Game;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Weapon extends Item {

    boolean twoHanded;
    private ArrayList<Integer> al;

    public Weapon(String name, String description, double weight, Sprite sprite, int amount, boolean twoHanded, int damageMean, int damageStdDev) {
        super(0, 0, sprite, Game.currentScreen);
        super.name = name;
        super.description = description;
        super.weight = weight;
        super.sprite = sprite;
        super.amount = amount;
        this.twoHanded = twoHanded;
        al = new ArrayList<>();
        for(int x = damageMean-damageStdDev; x <= damageMean+damageStdDev; ++x) al.add(x);
    }

    public ArrayList<Integer> getDamageRange(){
        return al;
    }

    public static Weapon unarmed() {
        return new Weapon(
                "Unarmed",
                "No weapon",
                0,
                Sprite.loadSprite("res//box.png"),
                1,
                false,
                5,
                2);
    }

    public boolean isUnarmed() {
        return name.equals("Unarmed");
    }

    public int getRange() {
        return 64;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public String getName() {
        return name;
    }

    @Override
    public Consumer<Integer> onClick(){
        return r->{
            Game.addText(name);
            Game.addText(description);
            if(Game.STATE == 2)
                Game.p.inv.removeItem(this);
                Game.p.equip.equipWeapon(this);
            if(Game.STATE == 3)
                Game.p.equip.dequipWeapon();
        };
    }
}
