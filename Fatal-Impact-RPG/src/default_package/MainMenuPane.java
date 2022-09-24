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
	private static final int BUTTON_SIZE = 100;
	private static final int FONT_SIZE = 50;
	
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GLabel game;
	private GButton play;
	private GButton music;
	private GButton howToPlay;
	private String musicButtonText;
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
		
		
		play = new GButton("Play", X, 290, WIDTH, HEIGHT);
		play.setFillColor(Color.GREEN);
		
		howToPlay = new GButton("How to play", X, 395, WIDTH, HEIGHT);
		howToPlay.setFillColor(Color.GREEN);
		
		musicButtonText = "Audio ON";
		music = new GButton("Audio", X, 500, WIDTH, HEIGHT);
		music.setFillColor(Color.GREEN);
	}

	@Override
	public void showContents() {
		program.add(mainMenu);
		program.add(game);
		program.add(play);
		program.add(howToPlay);
		program.add(music);
	}

	@Override
	public void hideContents() {
		program.remove(play);
		program.remove(howToPlay);
		program.remove(music);
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
		else if (obj == music){
			program.remove(music);
			if (program.isAudioOn()) { // toggle audio button from ON to OFF
				music = new GButton("Audio OFF", music.getX(), music.getY(), music.getWidth(), music.getHeight());
				music.setFillColor(Color.RED);
			}
			else { // toggle audio button from OFF to ON
				music = new GButton("Audio ON", music.getX(), music.getY(), music.getWidth(), music.getHeight());
				music.setFillColor(Color.GREEN);
			}
			program.setAudioOn(!program.isAudioOn()); // toggle audio
			program.add(music);
		}
	}
}

