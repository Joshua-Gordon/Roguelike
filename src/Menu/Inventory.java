package Menu;

import Control.Button;
import Control.Clickable;
import Entity.Item.Equipment;
import Entity.Item.Item;
import Environment.Tile;
import Environment.TilesStatic;
import Graphics.Render;
import Test.Game;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Inventory extends Menu {

    public LinkedList<Item> items;
    double carryCapacity;
    boolean usingItem;
    int numRows, numCols;

    Tile[][] boxes;

    LinkedList<Clickable> clickables;

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

    Consumer<BufferedImage> renderInv = bi->{
        Graphics g = bi.getGraphics();
        g.setColor(Color.WHITE);
        g.clearRect(0,0,Game.WIDTH,80);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,100,80);
        g.fillRect(120,0,100,80);
        g.fillRect(240,0,100,80);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,36));
        g.drawString("Exit",0,80);
        g.drawString("Use Item",120,80);
        g.drawString("Equipment",240,80);
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

    public void addItem(Item i) {
        Tile nextBox = boxes[items.size()%numCols][items.size()/numRows];
        items.add(i);
        clickables.add(i);
        i.moveTo(nextBox);
    }

    public void removeItem(Item i) {
        items.remove(i);
        clickables.remove(i);
    }

    void init(LinkedList<Clickable> c) {
        c.add(new Button("Exit",0,0,100,80,e->Game.normal()));
        c.add(new Button("Use Item",120,0,100,80,e->usingItem=true));
        c.add(new Button("Equip",240,0,100,80,e->Game.equip()));
    }

    public boolean isUsingItem(){
        return usingItem;
    }

    public void stopUsingItem() {usingItem = false;}

    public void equipItem(Equipment e) {
        removeItem(e);
        Equipment old = Game.p.equip.insert(e);
        if(old != null)
            addItem(old);
    }

    @Override
    public LinkedList<Clickable> clickables() {
        return clickables;
    }

}
