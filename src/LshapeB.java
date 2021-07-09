public class LshapeB extends Cell{
	protected int x=0;
	protected int y=0;
	protected final int WIDTH=2;
	protected final int HEIGHT=3;
	protected boolean[] rotation = {true,false,false,false};
	private int[][][] grid = Grid.grid;
	private int SQUARE = Grid.SQUARE;
	
	//constructors
	public LshapeB(World w) {
		w.setAt(x, y, this);
		w.setAt(x, y+1, this);
		w.setAt(x+1, y+1, this);
		w.setAt(x+2, y+1, this);
	}
	public LshapeB(World w, int x, int y) {
		this.x = x;
		this.y = y;
		w.setAt(x, y, this);
		w.setAt(x, y+1, this);
		w.setAt(x+1, y+1, this);
		w.setAt(x+2, y+1, this);
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
	
	
	
	public void setNull(World w, int position) { //set current position cells to null
		if(position == 0) {
			w.setAt(x, y, null);
			w.setAt(x, y+1, null);
			w.setAt(x+1, y+1, null);
			w.setAt(x+2, y+1, null);
		} else if (position == 1) {
			w.setAt(x, y, null);
			w.setAt(x+1, y, null);
			w.setAt(x, y+1, null);
			w.setAt(x, y+2, null);

		} else if (position == 2) {
			w.setAt(x, y, null);
			w.setAt(x+1, y, null);
			w.setAt(x+2, y, null);
			w.setAt(x+2, y+1, null);

		} else if (position == 3) {
			w.setAt(x+1, y, null);
			w.setAt(x+1, y+1, null);
			w.setAt(x, y+2, null);
			w.setAt(x+1, y+2, null);
		}
		
	}
	public void setNull(World w) { //set current position cells to null
		if(rotation[0]) {
			w.setAt(x, y, null);
			w.setAt(x, y+1, null);
			w.setAt(x+1, y+1, null);
			w.setAt(x+2, y+1, null);
		} else if (rotation[1]) {
			w.setAt(x, y, null);
			w.setAt(x+1, y, null);
			w.setAt(x, y+1, null);
			w.setAt(x, y+2, null);
		} else if (rotation[2]) {
			w.setAt(x, y, null);
			w.setAt(x+1, y, null);
			w.setAt(x+2, y, null);
			w.setAt(x+2, y+1, null);
		} else if (rotation[3]) {
			w.setAt(x+1, y, null);
			w.setAt(x+1, y+1, null);
			w.setAt(x, y+2, null);
			w.setAt(x+1, y+2, null);
		}
		
	}
	
	//set positition auto rotation
	public void setPosition(World w) { // set current cells
		if(rotation[0]) {
			w.setAt(x, y, this);
			w.setAt(x, y+1, this);
			w.setAt(x+1, y+1, this);
			w.setAt(x+2, y+1, this);
		} else if (rotation[1]) {
			w.setAt(x, y, this);
			w.setAt(x+1, y, this);
			w.setAt(x, y+1, this);
			w.setAt(x, y+2, this);

		}else if (rotation[2]) {
			w.setAt(x, y, this);
			w.setAt(x+1, y, this);
			w.setAt(x+2, y, this);
			w.setAt(x+2, y+1, this);

		} else if (rotation[3]) {
			w.setAt(x+1, y, this);
			w.setAt(x+1, y+1, this);
			w.setAt(x, y+2, this);
			w.setAt(x+1, y+2, this);
		}
		
	}
	
	//set position manual rotation
	public void setPosition(World w, int position) { // set current cells

		if(position == 0) {
			w.setAt(x, y, this);
			w.setAt(x, y+1, this);
			w.setAt(x+1, y+1, this);
			w.setAt(x+2, y+1, this);
		} else if (position == 1) {
			w.setAt(x, y, this);
			w.setAt(x+1, y, this);
			w.setAt(x, y+1, this);
			w.setAt(x, y+2, this);
		}else if (position == 2) {
			w.setAt(x, y, this);
			w.setAt(x+1, y, this);
			w.setAt(x+2, y, this);
			w.setAt(x+2, y+1, this);
		} else if (position == 3) {
			w.setAt(x+1, y, this);
			w.setAt(x+1, y+1, this);
			w.setAt(x, y+2, this);
			w.setAt(x+1, y+2, this);
		}
		
	}
	
	 // BOUNDARYS
	public boolean boundary(World w, String direction) {
		if(direction.equals("up")) { // UP BOUNDARY
			if(rotation[0]) {
				if(y<=0) return false;
				if(w.getAt(x, y-1) != null) return false;
				if(w.getAt(x+1, y) != null) return false;
				if(w.getAt(x+2, y) != null) return false;
			} else if(rotation[1]) {
				if(y<=0) return false;
				if(w.getAt(x, y-1) != null) return false;
				if(w.getAt(x+1, y-1) != null) return false;
			} else if(rotation[2]) {
				if(y<=0) return false;
				if(w.getAt(x, y-1) != null) return false;
				if(w.getAt(x+1, y-1) != null) return false;
				if(w.getAt(x+2, y-1) != null) return false;
			} else if(rotation[3]) {
				if(y<=0) return false;
				if(w.getAt(x+1, y-1) != null) return false;
				if(w.getAt(x, y+1) != null) return false;
			}

		} else if(direction.equals("down")) { // DOWN BOUNDARY
			if(rotation[0]) {
				if(y>=GRIDHEIGHT-2) return false;
				if(w.getAt(x, y+2) != null) return false;
				if(w.getAt(x+1, y+2) != null) return false;
				if(w.getAt(x+2, y+2) != null) return false;
			} else if (rotation[1]) {
				if(y>=GRIDHEIGHT-3) return false;
				if(w.getAt(x, y+3) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
			} else if (rotation[2]) {
				if(y>=GRIDHEIGHT-2) return false;
				if(w.getAt(x, y+1) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
				if(w.getAt(x+2, y+2) != null) return false;
			} else if (rotation[3]) {
				if(y>=GRIDHEIGHT-3) return false;
				if(w.getAt(x, y+3) != null) return false;
				if(w.getAt(x+1, y+3) != null) return false;
			}

		} else if (direction.equals("left")) { // LEFT BOUNDARY
			if(rotation[0]) {
				if(x<=0)return false;
				if(w.getAt(x-1, y) != null) return false;
				if(w.getAt(x-1, y+1) != null) return false;
			} else if (rotation[1]) {
				if(x<=0)return false;
				if(w.getAt(x-1, y) != null) return false;
				if(w.getAt(x-1, y+1) != null) return false;
				if(w.getAt(x-1, y+2) != null) return false;
			} else if (rotation[2]) {
				if(x<=0)return false;
				if(w.getAt(x-1, y) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
			} else if (rotation[3]) {
				if(x<=0)return false;
				if(w.getAt(x, y) != null) return false;
				if(w.getAt(x, y+1) != null) return false;
				if(w.getAt(x-1, y+2) != null) return false;
			}

		} else if(direction.equals("right")) { // RIGHT BOUNDARY
			if(rotation[0]) {
				if(x>=GRIDWIDTH-3)return false;
				if(w.getAt(x+1, y) != null) return false;
				if(w.getAt(x+3, y+1) != null) return false;
			} else if(rotation[1]) {
				if(x>=GRIDWIDTH-2)return false;
				if(w.getAt(x+2, y) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
				if(w.getAt(x+1, y+2) != null) return false;
			} else if(rotation[2]) {
				if(x>=GRIDWIDTH-3)return false;
				if(w.getAt(x+3, y) != null) return false;
				if(w.getAt(x+3, y+1) != null) return false;
			} else if(rotation[3]) {
				if(x>=GRIDWIDTH-2)return false;
				if(w.getAt(x+2, y) != null) return false;
				if(w.getAt(x+2, y+1) != null) return false;
				if(w.getAt(x+2, y+2) != null) return false;
			}
		}	
		return true;
	}
	
	//rotate piece method
	public void rotate(World w) {
		System.out.println("accessed rotate method");
		if(rotation[0] &&
		   x < GRIDWIDTH - 2 &&
		   y <= GRIDHEIGHT - 3 &&
		   rotateBoundary(w,0)) {
			this.setNull(w,0);
			this.setPosition(w, 1);
			rotation[0]=false;
			rotation[1]=true;
		} else if (rotation[1] &&
				y <  GRIDHEIGHT - 2 &&
				rotateBoundary(w,1)) {
			this.setNull(w,1);
			this.setPosition(w,2);
			rotation[1]=false;
			rotation[2]=true;
		} else if (rotation[2] &&
				y >= 0 &&
				y <=  GRIDHEIGHT - 3 &&
				x < GRIDWIDTH - 2 &&
				rotateBoundary(w,2)) {
			this.setNull(w,2);
			this.setPosition(w, 3);
			rotation[2]=false;
			rotation[3]=true;
		} else if (rotation[3] &&
				y <  GRIDHEIGHT - 2 &&
				x <= GRIDWIDTH - 3 &&
				rotateBoundary(w,0)) {
			this.setNull(w,3);
			this.setPosition(w, 0);
			rotation[3]=false;
			rotation[0]=true;
		}
	}
	//rotation boundary method
	public boolean rotateBoundary(World w, int rotation) {
		switch(rotation) {
			case 0:
				if( w.getAt(x+1, y) == null &&
					w.getAt(x, y+2) == null ) {
					return true;
				}
			case 1:
				if( w.getAt(x+2, y) == null &&
					w.getAt(x+2, y+1) == null ) {
					return true;
				}
			case 2:
				if( w.getAt(x+1, y+1) == null &&
					w.getAt(x, y+2) == null &&
					w.getAt(x+1, y+2) == null ) {
					return true;
				}
			case 3:
				if( w.getAt(x, y) == null &&
					w.getAt(x, y+1) == null &&
					w.getAt(x+2, y+1) == null ) {
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
