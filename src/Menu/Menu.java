package Menu;


import Control.Clickable;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.function.Consumer;

public abstract class Menu {

    public Consumer<BufferedImage> renderFunction;
    abstract public LinkedList<Clickable> clickables();
    public int state;

}
