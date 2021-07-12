
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.*;

public class Grid extends Thread implements KeyListener{

	public static final int GRIDSIZE = 10;
	public static final int GRIDWIDTH = 10;
	public static final int GRIDHEIGHT = 20;
	public static final int SQUARE = 40;//square pixel size of cell
	
    public static int[][][]grid = new int[GRIDHEIGHT][GRIDWIDTH][2];
    public boolean[] moveControl = {false,false,false,false};
    
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
    private Cell mp;
    
    //constructor
    public Grid() {
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
		        for(int i=0;i<rowFull.length;i++) rowFull[i] = true;
				boolean full = true;
				boolean shiftDown = false;
				for(int y=0;y<GRIDHEIGHT;y++) {
					for(int x=0;x<GRIDWIDTH;x++) {
						//if row is full
						if(w.grid[y][x] == null) rowFull[y] = false;
					}
				}
				//check if any rows are full
				for(int y=0;y<rowFull.length;y++) {
					if(rowFull[y]) {
						for(int x=0;x<GRIDWIDTH;x++) w.setAt(x,y,null);
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
									  if(w.grid[Y][X] != mp && w.grid[Y][X] != null) {
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
				for(int i=0;i<rowFull.length;i++) rowFull[i]=false;
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
	        headerPanel.add(scoreLable);
	        headerPanel.add(scoreNumber);
	        frame.add(headerPanel,BorderLayout.NORTH);
	        
    	} else {
    		frame.remove(headerPanel);
    	}
    	frame.pack();
    }
    
    //SHOW HELP METHOD & VARIABLES
    boolean showHelp = false;

    JLabel menuItems[] = new JLabel[] {
    		new JLabel("HELP MENU"),
    		new JLabel("Enter Or P = Start/Pause"),
    		new JLabel("Up Down Left Right = Move Shape"),
			new JLabel("Shift (Left Or Right) = Drop Shape To Bottom"),
			new JLabel("Space = Rotate Shape"),
			new JLabel("Z = Set Shape In Place"),
			new JLabel("S = Show Score"),
			new JLabel("R = Reset"),
			new JLabel("F = Toggle Speed Increase (Default On)"),
			new JLabel("H = Show Help Menu"),
    				};
    JPanel helpMenuPanel = new JPanel();
    
    
    public void showHelp() {
    	showHelp = !showHelp;
    	if(showHelp) {
    		helpMenuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    		helpMenuPanel.setBackground(Color.lightGray);
    		helpMenuPanel.setLayout(new BoxLayout(helpMenuPanel, BoxLayout.Y_AXIS));
    		for (JLabel item : menuItems) helpMenuPanel.add(item);
	        frame.add(helpMenuPanel,BorderLayout.EAST);
	        
    	} else {
    		frame.remove(helpMenuPanel);
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
    	// MOVE UP
    	if (e.getKeyCode() == KeyEvent.VK_UP ) { 
	    	//Up arrow key code
	    	System.out.println("Up keypressed");
            //MAIN SHAPES MOVE UP
            if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"up");
	    	graphics.repaint();
	    // MOVE DOWN	
    	} else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    		//Down arrow key code
    		System.out.println("Down keypressed");
            //MAIN SHAPES MOVE DOWN
            if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"down");
    		graphics.repaint();
    	//MOVE LEFT    
    	} else if (e.getKeyCode() == KeyEvent.VK_LEFT ) { 
    		//Left arrow key code
    		System.out.println("Left keypressed");
    		//MAIN SHAPES MOVE LEFT
    		if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"left");
    		graphics.repaint();
    	//MOVE RIGHT	
    	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT ) { 
    		//right arrow key code
            System.out.println("Right keypressed");
            //MAIN SHAPES MOVE RIGHT
            if(lShapeATrue||lShapeBTrue||skewAtrue||skewBtrue||straightTrue||squareTrue||tShapeTrue)mp.move(w,"right");
            graphics.repaint();
        //ROTATE SELECTED SHAPE
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE ) {
        	//space bar
        	System.out.println("SPACE pressed = ROTATE SHAPE");
        		mp.rotate(w);
        	graphics.repaint();
        //SET PIECE SPAWN NEW PIECE
        } else if (e.getKeyCode() == KeyEvent.VK_Z ) {
        	//Z key
        	System.out.println("Z pressed = SET SHAPE");
        	spawnPiece();
        	setPiece = true;
        	graphics.repaint();
    	//DROP PIECE SPAWN NEW PIECE
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT ) {
        	//shift key (left or right)
        	System.out.println("SHIFT pressed = DROP SHAPE");
    		mp.drop(w);
        	spawnPiece();
        	setPiece = true;
        	graphics.repaint();
        //RESET SCREEN
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
        	//R key
        	clearGrid(w);
        	score = 0;
        	scoreNumber.setText(String.valueOf(score));
        	gameSpeed = 1000;
        	graphics.repaint();
        //TOGGLE AUTO MOVE DOWN	
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_P) {
        	println("TOGGLE AUTO DOWN");
        	autoDownTrue = !autoDownTrue;
        	if(autoDownTrue) {
        		autoDown();
        	}
    	//TOGGLE SCORE
        } else if (e.getKeyCode() == KeyEvent.VK_S ) {
        	System.out.println("toggle show score");
        	showScore();
        //TOGGLE SPEED UP GAME
        } else if (e.getKeyCode() == KeyEvent.VK_F ) {
        	//F key
        	speedUpTrue = !speedUpTrue;
        	System.out.println("toggle speed up game "+speedUpTrue);
        	if(!speedUpTrue) gameSpeed = 1000;
        //SHOW HELP MENU
	    } else if (e.getKeyCode() == KeyEvent.VK_H ) {
	    	//H key
	    	System.out.println("TOGGLE HELP MENU");
	    	showHelp();
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
	public boolean speedUpTrue = true;
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

}