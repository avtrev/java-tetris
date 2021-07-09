import java.awt.Graphics;

public class Dot extends Cell {
	protected int moveX=0;
	protected int	moveY=9;
	private final int WIDTH=1;
	private final int HEIGHT=1;
	private int[][][] grid = Grid.grid;
	private int SQUARE = Grid.SQUARE;
	
	//constructors
	public Dot(World w) {
		w.setAt(moveX, moveY, this);
	}
	public Dot(Graphics g, World w) {
		g.clearRect(grid[moveX][moveY][0], grid[moveX][moveY][1], SQUARE, SQUARE);
		w.setAt(moveX, moveY, this);
	}
	public Dot(World w, int x, int y) {
		this.moveX = x;
		this.moveY = y;
		w.setAt(moveX, moveY, this);
	}
	public Dot(World w, int x, int y,boolean temp) {
		this.temp = temp;
		this.moveX = x;
		this.moveY = y;
		w.setAt(moveX, moveY, this);
	}
	public Dot(Graphics g, int x, int y) {
    	g.clearRect(grid[x][y][0], grid[x][y][1], SQUARE, SQUARE);
    }
	
	//methods
	
	public void render(Graphics g) {
		g.clearRect(grid[moveX][moveY][0], grid[moveX][moveY][1], SQUARE, SQUARE);
	}
	
	public void setNull(World w) { //set current position cells to null
			w.setAt(this.moveX, this.moveY, null);		
	}
	public void setPosition(World w) { // set current cells
			w.setAt(this.moveX, this.moveY, this);
	}
	
	public void move() { //from Cell Class abstract move() method
	}
	
	public void moveDot(World w, Dot dt) {
			boolean[] direction = {false,false,false,false};	
			
			if (this.moveY > 0 && this.boundary(w, "up")){ //up
				direction[0] = true;
			}
			if (this.moveY < GRIDHEIGHT - this.HEIGHT && this.boundary(w, "down")){ //down
				direction[1] = true;
			}
			if (this.moveX > 0 && this.boundary(w, "left")){ //left
				direction[2] = true;
			}
			if (this.moveX < GRIDWIDTH - this.WIDTH && this.boundary(w, "right")){ //right
				direction[3] = true;
			}
			
			//check if spaces are available
			boolean spacesAvailable = false;
			for(boolean i:direction) if(i) spacesAvailable = true;
			
			//if spaces are available
			if(spacesAvailable) {
				
				int available;
				//loop generate int 0-3 while direction[index] != true
				do {
					available = (int)(Math.random()*4);
				} while(!direction[available]);
				
				if(available == 0) {//DOWN
					w.setAt(this.moveX, this.moveY, null);
					this.moveY--;
					w.setAt(this.moveX, this.moveY, this);
				} else if (available == 1) {//DOWN
					w.setAt(this.moveX, this.moveY, null);
					this.moveY++;
	    			w.setAt(this.moveX, this.moveY, this);
				} else if (available == 2) {//LEFT
					w.setAt(this.moveX, this.moveY, null);
					this.moveX--;
	        		w.setAt(this.moveX, this.moveY, this);
				} else if (available == 3) {//RIGHT
					w.setAt(this.moveX, this.moveY, null);
					this.moveX++;
	            	w.setAt(this.moveX, this.moveY, this);
				}
			}
		}
	
	public boolean boundary(World w, String direction) {
		if(direction.equals("up")) {
			if(w.getAt(moveX, moveY-1) != null) {
				return false;
			}
			
		} else if(direction.equals("down")) {
			if(w.getAt(moveX, moveY+1) != null) {
				return false;
			}
		} else if (direction.equals("left")) {
			if(w.getAt(moveX-1, moveY) != null) {
				return false;
			}
		} else if(direction.equals("right")) {
			if(w.getAt(moveX+1, moveY) != null) {
				return false;
			}
		}
		return true;
	}
	@Override
	public void move(World w, String Direction) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setNull(World w, int rotation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPosition(World w, int rotation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rotate(World w) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drop(World w) {
		// TODO Auto-generated method stub
		
	}
}
