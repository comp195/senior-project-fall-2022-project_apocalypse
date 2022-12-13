package default_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
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
import java.util.Random;

public class ScreenDisplayPane extends GraphicsPane implements ActionListener {
	private static final int ZOMBIE_SPRITE_SIZE = 50;
	Random rand = new Random(); 
	int randMinXMainMap = 50;  
	int randMaxXMainMap = 600; 
	int randMinYMainMap = 50;
	int randMaxYMainMap = 450; 
	private static final int ATTACK_ANIMATION_INTERVAL = 15;
	private static final int DOOR_LABEL_INTERVAL = 50;

	private static final int PLAYER_SPRITE_SIZE = 30;
	private static final int HEART_SIZE = 50;
	private static final int PLAYER_STARTING_HEALTH = 10;
	private static final int PLAYER_STARTING_SPEED = 5;
	private static final double SQRT_TWO_DIVIDED_BY_TWO = 0.7071067811865476;
	private static final int INTERACT_INTERVAL = 50;
	private static final int HUNGER_AND_THIRST_INTERVAL = 50;
	private static final int LONG_RANGE_ENEMY_ATTACK_INTERVAL = 200;
	
	private MainApplication program;
	private Timer timer;
	private int timerCount; // to keep track of timer
	private int Z = 0;
	private ArrayList<GImage> playerHealth;
	private ArrayList<GImage> bossHealth;
	private ArrayList<Item> items; // items to display on the level.
	private ArrayList<Zombie> zombies;
	private ArrayList<House> houses; 
	private ArrayList<Integer> removeZombieIndex; // to keep track of enemy indexes to remove
	private int currentMap; // to display current room
	private GImage map; 
	private GImage mapTree;
	private GImage mapTree2;
	private GImage mapTree3;
	private GImage mapTree4;
	private GImage mapTree5;
	private GImage mapTree6;
	//private HashMap<String, String> doorLabel; // hash map to link items with labels
	private GLabel doorLabel;
	private int populatingItemsIndex;
	private int numOfZombiesMainMap;
	private int numOfZombiesHouse1;
	private int numOfZombiesHouse2;
	private int numOfZombiesHouse3;
	private int numOfFoodHouse1;
	private int numOfFoodHouse2;
	private int numOfFoodHouse3;
	private int numOfFoodLevel3House;
	private int numOfWaterHouse1;
	private int numOfWaterHouse2;
	private int numOfWaterHouse3;
	private int numOfWaterLevel3House;
	
	private int numOfZombiesLevel3;
	private int numOfZombiesLevel3House;


	//Vars for Player locations during map transitions
	private double playerLocationX;
	private double playerLocationY;
	private boolean playerExitsHouse1;
	private boolean playerExitsHouse2;
	private boolean playerExitsHouse3;
	private boolean playerExitsHouseLevel3;
	
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
	private int attackAnimationLeftAndRightAcc = 0;
	
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
	//private String[] playerAttackLeftKnife = {"FI-Char-Left-Knife-Attack.png", "FI-Char-Left-Knife-Attack2.png", "FI-Char-Left-Knife-Attack.png", "FI-Char-Left-Knife-Standing.png"};
	private String[] playerAttackLeftKnife = {"FI-Char-Left-Heavy-Attack.png", "FI-Char-Left-Heavy-Attack2.png", "FI-Char-Left-Heavy-Attack3.png", "FI-Char-Left-Heavy-Attack.png", "FI-Char-Left-Knife-Standing.png"};
	//private String[] playerAttackRightKnife = {"FI-Char-Right-Knife-Attack.png", "FI-Char-Right-Knife-Attack2.png", "FI-Char-Right-Knife-Attack.png", "FI-Char-Right-Knife-Standing.png"};
	private String[] playerAttackRightKnife = {"FI-Char-Right-Heavy-Attack.png", "FI-Char-Right-Heavy-Attack2.png", "FI-Char-Right-Heavy-Attack3.png", "FI-Char-Right-Heavy-Attack.png", "FI-Char-Right-Knife-Standing.png"};
	
	//Zombie 1 animation members
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

	
	/*Animation variables for Zombie 2 */
	
	private String[] zombie2MovingDown = {"FI-Zombie2-Moving-Down.png", "FI-Zombie2-Arms-Down.png", "FI-Zombie2-Moving-Down2.png", "FI-Zombie2-Arms-Down.png"};
	private String[] zombie2MovingUp = {"FI-Zombie2-Moving-Up.png", "FI-Zombie2-Standing-Up.png", "FI-Zombie2-Moving-Up2.png", "FI-Zombie2-Standing-Up.png"};
	private String[] zombie2MovingLeft = {"FI-Zombie2-Moving-Left.png", "FI-Zombie2-Arms-Left.png", "FI-Zombie2-Moving-Left2.png", "FI-Zombie2-Arms-Left.png"};
	private String[] zombie2MovingRight = {"FI-Zombie2-Moving-Right.png", "FI-Zombie2-Arms-Right.png", "FI-Zombie2-Moving-Right2.png", "FI-Zombie2-Arms-Right.png"};
	
