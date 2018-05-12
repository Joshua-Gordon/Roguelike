package Graphics;

import Entity.Player;
import Environment.Screen;
import Test.Game;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.function.Consumer;
import Menu.Menu;

import static Test.Game.w;

public class Renderer {

    BufferedImage renderImage;
    InfoBoxRenderer infoBox;
    public LinkedList<GameObject> gos;
    public Screen s;

    public Menu m;

    public Consumer<BufferedImage> normalBehaviour = e->{
        renderImage.getGraphics().clearRect(0,0,(int)(Game.WIDTH),Game.HEIGHT);
        renderImage.getGraphics().drawImage(s.render().image,0,0,null);
        for(GameObject go : gos) {
            Render r = go.render();
            if(!r.blank)
                renderImage.getGraphics().drawImage(r.image,r.x,r.y,null);
        }
        renderImage = infoBox.render(renderImage);
    };

    public Consumer renderFunction;

    public Renderer() {
        renderImage = new BufferedImage(Game.WIDTH,Game.HEIGHT,BufferedImage.TYPE_INT_RGB);
        renderImage.getGraphics().fillRect(0,0,Game.WIDTH,Game.HEIGHT);
        gos = new LinkedList<>();
        infoBox = new InfoBoxRenderer();
        renderFunction = normalBehaviour;
    }

    public void insert(GameObject goNew) {
        int idx = 0;
        for(int i = 0; i < gos.size(); ++i) {
            GameObject go = gos.get(i);
            if(go.layer() < goNew.layer()) idx=i;
        }
        gos.add(idx,goNew);
    }

    public void remove(GameObject old) {
        gos.remove(old);
    }

    public void render() {

        renderFunction.accept(renderImage);
        w.update(renderImage);

    }

    public void update() {
        for(GameObject go : gos) {
            go.update();
        }
    }

    public InfoBoxRenderer getInfoBox(){return infoBox;}

    public void setScreen(Screen ss) {
        Game.currentScreen = ss;
        this.s = ss;
    }

    public void setMenu(Menu menu) {
        this.m = menu;
        this.renderFunction = menu.renderFunction;
        Game.STATE = menu.state;
        Window.mouse.clickables = menu.clickables();
    }


}
