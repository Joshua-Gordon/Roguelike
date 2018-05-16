package Entity.Item;

import Sprites.Sprite;

import java.io.*;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemLoader {

    public static LinkedList<Item> loadItems(String filePath) throws IOException {
        File f = new File(filePath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        LinkedList<String> text = br.lines().collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Item> items = new LinkedList<>();
        while(!text.isEmpty()) {
            items.add(loadItem(text));
        }

        return items;
    }

    private static Item loadItem(LinkedList<String> text) {
        String itemtype = text.pop();
        String name, desc;
        double weight;
        Sprite sprite;
        int amount;
        switch(itemtype) {
            case "material":
                name = text.pop().substring(6);
                desc = text.pop().substring(6);
                weight = Double.parseDouble(text.pop().substring(8));
                sprite = Sprite.loadSprite("res//"+text.pop().substring(8)+".png");
                amount = Integer.parseInt(text.pop().substring(8));
                return new Material(name,desc,weight,sprite,amount);
            case "weapon":
                //not implemented
                break;
            case "equipment":
                name = text.pop().substring(6);
                desc = text.pop().substring(6);
                weight = Double.parseDouble(text.pop().substring(8));
                sprite = Sprite.loadSprite("res//"+text.pop().substring(8)+".png");
                Equipment.BodyPart type = Equipment.BodyPart.valueOf(text.pop().substring(6));
                String stat = text.pop().substring(7);
                String[] stats = stat.split(" ");
                int attack = Integer.parseInt(stats[0]);
                int defense = Integer.parseInt(stats[1]);
                int speed = Integer.parseInt(stats[2]);
                int processing = Integer.parseInt(stats[3]);
                amount = Integer.parseInt(text.pop().substring(8));
                return new Equipment(name,desc,weight,sprite,amount,type,attack,defense,speed,processing);
            case "consumable":
                //not implemented
                break;
            case "tool":
                //not implemented
                break;
            default:
                System.err.println("Unrecognized item type");
        }
        return null;
    }

}
