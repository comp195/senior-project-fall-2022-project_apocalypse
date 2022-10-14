package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
	private static final int PLAYER_STARTING_SPEED = 7;
	private static final double SQRT_TWO_DIVIDED_BY_TWO = 0.7071067811865476;
	
	private MainApplication program;
	private Timer timer;
	private int timerCount; // to keep track of timer
	private ArrayList<GImage> background;
	private ArrayList<GImage> playerHealth;
	private ArrayList<Item> items; // items to display on the level.
	private int currentMap; // to display current room
	private static final int BACKGROUND_TILE_SIZE = 50;
	private GImage map; 
	private GImage inventory;
	private int inventoryDisplayIndex;
	private int inventoryIndex;
	private int inventorySizeCount;
	private int populatingItemsIndex;
	
	//main game objects
	private Player player;
	

	
	private GParagraph healthPoints; 
	private static final int heartRootX = 75, heartRootY = 610, heartWidth = 30;
	ArrayList <GImage> playerHearts = new ArrayList <GImage>();
	private Font customFont;
	
	
	
	public ScreenDisplayPane(MainApplication app) {
		super();
		program = app;
		timer = new Timer(0, this); // create timer object
		initializeGame();
	}
	
	private void initializeGame() {
		
		
		currentMap = 1; // starting room number
		map = new GImage("city-map.jpg", 0, 0);
		map.setSize(800, 640); 
		
		playerHealth = new ArrayList<GImage>(); // initialize playerHealth
		GImage playerSprite = new GImage ("FI-short-ranged.PNG");
		player = new Player(playerSprite, PLAYER_STARTING_HEALTH);
		player.randomizeXLocation(program.getWidth(), program.getHeight()); //Randomize player location at bottom of screen
		player.setSpeed(PLAYER_STARTING_SPEED); // initialize speed
		
		
		inventory = new GImage("HotBar.png", 300, 535);
		inventoryDisplayIndex = 0;
		inventoryIndex = 0;
		inventorySizeCount = 0;
		//food = new GImage("waterBottle.jpg", 300, 100);
		
		
		
		timer.restart(); // reset timer
	}
	
	public void setBackground(String File) {
		background = new ArrayList<GImage>();
		background.add(new GImage(File, 800, 640));
		//background.add(new GImage(File,BACKGROUND_TILE_SIZE, BACKGROUND_TILE_SIZE ));
		//for (GImage b: background) { //Add all tiles to the screen.
		//	program.add(b);
		//}
		
		
		
		//program.add(backgroundImage)
	}
	
	public void createMap(int mapNum) {
		timer.start();
		Map newMap = new Map(mapNum, program.getWidth(), program.getHeight());
		// TODO Auto-generated method stub
		//program.removeAll();
		setBackground(newMap.getImageName()); //Set background map
		
		program.add(player.getSprite()); //Add player sprite to screen.
		player.getSprite().sendToFront(); //send player sprite to front.
		
		items = new ArrayList<Item>(); // initialize items in items arrayList. 
		
		Item water = new Item(new GImage("waterBottle.jpg", 300, 100), "water");
		populatingItems(water);
		
		//items.add(water);
		//items.get(0).getSprite().setSize(50, 50);
		//program.add(items.get(0).getSprite());
		
		
		Item water2 = new Item(new GImage("waterBottle.jpg", 400, 200), "water");
		populatingItems(water2);
		
		//items.add(water2);
		//items.get(1).getSprite().setSize(50, 50);
		//program.add(items.get(1).getSprite());
		
		Item water3 = new Item(new GImage("waterBottle.jpg", 500, 300), "water");
		populatingItems(water3);
		
		Item water4 = new Item(new GImage("waterBottle.jpg", 600, 100), "water");
		populatingItems(water4);
		
		Item water5 = new Item(new GImage("waterBottle.jpg", 550, 100), "water");
		populatingItems(water5);
		
		Item water6 = new Item(new GImage("waterBottle.jpg", 600, 300), "water");
		populatingItems(water6);
		
		/* For adding all maps to the screen
		for (GImage currentMap: background) { //Add all tiles to the screen.
			program.add(currentMap);
		}
		*/ 
		
		updateHealth();
		
	}
	
	public void populatingItems(Item item) {
		items.add(item);
		items.get(populatingItemsIndex).getSprite().setSize(30, 30);
		program.add(items.get(populatingItemsIndex).getSprite());
		populatingItemsIndex++;
	}
	
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
	
	public double inRange(double x, double min, double max) { // return value between minimum and maximum
        if (x > min && x < max) {
            return x;
        } else if (x <= min){
            return min + 1;
        } else { // x >= max
            return max - 1;
        }
    }
	
	private void setInBounds(Entity character) { // set character sprite in bounds on the screen
        GImage sprite = character.getSprite();
        double x = sprite.getX();
        double y = sprite.getY();
        double min = 0;
        double xMax = program.getWidth() - 82; // before it was - 1.75 * sprite.getWidth()
        double yMax = program.getHeight() - 150; // before it was - 2.25 * sprite.getHeight()
        /* if (character instanceof Enemy) { // check if character is an enemy
            yMax = program.getHeight() - 3 * sprite.getHeight();
        } */
        sprite.setLocation(inRange(x, min, xMax), inRange(y, min, yMax)); // set location of sprite in bounds
    }
	
	public void gameOver() {
		System.out.println("Player is dead. Game Over.");
		program.removeAll(); // remove all objects from screen
		initializeGame(); // reset all game values
		timer.stop();
		populatingItemsIndex = 0;
		program.switchTo(3); // switch to game end screen
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		GImage playerSprite = player.getSprite();
		
		int keyCode = e.getKeyCode();
		if (keyCode == 87) { // w
			player.setMoveY(-1);
		} else if (keyCode == 65) { // a
			player.setMoveX(-1);
		} else if (keyCode == 83) { // s
			player.setMoveY(1);
		} else if (keyCode == 68) { // d
			player.setMoveX(1);
		}else if (keyCode == 16) { // shift
			timer.stop();
			player.setSpeed(PLAYER_STARTING_SPEED*2); //Make player very fast if sprinting
			timer.start();
		}else if (keyCode == 69) { // e
			//Player should pick up an item if it's an item.
			Item nearestItem = player.nearestItem(items); //check for item nearest to player
			//If player can interact with closest item and presses "e"
			if (player.canInteract(nearestItem.getSprite().getX(), nearestItem.getSprite().getY())) {
				if (inventorySizeCount < 5) {
					player.addToInventory(nearestItem); // add item to player inventory
					//Move item to inventory location
					player.getInventory().get(inventoryIndex).getSprite().setLocation(inventory.getX() + inventoryDisplayIndex, inventory.getY() + 33);
					player.getInventory().get(inventoryIndex).getSprite().sendToFront();
				}
				inventoryDisplayIndex += 32;
				inventoryIndex++;
				inventorySizeCount++;
				//TO DO: Need to create boundary around player inventory so that items can't be added again
			}
		} else if (keyCode == 82) { // r
			int removeIndex = -1;
			if (player.getInventory().size() > 0) {
				removeIndex = player.searchItemIndex(player, removeIndex, "water");
				if (removeIndex >= 0) { //check if the player has the heart to remove
					//player.getInventory().get(removeIndex).getSprite().setLocation(0, 0);
					program.remove(player.getInventory().get(removeIndex).getSprite());
					player.removeFromInventory(removeIndex); //Remove the heart from the inventory.
					
					System.out.println("water consumed");
					player.SetThirst(player.GetThirst() + 50);
					
					inventorySizeCount--;
					inventoryIndex--;
					inventoryDisplayIndex -= 32;
				}
			}
		}
		  
		//playerSprite.move(player.getMoveX() * player.getSpeed(), player.getMoveY() * player.getSpeed()); // move playerSprite
		// for normalizing diagonal movement
		if (Math.abs(player.getMoveX()) == 1 && Math.abs(player.getMoveY()) == 1) { // check if diagonal movement is happening
			player.setMoveX(player.getMoveX() * SQRT_TWO_DIVIDED_BY_TWO);
			player.setMoveY(player.getMoveY() * SQRT_TWO_DIVIDED_BY_TWO);
		}
				
		playerSprite.move(player.getMoveX() * player.getSpeed(), player.getMoveY() * player.getSpeed()); // move playerSprite
	    setInBounds(player); 
		
	}
	
	@Override 
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 87 || keyCode == 83) { // w or s -> reset vertical movement when released
			player.setMoveY(0);
		} else if (keyCode == 65 || keyCode == 68) { // a or d -> reset horizontal movement when released
			player.setMoveX(0);
		}
		
		//Reset player speed to normal speed if player stops sprinting
		//This is important so that the player can stop running fast.
		player.setSpeed(PLAYER_STARTING_SPEED);
	}

	@Override
	public void showContents() {
		program.add(map);
		createMap(currentMap); // currentMap is initially at 1
		program.add(inventory);
		
		
	
		//program.add(new GImage("Fi-short-ranged.PNG",BACKGROUND_TILE_SIZE, BACKGROUND_TILE_SIZE));
		
		
		//setBackground("city-map.jpg");
		// TODO Auto-generated method stub
		//program.player.getInventory();
		//program.add(Inventory.INVENTORY_IMG, Inventory.INVENTORY_X, Inventory.INVENTORY_Y);
		//program.add(healthPoints);
		
		
	}

	

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		//program.remove(map);
		//program.remove(Inventory.INVENTORY_IMG);
		//program.removeGUI();
		//program.remove(healthPoints);
		program.removeAll();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timerCount++;
		
		//Slowly reduce hunger and thirst
		if (timerCount % 100 == 0) {
			player.SetHunger(player.GetHunger() - 1);
			player.SetThirst(player.GetThirst() - 1);
			System.out.println("Hunger: " + player.GetHunger());
			System.out.println("Thirst: " + player.GetThirst());
		}
		
		//When hunger and thirst run out, game over.
		if (player.GetHunger() == 0 || player.GetThirst() == 0) {
			gameOver();
		}
		
		
		
	}
}
