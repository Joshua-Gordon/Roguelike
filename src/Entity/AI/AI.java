package Entity.AI;

import Entity.NPC;

public class AI {

    int counter;

    public AI() {
        counter = 0;
    }

    public void act(NPC npc) {
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
