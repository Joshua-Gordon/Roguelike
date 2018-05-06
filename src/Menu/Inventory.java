package Menu;

import Control.Button;
import Control.Clickable;
import Entity.Item.Item;
import Test.Game;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Inventory extends Menu { //Singleton

    static LinkedList<Item> items;
    static double carryCapacity;
    static boolean usingItem;
    static int numRows, numCols;

    static LinkedList<Clickable> clickables;

    public Inventory(double carryCapacity) {
        super();
        super.state=2;
        items = new LinkedList<>();
        this.carryCapacity = carryCapacity;
        usingItem = false;

        numRows = (int)(Game.HEIGHT*.7 / 48); //48x48 pixel windows
        numCols = (int) (Game.WIDTH*.7 / 48);

        super.renderFunction = render;
        clickables = new LinkedList<>();
        init(clickables);
    }

    static Consumer<BufferedImage> render = bi->{
        Graphics g = bi.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,100,80);
        g.fillRect(120,0,100,80);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        g.setColor(Color.BLACK);
        g.drawString("Exit",0,0); //TODO: Make this work
        g.drawString("Use Item",120,0);
        g.setColor(Color.BLUE);
        g.fillRect((int)(Game.WIDTH*.1),(int)(Game.HEIGHT*.1),
                (int)(Game.WIDTH*.7),(int)(Game.HEIGHT*.8));
        int i = 0;
        for(int y = 0; y < numRows; ++y) {
            for(int x = 0; x < numCols; ++x) {

                int xCoord = (int)(Game.WIDTH*.1)+x*48;
                int yCoord = (int)(Game.HEIGHT*.1)+y*48;

                g.setColor(Color.BLACK);
                g.drawRect(xCoord,yCoord,48,48);
                if(i < items.size()) {
                    g.drawImage(items.get(i).render().image, xCoord + 8, yCoord + 8, null);
                    items.get(i++).setPosition(xCoord + 8, yCoord + 8);
                }

            }
        }

        g.drawImage(Game.getRenderer().getInfoBox().render(bi),0,0,null);
    };

    public static void addItem(Item i) {
        items.add(i);
        clickables.add(i);
    }

    public static void removeItem(Item i) {
        items.remove(i);
    }

    static void init(LinkedList<Clickable> c) {
        c.add(new Button("Exit",0,0,100,80,e->Game.normal()));
        c.add(new Button("Use Item",120,0,100,80,e->usingItem=true));
    }

    public static boolean isUsingItem(){
        return usingItem;
    }

    public static void stopUsingItem() {usingItem = false;}

    @Override
    public LinkedList<Clickable> clickables() {
        return clickables;
    }

}
