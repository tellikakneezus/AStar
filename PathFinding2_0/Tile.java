
/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.Math;

public class Tile implements Comparable
{
    // instance variables - replace the example below with your own
    private int xPos;
    private int yPos;
    public int type;
    
    public Tile parent;
    public int h;
    public int g;
    private int gX;
    private int gY;
    

    

    
    
    /**
     * Constructor for objects of class Tile
     */
    public Tile(int x, int y, int t, int startX, int startY, int goalX, int goalY)
    {
        xPos = x;
        yPos = y;
        type = t;
        gX = goalX;
        gY = goalY;
    }

    @Override
    public int compareTo(Object dos){
        Tile two = (Tile)dos;
        return this.calculateF(gX,gY) - two.calculateF(gX, gY);
    }
   
    
    public int getX(){
        return xPos;
        
    }
    
    public int getY(){
        return yPos;
    }

    public int calculateF(int gX, int gY){
        g = (Math.abs(gX - xPos) + Math.abs(gY - yPos)) * 10;
        h = 0;
        
        if(parent != null){
            if(parent.xPos == xPos || parent.yPos == yPos){
                h = parent.h + 10;
            }else{
                h = parent.h + 14;
            }
        }
        
        return g + h;
    }
    
   
}