	private String[] zombie2AttackDown = {"FI-Zombie2-Attack-Down.png", "FI-Zombie2-Attack-Down2.png", "FI-Zombie2-Standing-Down.png"};
	private String[] zombie2AttackUp = {"FI-Zombie2-Attack-Up.png", "FI-Zombie2-Attack-Up2.png", "FI-Zombie2-Standing-Up.png"};
	private String[] zombie2AttackLeft = {"FI-Zombie2-Attack-Left.png", "FI-Zombie2-Attack-Left2.png", "FI-Zombie2-Standing-Left.png"};
	private String[] zombie2AttackRight = {"FI-Zombie2-Attack-Right.png", "FI-Zombie2-Attack-Right2.png", "FI-Zombie2-Standing-Right.png"};


	
	/* Hunger+Thirst Display Members */
	private String[] hungerDisplayImages = {"Hunger100.png","Hunger90.png","Hunger80.png","Hunger70.png","Hunger60.png","Hunger50.png","Hunger40.png","Hunger30.png","Hunger20.png","Hunger10.png","Hunger-Warning.png","Hunger0.png"};
	private String[] thirstDisplayImages = {"Thirst100.png","Thirst90.png","Thirst80.png","Thirst70.png","Thirst60.png","Thirst50.png","Thirst40.png","Thirst30.png","Thirst20.png","Thirst10.png","Thirst-Warning.png","Thirst0.png"};
	private int hungerDisplayIndex;
	private int thirstDisplayIndex;
	private GImage hungerDisplayCurrentImage = new GImage("Hunger100.png", 50, 300);
	private GImage thirstDisplayCurrentImage = new GImage("Thirst100.png", 50, 300);
	
	//main game objects
	private Player player;
	ArrayList <GImage> playerHearts = new ArrayList <GImage>();
	
	public ScreenDisplayPane(MainApplication app) {
		super();
		program = app;
		timer = new Timer(0, this); // create timer object
		initializeGame();
	}
	
