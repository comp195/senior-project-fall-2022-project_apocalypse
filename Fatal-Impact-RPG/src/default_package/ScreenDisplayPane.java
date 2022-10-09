package default_package;

import java.awt.Color;
import java.awt.Font;
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


public class ScreenDisplayPane extends GraphicsPane implements ActionListener {
	
	private static final int HEART_SIZE = 50;
	private static final int PLAYER_STARTING_HEALTH = 10;
	
	private MainApplication program;
	private Timer timer;
	private ArrayList<GImage> background;
	private ArrayList<GImage> playerHealth;
	private int currentRoom; // to display current room
	private static final int BACKGROUND_TILE_SIZE = 50;
	private GImage map; 
	
	//main game objects
	private Player player;
	

	
	private GParagraph healthPoints; 
	private static final int heartRootX = 75, heartRootY = 610, heartWidth = 30;
	ArrayList <GImage> playerHearts = new ArrayList <GImage>();
	private Font customFont;
	
	public void updateHealth() {
		while (playerHealth.size() > 0) { // remove all player hearts from screen
			program.remove(playerHealth.get(0));
			playerHealth.remove(0);
		}
		
		playerHealth = player.displayHealth();
		for (GImage heart : playerHealth) { // display all player hearts
			heart.setSize(HEART_SIZE, HEART_SIZE);
			program.add(heart);
		}
		/*
		if (currentRoom % 6 == 0) { // every sixth room is a boss room
			bossLabel.sendToFront();
			while (bossHealth.size() > 0) { // remove all boss hearts from screen
				program.remove(bossHealth.get(0));
				bossHealth.remove(0);
			}
			if (enemies.size() > 0) {
				for (Enemy e: enemies) { // loop through all enemies on screen
					if (e instanceof Boss) { // check if enemy is a boss
						if (e.isDead()) { // check if boss is dead
							program.remove(bossLabel); // remove bossLabel from screen
							if (!dropWeaponUpgrade) { // if weapon has not been dropped yet
								String upgradeType = ImageFolder.get() + "WizardUpgrade.png";
								if (program.isCloseRangeCharacter()) {
									upgradeType = ImageFolder.get() + "KnightUpgrade.png";
								}
								GImage upgradeSprite = new GImage (upgradeType, e.getSprite().getX(), e.getSprite().getY() + ITEM_SIZE); //Create a new sprite for weapon upgrade.
								Weapon upgrade = new Weapon(upgradeSprite, "upgrade");
								items.add(upgrade); // add weaponUpgrade to items list
								program.add(upgradeSprite); // add weapon upgrade to screen
								program.add(upgrade.getLabel()); //add label to screen.
								player.getSprite().sendToFront(); // send player sprite to front
								dropWeaponUpgrade = true; // weapon has been dropped
								addHeart(e, 0, ITEM_SIZE * 2);
								addHeart(e, 0, -ITEM_SIZE);
							}
						}
						else { // boss is alive
							bossLabel.setLocation(bossLabel.getX(), inventoryBox.getHeight() + ITEM_SIZE); // update boss label based on player inventory
							int xOffset = (int)program.getWidth() - (int)(HEART_SIZE * 1.5);
							int yOffset = ITEM_SIZE * (3 + (int)(player.getInventory().size() / 10));
							bossHealth = ((Boss) e).displayHealth(xOffset, yOffset);
						}
					}
				}
			}
		}
		*/
	}
	
	public ScreenDisplayPane(MainApplication app) {
		super();
		program = app;
		timer = new Timer(0, this); // create timer object
		map = new GImage("city-map.jpg", 0, 0);
		map.setSize(800, 640); 
		initializeGame();
	}
	
	private void initializeGame() {
		currentRoom = 1; // starting room number
		playerHealth = new ArrayList<GImage>(); // initialize playerHealth
		GImage playerSprite = new GImage ("Fi-short-ranged.PNG");
		player = new Player(playerSprite, PLAYER_STARTING_HEALTH);
		
		
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
	
	/*
	public void setGUI() {
		healthPoints = new GParagraph("HP:", 45, 625);
		healthPoints.setColor(Color.white); 
		healthPoints.setFont(this.customFont);
	}
	public void addGUI() {
		//this.add(healthPoints);
		//updatePlayerHeartsGUI(this.player.getHP());
	}
	public void removeGUI() {
		//this.remove(healthPoints);
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
				//this.add(heart); 
			}
		}
		else if (dif < 0) {
			dif = dif * -1; // Absolute value
			for (int i = 0; i < dif; i++) {
				int end = playerHearts.size() - 1;
				GImage heart = playerHearts.get(end);
				//this.remove(heart);
				playerHearts.remove(end);
			}
		}
	}
	*/
	

	

	@Override
	public void showContents() {
		program.add(map); 
		//program.add(new GImage("Fi-short-ranged.PNG",BACKGROUND_TILE_SIZE, BACKGROUND_TILE_SIZE));
		updateHealth();
		//setBackground("city-map.jpg");
		// TODO Auto-generated method stub
		//program.player.getInventory();
		//program.add(Inventory.INVENTORY_IMG, Inventory.INVENTORY_X, Inventory.INVENTORY_Y);
		//program.add(healthPoints);
		
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(map);
		//program.remove(Inventory.INVENTORY_IMG);
		//program.removeGUI();
		//program.remove(healthPoints);
		//program.removeAll();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
