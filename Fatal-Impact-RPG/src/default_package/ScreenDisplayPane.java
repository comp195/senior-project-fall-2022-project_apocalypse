package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.graphics.*;
import acm.program.*;

public class ScreenDisplayPane extends GraphicsPane implements ActionListener {
	
	private static final int ATTACK_ANIMATION_INTERVAL = 15;
	private static final int PLAYER_SPRITE_SIZE = 30;
	private static final int HEART_SIZE = 50;
	private static final int PLAYER_STARTING_HEALTH = 10;
	private static final int PLAYER_STARTING_SPEED = 5;
	private static final double SQRT_TWO_DIVIDED_BY_TWO = 0.7071067811865476;
	private static final int INTERACT_INTERVAL = 50;
	private static final int HUNGER_AND_THIRST_INTERVAL = 100;
	private static final int LONG_RANGE_ENEMY_ATTACK_INTERVAL = 200;
	
	private MainApplication program;
	private Timer timer;
	private int timerCount; // to keep track of timer
	private int Z = 0;
	private ArrayList<GImage> background;
	private ArrayList<GImage> playerHealth;
	private ArrayList<Item> items; // items to display on the level.
	private ArrayList<Zombie> zombies;
	private ArrayList<Zombie2> zombies2; 
	private ArrayList<Integer> removeZombieIndex; // to keep track of enemy indexes to remove
	private int currentMap; // to display current room
	private static final int BACKGROUND_TILE_SIZE = 50;
	private GImage attackArea; // to display player attack
	private GImage map; 
	private GImage mapTree;
	private GImage mapHouse;
	private GImage mapHouse2;
	private GImage mapHouse3;
	private GImage inventory;
	private int inventoryDisplayIndex;
	private int inventoryIndex;
	private int inventorySizeCount;
	private int populatingItemsIndex;
	
	//player animation members
	private boolean knifeEquipped;
	private int equipOnAndOff;
	private boolean enemyHitDown = false;
	private boolean enemyHitUp = false;
	private boolean enemyHitLeft = false;
	private boolean enemyHitRight = false;
	
	private boolean playerHitDown = false;
	private boolean playerHitUp = false;
	private boolean playerHitLeft = false;
	private boolean playerHitRight = false;
	
	private boolean zombieMoveDown = false;
	private boolean zombieMoveUp = false;
	private boolean zombieMoveLeft = false;
	private boolean zombieMoveRight = false;

	private int attackAnimationDownAcc = 0;
	private int attackAnimationAcc = 0;
	
	private int zombieAttackAnimationDownAcc = 0;
	
	
	private int frame = 0;
	private int frame2 = 0;
	private int frame3 = 0;
	private int frame4 = 0;
	
	private int playerMovingDownKnifeFrame = 0;
	private int playerMovingUpKnifeFrame = 0;
	private int playerMovingLeftKnifeFrame = 0;
	private int playerMovingRightKnifeFrame = 0;
	
	private int playerAttackDownKnifeFrame = 0;
	private int playerAttackUpKnifeFrame = 0;
	private int playerAttackLeftKnifeFrame = 0;
	private int playerAttackRightKnifeFrame = 0;
	
	
	
	private String[] playerMovingRight = {"FI-Char-Right.png", "FI-Char-Right-moving.png", "FI-Char-Right.png", "FI-Char-Right-moving-2.png"};
	private String[] playerMovingLeft = {"FI-Char-Left.png", "FI-Char-Left-moving.png", "FI-Char-Left.png", "FI-Char-Left-moving2.png"};
	private String[] playerMovingUp = {"FI-Char-Up.png","FI-Char-Up-moving.png","FI-Char-Up.png", "FI-Char-Up-moving2.png"};
	private String[] playerMovingDown = {"FI-Char-Down.png","FI-Char-Down-moving.png","FI-Char-Down.png", "FI-Char-Down-moving2.png"};
	
