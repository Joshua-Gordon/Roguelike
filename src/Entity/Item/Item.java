package Entity.Item;

import Entity.Entity;
import Sprites.Sprite;
import Graphics.Render;
import Test.Game;

import java.awt.*;
import java.util.function.Consumer;

public abstract class Item extends Entity{
    String name, description;
    double weight;
    Sprite sprite;

    @Override
    public Consumer<Integer> onClick() {
        return e->{
            Game.addText(name);
            Game.addText(description);
        };
    }

    @Override
    public Rectangle clickBox() {
        return new Rectangle(super.X(),super.Y(),32,32);
    }

    @Override
    public Render render() {
        return new Render(sprite.getBi(),super.X(),super.Y());
    }

    @Override
    public void update(){}

    public void setPosition(int x, int y) {
        super.move(x-super.X(),y-super.Y());
    }
}
