public class Straight extends Cell{
	protected int x=0;
	protected int y=0;
	protected final int WIDTH=2;
	protected final int HEIGHT=3;
	protected boolean[] rotation = {true,false};
	private int[][][] grid = Grid.grid;
	private int SQUARE = Grid.SQUARE;
	
	//constructors
	public Straight(World w) {
		w.setAt(x, y, this);
		w.setAt(x+1, y, this);
		w.setAt(x+2, y, this);
		w.setAt(x+3, y, this);
	}
	public Straight(World w, int x, int y) {
		this.x = x;
		this.y = y;
		w.setAt(x, y, this);
		w.setAt(x+1, y, this);
		w.setAt(x+2, y, this);
		w.setAt(x+3, y, this);
	}
	
	//methods


	public void move(World w, String direction) { //from Cell Class abstract move() method
		switch(direction){
			case "up":
				if(boundary(w,"up")) {
					setNull(w);
					y--;
					setPosition(w);
					break;
				} else {
					break;
				}
			case "down":
				if(boundary(w,"down")) {
					setNull(w);
					y++;
					setPosition(w);
					break;
				} else {
					break;
				}
			case "left":
				if(boundary(w,"left")) {
					setNull(w);
					x--;
					setPosition(w);
					break;
				} else {
					break;
				}
			case "right":
				if(boundary(w,"right")) {
					setNull(w);
					x++;
					setPosition(w);
					break;
				} else {
					break;
				}
				
			default:
				break;
		}
	}
	//setNull auto set rotation
	public void setNull(World w) { //set current position cells to null
		if(rotation[0]) {
			w.setAt(x, y, null);
			w.setAt(x+1, y, null);
			w.setAt(x+2, y, null);
			w.setAt(x+3, y, null);
		}
		if (rotation[1]) {
			w.setAt(x, y, null);
			w.setAt(x, y+1, null);
			w.setAt(x, y+2, null);
			w.setAt(x, y+3, null);
		}
		
	}
	//setNull manual set rotation
	public void setNull(World w, int rotation) {
		if(rotation == 0) {
			w.setAt(x, y, null);
			w.setAt(x+1, y, null);
			w.setAt(x+2, y, null);
			w.setAt(x+3, y, null);

		}
		if (rotation == 1) {
			w.setAt(x, y, null);
			w.setAt(x, y+1, null);
			w.setAt(x, y+2, null);
			w.setAt(x, y+3, null);
		}
		
	}
	
	//setPosition auto set rotation
	public void setPosition(World w) {
		if(rotation[0]) {
			w.setAt(x, y, this);
			w.setAt(x+1, y, this);
			w.setAt(x+2, y, this);
			w.setAt(x+3, y, this);
		}
		if (rotation[1]) {
			w.setAt(x, y, this);
			w.setAt(x, y+1, this);
			w.setAt(x, y+2, this);
			w.setAt(x, y+3, this);
		}
	}
	//setPosition manual set rotation
	public void setPosition(World w, int rotation) {
		if(rotation == 0) {
			w.setAt(x, y, this);
			w.setAt(x+1, y, this);
			w.setAt(x+2, y, this);
			w.setAt(x+3, y, this);
		}
		if (rotation == 1) {
			w.setAt(x, y, this);
			w.setAt(x, y+1, this);
			w.setAt(x, y+2, this);
			w.setAt(x, y+3, this);
		}
	}
	
	 // BOUNDARYS
	public boolean boundary(World w, String direction) {
		if(direction.equals("up")) { // UP BOUNDARY
			println(w.getAt(x,y+1));
			if(rotation[0]) {
				if(y<=0) return false; 
				if(w.getAt(x, y-1) != null) return false;
				if(w.getAt(x+1, y-1) != null) return false;
				if(w.getAt(x+2, y-1) != null) return false;
				if(w.getAt(x+3, y-1) != null) return false;
			} else if(rotation[1]) {
				if(y<=0) return false;
				if(w.getAt(x, y-1) != null) return false;
			}
		} else if(direction.equals("down")) { // DOWN BOUNDARY
			if(rotation[0]) {
				if(y>=GRIDHEIGHT-1) return false;
				if(w.getAt(x, y+1) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
				if(w.getAt(x+2, y+1) != null) return false;
				if(w.getAt(x+3, y+1) != null) return false;
			} else if (rotation[1]) {
				if(y>=GRIDHEIGHT-4) return false;
				if(w.getAt(x, y+4) != null) return false;
			}
		} else if (direction.equals("left")) { // LEFT BOUNDARY
			if(rotation[0]) {
				if(x<=0) return false;
				if(w.getAt(x-1, y) != null) return false;
			} else if (rotation[1]) {
				if(x<=0) return false;
				if(w.getAt(x-1, y) != null) return false;
				if(w.getAt(x-1, y+1) != null) return false;
				if(w.getAt(x-1, y+2) != null) return false;
				if(w.getAt(x-1, y+3) != null) return false;
			}
		} else if(direction.equals("right")) { // RIGHT BOUNDARY
			if(rotation[0]) {
				if(x>=GRIDWIDTH-4) return false;
				if(w.getAt(x+4, y) != null) return false;
			} else if(rotation[1]) {
				if(x>=GRIDWIDTH-1) return false;
				if(w.getAt(x+1, y) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
				if(w.getAt(x+1, y+2) != null) return false;
				if(w.getAt(x+1, y+3) != null) return false;
			}
		}	
		return true;
	}
	
	//rotate piece method
	public void rotate(World w) {
		System.out.println("accessed rotate method");
		if(rotation[0] &&
		   x < GRIDWIDTH - 2 &&
		   y <=  GRIDHEIGHT - 4 &&
		   rotateBoundary(w,0)) {
			this.setNull(w,0);
			this.setPosition(w, 1);
			rotation[0]=false;
			rotation[1]=true;
		} else if (rotation[1] &&
				   x <=  GRIDWIDTH - 4 &&
				   y <  GRIDHEIGHT - 2 &&
				   rotateBoundary(w,1)) {
			this.setNull(w,1);
			this.setPosition(w,0);
			rotation[1]=false;
			rotation[0]=true;
		}
	}
	//rotation boundary method
	public boolean rotateBoundary(World w, int rotation) {
		switch(rotation) {
			case 0:
				if( w.getAt(x, y+1) == null &&
					w.getAt(x, y+2) == null &&
					w.getAt(x, y+3) == null ) {
					return true;
				}
			case 1:
				if( w.getAt(x+1, y) == null &&
					w.getAt(x+2, y) == null &&
					w.getAt(x+3, y) == null ) {
					return true;
				}

			default:
				return false;
		}
	}
	
	//drop
	
	public void drop(World w) {
		int count = 0;
		while(boundary(w,"down")) {
			move(w,"down");
			println((++count)+"dropping");
		}
	}
}
