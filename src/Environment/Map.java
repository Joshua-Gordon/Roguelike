package Environment;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {

    public Screen[][] screens;

    public Map(Screen[][] screens) {
        this.screens = screens;
    }

    public static Map proceduralMap() {
        return null; //Not implemented
    }

    public static Map parseMap(BufferedImage mapImage) {

        int width = mapImage.getWidth();
        int height = mapImage.getHeight();
        int numScreensX = width/48;
        int numScreensY = height/33;

        Screen[][] parsed = new Screen[numScreensX][numScreensY];

        int count = 0;
        int total = numScreensX*numScreensY;

        for(int xScreen = 0; xScreen < numScreensX; ++xScreen) {
            for(int yScreen = 0; yScreen < numScreensY; ++yScreen) {

                Tile[][] tiles = new Tile[48][33];

                for(int x = 0; x < 48; ++x) {
                    for(int y = 0; y < 33; ++y) {
                        int pixel = -1;
                        try{
                            pixel = (mapImage.getRGB(x+xScreen*48,y+yScreen*33) << 8)>>>8;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("X:" + x + ", xScreen: " + xScreen + ", Total: " + (x+xScreen*48));
                            System.out.println("Y:" + y + ", yScreen: " + yScreen + ", Total: " + (y+yScreen*33));

                        }
                        switch(pixel){
                            //Draw more tiles.
                            //Put color codes here to set the value of tiles[x][y]
                            case 5680950: //RGB 86 175 54
                                tiles[x][y] = TilesStatic.grass(x,y);
                                break;
                            case 8142848: //RGB 124 64 0
                                tiles[x][y] = TilesStatic.loadTile("dirt",x,y);
                                break;
                            case 7732479: //RGB 117 252 255
                                tiles[x][y] = TilesStatic.loadTile("water",x,y);
                                break;
                            case 12566463: //RGB 191 191 191
                                tiles[x][y] = TilesStatic.loadTile("stone",x,y);
                                break;
                            case 0:
                                tiles[x][y] = TilesStatic.loadTile("wall",x,y);
                                break;
                            default:
                                System.err.println("Unrecognized pixel: " + pixel);
                        }
                    }
                }

                parsed[xScreen][yScreen] = new Screen(tiles);
                count++;
                System.out.println("Screen finished. " + 100*((double)count)/((double)total) + "% done.");
            }
        }

        return new Map(parsed);
    }

}
