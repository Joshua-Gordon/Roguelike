package Entity;

import Graphics.Render;
import Sprites.Sprite;
import Test.Game;

import java.awt.*;
import java.util.function.Consumer;

public class Player extends Entity{

    public boolean outOfBounds;


    public Player() {
        super(5,5,Sprite.loadSprite("res//bird.jpg"),Game.currentScreen);
        outOfBounds = false;
    }

    public Sprite getSprite(){
        return super.s;
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

}
