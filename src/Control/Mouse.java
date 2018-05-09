package Control;

import Menu.Inventory;
import Test.Game;
import Test.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class Mouse implements MouseListener {

    public LinkedList<Clickable> clickables;

    public Mouse() {
        clickables = new LinkedList<>();
    }

    public void addClickable(Clickable c) {
        clickables.add(c);
    }

    public void removeClickable(Clickable c) {
        clickables.remove(c);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(e.getLocationOnScreen());
        for(Clickable c : clickables) {
            if(c.clickBox().contains(e.getLocationOnScreen())) {
                if(Game.STATE == 2) {
                    System.out.println("Inventory!");
                    if(e.getY() < 80 || Inventory.isUsingItem()) {
                        c.onClick().accept(e.getButton());
                        //Inventory.stopUsingItem();
                    }
                } else {
                    c.onClick().accept(e.getButton());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
