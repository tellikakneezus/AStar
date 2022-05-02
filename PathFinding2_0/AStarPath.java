
/**
 * Write a description of class AStarPath here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class AStarPath
{

    private Tile[][] gameMap;
    /**
     * Constructor for objects of class AStarPath
     */
    public AStarPath(Tile[][] gMap)
    {
        gameMap = gMap;
    }

    public ArrayList<Tile> findPath(int sX, int sY, int gX, int gY)
    {
        ArrayList<Tile> path = new ArrayList<Tile>();

        Tile currentTile = gameMap[sX][sY];

        ArrayList<Tile> open = new ArrayList<Tile>();
        ArrayList<Tile> closed = new ArrayList<Tile>();
        if(gameMap != null){
            open.add(currentTile); //add start tile to open list

            while(open.size() > 0){ //while open has elements
                int f_Cost = -1; //value for f_Cost when at current element
                for(Tile c: open){
                    if(f_Cost == -1){
                        f_Cost = c.calculateF(gX, gY);
                    }

                    int currF_Cost = c.calculateF(gX, gY);

                    if(currF_Cost <= f_Cost){
                        f_Cost = currF_Cost;
                        //c.parent = currentTile;
                        currentTile = c;
                    }
                }

                open.remove(currentTile);
                closed.add(currentTile);

                ArrayList<Tile> currentNeighbors = getAdjacentTiles(currentTile.getX(), currentTile.getY());
                for(Tile c: currentNeighbors){

                    if(!closed.contains(c)){

                        if(!open.contains(c)){
                            if(c.type == 0 || c.type == 4){
                                open.add(c);
                                c.parent = currentTile;

                                if(c == gameMap[gX][gY]){
                                    path = constructPath(c);
                                    return path;                        
                                }

                            }
                        }
                    }
                }

            }
        }

        return path;
    }

    private ArrayList<Tile> constructPath(Tile node){
        ArrayList<Tile> path = new ArrayList<Tile>();
        path.add(node);
        while(node.parent != null){
            node = node.parent;
            path.add(node);
        }

        return path;
    }

    private ArrayList<Tile> getAdjacentTiles(int x, int y)
    {
        ArrayList<Tile> adjacentTiles = new ArrayList<Tile>();

        for(int i = -1; i < 2; i++){
            for(int j = 1; j > -2; j--){
                if(i == 0 && j == 0){}
                else{
                    if((i + x > 0 && j + y > 0) && (i + x < gameMap.length && j + y < gameMap[i+x].length)){
                        if((gameMap[i + x][j + y].type == 0) || (gameMap[i + x][j + y].type == 4)){
                            adjacentTiles.add(gameMap[i + x][j + y]);
                        }
                    }
                }
            }

        }

        return adjacentTiles;
    }
}
