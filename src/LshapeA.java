public class LshapeA extends Cell{
	protected int x=0;
	protected int y=0;
	protected final int WIDTH=2;
	protected final int HEIGHT=3;
	protected boolean[] rotation = {true,false,false,false};
	private int[][][] grid = Grid.grid;
	private int SQUARE = Grid.SQUARE;
	
	//constructors
	public LshapeA(World w) {
		w.setAt(x, y, this);
		w.setAt(x, y+1, this);
		w.setAt(x, y+2, this);
		w.setAt(x+1, y+2, this);
	}
	public LshapeA(World w, int x, int y) {
		this.x = x;
		this.y = y;
		w.setAt(this.x, this.y, this);
		w.setAt(this.x, this.y+1, this);
		w.setAt(this.x, this.y+2, this);
		w.setAt(this.x+1, this.y+2, this);
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
	
	public void moveLshape(World w) {
			boolean[] direction = {false,false,false,false};	
			
			if (this.y > 0 && this.boundary(w, "up")){ //up
				direction[0] = true;
			}
			if (this.y < GRIDHEIGHT - this.HEIGHT && this.boundary(w, "down")){ //down
				direction[1] = true;
			}
			if (this.x > 0 && this.boundary(w, "left")){ //left
				direction[2] = true;
			}
			if (this.x < GRIDWIDTH - this.WIDTH && this.boundary(w, "right")){ //right
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
					setNull(w,0);
					this.y--;
					setPosition(w,0);
				} else if (available == 1) {//DOWN
					setNull(w,0);
					this.y++;
					setPosition(w,0);
				} else if (available == 2) {//LEFT
					setNull(w,0);
					this.x--;
					setPosition(w,0);
				} else if (available == 3) {//RIGHT
					setNull(w,0);
					this.x++;
					setPosition(w,0);
				}
			}
		}
	
	public void setNull(World w, int position) { //set current position cells to null
		if(position == 0) {
			w.setAt(this.x, this.y, null);
			w.setAt(this.x, this.y+1, null);
			w.setAt(this.x, this.y+2, null);
			w.setAt(this.x+1, this.y+2, null);
		} else if (position == 1) {
			w.setAt(this.x, this.y, null);
			w.setAt(this.x+1, this.y, null);
			w.setAt(this.x+2, this.y, null);
			w.setAt(this.x, this.y+1, null);
		} else if (position == 2) {
			w.setAt(this.x, this.y, null);
			w.setAt(this.x+1, this.y, null);
			w.setAt(this.x+1, this.y+1, null);
			w.setAt(this.x+1, this.y+2, null);
		} else if (position == 3) {
			w.setAt(this.x, this.y+1, null);
			w.setAt(this.x+1, this.y+1, null);
			w.setAt(this.x+2, this.y+1, null);
			w.setAt(this.x+2, this.y, null);
		}
		
	}
	public void setNull(World w) { //set current position cells to null
		if(rotation[0]) {
			w.setAt(this.x, this.y, null);
			w.setAt(this.x, this.y+1, null);
			w.setAt(this.x, this.y+2, null);
			w.setAt(this.x+1, this.y+2, null);
		} else if (rotation[1]) {
			w.setAt(this.x, this.y, null);
			w.setAt(this.x+1, this.y, null);
			w.setAt(this.x+2, this.y, null);
			w.setAt(this.x, this.y+1, null);
		} else if (rotation[2]) {
			w.setAt(this.x, this.y, null);
			w.setAt(this.x+1, this.y, null);
			w.setAt(this.x+1, this.y+1, null);
			w.setAt(this.x+1, this.y+2, null);
		} else if (rotation[3]) {
			w.setAt(this.x, this.y+1, null);
			w.setAt(this.x+1, this.y+1, null);
			w.setAt(this.x+2, this.y+1, null);
			w.setAt(this.x+2, this.y, null);
		}
		
	}
	
	//set positition auto rotation
	public void setPosition(World w) { // set current cells
		/*Position 0
		 * [1]
		 * [2]
		 * [3][4]
		 * */
		if(rotation[0]) {
			w.setAt(this.x, this.y, this);
			w.setAt(this.x, this.y+1, this);
			w.setAt(this.x, this.y+2, this);
			w.setAt(this.x+1, this.y+2, this);
		/*Position 1
		 * [1][2][3]
		 * [4]
		 * */
		} else if (rotation[1]) {
			w.setAt(this.x, this.y, this);
			w.setAt(this.x+1, this.y, this);
			w.setAt(this.x+2, this.y, this);
			w.setAt(this.x, this.y+1, this);
		/*Position 2
		 * [1][2]
		 *    [3]
		 *    [4]
		 * */
		}else if (rotation[2]) {
			w.setAt(this.x, this.y, this);
			w.setAt(this.x+1, this.y, this);
			w.setAt(this.x+1, this.y+1, this);
			w.setAt(this.x+1, this.y+2, this);
		/*Position 3
		 *       [4]
		 * [1][2][3]
		 * */
		} else if (rotation[3]) {
			w.setAt(this.x, this.y+1, this);
			w.setAt(this.x+1, this.y+1, this);
			w.setAt(this.x+2, this.y+1, this);
			w.setAt(this.x+2, this.y, this);
		}
		
	}
	
	//set position manual rotation
	public void setPosition(World w, int position) { // set current cells
		/*Position 0
		 * [1]
		 * [2]
		 * [3][4]
		 * */
		if(position == 0) {
			w.setAt(this.x, this.y, this);
			w.setAt(this.x, this.y+1, this);
			w.setAt(this.x, this.y+2, this);
			w.setAt(this.x+1, this.y+2, this);
			/*Position 1
			 * [1][2][3]
			 * [4]
			 * */
		} else if (position == 1) {
			w.setAt(this.x, this.y, this);
			w.setAt(this.x+1, this.y, this);
			w.setAt(this.x+2, this.y, this);
			w.setAt(this.x, this.y+1, this);
			/*Position 2
			 * [1][2]
			 *    [3]
			 *    [4]
			 * */
		}else if (position == 2) {
			w.setAt(this.x, this.y, this);
			w.setAt(this.x+1, this.y, this);
			w.setAt(this.x+1, this.y+1, this);
			w.setAt(this.x+1, this.y+2, this);
			/*Position 3
			 *       [4]
			 * [1][2][3]
			 * */
		} else if (position == 3) {
			w.setAt(this.x, this.y+1, this);
			w.setAt(this.x+1, this.y+1, this);
			w.setAt(this.x+2, this.y+1, this);
			w.setAt(this.x+2, this.y, this);
		}
		
	}
	
	 // BOUNDARYS
	/*
	 * [1]
	 * [2]
	 * [3][4]
	 */
	public boolean boundary(World w, String direction) {
		if(direction.equals("up")) { // UP BOUNDARY
			if(rotation[0]) {
				if(y<=0) return false;
				if(w.getAt(x, y-1) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
			} else if(rotation[1]) {
				if(y<=0) return false;
				if(w.getAt(x, y-1) != null) return false;
				if(w.getAt(x+1, y-1) != null) return false;
				if(w.getAt(x+2, y-1) != null) return false;
			} else if(rotation[2]) {
				if(y<=0) return false;
				if(w.getAt(x, y-1) != null) return false;
				if(w.getAt(x+1, y-1) != null) return false;
			} else if(rotation[3]) {
				if(y<=1) return false;
				if(w.getAt(x, y) != null) return false;
				if(w.getAt(x+1, y) != null) return false;
				if(w.getAt(x+2, y-1) != null) return false;
			}

		} else if(direction.equals("down")) { // DOWN BOUNDARY
			if(rotation[0]) {
				if(y>=GRIDHEIGHT-3) return false;
				if(w.getAt(x, y+3) != null) return false;
				if(w.getAt(x+1, y+3) != null) return false;
			} else if (rotation[1]) {
				if(y>=GRIDHEIGHT-2) return false;
				if(w.getAt(x, y+2) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
				if(w.getAt(x+2, y+1) != null) return false;
			} else if (rotation[2]) {
				if(y>=GRIDHEIGHT-3) return false;
				if(w.getAt(x, y+1) != null) return false;
				if(w.getAt(x+1, y+3) != null) return false;
			} else if (rotation[3]) {
				if(y>=GRIDHEIGHT-2) return false;
				if(w.getAt(x, y+2) != null) return false;
				if(w.getAt(x+1, y+2) != null) return false;
				if(w.getAt(x+2, y+2) != null) return false;
			}

		} else if (direction.equals("left")) { // LEFT BOUNDARY
			if(rotation[0]) {
				if(x<=0)return false;
				if(w.getAt(x-1, y) != null) return false;
				if(w.getAt(x-1, y+1) != null) return false;
				if(w.getAt(x-1, y+2) != null) return false;
			} else if (rotation[1]) {
				if(x<=0)return false;
				if(w.getAt(x-1, y) != null) return false;
				if(w.getAt(x-1, y+1) != null) return false;
			} else if (rotation[2]) {
				if(x<=0)return false;
				if(w.getAt(x-1, y) != null) return false;
				if(w.getAt(x, y+1) != null) return false;
				if(w.getAt(x, y+2) != null) return false;
			} else if (rotation[3]) {
				if(x<=0)return false;
				if(w.getAt(x+1, y) != null) return false;
				if(w.getAt(x-1, y+1) != null) return false;
			}

		} else if(direction.equals("right")) { // RIGHT BOUNDARY
			if(rotation[0]) {
				if(x>=GRIDWIDTH-2)return false;
				if(w.getAt(x+1, y) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
				if(w.getAt(x+2, y+2) != null) return false;
			} else if(rotation[1]) {
				if(x>=GRIDWIDTH-3)return false;
				if(w.getAt(x+3, y) != null) return false;
				if(w.getAt(x+1, y+1) != null) return false;
			} else if(rotation[2]) {
				if(x>=GRIDWIDTH-2)return false;
				if(w.getAt(x+2, y) != null) return false;
				if(w.getAt(x+2, y+1) != null) return false;
				if(w.getAt(x+2, y+2) != null) return false;
			} else if(rotation[3]) {
				if(x>=GRIDWIDTH-3)return false;
				if(w.getAt(x+3, y) != null) return false;
				if(w.getAt(x+3, y+1) != null) return false;
			}
		}	
		return true;
	}
	
	//rotate piece method
	public void rotate(World w) {
		System.out.println("accessed rotate method");
		if(rotation[0] && x < GRIDWIDTH - 2 && rotateBoundary(w,0)) {
			this.setNull(w,0);
			this.setPosition(w, 1);
			rotation[0]=false;
			rotation[1]=true;
		} else if (rotation[1] && y <  GRIDHEIGHT - 2 && rotateBoundary(w,1)) {
			this.setNull(w,1);
			this.setPosition(w,2);
			rotation[1]=false;
			rotation[2]=true;
		} else if (rotation[2] && y >= 0 && x < GRIDWIDTH - 2 && rotateBoundary(w,2)) {
			this.setNull(w,2);
			this.setPosition(w, 3);
			rotation[2]=false;
			rotation[3]=true;
		} else if (rotation[3] && y <  GRIDHEIGHT - 2 && rotateBoundary(w,0)) {
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
				if( w.getAt(x, y) == null &&
					w.getAt(x, y+2) == null &&	
					w.getAt(x+1, y+2) == null ) {
					return true;
				}
			case 1:
				if( w.getAt(x+1, y) == null &&
					w.getAt(x+2, y) == null ) {
					return true;
				}
			case 2:
				if( w.getAt(x+1, y+1) == null &&
					w.getAt(x+1, y+2) == null ) {
					return true;
				}
			case 3:
				if( w.getAt(x+2, y) == null &&
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
