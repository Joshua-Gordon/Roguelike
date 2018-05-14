package Menu;

import Control.Button;
import Control.Clickable;
import Entity.Item.Item;
import Environment.Tile;
import Environment.TilesStatic;
import Graphics.Render;
import Test.Game;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Inventory extends Menu { //Singleton

    public static LinkedList<Item> items;
    static double carryCapacity;
    static boolean usingItem;
    static int numRows, numCols;

    static Tile[][] boxes;

    static LinkedList<Clickable> clickables;

    public Inventory(double carryCapacity) {
        super();
        super.state=2;
        items = new LinkedList<>();
        this.carryCapacity = carryCapacity;
        usingItem = false;

        numRows = (int)(Game.HEIGHT*.7 / 48); //48x48 pixel windows
        numCols = (int) (Game.WIDTH*.7 / 48);

        boxes = new Tile[numCols][numRows];
        for(int y = 0; y < numRows; ++y) {
            for (int x = 0; x < numCols; ++x) {
                boxes[x][y] = TilesStatic.loadTileExplicitLocation("box",(int)(Game.WIDTH*.2)+x*32,(int)(Game.HEIGHT*.2)+y*32);
            }
        }

        super.renderFunction = renderInv;
        clickables = new LinkedList<>();
        init(clickables);
    }

    static Consumer<BufferedImage> renderInv = bi->{
        Graphics g = bi.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,100,80);
        g.fillRect(120,0,100,80);
        g.fillRect(240,0,100,80);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,48));
        g.drawString("Exit",0,80);
        g.drawString("Use Item",120,80);
        for(int y = 0; y < numRows; ++y) {
            for (int x = 0; x < numCols; ++x) {
                Render box = boxes[x][y].render();
                g.drawImage(box.image,box.x,box.y,null);
            }
        }
        for(Item i : items) {
            Render r = i.render();
            g.drawImage(r.image,r.x,r.y,null);
        }

        g.drawImage(Game.getRenderer().getInfoBox().render(bi),0,0,null);
    };

    public static void addItem(Item i) {

        Tile nextBox = boxes[items.size()%numCols][items.size()/numRows];
        items.add(i);
        clickables.add(i);
        i.moveTo(nextBox);
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
