/* Jaewon Chang, CSE 143,
   Spring 2014, Section AI
   T.A: HARTONO,DIAN AULIA
   Programming Assignment #1, 10th/April/2014
   
   This program's behavior is to work with other program and gives
   brief demonstration or insight on how multiple programs in the
   window operate.*/
   
import java.awt.*;
import java.util.*;

public class TileManager {
   private ArrayList<Tile> list;
   private Random rand;
   
 /* Post: Initializes a instance of the
random and ArrayList class. */
 public TileManager() {
      list = new ArrayList<Tile>();
      rand = new Random();
   }
   
   /* Pre: Tile rect shouldn't be null.
      Post: The parameter Tile is added to the list.*/
   public void addTile(Tile rect) {
      list.add(rect);
   }
   
   /* Pre: Tile object already has a draw method.
      Post: The parameter interact with this class to draw Tiles. */
   public void drawAll(Graphics g) {
      for (int i = 0; i < list.size(); i++) {
         list.get(i).draw(g);
      }
   }
   
 /* Pre: x && y > 0 and it shouldn't be the white
background.
      Parameter x && y is the coordinate user clicked.   
 Post: This method raises the tile to end of the list within the
bound.*/
   public void raise(int x, int y) {
      int place = indexFinder(x, y);
      if (place >= 0) {
         list.add(list.remove(place));
      }
   }
   
 /* Pre: x && y > 0 and it shouldn't be the white
background.
 Post: This method moves the tile to bottom of the list within the
bound.
      Parameter x && y is the coordinate user clicked.*/
   public void lower(int x, int y) {
      int place = indexFinder(x, y);
      if (place >= 0) {
         list.add(0, list.remove(place));
      }
   }
   
 /* Pre: x && y > 0 and it shouldn't be the white
background.
      Parameter x && y is the coordinate user clicked.
 Post: This method deletes topmost of the tile within the bound.*/
   public void delete(int x, int y) {
      int place = indexFinder(x, y);
      if (place >= 0) {
         list.remove(place);
      }
   }
   
 /* Pre: x && y > 0 and it shouldn't be the white
background.
      Parameter x && y is the coordinate user clicked.
 Post: This method deletes all the tile in the list that is within the
bound.*/
 public void deleteAll(int x, int y) {
  while (indexFinder(x, y)
>= 0) {
         list.remove(indexFinder(x, y));
      }
   }
   
 /* Pre: Parameter width & height of tile should be bigger than
size of tile.
      The parameter width and height is the size of the panel.
 Post: This method shuffles all Tile in the drawing panel randomly. */
   public void shuffle(int width, int height) {
      Collections.shuffle(list);
      for (int i = list.size() - 1; i >= 0; i--) {
 list.get(i).setX(rand.nextInt(width - list.get(i).getWidth()));
 list.get(i).setY(rand.nextInt(height - list.get(i).getHeight()));
      }
   }
   
   /* Pre: x && y > 0 and i >= 0.
 Parameter x and y is the user's mouse coordinate and parameter i is
index of list.
 Post: This method check whether user's mouse coordinate is within the
bound of max
      and max of the tiles. */
   private boolean inBound(int x, int y, int i) {
 return (x >= list.get(i).getX() && x <
list.get(i).getX() + list.get(i).getWidth())
 && (y >= list.get(i).getY() && y <
list.get(i).getY() + list.get(i).getHeight());
   }
   
   /* Pre: x && y > 0.
      Parameter x and y is the user's mouse coordinate.
      Post: This method returns the index of the list. */
   private int indexFinder(int x, int y) {
      for (int index = list.size() - 1; index >= 0; index--) {
         if (inBound(x, y, index)) {
            return index;
         }
      }
      return -1;      
   }
}
