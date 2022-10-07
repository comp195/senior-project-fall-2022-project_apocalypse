package default_package;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
//import edu.pacific.comp55.starter.MainApplication;

// Second Commit by Eesa Khan

public class ScreenDisplayPane extends GraphicsPane implements ActionListener {
	
	
	private MainApplication program;
	private Timer timer;
	private ArrayList<GImage> background;
	private ArrayList<GImage> playerHealth;
	private int currentRoom; // to display current room
	private static final int BACKGROUND_TILE_SIZE = 50;
	
	public ScreenDisplayPane(MainApplication app) {
		super();
		program = app;
		timer = new Timer(0, this); // create timer object
		initializeGame();
	}
	
	private void initializeGame() {
		currentRoom = 1; // starting room number
		playerHealth = new ArrayList<GImage>(); // initialize playerHealth
		
		
		
	}
	
	public void setBackground(String File) {
		//background = new ArrayList<GImage>();
		//background.add(new GImage(File,BACKGROUND_TILE_SIZE, BACKGROUND_TILE_SIZE ));
		//for (GImage b: background) { //Add all tiles to the screen.
		//	program.add(b);
		//}
		
		
		
		//program.add(backgroundImage)
	}

	public static void main(String[] args) {
		//new ScreenDisplayPane().start();
	}

	@Override
	public void showContents() {
		program.add(new GImage("city-map.jpg",BACKGROUND_TILE_SIZE, BACKGROUND_TILE_SIZE ));
		program.add(new GImage("Fi-short-ranged.PNG",BACKGROUND_TILE_SIZE, BACKGROUND_TILE_SIZE));
		//setBackground("city-map.jpg");
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
