package Entity;

import Environment.Screen;
import Sprites.Sprite;
import Test.Game;

import java.util.function.Consumer;

public class NPC extends Entity {

    String name;

    public NPC(String name, int x, int y, Sprite s, Screen sc) {
        super(x,y,s,sc);
        this.name = name;
    }

    @Override
    public Consumer<Integer> onClick() {
        return e-> {
            System.out.println(Game.distanceToPlayer(this));
            if (Game.distanceToPlayer(this) <= 96) { //3 tiles
                Game.addText("Hi there! I'm " + name);
            }
        };
    }

    @Override
    public void update() {
        //System.out.println("AAAAAAAAAA");
        super.s.rotate(true);
    }
}
