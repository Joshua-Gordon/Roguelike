package Entity.Item;

import Sprites.Sprite;

public class Material extends Item {

    public Material(String name, String description, double weight, Sprite sprite) {
        super(0,0,sprite);
        super.name = name;
        super.description = description;
        super.weight = weight;
        super.sprite = sprite;
    }
}