package Entity.AI;

import Entity.NPC;

public class SpinAI extends AI {

    int counter;

    public SpinAI() {
        this.counter = 0;
    }

    @Override
    public void act(NPC npc) {
        System.out.println("Acting");
        switch(counter) {
            case 0:
                npc.move(1,0);
                break;
            case 1:
                npc.move(0,1);
                break;
            case 2:
                npc.move(-1,0);
                break;
            case 3:
                npc.move(0,-1);
                break;
            default:
                System.err.println("oh shit");
        }
        npc.rotate(true);
        counter = (counter+1)%4;
    }
}
