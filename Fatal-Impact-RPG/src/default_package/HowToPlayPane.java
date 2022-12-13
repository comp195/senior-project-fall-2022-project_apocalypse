package default_package;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class HowToPlayPane extends GraphicsPane {
	
	private static final int BUTTON_SIZE = 100;
	
	private MainApplication program; // you will use program to get access to
									// all of the GraphicsProgram calls
	private GParagraph howToPlay;
	private GButton menu;
	private GButton play;
	private GImage mainMenu;
	private GImage forestPathPic;
	private final int WIDTH = 200;
	private final int HEIGHT = 88;

	public HowToPlayPane (MainApplication app) {
		this.program = app;
		
		mainMenu = new GImage("main menu.png", 0, 0);
		mainMenu.setSize(800,640);
		// double xCenter = program.getWidth() / 2 - BUTTON_SIZE / 2;
		
		double X = app.getWidth()/2 - WIDTH/2 -7;
		
		play = new GButton("Play", X, 100, WIDTH, HEIGHT);
		play.setFillColor(Color.GREEN);
		
		menu = new GButton("Menu", X, 240, WIDTH, HEIGHT);
		menu.setFillColor(Color.GREEN);
		
		howToPlay = new GParagraph("Press w, a, s, d to move up, down, left, right, respectively\nPress SHIFT to dash\nPress x to equip/unequip knife\nPress the Left-Click button on the mouse to attack (must have knife equipped)\nPress e to interact with items, enter houses\nPress z to exit houses (when near a door)\nWhen at or near the end of forest pathway, press e to further explore the forest", 0, 0);
		howToPlay.setLocation(program.getWidth() / 2 - howToPlay.getWidth() / 2, (program.getHeight() * 4 / 5 - howToPlay.getHeight() / 2) - 100);
		howToPlay.setColor(Color.GREEN);
		
		forestPathPic = new GImage("Forest-Pathway-Guide.png", 382, 492);
		forestPathPic.setSize(100, 100);
		
	}
	
	@Override
	public void showContents() {
		program.add(mainMenu);
		program.add(howToPlay);
		program.add(menu);
		program.add(play);
		program.add(forestPathPic);
	}

	@Override
	public void hideContents() {
		program.remove(mainMenu);
		program.remove(howToPlay);
		program.remove(menu);
		program.remove(play);
		program.remove(forestPathPic);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == play) {
			program.switchTo(0); // switch to playGame pane
		}
		else if (obj == menu) {
			program.switchToMenu(); // switch to menu pane
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
