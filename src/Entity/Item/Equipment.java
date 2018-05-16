package Entity.Item;

import Environment.Screen;
import Menu.EquipMenu;
import Menu.Inventory;
import Sprites.Sprite;
import Test.Game;

import java.util.function.Consumer;

public class Equipment extends Item {

    public enum BodyPart{
        HEAD,CHEST,ARM_L,ARM_R,HAND_L,HAND_R,FINGER_L,FINGER_R,LEG_L,LEG_R,FOOT_L,FOOT_R,                //External
        INTERNAL_ARM_L,INTERNAL_ARM_R,INTERNAL_LEG_L,INTERNAL_LEG_R,EYES,INTERNAL_HEAD,INTERNAL_CHEST,   //Internal
    }

    int attack, defense, speed, processing;
    BodyPart type;


    public Equipment(String name, String description, double weight, Sprite sprite, int amount, BodyPart type, int attack, int defense, int speed, int processing) {
        super(0,0,sprite, Game.currentScreen);
        super.name = name;
        super.description = description;
        super.weight = weight;
        super.sprite = sprite;
        super.amount = amount;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.processing = processing;
    }


    @Override
    public Consumer<Integer> onClick(){
        return r->{
            Game.addText(name);
            Game.addText(description);
            if(Game.STATE == 2)
                Game.p.inv.equipItem(this);
            if(Game.STATE == 3)
                EquipMenu.dequip(this);
        };
    }


    public BodyPart getType() {
        return type;
    }

    public void setType(BodyPart type) {
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getProcessing() {
        return processing;
    }

    public void setProcessing(int processing) {
        this.processing = processing;
    }
}