	private String[] playerMovingDownKnife = {"FI-Char-Down-Knife-Standing.png", "FI-Char-Down-Knife-Moving.png", "FI-Char-Down-Knife-Standing.png", "FI-Char-Down-Knife-Moving2.png" };
	private String[] playerMovingUpKnife = {"FI-Char-Up-Knife-Standing.png", "FI-Char-Up-Knife-Moving.png", "FI-Char-Up-Knife-Standing.png", "FI-Char-Up-Knife-Moving2.png" };
	private String[] playerMovingLeftKnife = {"FI-Char-Left-Knife-Standing.png", "FI-Char-Left-Knife-Moving.png", "FI-Char-Left-Knife-Standing.png", "FI-Char-Left-Knife-Moving2.png"};
	private String[] playerMovingRightKnife = {"FI-Char-Right-Knife-Standing.png","FI-Char-Right-Knife-Moving.png", "FI-Char-Right-Knife-Standing.png", "FI-Char-Right-Knife-Moving2.png"};
	
	private String[] playerAttackDownKnife = {"FI-Char-Down-Knife-Attack.png", "FI-Char-Down-Knife-Attack2.png", "FI-Char-Down-Knife-Standing.png"};
	private String[] playerAttackUpKnife = {"FI-Char-Up-Knife-Attack.png", "FI-Char-Up-Knife-Attack2.png", "FI-Char-Up-Knife-Attack.png", "FI-Char-Up-Knife-Standing.png"};
	private String[] playerAttackLeftKnife = {"FI-Char-Left-Knife-Attack.png", "FI-Char-Left-Knife-Attack2.png", "FI-Char-Left-Knife-Attack.png", "FI-Char-Left-Knife-Standing.png"};
	private String[] playerAttackRightKnife = {"FI-Char-Right-Knife-Attack.png", "FI-Char-Right-Knife-Attack2.png", "FI-Char-Right-Knife-Attack.png", "FI-Char-Right-Knife-Standing.png"};
	
	
	//enemy animation members
	private int zombieMovingDownFrame = 0;
	private int zombieMovingUpFrame = 0;
	private int zombieMovingLeftFrame = 0;
	private int zombieMovingRightFrame = 0;
	
	private int zombieAttackDownFrame = 0;
	private int zombieAttackUpFrame = 0;
	private int zombieAttackLeftFrame = 0;
	private int zombieAttackRightFrame = 0;

