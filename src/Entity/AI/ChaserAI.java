package Entity.AI;

import Entity.NPC;
import Test.Game;

public class ChaserAI extends AI {

    @Override
    public void act(NPC npc) {
        if(Game.distanceToPlayer(npc) < npc.getStats().getRange()) {
            npc.attack(Game.p);
        } else {
            int xDiff = Game.p.X() - npc.X();
            int yDiff = Game.p.Y() - npc.Y();
            System.out.println("xDiff: " + xDiff + ", yDiff: " + yDiff);
            if(Math.abs(xDiff) > Math.abs(yDiff)) {
                 npc.move((int)Math.signum(xDiff),0);
            } else {
                npc.move(0,(int)Math.signum(yDiff));
            }
        }
    }
}
