package Entity.Item;

import Sprites.Sprite;
import Test.Game;

import java.util.function.Consumer;

public class Material extends Item {

    public Material(String name, String description, double weight, Sprite sprite, int amount) {
        super(0,0,sprite, Game.currentScreen);
        super.name = name;
        super.description = description;
        super.weight = weight;
        super.sprite = sprite;
        super.amount = amount;
    }
}
