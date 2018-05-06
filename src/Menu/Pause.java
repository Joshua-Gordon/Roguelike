package Menu;

import Control.Button;
import Control.Clickable;
import Test.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Pause extends Menu{

    LinkedList<Clickable> clickables;
    Button b;

    public Pause(){
        super();
        super.state=1;
        super.renderFunction = paused;

        clickables = new LinkedList<>();
        b=new Button("Unpause",Game.WIDTH*.2,Game.HEIGHT*.7,140,80,e->{
            Game.normal();
        });
        clickables.add(b);

    }

    @Override
    public LinkedList<Clickable> clickables() {
        return clickables;
    }

    public Consumer<BufferedImage> paused = renderImage->{
        System.out.println("Paused!" + System.currentTimeMillis());
        Graphics g = renderImage.getGraphics();
        //renderImage.getGraphics().clearRect((int)(Game.WIDTH*.1),(int)(Game.HEIGHT*.1),
        //        (int)(Game.WIDTH*.8),(int)(Game.HEIGHT*.8));
        g.setColor(Color.GREEN);
        g.fillRect((int)(Game.WIDTH*.1),(int)(Game.HEIGHT*.1),
                (int)(Game.WIDTH*.8),(int)(Game.HEIGHT*.8));
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,45));
        g.drawString("PAUSE",(int)(Game.WIDTH*.4),(int)(Game.HEIGHT*.4));
        g.setColor(Color.RED);
        g.fillRect(b.getX(),b.getY(),b.getW(),b.getH());
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        g.drawString(b.getText(),b.getX()+10,b.getY()+10);
        System.out.println("Still Paused!" + System.currentTimeMillis());
    };



}