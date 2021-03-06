package Menu;

import Control.Button;
import Control.Clickable;
import Entity.Item.Equipment;
import Entity.Item.Equipment.BodyPart;
import Entity.Item.Weapon;
import Environment.Tile;
import Environment.TilesStatic;
import Graphics.Render;
import Test.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.function.Consumer;

public class EquipMenu extends Menu {

    public EnumMap<BodyPart,Equipment> equipment;

    private EnumMap<BodyPart,Tile> slots;

    private LinkedList<Clickable> clickables;

    private Weapon w;
    private Tile weaponSlot;

    public EquipMenu() {
        super();
        equipment = new EnumMap<>(BodyPart.class);
        w = Weapon.unarmed();
        clickables = new LinkedList<>();
        super.state = 3;
        super.renderFunction = renderEquipment;

        slots = new EnumMap<>(BodyPart.class);

        slots.put(BodyPart.HEAD,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2,Game.HEIGHT/8));                //head
        slots.put(BodyPart.CHEST,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2,Game.HEIGHT/8 + 50));           //chest
        slots.put(BodyPart.ARM_L,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 - 40,Game.HEIGHT/8 + 60));      //armL
        slots.put(BodyPart.ARM_R,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 + 40,Game.HEIGHT/8 + 60));      //armR
        slots.put(BodyPart.HAND_L,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 - 80,Game.HEIGHT/8 + 70));      //handL
        slots.put(BodyPart.HAND_R,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 + 80,Game.HEIGHT/8 + 70));      //handR
        slots.put(BodyPart.FINGER_L,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 - 120,Game.HEIGHT/8 + 90));     //fingerL
        slots.put(BodyPart.FINGER_R,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 + 120,Game.HEIGHT/8 + 90));     //fingerR
        slots.put(BodyPart.LEG_L,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 - 40,Game.HEIGHT/8 + 140));     //legL
        slots.put(BodyPart.LEG_R,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 + 40,Game.HEIGHT/8 + 140));     //legR
        slots.put(BodyPart.FOOT_L,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 - 50,Game.HEIGHT/8 + 180));     //footL
        slots.put(BodyPart.FOOT_R,TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 + 50,Game.HEIGHT/8 + 180));     //footR

        weaponSlot = TilesStatic.loadTileExplicitLocation("box",Game.WIDTH/2 + 100,Game.HEIGHT/8 + 180);
    }

    public Consumer<BufferedImage> renderEquipment = bi->{
        Graphics g = bi.getGraphics();
        g.setColor(Color.WHITE);
        g.clearRect(0,0,Game.WIDTH,80);
        g.setColor(Color.BLUE);
        g.fillRect(Game.WIDTH/2 - 140,Game.HEIGHT/8-20,280,220);
        //Add buttons
        g.fillRect(0,0,100,80);
        g.fillRect(120,0,100,80);
        g.setColor(Color.BLACK);
        //Label buttons
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,36));
        g.drawString("Exit",0,80);
        g.drawString("Inventory",120,80);
        //Draw slots
        for(Tile t : slots.values()) {
            Render r = t.render();
            g.drawImage(r.image,r.x,r.y,null);
        }
        Render r = weaponSlot.render();
        g.drawImage(r.image,r.x,r.y,null);
        //Draw equipment
        for(Equipment e : equipment.values()) {
            r = e.render();
            g.drawImage(r.image,r.x,r.y,null);
        }
        //Draw weapon
        r = w.render();
        g.drawImage(r.image,r.x,r.y,null);
    };

    @Override
    public LinkedList<Clickable> clickables() {
        clickables.addAll(equipment.values());
        clickables.add(new Button("Exit",0,0,100,80,e->Game.normal()));
        clickables.add(new Button("Inventory",120,0,100,80,e->Game.inventory()));
        return clickables;
    }

    public Weapon getWeapon() {
        return w;
    }

    public Equipment insert(Equipment e) {
        Equipment old = equipment.put(e.getType(),e);
        e.moveTo(slots.get(e.getType()));
        Game.p.getStats().addEquipmentStats(e);
        return old;
    }

    public void dequip(Equipment e) {
        equipment.values().remove(e);
        Game.p.inv.addItem(e);
        Game.p.getStats().removeEquipmentStats(e);
    }

    public void equipWeapon(Weapon wNew) {
        if(w.equals(wNew)){
            return;
        }
        dequipWeapon();
        w = wNew;
        w.moveTo(weaponSlot);
        System.out.println("Equipped the " + w.getName());
    }


    public void dequipWeapon() {
        if(!w.isUnarmed()){
            Game.p.inv.addItem(w);
            System.out.println("Dequipped the " + w.getName());
            w = Weapon.unarmed();
        }
    }
}
