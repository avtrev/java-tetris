import java.awt.EventQueue;

public class TetrisDriver {

	public static void main(String[] args) {
    	Grid GUI = new Grid();
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    GUI.createGUI();
            }
        });

	}

}