	private String[] zombieMovingDown = {"Zombie-Moving-Down.png", "Zombie-Standing-Down.png", "Zombie-Moving-Down2.png", "Zombie-Standing-Down.png"};
	private String[] zombieMovingUp = {"Zombie-Moving-Up.png", "Zombie-Standing-Up.png", "Zombie-Moving-Up2.png", "Zombie-Standing-Up.png"};
	private String[] zombieMovingLeft = {"Zombie-Moving-Left.png", "Zombie-Standing-Left.png", "Zombie-Moving-Left2.png", "Zombie-Standing-Left.png"};
	private String[] zombieMovingRight = {"Zombie-Moving-Right.png", "Zombie-Standing-Right.png", "Zombie-Moving-Right2.png", "Zombie-Standing-Right.png"};
	
	
	private String[] zombieAttackDown = {"Zombie-Attack-Down.png", "Zombie-Attack-Down2.png", "Zombie-Attack-Down.png", "Zombie-Standing-Down.png"};
	private String[] zombieAttackUp = {"Zombie-Attack-Up.png", "Zombie-Attack-Up2.png", "Zombie-Attack-Up.png", "Zombie-Standing-Up.png"};
	private String[] zombieAttackLeft = {"Zombie-Attack-Left.png", "Zombie-Attack-Left2.png", "Zombie-Attack-Left.png", "Zombie-Standing-Left.png"};
	private String[] zombieAttackRight = {"Zombie-Attack-Right.png", "Zombie-Attack-Right2.png", "Zombie-Attack-Right.png", "Zombie-Standing-Right.png"};

	
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
        double xmin = 0;
        double ymin = 0;
        double xMax = program.getWidth() - 45; // before it was - 1.75 * sprite.getWidth()
        double yMax = program.getHeight() - 70; // before it was - 2.25 * sprite.getHeight()
        /* if (character instanceof Enemy) { // check if character is an enemy
            yMax = program.getHeight() - 3 * sprite.getHeight();
        } */
        sprite.setLocation(inRange(x, xmin, xMax), inRange(y, ymin, yMax)); // set location of sprite in bounds
    }
	
	/*
	private void setBoundsFromHouse(Entity character) { // set character sprite in bounds on the screen
		GImage sprite = character.getSprite();
        double x = sprite.getX();
        double y = sprite.getY();
        
        
        if(mapHouse.getBounds() == sprite.getBounds()) {
        	sprite.setLocation(x-5, y-5);
        }
	}*/
	
	private double angle(GImage enemySprite, GImage playerSprite) { // return angle between player and enemy
		double x = (enemySprite.getX() + (enemySprite.getWidth() / 2)) - (playerSprite.getX() + (playerSprite.getWidth() / 2)); //x is set to horizontal distance between enemy and player
		double y = (enemySprite.getY() + (enemySprite.getHeight() / 2)) - (playerSprite.getY() + (playerSprite.getHeight() / 2));  //y is set to vertical distance between enemy and player
		double angle = 180 * Math.atan2(-y, x) / Math.PI; // calculate angle from player to enemy
		return angle;
	}
	
	private void initializeGame() {
		knifeEquipped = false;
		equipOnAndOff = 0;
		
		currentMap = 1; // starting room number
		map = new GImage("FI-Official-Map.png", 0, 0);
		//map.setSize(800, 640); 
		mapTree = new GImage("FI-Official-Map-Tree.png", 0, 0);
		mapHouse = new GImage("FI-House1.png", 100, 100);
		mapHouse.setSize(100, 100);
		
		mapHouse2 = new GImage("FI-House2.png", 100, 400);
		mapHouse2.setSize(100, 100);
		
		mapHouse3 = new GImage("FI-House3.png", 500, 250);
		mapHouse3.setSize(100, 100);
		
		
		playerHealth = new ArrayList<GImage>(); // initialize playerHealth
		GImage playerSprite = new GImage ("FI-Char-Down.PNG");
		playerSprite.setSize(PLAYER_SPRITE_SIZE,PLAYER_SPRITE_SIZE);
		player = new Player(playerSprite, PLAYER_STARTING_HEALTH);
		zombies = new ArrayList<Zombie>(); // initialize enemy array list
		zombies2 = new ArrayList<Zombie2>(); // initialized another enemy array list
		player.randomizeXLocation(program.getWidth(), program.getHeight()); //Randomize player location at bottom of screen
		player.setSpeed(PLAYER_STARTING_SPEED); // initialize speed
		
		inventory = new GImage("HotBar.png", 300, 535);
		inventoryDisplayIndex = 0;
		inventoryIndex = 0;
		inventorySizeCount = 0;
		populatingItemsIndex = 0;
		//food = new GImage("waterBottle.jpg", 300, 100);
		
		
		
		//timer.restart(); // reset timer
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
		timer.restart(); //When the game restarts, this is important for restarting the timer.
		Map newMap = new Map(mapNum, program.getWidth(), program.getHeight());
		GImage zombieImage = new GImage("ZombieSprite.png", 500, 100);
		zombieImage.setSize(30, 30);
		Zombie zombie = new Zombie(zombieImage, 5, "zombie");
		zombie.setSpeed(5);
		zombies.add(zombie);
		for (Zombie z: zombies) { // loop for all enemies
			program.add(z.getSprite()); //Add enemy sprite to screen.
			
		}
		
		GImage zombieImage2 = new GImage("zombie2.png", 650, 300);
		zombieImage2.setSize(30, 70);
		Zombie2 zombiesSecond = new Zombie2(zombieImage2, 5, "zombie2");
		zombiesSecond.setSpeed(10);
		zombies2.add(zombiesSecond);
		for (Zombie2 z: zombies2) {
			program.add(z.getSprite()); // Added another enemy sprite to the screen 
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
			
			//this.pause();
		}
		if (obj == this.resume) {
			this.resume();
			//this.monsterTimer.setInitialDelay(0);
			//this.monsterTimer.restart();
		}	
		if (obj == this.quit) {System.exit(0);}
	}
	/*
	public void pause() {
		program.add(resumeImg);
		program.add(quitImg);
		program.add(resume);
		program.add(quit);
		paused = true;
	}*/
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
	
	
	public void setSpriteImage(String[] playerMoving, int frames, Player player, GImage playerSprite, GImage newPlayerSprite) {
		newPlayerSprite.setImage(playerMoving[frames]);
		newPlayerSprite.setSize(PLAYER_SPRITE_SIZE,PLAYER_SPRITE_SIZE);
		player.setSprite(newPlayerSprite);
		program.remove(playerSprite); // remove previous player sprite
		program.add(newPlayerSprite); // add new player sprite
	}
	
	private void setSpriteImageZombie(String[] zombieAttackDown2, int frames, Zombie zombie, GImage zombieSprite, GImage newZombieSprite) {
		newZombieSprite.setImage(zombieAttackDown2[frames]);
		newZombieSprite.setSize(PLAYER_SPRITE_SIZE,PLAYER_SPRITE_SIZE);
		zombie.setSprite(newZombieSprite);
		program.remove(zombieSprite); // remove previous player sprite
		program.add(newZombieSprite); // add new player sprite
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
		GImage newPlayerSprite = new GImage("", playerSprite.getX(), playerSprite.getY()); // For player animation
		int keyCode = e.getKeyCode();
		if (!player.isDamaged()) {
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
			}
		}
		if (keyCode == 69) { // e
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
		} if (keyCode == 82) { // r
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
		} if (keyCode == 81) { // q
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
		} if (keyCode == KeyEvent.VK_ESCAPE) {
			//pause();
		} if (keyCode == 88) { //User clicks x (equip knife)
			if (equipOnAndOff % 2 == 0) { //Equips knife when x is entered every other time
				knifeEquipped = true;
				//Set image showing player equips knife
				setSpriteImage(playerMovingDownKnife, 0, player, playerSprite, newPlayerSprite);
			}
			else { //Unequip knife
				knifeEquipped = false;
				//Set image showing player un equips knife
				setSpriteImage(playerMovingDown, 0, player, playerSprite, newPlayerSprite);
			}
			equipOnAndOff++;
				
		} /*else if (keyCode == 90) { //User clicks z (Un-equip knife)
			knifeEquipped = false;
		}*/
		
		if (player.getMoveX() > 0 && knifeEquipped) { // player moving right
			setSpriteImage(playerMovingRightKnife, playerMovingRightKnifeFrame, player, playerSprite, newPlayerSprite);
			playerMovingRightKnifeFrame++;
			 if(playerMovingRightKnifeFrame>=playerMovingRightKnife.length){
				 playerMovingRightKnifeFrame = 0;
			 }
		}
		
		else if (player.getMoveX() > 0) { // player moving right
			 //knifeEquipped = false; //Player should be moving without knife
			 setSpriteImage(playerMovingRight, frame, player, playerSprite, newPlayerSprite);
			 frame++;
			 if(frame>=playerMovingRight.length){
				 frame = 0;
			 }
		 }
		 
		 if (player.getMoveX() < 0 && knifeEquipped) { // player moving left
			 setSpriteImage(playerMovingLeftKnife, playerMovingLeftKnifeFrame, player, playerSprite, newPlayerSprite);
			 playerMovingLeftKnifeFrame++;
			 if(playerMovingLeftKnifeFrame>=playerMovingLeftKnife.length){
				 playerMovingLeftKnifeFrame = 0;
			 }
		 }
		 
		 else if (player.getMoveX() < 0) { // player moving left
			 //knifeEquipped = false;
			 setSpriteImage(playerMovingLeft, frame2, player, playerSprite, newPlayerSprite);
			 frame2++;
			 if(frame2>=playerMovingLeft.length){
				 frame2 = 0;
			 }
		 }
		 
		 if (player.getMoveY() < 0 && knifeEquipped) { // player moving up
			 setSpriteImage(playerMovingUpKnife, playerMovingUpKnifeFrame, player, playerSprite, newPlayerSprite);
			 playerMovingUpKnifeFrame++;
			 if(playerMovingUpKnifeFrame>=playerMovingUpKnife.length){
				 playerMovingUpKnifeFrame = 0;
			 }
		 }
		 
		 else if (player.getMoveY() < 0) { // player moving up
			 //knifeEquipped = false;
			 setSpriteImage(playerMovingUp, frame3, player, playerSprite, newPlayerSprite);
			 frame3++;
			 	if(frame3>=playerMovingUp.length){
			 		frame3 = 0;
			 	}
		 }
		 
		 
		 
		 if (player.getMoveY() > 0 && knifeEquipped) { // player moving down
			 setSpriteImage(playerMovingDownKnife, playerMovingDownKnifeFrame, player, playerSprite, newPlayerSprite);
			 playerMovingDownKnifeFrame++;
			 	if(playerMovingDownKnifeFrame>=playerMovingDownKnife.length){
			 		playerMovingDownKnifeFrame = 0;
			 	}

		 }
		 
		 else if (player.getMoveY() > 0) { // player moving down
			// knifeEquipped = false;
			 setSpriteImage(playerMovingDown, frame4, player, playerSprite, newPlayerSprite);
			 frame4++;
			 if(frame4>=playerMovingDown.length){
				 frame4 = 0;
			 }
		 }
		
		 
		
		  
		//playerSprite.move(player.getMoveX() * player.getSpeed(), player.getMoveY() * player.getSpeed()); // move playerSprite
		// for normalizing diagonal movement
		if (Math.abs(player.getMoveX()) == 1 && Math.abs(player.getMoveY()) == 1) { // check if diagonal movement is happening
			player.setMoveX(player.getMoveX() * SQRT_TWO_DIVIDED_BY_TWO);
			player.setMoveY(player.getMoveY() * SQRT_TWO_DIVIDED_BY_TWO);
		}
				
		playerSprite.move(player.getMoveX() * player.getSpeed(), player.getMoveY() * player.getSpeed()); // move playerSprite
		newPlayerSprite.move(player.getMoveX() * player.getSpeed(), player.getMoveY() * player.getSpeed()); // move playerSprite
		//newPlayerSprite2.move(player.getMoveX() * player.getSpeed(), player.getMoveY() * player.getSpeed()); // move playerSprite
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
		GImage newPlayerSprite = new GImage("", playerSprite.getX(), playerSprite.getY()); // For player animation
		if (player.isAttackAvailable()) {
			//if (program.isCloseRangeCharacter()) {
			//TO DO: Move player attack logic here after attack cooldown implementation.
			//}
			removeZombieIndex = new ArrayList<Integer>(); // initialize array list for indexes of dead enemies
			for (int z = 0; z < zombies.size(); z++) { // loop for all enemies
				Zombie zombie = zombies.get(z);
				
				if (player.canInteract(zombie.getSprite().getX(), zombie.getSprite().getY())) { //player in range of enemy.
					if(!player.isDamaged() && knifeEquipped) {
						double playerYDiff = Math.abs(zombie.getSprite().getY() - player.getSprite().getY()); //For determining accuracy of player location.
						double playerXDiff = Math.abs(zombie.getSprite().getX() - player.getSprite().getX()); //For determining accuracy of player location.
						if (zombie.getSprite().getY() > player.getSprite().getY() && playerYDiff > playerXDiff) { //if zombie is below player. 
							enemyHitDown = true;
						}
						
						if (zombie.getSprite().getY() < player.getSprite().getY() && playerYDiff > playerXDiff) { //if player is below zombie . 
							enemyHitUp = true;
						}
						
						if (player.getSprite().getX() > zombie.getSprite().getX() && playerXDiff > playerYDiff) { //if zombie is to the left of player  . 
							enemyHitLeft = true;
						}
						
						if (zombie.getSprite().getX() > player.getSprite().getX() && playerXDiff > playerYDiff) { //if zombie is to the right of player  . 
							enemyHitRight = true;
						}
						
						
						
						
						//Change zombie health and manage zombie death
						System.out.println("Enemy is hit.");
						zombie.changeHealth(-1); //Reduce health by 1.
					}
					
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
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GImage playerSprite = player.getSprite();
		GImage newPlayerSprite = new GImage("", playerSprite.getX(), playerSprite.getY()); // For player animation
		removeZombieIndex = new ArrayList<Integer>(); // initialize array list for indexes of dead enemies
		timerCount++;
		for (Z = 0; Z < zombies.size(); Z++) { // loop through all enemies
			Zombie zombie = zombies.get(Z);
			GImage zombieSprite = zombie.getSprite();
			if (zombie.canInteract(playerSprite.getX(), playerSprite.getY())) { //enemy detects player
				zombieMoveDown = false;
				zombieMoveUp = false;
				zombieMoveRight = false;
				zombieMoveLeft = false;
				if (timerCount % INTERACT_INTERVAL == 0) {
					zombieSprite.movePolar(zombie.getSpeed(), angle(zombieSprite, playerSprite) + 180); // close range zombie moves towards player
					if (timerCount % player.getAttackCooldown() == 0) {
						System.out.println("Attack Available!");
						player.setAttackAvailable(true); //player can now attack
					}
					if (timerCount % zombie.getAttackCooldown() == 0) {
						zombie.setAttackAvailable(true); //zombie can now attack
					}
					else if (!player.isAttackAvailable()){
						System.out.println("Player is too tired to attack!");
					}
					if (timerCount % LONG_RANGE_ENEMY_ATTACK_INTERVAL == 0) {
						for (Zombie z1 : zombies) {
							z1.setAttackAvailable(true); //enemy can now attack
						}
					}
					
					/* Logic for zombie movement animation */
					double zombieYDiff = Math.abs(zombie.getSprite().getY() - player.getSprite().getY()); //For determining accuracy of zombie location to player.
					double zombieXDiff = Math.abs(zombie.getSprite().getX() - player.getSprite().getX()); //For determining accuracy of zombie location to player.
					if (player.getSprite().getY() > zombie.getSprite().getY() && zombieYDiff > zombieXDiff) { //if player is below zombie. 
						zombieMoveDown = true;
					}
					
					if (player.getSprite().getY() < zombie.getSprite().getY() && zombieYDiff > zombieXDiff) { //if zombie is below player . 
						zombieMoveUp = true;
					}
					
					if (zombie.getSprite().getX() > player.getSprite().getX() && zombieXDiff > zombieYDiff) { //if player is to the left of zombie  . 
						zombieMoveLeft = true;
					}
					
					if (player.getSprite().getX() > zombie.getSprite().getX() && zombieXDiff > zombieYDiff) { //if player is to the right of zombie  . 
						zombieMoveRight = true;
					}
				}
				
				if (Collision.check(zombie.getSprite().getBounds(), player.getSprite().getBounds()) && zombie.isAttackAvailable()) { // player collides with enemy
					//playSound("player", AudioPlayer.getInstance()); // player is damaged
				
					/* Logic for player getting hit back */
					/*double x = (zombieSprite.getX() + (zombieSprite.getWidth() / 2)) - (playerSprite.getX() + (playerSprite.getWidth() / 2)); //x is set to horizontal distance between enemy and player
					double y = (zombieSprite.getY() + (zombieSprite.getHeight() / 2)) - (playerSprite.getY() + (playerSprite.getHeight() / 2));  //y is set to vertical distance between enemy and player
					playerSprite.movePolar(Math.sqrt(x*x+y*y) * 2, angle(zombieSprite, playerSprite) + 180); // player moves away from enemy
					*/
					
					/* Logic for determining Zombie attack animation from direction */
					double zombieY2Diff = Math.abs(zombie.getSprite().getY() - player.getSprite().getY()); //For determining accuracy of zombie location to player.
					double zombieX2Diff = Math.abs(zombie.getSprite().getX() - player.getSprite().getX()); //For determining accuracy of zombie location to player.
					if (player.getSprite().getY() > zombie.getSprite().getY() && zombieY2Diff > zombieX2Diff) { //if player is below zombie. 
						playerHitDown = true;
					}
					
					if (player.getSprite().getY() < zombie.getSprite().getY() && zombieY2Diff > zombieX2Diff) { //if zombie is below player . 
						playerHitUp = true;
					}
					
					if (zombie.getSprite().getX() > player.getSprite().getX() && zombieX2Diff > zombieY2Diff) { //if player is to the left of zombie  . 
						playerHitLeft = true;
					}
					
					if (player.getSprite().getX() > zombie.getSprite().getX() && zombieX2Diff > zombieY2Diff) { //if player is to the right of zombie  . 
						playerHitRight = true;
					}
					
					
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
			//System.out.println("Hunger: " + player.GetHunger());
			//System.out.println("Thirst: " + player.GetThirst());
		}
		
		
		
		
		//Use attackAnimationAcc for sing full cycle of attack animation.
		if (attackAnimationDownAcc == 3) {
			enemyHitDown = false; //Full cycle complete, stop from next cycle.
			attackAnimationDownAcc = 0; //When next attack happens, start from beginning frame/
		}
		
		if (attackAnimationAcc == 4) {
			enemyHitUp = false; //Full cycle complete, stop from next cycle.
			enemyHitLeft = false;
			enemyHitRight = false;
			attackAnimationAcc = 0; //When next attack happens, start from beginning frame/
		}
		
		
		
		/*Initiate full down attack animation frames for Player */
		
		//Use enemyHit to cycle through full attack animation frames.
		if (enemyHitDown && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
			//Player attack animation
			setSpriteImage(playerAttackDownKnife, playerAttackDownKnifeFrame, player, playerSprite, newPlayerSprite);
			playerAttackDownKnifeFrame++;
			if(playerAttackDownKnifeFrame>=playerAttackDownKnife.length){
				playerAttackDownKnifeFrame = 0;
		 	}
			attackAnimationDownAcc++;
			
		}
		
		
		
		/*Initiate full up attack animation frames for Player */
		
		//Use enemyHit to cycle through full attack animation frames.
		if (enemyHitUp && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
			//Player attack animation
			setSpriteImage(playerAttackUpKnife, playerAttackUpKnifeFrame, player, playerSprite, newPlayerSprite);
			playerAttackUpKnifeFrame++;
			if(playerAttackUpKnifeFrame>=playerAttackUpKnife.length){
				playerAttackUpKnifeFrame = 0;
		 	}
			attackAnimationAcc++;
			
		}
		
		if (enemyHitLeft && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
			//Player attack animation
			setSpriteImage(playerAttackLeftKnife, playerAttackLeftKnifeFrame, player, playerSprite, newPlayerSprite);
			playerAttackLeftKnifeFrame++;
			if(playerAttackLeftKnifeFrame>=playerAttackLeftKnife.length){
				playerAttackLeftKnifeFrame = 0;
		 	}
			attackAnimationAcc++;
			
		}
		
		if (enemyHitRight && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
			//Player attack animation
			setSpriteImage(playerAttackRightKnife, playerAttackRightKnifeFrame, player, playerSprite, newPlayerSprite);
			playerAttackRightKnifeFrame++;
			if(playerAttackRightKnifeFrame>=playerAttackRightKnife.length){
				playerAttackRightKnifeFrame = 0;
		 	}
			attackAnimationAcc++;
			
		}
		
		/*Initiate full movement and attack animation frames for Zombie */
		for (Z = 0; Z < zombies.size(); Z++) { // loop through all enemies
			Zombie zombie = zombies.get(Z);
			GImage zombieSprite = zombie.getSprite();
			GImage newZombierSprite = new GImage("", zombieSprite.getX(), zombieSprite.getY()); // For player animation
			if (zombieAttackAnimationDownAcc == 4) {
				playerHitDown = false; //Full cycle complete, stop from next cycle.
				playerHitUp = false; //Full cycle complete, stop from next cycle.
				playerHitLeft = false; //Full cycle complete, stop from next cycle.
				playerHitRight = false; //Full cycle complete, stop from next cycle.
				zombieAttackAnimationDownAcc = 0; //When next attack happens, start from beginning frame/
				player.setDamaged(false);
			}
			
			if (playerHitDown && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
				//Zombie attack animation
				setSpriteImageZombie(zombieAttackDown, zombieAttackDownFrame, zombie, zombieSprite, newZombierSprite);
				zombieAttackDownFrame++;
				if(zombieAttackDownFrame>=zombieAttackDown.length){
					zombieAttackDownFrame = 0;
			 	}
				zombieAttackAnimationDownAcc++;
			}
			
			if (playerHitUp && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
				//Zombie attack animation
				setSpriteImageZombie(zombieAttackUp, zombieAttackUpFrame, zombie, zombieSprite, newZombierSprite);
				zombieAttackUpFrame++;
				if(zombieAttackUpFrame>=zombieAttackUp.length){
					zombieAttackUpFrame = 0;
			 	}
				zombieAttackAnimationDownAcc++;
			}
			
			if (playerHitLeft && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
				//Zombie attack animation
				setSpriteImageZombie(zombieAttackLeft, zombieAttackLeftFrame, zombie, zombieSprite, newZombierSprite);
				zombieAttackLeftFrame++;
				if(zombieAttackLeftFrame>=zombieAttackLeft.length){
					zombieAttackLeftFrame = 0;
			 	}
				zombieAttackAnimationDownAcc++;
			}
			
			if (playerHitRight && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
				//Zombie attack animation
				setSpriteImageZombie(zombieAttackRight, zombieAttackRightFrame, zombie, zombieSprite, newZombierSprite);
				zombieAttackRightFrame++;
				if(zombieAttackRightFrame>=zombieAttackRight.length){
					zombieAttackRightFrame = 0;
			 	}
				zombieAttackAnimationDownAcc++;
			}
			
			/* Zombie movement animation logic */
			if (zombieMoveDown) {
				setSpriteImageZombie(zombieMovingDown, zombieMovingDownFrame, zombie, zombieSprite, newZombierSprite);
				zombieMovingDownFrame++;
				if(zombieMovingDownFrame>=zombieMovingDown.length){
					zombieMovingDownFrame = 0;
			 	}
			}
			
			if (zombieMoveUp) {
				setSpriteImageZombie(zombieMovingUp, zombieMovingUpFrame, zombie, zombieSprite, newZombierSprite);
				zombieMovingUpFrame++;
				if(zombieMovingUpFrame>=zombieMovingUp.length){
					zombieMovingUpFrame = 0;
			 	}
			}
			
			if (zombieMoveLeft) {
				setSpriteImageZombie(zombieMovingLeft, zombieMovingLeftFrame, zombie, zombieSprite, newZombierSprite);
				zombieMovingLeftFrame++;
				if(zombieMovingLeftFrame>=zombieMovingLeft.length){
					zombieMovingLeftFrame = 0;
			 	}
			}
			
			if (zombieMoveRight) {
				setSpriteImageZombie(zombieMovingRight, zombieMovingRightFrame, zombie, zombieSprite, newZombierSprite);
				zombieMovingRightFrame++;
				if(zombieMovingRightFrame>=zombieMovingRight.length){
					zombieMovingRightFrame = 0;
			 	}
			}
			
		}
		
		//When hunger and thirst run out, game over.
		if (player.GetHunger() == 0 || player.GetThirst() == 0) {
			 //gameOver();
		}
		
		
		
	}

	

	@Override
	public void showContents() {
		program.add(map);
		program.add(mapTree);
		program.add(mapHouse);
		program.add(mapHouse2);
		program.add(mapHouse3);
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