import java.applet.*;
import java.awt.Graphics;
import java.awt.Color;

public class World {
	public static final int WORLDSIZE = Grid.GRIDSIZE;
	public static final int WORLDWIDTH = Grid.GRIDWIDTH;
	public static final int WORLDHEIGHT = Grid.GRIDHEIGHT;
	

	protected Cell[][] grid;


	public World()	{
	   grid = new Cell[WORLDHEIGHT][WORLDWIDTH];
	}

	public Cell getAt(int x, int y)	{
		if ((x >= 0) && (x < WORLDWIDTH) && 
		    (y >= 0) && (y < WORLDHEIGHT)) {
			return grid[y][x];
		}
		
		// return null for out of bounds checks
		return null;
	}

	/** Puts the Organism on the grid at x,y.
	 * @param x - X grid coordinate 
	 * @param y - Y grid coordinate 
	 * @param org (Organism) - to place on the grid
	 */
	public void setAt(int x, int y, Cell cell) {
	   if ((x >= 0) && (x < WORLDWIDTH) && 
         (y >= 0) && (y < WORLDHEIGHT))	{
	      grid[y][x] = cell;
	   }
	}

	/** Displays the world grid in ASCII.text with Organisms denoted by
	 *  their display character, getPrintableChar()
	 */
	public void displayWorld(Graphics g) {
	   for (int y = 0; y < WORLDHEIGHT; y++)	{
	      for (int x = 0; x < WORLDWIDTH; x++)	{
	         if (grid[y][x]==null) { 
	        	 g.setColor(Color.lightGray);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof Square){
	        	 g.setColor(Color.yellow);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof Straight){
	        	 g.setColor(Color.cyan);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof SkewA){
	        	 g.setColor(Color.green);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof SkewB){
	        	 g.setColor(Color.red);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof LshapeA){
	        	 g.setColor(Color.orange);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof LshapeB){
	        	 g.setColor(Color.blue);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof Tshape){
	        	 g.setColor(Color.magenta);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         } else if (grid[y][x] instanceof Dot){
	        	 g.setColor(Color.black);
	        	 g.fillRect(Grid.grid[y][x][0], Grid.grid[y][x][1], Grid.SQUARE, Grid.SQUARE);
	         }
	         
	      }
	   }
	}
	


	
}