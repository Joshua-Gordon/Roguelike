package Control;

import Test.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("Key: " + e.getKeyChar());

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //System.out.println("Escape");
            if(Game.STATE == 0) {
                Game.pause();
            } else if(Game.STATE == 1) {
                Game.normal();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_I) {
            if(Game.STATE == 0) {
                Game.inventory();
            } else if(Game.STATE==2) {
                Game.normal();
            }
        }

        if(Game.STATE==0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    Game.p.move(0, -32);
                    break;
                case KeyEvent.VK_A:
                    Game.p.move(-32, 0);
                    break;
                case KeyEvent.VK_S:
                    Game.p.move(0, 32);
                    break;
                case KeyEvent.VK_D:
                    Game.p.move(32, 0);
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
