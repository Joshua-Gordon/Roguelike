package Entity;

import Entity.AI.AI;
import Entity.AI.ChaserAI;
import Entity.AI.SpinAI;
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
        this.ai = new ChaserAI();
        this.stats = Stats.defaultStats();
    }

    public NPC(String name, int x, int y, Sprite s, Screen sc, Stats st) {
        super(x,y,s,sc);
        this.name = name;
        this.ai = new SpinAI();
        this.stats = st;
    }

    @Override
    public Consumer<Integer> onClick() {
        return e-> {
            System.out.println(Game.distanceToPlayer(this));
            if (Game.distanceToPlayer(this) <= 96 && sc.equals(Game.currentScreen)) { //3 tiles
                Game.p.attack(this);
                Game.getRenderer().update();
                Game.addText("Ow! My HP is: " + stats.getHp().getLevel());
            }
        };
    }

    @Override
    public void update() {
        ai.act(this);
        if(stats.getHp().getLevel() <= 0){
            Game.addText("The " + name + " dies.");
            die();
        }
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public String getName() {
        return name;
    }
}