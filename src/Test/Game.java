package Test;

import Entity.Entity;
import Entity.Item.Item;
import Entity.Item.ItemLoader;
import Entity.Player;
import Environment.Map;
import Environment.Screen;
import Graphics.InfoBoxRenderer;
import Graphics.Renderer;
import Graphics.Window;
import Menu.Gameplay;
import Menu.Inventory;
import Menu.Pause;
import Sprites.Sprite;

import java.io.IOException;
import java.util.LinkedList;

public class Game {
    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;

    public static Window w;

    private static Renderer renderer;
    private static InfoBoxRenderer ibr;

    public static int STATE=0;
    //0 for normal
    //1 for paused
    //2 for inventory

    public static Player p;
    private static Gameplay gp;
    private static Inventory i;

    public static Screen currentScreen;
    public static Map map;
    public static int screenX,screenY;

    private static LinkedList<Entity> entities;

    public Game() {
        renderer = new Renderer();

        ibr = renderer.getInfoBox();

        w = new Window();
        w.init();

        entities = new LinkedList<>();


        map = Map.parseMap(Sprite.loadSprite("res//map1.png").getBi());
        Screen s = map.screens[0][0];
        currentScreen = s;

        p = new Player();

        i = new Inventory(50);

        renderer.insert(p);
        entities.add(p);

        screenX = screenY = 0;
        renderer.setScreen(s);
        gp = new Gameplay();
        renderer.setMenu(gp);

    }

    public void start() {

        try {
            LinkedList<Item> items = ItemLoader.loadItems("res//items.txt");
            items.forEach(Inventory::addItem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            renderer.render();
            renderer.update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void pause() {
        renderer.setMenu(new Pause());
        STATE = 1;
    }

    public static void normal() {
        renderer.setMenu(gp);
    }

    public static void inventory() {
        renderer.setMenu(i);
    }

    public static void addText(String text) {
        while(text.length() >= 20) {
            String temp = text.substring(0,20);
            ibr.addText(temp);
            text = text.substring(20);
        }
        if(text.length() > 0)
            ibr.addText(text);
    }

    public static Renderer getRenderer() {
        return renderer;
    }

    public static LinkedList<Entity> getEntities() {
        return entities;
    }

}
