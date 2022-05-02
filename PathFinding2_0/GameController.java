
/**
 * Write a description of class GameController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.*;


public class GameController extends JFrame
{
    public static void main(String[] args){
        JFrame gameFrame = new JFrame("Path Finding");
        JLayeredPane layeredPane = new JLayeredPane();
        
        int size = 25;
        int numberObs = 200;
        
        int startPosX = 20;
        int startPosY = 20;
        int goalPosX = 1;
        int goalPosY = 1;
        
        
        int widthOffset = 16;
        int heightOffset = 38;
        int tileSize = 32;
        int mapSize = size * tileSize;

        
        
        GameMap gameMap = new GameMap("blank_tile.gif","blocked_tile.gif","current_tile.gif", "past_tile.gif","goal_tile.gif", size, numberObs, startPosX, startPosY, goalPosX, goalPosY);
        AStarPath aStar = new AStarPath(gameMap.mapTiles);
        
        ArrayList<Tile> path = aStar.findPath(startPosX, startPosY, goalPosX, goalPosY);
        
        
        //layeredPane.setPreferredSize(new Dimension(mapSize + widthOffset, mapSize + heightOffset));
  
        gameFrame.add(gameMap);
        
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(mapSize + widthOffset, mapSize + heightOffset);
        gameFrame.setVisible(true);
        
        
        int n = path.size() - 1;
        while(n >= 0){
            Tile currTile = path.get(n);
            n--;
            currTile.type = 2;
            gameMap.mapChanged();
            
            try{
                
                Thread.sleep(400);
                
            }catch(InterruptedException ie){
                
            }
            
            currTile.type = 3;
        }
        
        
        
        
        
        
    }
}
