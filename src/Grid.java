
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.event.*;

public class Grid extends Thread implements KeyListener{

	public static final int GRIDSIZE = 10;
	public static final int GRIDWIDTH = 10;
	public static final int GRIDHEIGHT = 20;
	public static final int SQUARE = 40;//square pixel size of cell
	
    public static int[][][]grid = new int[GRIDHEIGHT][GRIDWIDTH][2];
    public boolean[] moveControl = {false,false,false,false};
   
    public boolean rectTrue = false;
    public boolean dotTrue = false;
    public boolean autoSquare = false;
    public boolean autoRectangle = false;
    public boolean autoDot = false;
    
    //main shapes boolean
    public boolean lShapeATrue = false;
    public boolean lShapeBTrue = false;
    public boolean skewAtrue = false;
    public boolean skewBtrue = false;
    public boolean straightTrue = false;
    public boolean squareTrue = false;
    public boolean tShapeTrue = false;
    
     
    public boolean setPiece = false;
    public int lastLine = GRIDHEIGHT-1;
    public World w = new World();
    private Square sq; //= new Square(w,0,0);
    private Rectangle rc;
    private Cell mp;
    
   
   
   
    public Grid() {
//    	for(int i=0;i<dots.length;i++) dots[i] = new Dot(w);//intialize dots array
    	spawnPiece();
    }

    
    public class MyGraphics extends JComponent{

        private static final long serialVersionUID = 1L;
        
        MyGraphics() {
            setPreferredSize(new Dimension(GRIDWIDTH*(SQUARE+1),GRIDHEIGHT*(SQUARE+1)));
        }
        
        public void println(Object obj) {
        	System.out.println(obj);
        }
        
        
        
        public void shift() {
  
	    	if(setPiece) {
	        boolean[] rowFull = new boolean[GRIDHEIGHT];
	        for(int i=0;i<rowFull.length;i++) {
	        	rowFull[i]=true;
	        }

	  	    boolean full = true;
	  	    boolean shiftDown = false;
			  for(int y=0;y<GRIDHEIGHT;y++) {
			  	for(int x=0;x<GRIDWIDTH;x++) {
			  		//if row is full
			  		if(w.grid[y][x] == null) {
			  			rowFull[y] = false;
			  		}
			  	}
			  }
              //check if any rows are full
	          for(int y=0;y<rowFull.length;y++) {
		        	if(rowFull[y]) {
		        		for(int x=0;x<GRIDWIDTH;x++) {
		   	        	 	w.setAt(x,y,null);
		        		}
		        		//add to score after lines are cleared
		        		score+=1000;
		        		scoreNumber.setText(String.valueOf(score));
		        		//speed up game after 10 lines
		        		if(speedUpTrue && score % 10000 == 0) gameSpeed-=100;
		        		shiftDown = true;
		        		if(shiftDown) {
		  	        	  //reverse for loop
		  	        	  for(int Y=GRIDHEIGHT-1;Y>=0;Y--) {
		  	        		  for(int X=GRIDWIDTH-1;X>=0;X--) {
		  	        			  if(Y <= y ) {
			  	        			  if(w.grid[Y][X] != mp &&
			  	    					  w.grid[Y][X] != null
			  	    					  ) {
			  	        				  w.setAt(X, Y+1,w.getAt(X, Y));
			  	        				  w.setAt(X, Y,null);
			  	        				  }
			  	        			  }
		  			  			}
		  	        	  }
		  	        	  shiftDown = false;
		  	        	  println("shiftdown");
		  	          	}
		        	}
		        }
          	  //set rowFull[] to false
	          for(int i=0;i<rowFull.length;i++) {
		        	rowFull[i]=false;
		        }
          		setPiece = false;
        	}
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x = 0;
            int y = 0;
            //load x y coordinates
            for(int i=0;i<GRIDHEIGHT;i++) {
            	for(int j=0;j<GRIDWIDTH;j++) {
            		grid[i][j][0] = x;
            		grid[i][j][1] = y;
            		x+=SQUARE+1;
            		if(j == GRIDWIDTH - 1) x=0;
            	}
            	y+=SQUARE+1;
            }
            shift();
        	w.displayWorld(g);
        }

    }
    
