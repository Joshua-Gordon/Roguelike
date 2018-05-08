package Menu;

import Control.Clickable;
import Environment.Tile;
import Graphics.Window;
import Test.Game;

import java.util.Arrays;
import java.util.LinkedList;

public class Gameplay extends Menu{

    LinkedList<Clickable> clickables;

    public Gameplay() {
        super();
        super.renderFunction = Game.getRenderer().normalBehaviour;
        clickables = new LinkedList<>();
        clickables.addAll(Game.getEntities());
        for(Tile[] ts : Game.currentScreen.getTiles()) {
            clickables.addAll(Arrays.asList(ts));
        }
        super.state = 0;
    }

    @Override
    public LinkedList<Clickable> clickables() {
        return clickables;
    }
}
