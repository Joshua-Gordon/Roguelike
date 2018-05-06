package Entity;

import Control.Clickable;
import Environment.Collidable;
import Environment.Screen;
import Environment.Tile;
import Graphics.GameObject;
import Graphics.Render;
import Test.Game;

public abstract class Entity implements GameObject, Clickable {
    int x, y;

    @Override
    public int X() {
        return x;
    }

    @Override
    public int Y() {
        return y;
    }

    @Override
    public int layer() {
        return 2;
    }

    public void move(int dx, int dy) {
        x+=dx;y+=dy;
        /*if(x+dx<0){
            x+=dx;
        }
        if(y+dy<0){
            y+=dy;
        }
        if(!checkOccupied((x+dx)/32,(y+dy)/32)) {
            try {
                Game.currentScreen.getTiles()[x/32][y/32].setEntity(null);
                x += dx;
                y += dy;
                Game.currentScreen.getTiles()[x / 32][y / 32].setEntity(this);
            } catch(ArrayIndexOutOfBoundsException e) {
                try {
                    if (dx < 0) {
                        Game.getRenderer().setScreen(Game.map.screens[--Game.screenX][Game.screenY]);
                        x += 47 * 32;
                    } else if (dx > 0) {
                        Game.getRenderer().setScreen(Game.map.screens[++Game.screenX][Game.screenY]);
                        x -= 47 * 32;
                    } else if (dy < 0) {
                        Game.getRenderer().setScreen(Game.map.screens[Game.screenX][--Game.screenY]);
                        y += 33 * 32;
                    } else if (dy > 0) {
                        Game.getRenderer().setScreen(Game.map.screens[Game.screenX][++Game.screenY]);
                        y -= 33 * 32;
                    }
                } catch(ArrayIndexOutOfBoundsException a){}
            }
        } else {
//            Collidable c = (Collidable)Game.currentScreen.getTiles()[(x+dx)/32][(y+dy)/32].getEntity();
//            c.onCollide().accept(this);
        }*/
    }

    private boolean checkOccupied(int xCoord, int yCoord) {
        if(xCoord < 0 || yCoord < 0 || xCoord >= 48 || yCoord >= 33) {
            return false;
        }
        return !(Game.currentScreen.getTiles()[xCoord][yCoord].getEntity() instanceof Collidable);
    }
}
