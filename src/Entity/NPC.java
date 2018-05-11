package Entity;

import Entity.AI.AI;
import Entity.Stats.Statistical;
import Entity.Stats.Stats;
import Environment.Screen;
import Sprites.Sprite;
import Test.Game;

import java.util.function.Consumer;

public class NPC extends Entity implements Statistical {

    String name;
    AI ai;

    Stats stats;

    public NPC(String name, int x, int y, Sprite s, Screen sc) {
        super(x,y,s,sc);
        this.name = name;
        this.ai = new AI();
        this.stats = Stats.defaultStats();
    }

    public NPC(String name, int x, int y, Sprite s, Screen sc, Stats st) {
        super(x,y,s,sc);
        this.name = name;
        this.ai = new AI();
        this.stats = st;
    }

    @Override
    public Consumer<Integer> onClick() {
        return e-> {
            System.out.println(Game.distanceToPlayer(this));
            if (Game.distanceToPlayer(this) <= 96) { //3 tiles
                Game.p.attack(this);
                Game.addText("Ow! My HP is: " + stats.getHp().getLevel());
            }
        };
    }

    @Override
    public void update() {
        ai.act(this);
    }

    @Override
    public Stats getStats() {
        return stats;
    }
}