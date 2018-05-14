package Menu;

import Control.Clickable;
import Entity.Item.Equipment;
import Entity.Item.Equipment.BodyPart;
import Test.Game;

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.function.Consumer;

public class EquipMenu extends Menu { //Singleton

    public static EnumMap<BodyPart,Equipment> equipment;

    public EquipMenu() {
        super();
        equipment = new EnumMap<>(BodyPart.class);
        super.state = 3;
        Game.STATE = 3;
    }

    static Consumer<BufferedImage> renderEquipment = bi->{

    };

    @Override
    public LinkedList<Clickable> clickables() {
        return new LinkedList<>(equipment.values());
    }

    public static Equipment insert(Equipment e) {
        return equipment.put(e.getType(),e);
    }
}
