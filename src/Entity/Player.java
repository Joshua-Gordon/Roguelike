package Entity;

import Graphics.Render;
import Sprites.Sprite;
import Test.Game;

import java.awt.*;
import java.util.function.Consumer;

public class Player extends Entity{

    public boolean outOfBounds;

    Sprite s;

    public Player() {
        super();
        super.x = 5;
        super.y = 5;
        s = Sprite.loadSprite("res//bird.jpg");
        outOfBounds = false;
    }

    public Sprite getSprite(){
        return s;
    }

    @Override
    public Render render() {
        return new Render(s.getBi(),super.x,super.y);
    }

    @Override
    public void update() {

    }

    @Override
    public Consumer<Integer> onClick() {
        return e->{
            Game.addText("That's you!");
        };
    }

    @Override
    public Rectangle clickBox() {
        return new Rectangle(super.x,super.y,32,32);
    }
}
