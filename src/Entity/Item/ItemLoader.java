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
        switch(itemtype) {
            case "material":
                String name = text.pop().substring(6);
                String desc = text.pop().substring(6);
                double weight = Double.parseDouble(text.pop().substring(8));
                Sprite sprite = Sprite.loadSprite("res//"+text.pop().substring(8)+".png");
                return new Material(name,desc,weight,sprite);
            case "weapon":
                //not implemented
                break;
            case "equipment":
                //not implemented
                break;
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
