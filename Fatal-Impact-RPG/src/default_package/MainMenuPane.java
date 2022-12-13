package default_package;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.MouseEvent;

import default_package.GButton;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class MainMenuPane extends GraphicsPane {
	//Main menu Pane
	private static final int FONT_SIZE = 50;
	
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GLabel game;
	private GButton play;
	private GButton howToPlay;
	private GImage mainMenu; 
	private final int WIDTH = 200;
	private final int HEIGHT = 88;

	public MainMenuPane(MainApplication app) {
		super();
		program = app;
		
		game = new GLabel("Fatal Impact");
		mainMenu = new GImage("main menu.png", 0, 0);
		mainMenu.setSize(800,640);
		game.setFont(new Font("Merriweather", Font.BOLD, FONT_SIZE));
		game.setColor(Color.RED);
		game.setLocation(program.getWidth() / 2 - game.getWidth() / 2, program.getHeight() / 5);
		
		// double xCenter = program.getWidth() / 2 - BUTTON_SIZE / 2;
		double X = app.getWidth()/2 - WIDTH/2 -7;
		
		
		play = new GButton("Play", X, 240, WIDTH, HEIGHT);
		play.setFillColor(Color.GREEN);
		
		howToPlay = new GButton("How to play", X, 345, WIDTH, HEIGHT);
		howToPlay.setFillColor(Color.GREEN);
		
	}

	@Override
	public void showContents() {
		program.add(mainMenu);
		program.add(game);
		program.add(play);
		program.add(howToPlay);
	}

	@Override
	public void hideContents() {
		program.remove(play);
		program.remove(howToPlay);
		program.remove(mainMenu);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == play) {
			program.remove(game);
			program.switchTo(0); // switch to playGame pane
		}
		else if (obj == howToPlay) {
			program.remove(game);
			program.switchTo(1); // switch to howToPlay pane
		}
	}
}

