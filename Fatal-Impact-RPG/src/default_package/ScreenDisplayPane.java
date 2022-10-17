package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;


import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class ScreenDisplayPane extends GraphicsPane implements ActionListener {
	
	private static final int HEART_SIZE = 50;
	private static final int PLAYER_STARTING_HEALTH = 10;
	private static final int PLAYER_STARTING_SPEED = 7;
	private static final double SQRT_TWO_DIVIDED_BY_TWO = 0.7071067811865476;
	private static final int INTERACT_INTERVAL = 50;
	private static final int HUNGER_AND_THIRST_INTERVAL = 100;
	private static final int LONG_RANGE_ENEMY_ATTACK_INTERVAL = 200;
	
	private MainApplication program;
	private Timer timer;
	private int timerCount; // to keep track of timer
	private ArrayList<GImage> background;
	private ArrayList<GImage> playerHealth;
	private ArrayList<Item> items; // items to display on the level.
	private ArrayList<Zombie> zombies;
	private ArrayList<Integer> removeZombieIndex; // to keep track of enemy indexes to remove
	private int currentMap; // to display current room
	private static final int BACKGROUND_TILE_SIZE = 50;
	private GImage attackArea; // to display player attack
	private GImage map; 
	private GImage inventory;
	private int inventoryDisplayIndex;
	private int inventoryIndex;
	private int inventorySizeCount;
	private int populatingItemsIndex;
	
	//main game objects
	private Player player;
	
	
	//pause and resume
	private GImage pauseImg;
	public GButton pauseButton;
	public boolean paused = false;
	public GButton resume = new GButton("", 296, 180, 208, 95);
	public GButton quit = new GButton("", 296, 420, 208, 95);
	private GImage quitImg = new GImage("Quit.png",296, 420);
	private GImage resumeImg = new GImage("Resume.png",296,180);

	
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
	
	private void removeAllDeadEnemies() {
		for (int y = removeZombieIndex.size() - 1; y >= 0 ; y--) {
			zombies.remove((int)removeZombieIndex.get(y));
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
	
	private double angle(GImage enemySprite, GImage playerSprite) { // return angle between player and enemy
		double x = (enemySprite.getX() + (enemySprite.getWidth() / 2)) - (playerSprite.getX() + (playerSprite.getWidth() / 2)); //x is set to horizontal distance between enemy and player
		double y = (enemySprite.getY() + (enemySprite.getHeight() / 2)) - (playerSprite.getY() + (playerSprite.getHeight() / 2));  //y is set to vertical distance between enemy and player
		double angle = 180 * Math.atan2(-y, x) / Math.PI; // calculate angle from player to enemy
		return angle;
	}
	
	private void initializeGame() {
		
		
		currentMap = 1; // starting room number
		map = new GImage("city-map.jpg", 0, 0);
		map.setSize(800, 640); 
		
		playerHealth = new ArrayList<GImage>(); // initialize playerHealth
		GImage playerSprite = new GImage ("FI-short-ranged.PNG");
		player = new Player(playerSprite, PLAYER_STARTING_HEALTH);
		zombies = new ArrayList<Zombie>(); // initialize enemy array list
		player.randomizeXLocation(program.getWidth(), program.getHeight()); //Randomize player location at bottom of screen
		player.setSpeed(PLAYER_STARTING_SPEED); // initialize speed
		
		
		inventory = new GImage("HotBar.png", 300, 535);
		inventoryDisplayIndex = 0;
		inventoryIndex = 0;
		inventorySizeCount = 0;
		populatingItemsIndex = 0;
		//food = new GImage("waterBottle.jpg", 300, 100);
		
		
		
		timer.restart(); // reset timer
	}
	
	private void checkPlayerDeath() {
			if (player.isDead()) {
				program.removeAll();
				while (zombies.size() > 0) { // remove all enemies from ArrayList
					zombies.remove(0);
				}
				gameOver(); // go to game over screen
			}
			
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
		timer.start(); //When the game restarts, this is important for restarting the timer.
		Map newMap = new Map(mapNum, program.getWidth(), program.getHeight());
		GImage zombieImage = new GImage("zombie.png", 500, 100);
		zombieImage.setSize(60, 100);
		Zombie zombie = new Zombie(zombieImage, 5, "zombie");
		zombie.setSpeed(10);
		zombies.add(zombie);
		for (Zombie z: zombies) { // loop for all enemies
			program.add(z.getSprite()); //Add enemy sprite to screen.
			
		}
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
		
		Item chicken1 = new Item(new GImage("chickenDrumstick.jpg", 400, 300), "food");
		populatingItems(chicken1);
		
		Item chicken2 = new Item(new GImage("chickenDrumstick.jpg", 400, 500), "food");
		populatingItems(chicken2);
		
		Item chicken3 = new Item(new GImage("chickenDrumstick.jpg", 300, 500), "food");
		populatingItems(chicken3);
		
		
		/* For adding all maps to the screen
		for (GImage currentMap: background) { //Add all tiles to the screen.
			program.add(currentMap);
		}
		*/ 
		
		updateHealth();
		
	}
	
	/* Pause and Resume
	 * ----------------
	 * pauseMenu(GObject obj)
	 * pause()
	 * resume()
	 */
	public void pauseMenu(GObject obj) {
		if (obj == this.pauseButton) {
			//this.monsterTimer.stop();
			
			this.pause();
		}
		if (obj == this.resume) {
			this.resume();
			//this.monsterTimer.setInitialDelay(0);
			//this.monsterTimer.restart();
		}	
		if (obj == this.quit) {System.exit(0);}
	}
	public void pause() {
		program.add(resumeImg);
		program.add(quitImg);
		program.add(resume);
		program.add(quit);
		paused = true;
	}
	public void resume() {
		program.remove(resumeImg);
		program.remove(resume);
		program.remove(quit);
		program.remove(quitImg);
		paused = false;
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
	}

	public static void main(String[] args) {
		//new ScreenDisplayPane().start();
	}
	
	public double inRange(double x, double min, double max) { // return value between minimum and maximum
        if (x > min && x < max) {
            return x;
        } else if (x <= min){
            return min + 1;
        } else { // x >= max
            return max - 1;
        }
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
			//replenish thirst from inventory.
			int removeIndexWater = -1;
			if (player.getInventory().size() > 0) {
				removeIndexWater = player.searchItemIndex(player, removeIndexWater, "water");
				if (removeIndexWater >= 0) { //check if the player has the heart to remove
					//player.getInventory().get(removeIndex).getSprite().setLocation(0, 0);
					program.remove(player.getInventory().get(removeIndexWater).getSprite());
					player.removeFromInventory(removeIndexWater); //Remove the heart from the inventory.
					
					System.out.println("water consumed");
					player.SetThirst(player.GetThirst() + 50);
					
					inventorySizeCount--;
					inventoryIndex--;
					inventoryDisplayIndex -= 32;
				}
			}
		} else if (keyCode == 81) { // q
			//Replenish hunger from inventory.
			int removeIndexFood = -1;
			if (player.getInventory().size() > 0) {
				removeIndexFood = player.searchItemIndex(player, removeIndexFood, "food");
				if (removeIndexFood >= 0) { //check if the player has the heart to remove
					//player.getInventory().get(removeIndex).getSprite().setLocation(0, 0);
					program.remove(player.getInventory().get(removeIndexFood).getSprite());
					player.removeFromInventory(removeIndexFood); //Remove the heart from the inventory.
					
					System.out.println("food consumed");
					player.SetHunger(player.GetHunger() + 50);
					
					inventorySizeCount--;
					inventoryIndex--;
					inventoryDisplayIndex -= 32;
				}
			}
		} else if (keyCode == KeyEvent.VK_ESCAPE) {
			pause();
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
	public void mouseClicked(MouseEvent e) {
		GImage playerSprite = player.getSprite();
		if (player.isAttackAvailable()) {
			//if (program.isCloseRangeCharacter()) {
			//TO DO: Move player attack logic here after attack cooldown implementation.
			//}
			removeZombieIndex = new ArrayList<Integer>(); // initialize array list for indexes of dead enemies
			for (int z = 0; z < zombies.size(); z++) { // loop for all enemies
				Zombie zombie = zombies.get(z);
				
				if (player.canInteract(zombie.getSprite().getX(), zombie.getSprite().getY())) { //player in range of enemy.
					System.out.println("Enemy is hit.");
					zombie.changeHealth(-1); //Reduce health by 1.
					if (zombie.isDead()) { //Enemy has no health.
						removeZombieIndex.add(z); // add zombie to list if he dies
						program.remove(zombie.getSprite()); //Remove enemy from the screen since he is dead.
						System.out.println("Enemy is dead.");
					}
				}
			}
		}
	
		
		
		removeAllDeadEnemies(); //Remove all zombie objects added to the dead list
		player.setAttackAvailable(false); // Initiate attack cool down.
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GImage playerSprite = player.getSprite();
		removeZombieIndex = new ArrayList<Integer>(); // initialize array list for indexes of dead enemies
		timerCount++;
		for (int z = 0; z < zombies.size(); z++) { // loop through all enemies
			Zombie zombie = zombies.get(z);
			GImage zombieSprite = zombie.getSprite();
			if (zombie.canInteract(playerSprite.getX(), playerSprite.getY())) { //enemy detects player
				if (timerCount % INTERACT_INTERVAL == 0) {
					zombieSprite.movePolar(zombie.getSpeed(), angle(zombieSprite, playerSprite) + 180); // close range zombie moves towards player
					if (timerCount % player.getAttackCooldown() == 0) {
						System.out.println("Attack Available!");
						player.setAttackAvailable(true); //player can now attack
					}
					else if (!player.isAttackAvailable()){
						System.out.println("Player is too tired to attack!");
					}
					if (timerCount % LONG_RANGE_ENEMY_ATTACK_INTERVAL == 0) {
						for (Zombie z1 : zombies) {
							z1.setAttackAvailable(true); //enemy can now attack
						}
					}
				}
				
				if (Collision.check(zombie.getSprite().getBounds(), player.getSprite().getBounds()) && zombie.isAttackAvailable()) { // player collides with enemy
					//playSound("player", AudioPlayer.getInstance()); // player is damaged
					double x = (zombieSprite.getX() + (zombieSprite.getWidth() / 2)) - (playerSprite.getX() + (playerSprite.getWidth() / 2)); //x is set to horizontal distance between enemy and player
					double y = (zombieSprite.getY() + (zombieSprite.getHeight() / 2)) - (playerSprite.getY() + (playerSprite.getHeight() / 2));  //y is set to vertical distance between enemy and player
					playerSprite.movePolar(Math.sqrt(x*x+y*y) * 2, angle(zombieSprite, playerSprite) + 180); // player moves away from enemy
					
					/*
					if (zombie.getEnemyType().contains("boss")) {
						player.changeHealth(-2);
					}
					*/
					//else {
					player.changeHealth(-1);
					//}
					updateHealth();
					//updateInventory(); ==> Should update player inventory based on player health.
					System.out.println("Player hit by " + zombie.getEnemyType() + ". Player health: " + player.getHealth());
					player.setDamaged(true); //player is damaged.
					checkPlayerDeath();
					zombie.setAttackAvailable(false);
				}
			}
			setInBounds(zombie); // set long range enemy in bounds
			
		}
		
		
		
		//Slowly reduce hunger and thirst
		if (timerCount % HUNGER_AND_THIRST_INTERVAL == 0) {
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

	
}