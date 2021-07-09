public abstract class Cell {
		 protected int x, y;		// Position in the world
		 protected boolean moved;	// boolean to indicate if moved this turn
		 protected boolean temp = false;
		 protected boolean[] rotation;
		 protected World world = null;
		 protected final int GRIDWIDTH = Grid.GRIDWIDTH;
		 protected final int GRIDHEIGHT = Grid.GRIDHEIGHT;

	   public abstract void move(World w, String Direction);
	   public abstract void setNull(World w, int rotation);
	   public abstract void setPosition(World w, int rotation);
//	   public abstract boolean boundary(World w, String direction);
	   public abstract void rotate(World w);
	   public abstract void drop(World w);

		
		 /** Empty Constructor */
		 public Cell()	{
		 }

		 public Cell(World world, int x, int y) {
	      this.world = world;
	  		this.x=x;
		  	this.y=y;
			  world.setAt(x, y, this);
		 }

		 public boolean getMoved() {
	      return moved;
		 }

		 public void setMoved(boolean moved)	{
	      this.moved = moved;
		 }
		 public boolean getTemp() {
		      return temp;
	 	}

		 public void setTemp(boolean temp)	{
	      this.temp = temp;
		 }
		 public void println(Object obj) {
			 System.out.println(obj);
		 }
		 //shape boundaries
		 public boolean boundary(World w, String direction) {
				if(direction.equals("up")) { // UP BOUNDARY
					for(int y=0;y<GRIDHEIGHT;y++) {
						for(int x=0;x<GRIDWIDTH;x++) {
							if(w.getAt(x,y) == this) {// if is any of this object
								if(y<=0) return false;// out of bounds
								if(w.getAt(x,y-1) == this) {
									continue;
								} else if (w.getAt(x,y-1) != null) {
									return false;
								}
							}
						}
					}

				} else if(direction.equals("down")) { // DOWN BOUNDARY
					for(int y=0;y<GRIDHEIGHT;y++) {
						for(int x=0;x<GRIDWIDTH;x++) {
							if(w.getAt(x,y) == this) {// if is any of this object
								if(y>=GRIDHEIGHT-1) return false;// out of bounds
								if(w.getAt(x,y+1) == this) {
									continue;
								} else if (w.getAt(x,y+1) != null) {
									return false;
								}
							}
						}
					}

				} else if (direction.equals("left")) { // LEFT BOUNDARY
					for(int y=0;y<GRIDHEIGHT;y++) {
						for(int x=0;x<GRIDWIDTH;x++) {
							if(w.getAt(x,y) == this) {// if is any of this object
								if(x<=0) return false;// out of bounds
								if(w.getAt(x-1,y) == this) {
									continue;
								} else if (w.getAt(x-1,y) != null) {
									return false;
								}
							}
						}
					}

				} else if(direction.equals("right")) { // RIGHT BOUNDARY
					for(int y=0;y<GRIDHEIGHT;y++) {
						for(int x=0;x<GRIDWIDTH;x++) {
							if(w.getAt(x,y) == this) {// if is any of this object
								if(x>=GRIDWIDTH-1) return false;// out of bounds
								if(w.getAt(x+1,y) == this) {
									continue;
								} else if (w.getAt(x+1,y) != null) {
									return false;
								}
							}
						}
					}
				}	
				return true;
			}
	}