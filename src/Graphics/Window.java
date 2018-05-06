package Graphics;

import Control.Keyboard;
import Control.Mouse;
import Test.Game;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Window {

    public static Mouse mouse;
    public static Keyboard keyboard;

    JFrame frame;
    JLabel imSorryThomas;

    public Window() {
        frame = new JFrame("I like Rogues");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setSize(Game.WIDTH,Game.HEIGHT);
        imSorryThomas = new JLabel();
        frame.add(imSorryThomas);
    }

    public void update(BufferedImage bi) {
        imSorryThomas.setIcon(new ImageIcon(bi));
    }

    public void init() {
        mouse = new Mouse();
        keyboard = new Keyboard();
        frame.addKeyListener(keyboard);
        frame.addMouseListener(mouse);
        frame.setVisible(true);
    }

}
