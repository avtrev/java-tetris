import java.awt.Graphics;

public class Rectangle extends Cell{
	public static int moveX=0;
	public static int moveY=0;
	public static final int WIDTH=4;
	public static final int HEIGHT=1;
	public int[][][] grid = Grid.grid;
	public int SQUARE = Grid.SQUARE;
	Rectangle( World w) {
		w.setAt(moveX, moveY, this);
		w.setAt(moveX+1, moveY, this);
		w.setAt(moveX+2, moveY, this);
		w.setAt(moveX+3, moveY, this);
	}
	Rectangle( World w, int x, int y) {
		moveX = x;
		moveY = y;
		w.setAt(moveX, moveY, this);
		w.setAt(moveX+1, moveY, this);
		w.setAt(moveX+2, moveY, this);
		w.setAt(moveX+3, moveY, this);
	}
	Rectangle(Graphics g, World w) {
		g.clearRect(grid[moveX][moveY][0], grid[moveX][moveY][1], SQUARE, SQUARE);
		g.clearRect(grid[moveX+1][moveY][0], grid[moveX+1][moveY][1], SQUARE, SQUARE);
		g.clearRect(grid[moveX+2][moveY][0], grid[moveX+2][moveY][1], SQUARE, SQUARE);
		g.clearRect(grid[moveX+3][moveY][0], grid[moveX+3][moveY][1], SQUARE, SQUARE);
		w.setAt(moveX, moveY, this);
		w.setAt(moveX+1, moveY, this);
		w.setAt(moveX+2, moveY, this);
		w.setAt(moveX+3, moveY, this);
	}
	Rectangle(Graphics g, int x, int y) {
    	g.clearRect(grid[x][y][0], grid[x][y][1], SQUARE, SQUARE);
    	g.clearRect(grid[x+1][y][0], grid[x+1][y][1], SQUARE, SQUARE);
    	g.clearRect(grid[x+2][y][0], grid[x+2][y][1], SQUARE, SQUARE);
    	g.clearRect(grid[x+3][y][0], grid[x+3][y][1], SQUARE, SQUARE);
    }
	
	public void move() {	
	}
	
	public void moveRectangle(World w, Rectangle rc) {
		boolean[] direction = {false,false,false,false};	
		
		if (Rectangle.moveY > 0 && rc.boundary(w, "up")){ //up
			direction[0] = true;
		}
		if (Rectangle.moveY < Grid.GRIDSIZE - Rectangle.HEIGHT && rc.boundary(w, "down")){ //down
			direction[1] = true;
		}
		if (Rectangle.moveX > 0 && rc.boundary(w, "left")){ //left
			direction[2] = true;
		}
		if (Rectangle.moveX < Grid.GRIDSIZE - Rectangle.WIDTH && rc.boundary(w, "right")){ //right
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
				setNull(w);
				Rectangle.moveY--;
				setRect(w,rc);
			} else if (available == 1) {//DOWN
				setNull(w);
    			Rectangle.moveY++;
    			setRect(w,rc);
			} else if (available == 2) {//LEFT
				setNull(w);
        		Rectangle.moveX--;
        		setRect(w,rc);
			} else if (available == 3) {//RIGHT
				setNull(w);
            	Rectangle.moveX++;
            	setRect(w,rc);
			}
		}
	}
	public void setNull(World w) { //set current position cells to null
		w.setAt(Rectangle.moveX, Rectangle.moveY, null);
		w.setAt(Rectangle.moveX+1, Rectangle.moveY, null);
		w.setAt(Rectangle.moveX+2, Rectangle.moveY, null);
		w.setAt(Rectangle.moveX+3, Rectangle.moveY, null);
	}
	public void setRect(World w, Rectangle rc) { // set current cells
		w.setAt(Rectangle.moveX, Rectangle.moveY, rc);
		w.setAt(Rectangle.moveX+1, Rectangle.moveY, rc);
		w.setAt(Rectangle.moveX+2, Rectangle.moveY, rc);
		w.setAt(Rectangle.moveX+3, Rectangle.moveY, rc);
	}
	
	public boolean boundary(World w, String direction) {
		if(direction.equals("up")) {
			if(w.getAt(moveX, moveY-1) != null) {
				return false;
			}
			if(w.getAt(moveX+1, moveY-1) != null) {
				return false;
			}
			if(w.getAt(moveX+2, moveY-1) != null) {
				return false;
			}
			if(w.getAt(moveX+3, moveY-1) != null) {
				return false;
			}	
		} else if(direction.equals("down")) {
			if(w.getAt(moveX, moveY+1) != null) {
				return false;
			}
			if(w.getAt(moveX+1, moveY+1) != null) {
				return false;
			}
			if(w.getAt(moveX+2, moveY+1) != null) {
				return false;
			}
			if(w.getAt(moveX+3, moveY+1) != null) {
				return false;
			}
		} else if (direction.equals("left")) {
			if(w.getAt(moveX-1, moveY) != null) {
				return false;
			}
		} else if(direction.equals("right")) {
			if(w.getAt(moveX+4, moveY) != null) {
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