	private void removeAllDeadEnemies() {
		Zombie zombie = zombies.get(0);
		if (zombie.getEnemyType() == "zombieBoss" && zombie.isDead()) {
			initializeGame(); // reset all game values
			program.removeAll(); // remove all objects from screen
			timer.stop();
			populatingItemsIndex = 0;
			program.setPlayerWin(true); // set player win
			program.switchTo(3); // switch to game end screen
		}
		
		if (removeZombieIndex != null) {
			for (int y = removeZombieIndex.size() - 1; y >= 0 ; y--) {
				zombies.remove((int)removeZombieIndex.get(y));
			}
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
        sprite.setLocation(inRange(x, xmin, xMax), inRange(y, ymin, yMax)); // set location of sprite in bounds
    }
	
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
		playerHealth = new ArrayList<GImage>(); // initialize playerHealth
		bossHealth = new ArrayList<GImage>(); //initialize bossHealth
		GImage playerSprite = new GImage ("FI-Char-Down.PNG");
		playerSprite.setSize(PLAYER_SPRITE_SIZE,PLAYER_SPRITE_SIZE);
		player = new Player(playerSprite, PLAYER_STARTING_HEALTH);
		zombies = new ArrayList<Zombie>(); // initialize enemy array list
		items = new ArrayList<Item>(); // initialize items in items arrayList. 
		houses = new ArrayList<House>(); // initialize enemy array list
		player.randomizeXLocation(program.getWidth(), program.getHeight()); //Randomize player location at bottom of screen
		player.setSpeed(PLAYER_STARTING_SPEED); // initialize speed
		
		populatingItemsIndex = 0;
		
		hungerDisplayIndex = 0;
		thirstDisplayIndex = 0;
		//food = new GImage("waterBottle.jpg", 300, 100);
		
		playerExitsHouse1 = false;
		playerExitsHouse2 = false;
		playerExitsHouse3 = false;
		playerExitsHouseLevel3 = false;
		
		//timer.restart(); // reset timer
		numOfZombiesMainMap = 4;
		numOfZombiesHouse1 = 3;
		numOfZombiesHouse2 = 2;
		numOfZombiesHouse3 = 1;
		
		numOfFoodHouse1 = 2;
		numOfFoodHouse2 = 2;
		numOfFoodHouse3 = 1;
		numOfFoodLevel3House = 4;
		numOfWaterLevel3House = 4;
		
		numOfWaterHouse1 = 2;
		numOfWaterHouse2 = 2;
		numOfWaterHouse3 = 1;
		
		numOfZombiesLevel3 = 3;
		numOfZombiesLevel3House = 1;


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
	
	
	public void createMap(int mapNum) {
		program.removeAll();
		timer.restart(); //When the game restarts, this is important for restarting the timer.
		Map newMap = new Map(mapNum, program.getWidth(), program.getHeight());
		
		if (mapNum == 1) {
			newMap.setNumOfZombies(numOfZombiesMainMap);
			//doorLabel = new HashMap<String, String>();
			doorLabel = new GLabel("Press 'e' to open");
		}
		
		
		
		if (mapNum == 2) {
			newMap.setNumOfZombiesHouse1(numOfZombiesHouse1);
			newMap.setNumOffoodsHouse1(numOfFoodHouse1);
			newMap.setNumOfWaterHouse1(numOfWaterHouse1);
		}
		
		if (mapNum == 3) {
			newMap.setNumOfZombiesHouse2(numOfZombiesHouse2);
			newMap.setNumOffoodsHouse2(numOfFoodHouse2);
			newMap.setNumOfWaterHouse2(numOfWaterHouse2);
		}
		
		if (mapNum == 4) {
			newMap.setNumOfZombiesHouse3(numOfZombiesHouse3);
			newMap.setNumOffoodsHouse3(numOfFoodHouse3);
			newMap.setNumOfWaterHouse3(numOfWaterHouse3);
		}
		
		if (mapNum == 5) {
			numOfZombiesMainMap = 8;
			newMap.setNumOfZombies(numOfZombiesMainMap);
		}
		
		if (mapNum == 6) {
			newMap.setNumOfZombiesLevel3(numOfZombiesLevel3);
		}
		
		if (mapNum == 7) {
			newMap.setNumOfZombiesLevel3House(numOfZombiesLevel3House);
			newMap.setNumOffoodsLevel3House(numOfFoodLevel3House);
			newMap.setNumOfWaterLevel3House(numOfWaterLevel3House);
		}
		
		if (mapNum == 8) {
			numOfZombiesMainMap = 4;
			newMap.setNumOfZombies(numOfZombiesMainMap);
		}
		
		if (mapNum == 9) {
			numOfZombiesMainMap = 8;
			newMap.setNumOfZombies(numOfZombiesMainMap);
		}
		

		if (mapNum == 10) {
			numOfZombiesMainMap = 1;
			newMap.setNumOfZombies(numOfZombiesMainMap);
		}
		
		
		
		//Add zombies to the map
		if (mapNum == 10) {
			zombies = newMap.getZombies();
		}
		else {
			zombies = newMap.getZombies();
		}
		
		houses = newMap.getHouses();
		items = newMap.getItems();
		
		
		if (mapNum == 1) {
			map = new GImage("FI-Forest-Map.png", 0, 0);
			mapTree = new GImage("FI-Forest-Map-Tree.png", 0, 0);
			mapTree2 = new GImage("FI-Forest-Map-Tree.png", 500, 420);
			mapTree3 = new GImage("FI-Forest-Map-Tree.png", 200, 150);
			mapTree4 = new GImage("FI-Forest-Map-Tree.png", 555, 130);
			mapTree5 = new GImage("FI-Forest-Map-Tree.png", 150, 450);
			mapTree6 = new GImage("FI-Forest-Map-Tree.png", 300, -40);
			
		}
		
		if (mapNum == 2) {
			map = new GImage("FI-House-interior-map1.png", 0, 0);
			map.setSize(program.getWidth()+80,program.getHeight()+65); //set Map to fit program window size
			player.getSprite().setLocation(140, 650); //Set player at inside house entry location
			
		}
		
		if (mapNum == 3) {
			map = new GImage("FI-House-interior-map2.png", 0, 0);
			map.setSize(program.getWidth(),program.getHeight()); //set Map to fit program window size
			
			//Replace old player sprite with new one, facing downwards
			GImage playerSprite = player.getSprite();
			GImage newPlayerSprite = new GImage("FI-Char-Down.png", playerSprite.getX(), playerSprite.getY()); // For player animation
			newPlayerSprite.setSize(PLAYER_SPRITE_SIZE,PLAYER_SPRITE_SIZE);
			player.setSprite(newPlayerSprite);
			player.getSprite().setLocation(416, 91); //Set player at inside house entry location
			program.remove(playerSprite);

			
		}
		
		if (mapNum == 4) {
			//map = new GImage("city-map.jpg", 0, 0);
			map = new GImage("FI-House-interior-map3.png", 0, 0);
			//map.setSize(program.getWidth()+80,program.getHeight()+65); //set Map to fit program window size
			player.getSprite().setLocation(429, 474); //Set player at inside house entry location
		}
		
		if (mapNum == 5) {
			map = new GImage("FI-Map-Level2.png", 0, 0);
			player.getSprite().setLocation(495,545);
		}
		
		if (mapNum == 6) {
			map = new GImage("FI-Level3-Map.png", 0, 0);
			player.getSprite().setLocation(286,582);
		}
		
		if (mapNum == 7) {
			map = new GImage("FI-Level3-Interior-House.png", 0, 0);
			player.getSprite().setLocation(185,562);
		}
		
		if (mapNum == 8) {
			map = new GImage("FI-Map-Level2.png", 0, 0);
			player.getSprite().setLocation(496,560);
		}
		
		if (mapNum == 9) {
			map = new GImage("FI-Map-Level2.png", 0, 0);
			player.getSprite().setLocation(496,560);
		}
		
		if (mapNum == 10) {
			map = new GImage("FI-Level3-Map.png");
			player.getSprite().setLocation(286,582);
		}
		
		//Add map and map stuff sprites to the screen
		program.add(map);
		if (mapNum == 1) {
			program.add(mapTree);
			program.add(mapTree2);
			program.add(mapTree3);
			program.add(mapTree4);
			program.add(mapTree5);
			program.add(mapTree6);
			for (House h : houses) {
				program.add(h.getSprite()); //Add house sprite to screen.
			}
			 
		}
		if (mapNum == 6) {
			for (House h : houses) {
				program.add(h.getSprite()); //Add house sprite to screen.
			}
		}
		
		//Add zombies sprites to the screen
		for (Zombie z: zombies) { // loop for all enemies
			program.add(z.getSprite()); //Add enemy sprite to screen.
			z.getSprite().sendToFront();
		}
		
		
		
		/* If player exits houses */
		if (playerExitsHouse1) {
			player.getSprite().setLocation(110, 210); //Set player at inside house entry location
		}
		
		if (playerExitsHouse2) {
			player.getSprite().setLocation(115, 558); //Set player at inside house entry location
		}
		
		if (playerExitsHouse3) {
			player.getSprite().setLocation(765, 495); //Set player at inside house entry location
		}
		
		if (playerExitsHouseLevel3) {
			player.getSprite().setLocation(292, 239); //Set player at inside house entry location
		}
		
		
		
		
		/*Add Player Sprite to the screen */
		program.add(player.getSprite()); //Add player sprite to screen.
		player.getSprite().sendToFront(); //send player sprite to front.
		
		/* reset house exit values */
		playerExitsHouse1 = false;
		playerExitsHouse2 = false;
		playerExitsHouse3 = false;
		playerExitsHouseLevel3 = false;
		
		for (Item i : items) {
			populatingItems(i);
		}
		
		
		//Display Hunger and Thirst to the screen.
		GImage newHungerDisplaySprite = new GImage("", 22, 550);
		GImage newThirstDisplaySprite = new GImage("", 72, 550);
		
		setSpriteImageHunger(hungerDisplayImages, hungerDisplayIndex, hungerDisplayCurrentImage, newHungerDisplaySprite);
		setSpriteImageThirst(thirstDisplayImages, thirstDisplayIndex, thirstDisplayCurrentImage, newThirstDisplaySprite);
		
		updateHealth();
		
		//Add door label to the screen as invisible for now.
		doorLabel.setLocation(0, 0);
		doorLabel.setFont(new Font("Serif", Font.BOLD, 15));
		doorLabel.setColor(Color.GREEN);
		program.add(doorLabel);
		doorLabel.setVisible(false);
		
	}
	
	public void populatingItems(Item item) {
		//items.add(item);
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
		
		if (currentMap == 10) {
			while (bossHealth.size() > 0) { // remove all boss hearts from screen
				program.remove(bossHealth.get(0));
				bossHealth.remove(0);
			}
			if (zombies.size() > 0) {
				for (Zombie z: zombies) { // loop through all enemies on screen
					int xOffset = (int)program.getWidth() - (int)(HEART_SIZE * 1.5);
					int yOffset = 25 * 3;
					bossHealth = z.displayHealth(xOffset, yOffset);
				}
			}
			for (GImage heart : bossHealth) { // display all zombie hearts
				heart.setSize(HEART_SIZE, HEART_SIZE);
				program.add(heart);
			}
			
		}
	}
	
	private void addHeart(Zombie zombie, int xOffset, int yOffset) {
		if (zombie.getEnemyType() == "zombieBoss") { //if enemy is a boss OR (50 + (2 * currentRoom)) % chance)
			GImage heartSprite = new GImage ("HP.png", zombie.getSprite().getX() + xOffset, zombie.getSprite().getY() + yOffset); //Create a new sprite for heart.
			heartSprite.setSize(25, 25); //Resize sprite to make it smaller.
			program.add(heartSprite);
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
	
	private void setSpriteImageZombie(String[] zombieAnimation, int frames, Zombie zombie, GImage zombieSprite, GImage newZombieSprite) {
		newZombieSprite.setImage(zombieAnimation[frames]);
		newZombieSprite.setSize(PLAYER_SPRITE_SIZE,PLAYER_SPRITE_SIZE);
		//Change the size for zombie boss
		if (currentMap == 10) {
			newZombieSprite.setSize(ZOMBIE_SPRITE_SIZE, ZOMBIE_SPRITE_SIZE);
		}
		zombie.setSprite(newZombieSprite);
		program.remove(zombieSprite); // remove previous player sprite
		program.add(newZombieSprite); // add new player sprite
	}
	
	private void setSpriteImageHunger(String[] hungerImages, int frames, GImage prevHungerSprite, GImage newHungerSprite) {
		newHungerSprite.setImage(hungerImages[frames]);
		newHungerSprite.setSize(50, 50);
		program.remove(prevHungerSprite);
		hungerDisplayCurrentImage = newHungerSprite;
		program.add(newHungerSprite);
	}
	
	private void setSpriteImageThirst(String[] ThirstImages, int frames, GImage prevThirstSprite, GImage newThirstSprite) {
		newThirstSprite.setImage(ThirstImages[frames]);
		newThirstSprite.setSize(50, 50);
		program.remove(prevThirstSprite);
		thirstDisplayCurrentImage = newThirstSprite;
		program.add(newThirstSprite);
	}
	
	public void gameOver() {
		System.out.println("Player is dead. Game Over.");
		currentMap = 1;
		
		
		initializeGame(); // reset all game values
		program.removeAll(); // remove all objects from screen
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
			for (House h : houses) {
				double houseX = h.getSprite().getX() + (h.getSprite().getWidth()/2);
				double houseY = h.getSprite().getY() + (h.getSprite().getHeight()/2);
				if (currentMap == 6) { //Since the house is big on this map, adjust y value
					houseY = h.getSprite().getY() + (h.getSprite().getHeight()/2) + 50;
				}
				if (player.canInteract(houseX, houseY)) {
					playerLocationX = player.getSprite().getX();
					playerLocationY = player.getSprite().getY();
					if (h.getItemType() == "house1") {
						currentMap = 2; // increase current room number
						createMap(currentMap); // create next room
					}
					if (h.getItemType() == "house2") {
						currentMap = 3; // increase current room number
						createMap(currentMap); // create next room
					}
					if (h.getItemType() == "house3") {
						currentMap = 4; // increase current room number
						createMap(currentMap); // create next room
					}
					if (h.getItemType() == "houseLevel3") {
						currentMap = 7; // increase current room number
						createMap(currentMap); // create next room
					}
					
				}
			}
			
			//Player should pick up an item if it's an item.
			if ((currentMap == 2 && numOfFoodHouse1 > 0) || ( currentMap == 2 && numOfWaterHouse1 > 0) || (currentMap == 3 && numOfFoodHouse2 > 0) || (currentMap == 3 && numOfWaterHouse2 > 0) || (currentMap == 4 && numOfFoodHouse3 > 0) || (currentMap == 4 && numOfWaterHouse3 > 0) || (currentMap == 7 && numOfFoodLevel3House > 0) || (currentMap == 7 && numOfWaterLevel3House > 0)) { // Add to condition for ALL maps that have items in it (otherwise its an error) 
				Item nearestItem = player.nearestItem(items); //check for item nearest to player
				//If player can interact with closest item and presses "e"
				if (player.canInteract(nearestItem.getSprite().getX(), nearestItem.getSprite().getY())) {
					if (nearestItem.getItemType() == "food") {
						if (currentMap == 2) {
							numOfFoodHouse1--;
						}
						if (currentMap == 3) {
							numOfFoodHouse2--;
						}
						if (currentMap == 4) {
							numOfFoodHouse3--;
						}
						if (currentMap == 7) {
							numOfFoodLevel3House--;
						}
						player.SetHunger(player.GetHunger() + 50); //Increase Hunger quench
						player.setHealth(player.getHealth() + 1); //Player gets an extra heart.
						updateHealth(); //update health display
					}
					else if (nearestItem.getItemType() == "water") {
						if (currentMap == 2) {
							numOfWaterHouse1--;
						}
						if (currentMap == 3) {
							numOfWaterHouse2--;
						}
						if (currentMap == 4) {
							numOfWaterHouse3--;
						}
						if (currentMap == 7) {
							numOfWaterLevel3House--;
						}
						player.SetThirst(player.GetThirst() + 50); //Increase Thirst quench
						player.setHealth(player.getHealth() + 1); //Player gets an extra heart.
						updateHealth(); //update health display
					}
					items.remove(nearestItem);
					program.remove(nearestItem.getSprite());
				}
			}
			
			if (currentMap == 1 && player.getSprite().getX() >= 500 && player.getSprite().getX() <= 607 && player.getSprite().getY() <= 23) {
				currentMap = 5;
				createMap(currentMap);
			}
			

			if (currentMap == 5 && player.getSprite().getX() >= 449 && player.getSprite().getX() <= 544 && player.getSprite().getY() <= 56) {
				currentMap = 6;
				createMap(currentMap);
			}
			
			if (currentMap == 6 && player.getSprite().getX() >= 739 && player.getSprite().getY() >= 327 && player.getSprite().getY() <= 420) {
				currentMap = 8;
				createMap(currentMap);
			}
			
			if (currentMap == 8 && player.getSprite().getX() >= 449 && player.getSprite().getX() <= 544 && player.getSprite().getY() <= 56) {
				currentMap = 9;
				createMap(currentMap);
			}
			
			if (currentMap == 9 && player.getSprite().getX() >= 449 && player.getSprite().getX() <= 544 && player.getSprite().getY() <= 56) {
				currentMap = 10;
				createMap(currentMap);
			}
			
			
		} if (keyCode == 90) { // z
			if (currentMap == 2) {
				//If Player is at the door, and wants to go back outside, send him to main map
				if (player.getSprite().getX() >= 117 && player.getSprite().getX() <= 178 && player.getSprite().getY() >= 540) {
					playerExitsHouse1 = true; //For seting player location accordingly
					currentMap = 1; // increase current room number
					//This is for if player exits house and re-enters it. Set item index back to 0 when re-populating house with it.
					populatingItemsIndex = 0; 
					createMap(currentMap); // create next room
				}
			}
			if (currentMap == 3) {
				//If Player is at the door, and wants to go back outside, send him to main map
				if (player.getSprite().getX() >= 381 && player.getSprite().getX() <= 460 && player.getSprite().getY() <= 113) {
					playerExitsHouse2 = true; //For seting player location accordingly
					currentMap = 1; // increase current room number
					//This is for if player exits house and re-enters it. Set item index back to 0 when re-populating house with it.
					populatingItemsIndex = 0; 
					createMap(currentMap); // create next room
				}
			}
			if (currentMap == 4) {
				//If Player is at the door, and wants to go back outside, send him to main map
				if (player.getSprite().getX() >= 383 && player.getSprite().getX() <= 475 && player.getSprite().getY() >= 474) {
					playerExitsHouse3 = true; //For seting player location accordingly
					currentMap = 1; // increase current room number
					//This is for if player exits house and re-enters it. Set item index back to 0 when re-populating house with it.
					populatingItemsIndex = 0; 
					createMap(currentMap); // create next room
				}
			}
			if (currentMap == 7) {
				if (player.getSprite().getX() >= 129 && player.getSprite().getX() <= 248 && player.getSprite().getY() >= 552) {
					playerExitsHouseLevel3 = true;
					currentMap = 6;
					populatingItemsIndex = 0; 
					createMap(currentMap); // create next room
				}
			}
		}
		
	
		if (keyCode == KeyEvent.VK_ESCAPE) {
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
						if (zombie.getEnemyType() == "zombieBoss") {
							updateHealth(); //update boss health
						}
					}
					
					if (zombie.isDead()) { //Enemy has no health.
						removeZombieIndex.add(z); // add zombie to list if he dies
						if (currentMap == 1) {
							numOfZombiesMainMap--; //If zombie dies in the main map, decrement the number of zombie re-spawns when exiting houses
						}
						if (currentMap == 2) {
							numOfZombiesHouse1--;
						}
						if (currentMap == 3) {
							numOfZombiesHouse2--;
						}
						if (currentMap == 4) {
							numOfZombiesHouse3--;
						}
						if (currentMap == 6) {
							numOfZombiesLevel3--;
						}
						if (currentMap == 7) {
							numOfZombiesLevel3House--;
						}
						updateHealth();
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
		GImage newHungerDisplaySprite = new GImage("", 22, 550);
		GImage newThirstDisplaySprite = new GImage("", 72, 550);
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
					//if (enemyHitDown || enemyHitUp || enemyHitLeft || enemyHitRight) {
					//	break;
					//}
					
					if (!enemyHitDown && !enemyHitUp && !enemyHitLeft && !enemyHitRight) {
						zombieSprite.movePolar(zombie.getSpeed(), angle(zombieSprite, playerSprite) + 180); // close range zombie moves towards player
					}
					
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
						if (enemyHitDown || enemyHitUp || enemyHitLeft || enemyHitRight) {
							zombieMoveDown = false;
						}
					}
					
					if (player.getSprite().getY() < zombie.getSprite().getY() && zombieYDiff > zombieXDiff) { //if zombie is below player . 
						zombieMoveUp = true;
						if (enemyHitDown || enemyHitUp || enemyHitLeft || enemyHitRight) {
							zombieMoveUp = false;
						}
					}
					
					if (zombie.getSprite().getX() > player.getSprite().getX() && zombieXDiff > zombieYDiff) { //if player is to the left of zombie  . 
						zombieMoveLeft = true;
						if (enemyHitDown || enemyHitUp || enemyHitLeft || enemyHitRight) {
							zombieMoveLeft = false;
						}
					}
					
					if (player.getSprite().getX() > zombie.getSprite().getX() && zombieXDiff > zombieYDiff) { //if player is to the right of zombie  . 
						zombieMoveRight = true;
						if (enemyHitDown || enemyHitUp || enemyHitLeft || enemyHitRight) {
							zombieMoveRight = false;
						}
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
					
					
					player.changeHealth(-1);
					updateHealth();
					System.out.println("Player hit by " + zombie.getEnemyType() + ". Player health: " + player.getHealth());
					player.setDamaged(true); //player is damaged.
					checkPlayerDeath();
					zombie.setAttackAvailable(false);
				}
				
				if (currentMap != 10) {
					GImage newZombierSprite = new GImage("", zombieSprite.getX(), zombieSprite.getY()); // For player animation
					/*Zombie attack animation*/
					if (zombieAttackAnimationDownAcc == zombieAttackDown.length) {
						playerHitDown = false; //Full cycle complete, stop from next cycle.
						playerHitUp = false; //Full cycle complete, stop from next cycle.
						playerHitLeft = false; //Full cycle complete, stop from next cycle.
						playerHitRight = false; //Full cycle complete, stop from next cycle.
						zombieAttackAnimationDownAcc = 0; //When next attack happens, start from beginning frame.
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
				else {
					GImage newZombierSprite = new GImage("", zombieSprite.getX(), zombieSprite.getY()); // For player animation
					/*Zombie attack animation*/
					if (zombieAttackAnimationDownAcc == zombie2AttackDown.length) {
						playerHitDown = false; //Full cycle complete, stop from next cycle.
						playerHitUp = false; //Full cycle complete, stop from next cycle.
						playerHitLeft = false; //Full cycle complete, stop from next cycle.
						playerHitRight = false; //Full cycle complete, stop from next cycle.
						zombieAttackAnimationDownAcc = 0; //When next attack happens, start from beginning frame/
						player.setDamaged(false);
					}
					
					if (playerHitDown && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
						//Zombie attack animation
						setSpriteImageZombie(zombie2AttackDown, zombieAttackDownFrame, zombie, zombieSprite, newZombierSprite);
						zombieAttackDownFrame++;
						if(zombieAttackDownFrame>=zombie2AttackDown.length){
							zombieAttackDownFrame = 0;
					 	}
						zombieAttackAnimationDownAcc++;
					}
					
					if (playerHitUp && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
						//Zombie attack animation
						setSpriteImageZombie(zombie2AttackUp, zombieAttackUpFrame, zombie, zombieSprite, newZombierSprite);
						zombieAttackUpFrame++;
						if(zombieAttackUpFrame>=zombie2AttackUp.length){
							zombieAttackUpFrame = 0;
					 	}
						zombieAttackAnimationDownAcc++;
					}
					
					if (playerHitLeft && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
						//Zombie attack animation
						setSpriteImageZombie(zombie2AttackLeft, zombieAttackLeftFrame, zombie, zombieSprite, newZombierSprite);
						zombieAttackLeftFrame++;
						if(zombieAttackLeftFrame>=zombie2AttackLeft.length){
							zombieAttackLeftFrame = 0;
					 	}
						zombieAttackAnimationDownAcc++;
					}
					
					if (playerHitRight && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
						//Zombie attack animation
						setSpriteImageZombie(zombie2AttackRight, zombieAttackRightFrame, zombie, zombieSprite, newZombierSprite);
						zombieAttackRightFrame++;
						if(zombieAttackRightFrame>=zombie2AttackRight.length){
							zombieAttackRightFrame = 0;
					 	}
						zombieAttackAnimationDownAcc++;
					}
					
					/* Zombie movement animation logic */
					
					if (zombieMoveDown) {
						setSpriteImageZombie(zombie2MovingDown, zombieMovingDownFrame, zombie, zombieSprite, newZombierSprite);
						zombieMovingDownFrame++;
						if(zombieMovingDownFrame>=zombie2MovingDown.length){
							zombieMovingDownFrame = 0;
					 	}
					}
					
					
					if (zombieMoveUp) {
						setSpriteImageZombie(zombie2MovingUp, zombieMovingUpFrame, zombie, zombieSprite, newZombierSprite);
						zombieMovingUpFrame++;
						if(zombieMovingUpFrame>=zombie2MovingUp.length){
							zombieMovingUpFrame = 0;
					 	}
					}
					
					
					if (zombieMoveLeft) {
						setSpriteImageZombie(zombie2MovingLeft, zombieMovingLeftFrame, zombie, zombieSprite, newZombierSprite);
						zombieMovingLeftFrame++;
						if(zombieMovingLeftFrame>=zombie2MovingLeft.length){
							zombieMovingLeftFrame = 0;
					 	}
					}
					
					if (zombieMoveRight) {
						setSpriteImageZombie(zombie2MovingRight, zombieMovingRightFrame, zombie, zombieSprite, newZombierSprite);
						zombieMovingRightFrame++;
						if(zombieMovingRightFrame>=zombie2MovingRight.length){
							zombieMovingRightFrame = 0;
					 	}
					}
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
			setSpriteImageHunger(hungerDisplayImages, hungerDisplayIndex, hungerDisplayCurrentImage, newHungerDisplaySprite);
			setSpriteImageThirst(thirstDisplayImages, thirstDisplayIndex, thirstDisplayCurrentImage, newThirstDisplaySprite);
			
		}
		
		//Decrement Hunger accumulator
		if (player.GetHunger() >= 100) {
			hungerDisplayIndex = 0;
		}
		
		//Decrement Hunger accumulator
		if (player.GetHunger() == 90) {
			hungerDisplayIndex = 1;
		}
		
		if (player.GetHunger() == 80) {
			hungerDisplayIndex = 2;
		}
		
		if (player.GetHunger() == 70) {
			hungerDisplayIndex = 3;
		}
		
		if (player.GetHunger() == 60) {
			hungerDisplayIndex = 4;
		}
		
		if (player.GetHunger() == 50) {
			hungerDisplayIndex = 5;
		}
		
		if (player.GetHunger() == 40) {
			hungerDisplayIndex = 6;
		}
		
		if (player.GetHunger() == 30) {
			hungerDisplayIndex = 7;
		}
		
		if (player.GetHunger() == 20) {
			hungerDisplayIndex = 8;
		}
		
		if (player.GetHunger() == 10) {
			hungerDisplayIndex = 9;
		}
		
		//Show HungerWarning to user
		if (player.GetHunger() <= 10) {
			if (timerCount % 20 == 0) {
				setSpriteImageHunger(hungerDisplayImages, 10, hungerDisplayCurrentImage, newHungerDisplaySprite);
			}
			if (timerCount % 40 == 0) {
				setSpriteImageHunger(hungerDisplayImages, 9, hungerDisplayCurrentImage, newHungerDisplaySprite);
			}
		}
		
		//Decrement Thirst accumulator
		if (player.GetThirst() >= 100) {
			thirstDisplayIndex = 0;
		}
		
		if (player.GetThirst() == 90) {
			thirstDisplayIndex = 1;
		}
				
		if (player.GetThirst() == 80) {
			thirstDisplayIndex = 2;
		}
				
		if (player.GetThirst() == 70) {
			thirstDisplayIndex = 3;
		}
				
		if (player.GetThirst() == 60) {
			thirstDisplayIndex = 4;
		}
				
		if (player.GetThirst() == 50) {
			thirstDisplayIndex = 5;
		}
				
		if (player.GetThirst() == 40) {
			thirstDisplayIndex = 6;
		}
				
		if (player.GetThirst() == 30) {
			thirstDisplayIndex = 7;
		}
				
		if (player.GetThirst() == 20) {
			thirstDisplayIndex = 8;
		}
				
		if (player.GetThirst() == 10) {
			thirstDisplayIndex = 9;
		}
		
		//Show thirst warning to user.
		if (player.GetThirst() <= 10) {
			if (timerCount % 20 == 0) {
				setSpriteImageThirst(thirstDisplayImages, 10, thirstDisplayCurrentImage, newThirstDisplaySprite);
			}
			if (timerCount % 40 == 0) {
				setSpriteImageThirst(thirstDisplayImages, 9, thirstDisplayCurrentImage, newThirstDisplaySprite);
			}
		}
		
		//Use attackAnimationAcc for sing full cycle of attack animation.
		if (attackAnimationDownAcc == 3) {
			enemyHitDown = false; //Full cycle complete, stop from next cycle.
			attackAnimationDownAcc = 0; //When next attack happens, start from beginning frame/
		}
		
		if (attackAnimationAcc == 4) {
			enemyHitUp = false; //Full cycle complete, stop from next cycle.
			attackAnimationAcc = 0; //When next attack happens, start from beginning frame/
		}
		
		if (attackAnimationLeftAndRightAcc == 5) {
			enemyHitRight = false;
			enemyHitLeft = false;
			attackAnimationLeftAndRightAcc = 0;
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
			attackAnimationLeftAndRightAcc++;
			
		}
		
		if (enemyHitRight && timerCount % ATTACK_ANIMATION_INTERVAL == 0) {
			//Player attack animation
			setSpriteImage(playerAttackRightKnife, playerAttackRightKnifeFrame, player, playerSprite, newPlayerSprite);
			playerAttackRightKnifeFrame++;
			if(playerAttackRightKnifeFrame>=playerAttackRightKnife.length){
				playerAttackRightKnifeFrame = 0;
		 	}
			attackAnimationLeftAndRightAcc++;
			
		}
		
		
		//When hunger and thirst run out, game over.
		if (player.GetHunger() == 0 || player.GetThirst() == 0) {
			 gameOver();
		}
		
		for (House h : houses) {
			double houseX = h.getSprite().getX() + (h.getSprite().getWidth()/2);
			double houseY = h.getSprite().getY() + (h.getSprite().getHeight()/2);
			if (player.canInteract(houseX, houseY)) {
				doorLabel.setVisible(true);
				doorLabel.setLocation(houseX-60, houseY);
			}
		}
		
		
		if (timerCount % DOOR_LABEL_INTERVAL == 0) {
			doorLabel.setVisible(false);
		}
		
		
	}

	@Override
	public void showContents() {
		createMap(currentMap); // currentMap is initially at 1
		
	}

	

	@Override
	public void hideContents() {
		program.removeAll();
		
	}

}	