package Control;

import java.awt.*;
import java.util.function.Consumer;

public class Button implements Clickable{

    String text;
    int x,y,w,h;
    Consumer<Integer> onClick;

    public Button(String text, int x, int y, int w, int h, Consumer<Integer> onClick) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.onClick = onClick;
    }

    public Button(String text,double x, double y, double w, double h, Consumer<Integer> onClick) {
        this.text = text;
        this.x = (int)x;
        this.y = (int)y;
        this.w = (int)w;
        this.h = (int)h;
        this.onClick = onClick;
    }

    @Override
    public Consumer<Integer> onClick() {
        return onClick;
    }

    @Override
    public Rectangle clickBox() {
        return new Rectangle(x,y,w,h);
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
