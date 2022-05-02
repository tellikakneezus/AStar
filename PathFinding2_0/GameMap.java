
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import javax.imageio.ImageIO;
import java.io.File;

import java.util.Random;

import java.io.IOException;

public class GameMap extends JPanel
{
    private BufferedImage open_tile;
    private BufferedImage closed_tile;
    private BufferedImage current_tile;
    private BufferedImage past_tile;
    private BufferedImage goal_tile;
    private BufferedImage[][] mapGUI;

    private int mapSize;
    private int startX;
    private int startY;
    private int goalX;
    private int goalY;
    
    public Tile[][] mapTiles;
    
    

    private Random rand = new Random();

    public GameMap(String openTileFP, String closedTileFP, String currentTileFP, String pastTileFP, String goalTileFP, int size, int obs, int sX, int sY, int gX, int gY){
        try{
            open_tile = ImageIO.read(new File(openTileFP));
            closed_tile = ImageIO.read(new File(closedTileFP));
            current_tile = ImageIO.read(new File(currentTileFP));
            past_tile = ImageIO.read(new File(pastTileFP));
            goal_tile = ImageIO.read(new File(goalTileFP));
        }catch(IOException ex){
            System.out.println("File not found");
        }

        mapSize = size;
        initMap(sX, sY, gX, gY);
        createMap(obs);
        //mapGUI = new BufferedImage[mapSize][mapSize];

        repaint();
    }
    
    private void initMap(int sX, int sY, int gX, int gY){
        
        mapTiles = new Tile[mapSize][mapSize];
        
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                mapTiles[i][j] = new Tile(i,j,0,sX,sY,gX,gY);
                if(i == sX && j == sY){
                    mapTiles[i][j].type = 2;
                }
                else if(i == gX && j == gY){
                    mapTiles[i][j].type = 4;
                }
                
            }
            
        }
    }

    private void createMap(int obs){
        int n = obs;

        if(n < (mapSize * mapSize)){
            while(n > 0)
            {
                int x = rand.nextInt(mapSize);
                int y = rand.nextInt(mapSize);

                if(mapTiles[x][y].type < 1){
                    mapTiles[x][y].type = 1;
                    n--;
                }
            }
        }else{
            for(int x = 0; x < mapSize; x++){
                for(int y = 0; y < mapSize; y++){
                    mapTiles[x][y].type = 1;
                }
            }
        }
    }
    
    
    public void setTileType(int x, int y, int type){
        mapTiles[x][y].type = type;
    }

    public void mapChanged(){
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int offset = 32;
        for(int x = 0; x < mapSize; x++){
            for(int y = 0; y < mapSize; y++){
                switch(mapTiles[x][y].type){
                    case 0: g.drawImage(open_tile,x*offset,y*offset,null);
                    break;
                    case 1: g.drawImage(closed_tile,x*offset,y*offset,null);
                    break;
                    case 2: g.drawImage(current_tile,x*offset,y*offset,null);
                    break;
                    case 3: g.drawImage(past_tile,x*offset,y*offset,null);
                    break;
                    case 4: g.drawImage(goal_tile,x*offset,y*offset,null);
                    break;

                }

            }
        }
    }
}