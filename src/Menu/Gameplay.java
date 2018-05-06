package Menu;

import Control.Clickable;
import Test.Game;

import java.util.LinkedList;

public class Gameplay extends Menu{

    LinkedList<Clickable> clickables;

    public Gameplay() {
        super();
        super.renderFunction = Game.getRenderer().normalBehaviour;
        clickables = new LinkedList<>();
        clickables.addAll(Game.getEntities());
        super.state = 0;
    }

    @Override
    public LinkedList<Clickable> clickables() {
        return clickables;
    }
}
