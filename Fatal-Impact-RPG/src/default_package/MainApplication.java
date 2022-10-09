package default_package;

import java.awt.Color;
import java.util.ArrayList;

import default_package.GButton;
import default_package.GParagraph;
import acm.graphics.GImage;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.Timer;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" };

	private HowToPlayPane howToPlay;
	private PlayerSelectionPane chooseCharacter;
	private MainMenuPane menu;
	private ScreenDisplayPane display; //IMPLEMENT LATER
	private GameOverPane gameEnd;
	private boolean closeRangeCharacter;
	private boolean audioOn;
	private boolean playerWin;
	private int count;
	
	
	public Player player = new Player(0, 0, this);
	//private ArrayList<Item> items = new ArrayList <Item>();
	
	// Player health GUI
	private GParagraph healthPoints; 
	private static final int heartRootX = 75, heartRootY = 610, heartWidth = 30;
	ArrayList <GImage> playerHearts = new ArrayList <GImage>();
	private Font customFont;
	
	

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		howToPlay = new HowToPlayPane(this);
		chooseCharacter = new PlayerSelectionPane(this);
		menu = new MainMenuPane(this);
		display = new ScreenDisplayPane(this); //IMPLEMENT LATER
		gameEnd = new GameOverPane(this);
		audioOn = true; // audio is on by default
		setupInteractions();
		switchToMenu();
		 
	}
	
	
	/* GUI
	 * -----------------------------
	 * setGUI()
	 * addGUI()
	 * removeGUI()
	 * updatePlayerHeartsGUI(int hp)
	 */
	
	public void setGUI() {
		healthPoints = new GParagraph("HP:", 45, 625);
		healthPoints.setColor(Color.white); 
		healthPoints.setFont(this.customFont);
	}
	public void addGUI() {
		this.add(healthPoints);
		updatePlayerHeartsGUI(this.player.getHP());
	}
	public void removeGUI() {
		this.remove(healthPoints);
		updatePlayerHeartsGUI(0);
	}
	
	
	public void updatePlayerHeartsGUI(int hp) {
		int heartLen = playerHearts.size();
		int dif = hp - heartLen;
		if (dif > 0) {
			for (int i = 0; i < dif; i++) {
				GImage heart = new GImage("media/HP.png", heartRootX + ((heartLen + i) * heartWidth), heartRootY);
				heart.setSize(25, 20);
				heart.setVisible(true); 
				playerHearts.add(heart);
				this.add(heart); 
			}
		}
		else if (dif < 0) {
			dif = dif * -1; // Absolute value
			for (int i = 0; i < dif; i++) {
				int end = playerHearts.size() - 1;
				GImage heart = playerHearts.get(end);
				this.remove(heart);
				playerHearts.remove(end);
			}
		}
	}
	
	
	/* public void grab(Item item)   {
		this.player.grabItem(item);
		item.setPickedUp(true);
		this.remove(item.getImage());
		this.add(item.getInvSprite());
	} */
	
	
	
	

	public void switchToMenu() {
		count++;
		switchToScreen(menu);
	}

	public void switchTo(int n) {
		if (n == 0) {
			switchToScreen(chooseCharacter);
		}
		else if (n == 1){ // n == 1
			switchToScreen(howToPlay);
		}
		else if (n == 2) {
			switchToScreen(display);
		}
		else if (n == 3) {
			switchToScreen(gameEnd);
		}
	}
	//IMPLEMENT LATER
	/*
	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	*/
	public static void main(String[] args) {
		new MainApplication().start();
	}

	public boolean isCloseRangeCharacter() {
		return closeRangeCharacter;
	}

	public void setCloseRangeCharacter(boolean closeRangeWeapon) {
		this.closeRangeCharacter = closeRangeWeapon;
	}

	public boolean isAudioOn() {
		return audioOn;
	}

	public void setAudioOn(boolean audioOn) {
		this.audioOn = audioOn;
	}

	public boolean isPlayerWin() {
		return playerWin;
	}

	public void setPlayerWin(boolean playerWin) {
		this.playerWin = playerWin;
	}
}