    //SHOW SCORE METHOD & VARIABLES
    boolean showScoreTrue = false;
    int score = 0;
    JLabel scoreNumber = new JLabel(String.valueOf(score));
    JLabel scoreLable = new JLabel("Score ");
    JPanel headerPanel = new JPanel();
    public void showScore() {
    	
    	showScoreTrue = !showScoreTrue;
    	if(showScoreTrue) {
    		Border blackBorder = BorderFactory.createLineBorder(Color.black);
    		scoreNumber.setOpaque(true);
    		headerPanel.setBorder(blackBorder);
	        headerPanel.add(scoreLable);
	        headerPanel.add(scoreNumber);
	        frame.add(headerPanel,BorderLayout.NORTH);
	        
    	} else {
    		frame.remove(headerPanel);
    	}
    	
    	frame.pack();
    	
    }
    
    MyGraphics graphics = new MyGraphics();
    JPanel panel = new JPanel();
    final JFrame frame = new JFrame();
    public void createGUI() {
        panel.add(graphics);
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);
        frame.add(panel,BorderLayout.CENTER);
        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    //KEY BOARD EVENTS
    public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_UP ) { // MOVE UP
	    	//Up arrow key code
	    	System.out.println("Up keypressed");

	    	//RECTANGLE UP
	    	if(rectTrue && Rectangle.moveY > 0 && rc.boundary(w, "up")) {
	    		w.setAt(Rectangle.moveX, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, null);
				Rectangle.moveY--;
				w.setAt(Rectangle.moveX, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, rc);
	    	}
            
            //MAIN SHAPES MOVE UP
            if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"up");

	    	graphics.repaint();
    	} else if (e.getKeyCode() == KeyEvent.VK_DOWN ) { // MOVE DOWN
    		//Down arrow key code
    		System.out.println("Down keypressed");

    		//RECTANGLE DOWN
    		if(rectTrue && Rectangle.moveY < GRIDHEIGHT - Rectangle.HEIGHT && rc.boundary(w, "down")) {
    			w.setAt(Rectangle.moveX, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, null);
				Rectangle.moveY++;
				w.setAt(Rectangle.moveX, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, rc);
    		}
            
            //MAIN SHAPES MOVE DOWN
            if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"down");
            
    		graphics.repaint();
    		
    	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT ) { //MOVE RIGHT
    		//right arrow key code
            System.out.println("Right keypressed");

            //RECTANGLE RIGHT
            if(rectTrue && Rectangle.moveX < GRIDWIDTH - Rectangle.WIDTH && rc.boundary(w, "right")) {
            	w.setAt(Rectangle.moveX, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, null);
				Rectangle.moveX++;
				w.setAt(Rectangle.moveX, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, rc);
            }
            
            //MAIN SHAPES MOVE RIGHT
            if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"right");
            
            graphics.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) { //MOVE LEFT
            //Left arrow key code
        	System.out.println("Left keypressed");

        	//RECTANGLE LEFT
        	if(rectTrue && Rectangle.moveX > 0 && rc.boundary(w, "left")) {
        		w.setAt(Rectangle.moveX, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, null);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, null);
				Rectangle.moveX--;
				w.setAt(Rectangle.moveX, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+1, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+2, Rectangle.moveY, rc);
				w.setAt(Rectangle.moveX+3, Rectangle.moveY, rc);
        	}
            
            //MAIN SHAPES MOVE LEFT
            if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"left");
            
        	graphics.repaint();
        
        //ROTATE SELECTED SHAPE
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE ) {
        	System.out.println("SPACE pressed = ROTATE SHAPE");
        		mp.rotate(w);
        	graphics.repaint();
        //SET PIECE SPAWN NEW PIECE
        } else if (e.getKeyCode() == KeyEvent.VK_Z ) {
        	System.out.println("SHIFT pressed = SET SHAPE");
        	spawnPiece();
        	setPiece = true;
        	graphics.repaint();
    	//DROP PIECE SPAWN NEW PIECE
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT ) {
        	System.out.println("ENTER pressed = DROP SHAPE");
    		mp.drop(w);
        	spawnPiece();
        	setPiece = true;
        	graphics.repaint();
        //CLEAR SCREEN
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
        	clearGrid(w);
        	score = 0;
        	scoreNumber.setText(String.valueOf(score));
        	gameSpeed = 1000;
        	graphics.repaint();
        //TOGGLE AUTO MOVE DOWN	
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
        	println("TOGGLE AUTO DOWN");
        	autoDownTrue = !autoDownTrue;
        	if(autoDownTrue) {
        		autoDown();
        	}
    	//TOGGLE SCORE MOVEMENTS
        } else if (e.getKeyCode() == KeyEvent.VK_S ) {
        	System.out.println("toggle show score");
        	showScore();
        //TOGGLE SPEED UP GAME
        } else if (e.getKeyCode() == KeyEvent.VK_I ) {
        	speedUpTrue = !speedUpTrue;
        	System.out.println("toggle speed up game "+speedUpTrue);
        	if(!speedUpTrue) gameSpeed = 1000;
        }
    }
    
    @Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void println(Object obj) {
		System.out.println(obj);
	}
	
	
	//SPAWN NEW PICE RANDOMLY
	public final int SHAPEAMOUNT = 7;
	public int nextShape;
	public int previousShape ;
	
	public void spawnPiece() {
		
		do {
			previousShape = nextShape;
			nextShape = (int)(Math.random()*SHAPEAMOUNT);
		} while (nextShape == previousShape);

	
		if(nextShape == 0) {
			lShapeATrue = lShapeBTrue = skewAtrue = skewBtrue = straightTrue = squareTrue = tShapeTrue = false;
			lShapeATrue = true;
			mp = new LshapeA(w,4,0);
		} else if(nextShape == 1) {
			lShapeATrue = lShapeBTrue = skewAtrue = skewBtrue = straightTrue = squareTrue = tShapeTrue = false;
			lShapeBTrue = true;
			mp = new LshapeB(w,4,0);
		} else if(nextShape == 2) {
			lShapeATrue = lShapeBTrue = skewAtrue = skewBtrue = straightTrue = squareTrue = tShapeTrue = false;
			skewAtrue = true;
			mp = new SkewA(w,4,0);
		} else if(nextShape == 3) {
			lShapeATrue = lShapeBTrue = skewAtrue = skewBtrue = straightTrue = squareTrue = tShapeTrue = false;
			skewBtrue = true;
			mp = new SkewB(w,4,0);
		} else if(nextShape == 4) {
			lShapeATrue = lShapeBTrue = skewAtrue = skewBtrue = straightTrue = squareTrue = tShapeTrue = false;
			straightTrue = true;
			mp = new Straight(w,4,0);
		} else if(nextShape == 5) {
			lShapeATrue = lShapeBTrue = skewAtrue = skewBtrue = straightTrue = squareTrue = tShapeTrue = false;
			squareTrue = true;
			mp = new Square(w,4,0);
		} else if(nextShape == 6) {
			lShapeATrue = lShapeBTrue = skewAtrue = skewBtrue = straightTrue = squareTrue = tShapeTrue = false;
			tShapeTrue = true;
			mp = new Tshape(w,4,0);
		}
	}
	
	
	//CLEAR BOARD
	public void clearGrid(World w) {
    	for(int y=0;y<GRIDHEIGHT;y++) {
        	for(int x=0;x<GRIDWIDTH;x++) {
        		if(w.grid[y][x] != mp) {
        			w.grid[y][x] = null;
        		}
        	} 
        }
    }
	
	
	//AUTO MOVE PIECE DOWN BOUND TO "A"
	public boolean autoDownTrue = false;
	public boolean speedUpTrue = false;
	public int gameSpeed = 1000;
	public void autoDown() {
		Thread autoDownThread  = new Thread() {
			public void run() {
				int count = 0;
				while(autoDownTrue) {
					

					if(w.getAt(4, 0) == mp && !(mp.boundary(w, "down"))) {
						autoDownTrue = false;
						println("GAME OVER");
						break;
					}

					
		            try {
					mp.move(w, "down");
					println("auto moving piece down");
					if(mp.boundary(w, "down")) count = 0;
					if(!mp.boundary(w, "down")) println(++count+" boundary count");
					if(count >= 2) {
						println("spawn new piece");
						spawnPiece();
						setPiece = true;
						count = 0;
					}
					
					graphics.repaint();
					Thread.sleep(gameSpeed);
					} catch (InterruptedException e1) {
						autoDownTrue = false;
						e1.printStackTrace();
					}
				}
			}
		};
		autoDownThread.start();
	}

	//MAIN DRIVER CLASS
	/*
    public static void main(String[] args) {
    	Grid GUI = new Grid();
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    GUI.createGUI();
            }
        });       
    }
    */

}