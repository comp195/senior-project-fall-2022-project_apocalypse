package default_package;
import java.util.ArrayList; // for ArrayList
import java.util.HashMap;
import java.util.Random;

import acm.graphics.GImage; // for GImage
import acm.graphics.GPoint;


public class Map {
	
	//For random placement of zombie spawns
	Random rand = new Random();
	int randMinXMainMap = 50;  
	int randMaxXMainMap = 600; 
	int randMinYMainMap = 50;
	int randMaxYMainMap = 450; 
	private boolean playerEntersHouse1FirstTime;
	private boolean playerEntersHouse2FirstTime;
	private boolean playerEntersHouse3FirstTime;
	
	private double width; // width of program
	private double height; // height of program
	private int map; // room number
	private HashMap<String, String> sprites;
	private ArrayList<GPoint> locations;
	private String backgroundImageName;
	private ArrayList<Item> items;
	private ArrayList<Zombie> zombies;
	private ArrayList<House> houses;
	private String backgroundName;
	private int zombieSpawnsMainMap;
	private int zombieSpawnsHouse1;
	private int zombieSpawnsHouse2;
	private int zombieSpawnsHouse3;
	
	private int foodsSpawnHouse1;
	private int foodsSpawnHouse2;
	private int foodsSpawnHouse3;
	
	private int waterSpawnHouse1;
	private int waterSpawnHouse2;
	private int waterSpawnHouse3;
	
	
	public Map(int mapNumber, double w, double h) {
		map = mapNumber;
		width = w;
		height = h;
		sprites = new HashMap<String, String>();
		
		if (mapNumber == 1) {
			backgroundImageName = "FI-Forest-Map.png";
		}
		if (mapNumber == 2) {
			backgroundImageName = "city-map.png";
		}
		
		sprites = new HashMap<String, String>();
		
		//enemy sprites for different kinds of enemies
		sprites.put("zombie", "ZombieSprite.png"); 
		
		
		//item sprites for different kinds of items
		sprites.put("food", "chickenDrumstick.jpg");
		sprites.put("water", "waterBottle.jpg");
		
		//house sprites for different house types
		sprites.put("house1", "FI-House1.png");
		sprites.put("house2", "FI-House2.png");
		sprites.put("house3", "FI-House3.png");
		
		//items = new ArrayList<Item>();
		zombies = new ArrayList<Zombie>();
		
	}
	
	public void setNumOfZombies(int numOfZombies) {
		zombieSpawnsMainMap = numOfZombies;
	}
	
	public void setNumOfZombiesHouse1(int numOfZombies) {
		zombieSpawnsHouse1 = numOfZombies;
	}
	
	public void setNumOfZombiesHouse2(int numOfZombies) {
		zombieSpawnsHouse2 = numOfZombies;
	}
	
	public void setNumOfZombiesHouse3(int numOfZombies) {
		zombieSpawnsHouse3 = numOfZombies;
	}
	
	public void setNumOffoodsHouse1(int numOfItems) {
		foodsSpawnHouse1 = numOfItems;
	}
	
	public void setNumOffoodsHouse2(int numOfItems) {
		foodsSpawnHouse2 = numOfItems;
	}
	
	public void setNumOffoodsHouse3(int numOfItems) {
		foodsSpawnHouse3 = numOfItems;
	}
	
	public void setNumOfWaterHouse1(int numOfItems) {
		waterSpawnHouse1 = numOfItems;
	}
	
	public void setNumOfWaterHouse2(int numOfItems) {
		waterSpawnHouse2 = numOfItems;
	}
	
	public void setNumOfWaterHouse3(int numOfItems) {
		waterSpawnHouse3 = numOfItems;
	}
	
	public void setPlayerHouse1FirstTime(boolean TrueOrFalse) {
		playerEntersHouse1FirstTime = TrueOrFalse;
	}
	
	public int getNumOfZombies() {
		return zombieSpawnsMainMap;
	}
	
	public int getNumOfZombiesHouse1() {
		return zombieSpawnsHouse1;
	}
	
	public int getNumOfZombiesHouse2() {
		return zombieSpawnsHouse2;
	}
	
	public int getNumOfZombiesHouse3() {
		return zombieSpawnsHouse3;
	}
	
	public int getNumOfFoodsHouse1() {
		return foodsSpawnHouse1;
	}
	
	public int getNumOfFoodsHouse2() {
		return foodsSpawnHouse2;
	}
	
	public int getNumOfFoodsHouse3() {
		return foodsSpawnHouse3;
	}
	
	public int getNumOfWaterHouse1() {
		return waterSpawnHouse1;
	}
	
	public int getNumOfWaterHouse2() {
		return waterSpawnHouse2;
	}
	
	public int getNumOfWaterHouse3() {
		return waterSpawnHouse3;
	}
	
	public boolean getPlayerHouse1FirstTime() {
		return playerEntersHouse1FirstTime;
	}
	
	
	public ArrayList<Zombie> getZombies() {
		zombies = new ArrayList<Zombie>(); // initialize enemy array list
		switch(map) {
		case 1:
			for (int i = 0; i < zombieSpawnsMainMap; i++) {
				addZombie("zombie", 3, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 2:
			for (int i = 0; i < zombieSpawnsHouse1; i++) {
				addZombie("zombie", 3, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 3:
			for (int i = 0; i < zombieSpawnsHouse2; i++) {
				addZombie("zombie", 3, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 4:
			for (int i = 0; i < zombieSpawnsHouse3; i++) {
				addZombie("zombie", 3, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
			
		}
		
		
		
		
		return zombies;
	}
	
	public ArrayList<Item> getItems() {
		items = new ArrayList<Item>();
		switch(map) {
		case 2:
			/*
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 400, 200);
			addItem("water", "water", 30, 30, 500, 300);
			addItem("water", "water", 30, 30, 600, 100);
			addItem("water", "water", 30, 30, 550, 100);
			addItem("water", "water", 30, 30, 600, 300);

			addItem("food", "food", 30, 30, 400, 300);
			addItem("food", "food", 30, 30, 400, 500);
			addItem("food", "food", 30, 30, 300, 500);
			*/
			for (int i = 0; i < waterSpawnHouse1; i++) {
				
				addItem("water", "water", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap,  Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			for (int i = 0; i < foodsSpawnHouse1; i++) {
				
				addItem("food", "food", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			break;
		case 3:
			/*
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 400, 200);
			addItem("water", "water", 30, 30, 500, 300);
			addItem("water", "water", 30, 30, 600, 100);
			addItem("water", "water", 30, 30, 550, 100);
			addItem("water", "water", 30, 30, 600, 300);

			addItem("food", "food", 30, 30, 400, 300);
			addItem("food", "food", 30, 30, 400, 500);
			addItem("food", "food", 30, 30, 300, 500);
			*/
			for (int i = 0; i < waterSpawnHouse2; i++) {
				
				addItem("water", "water", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap,  Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			for (int i = 0; i < foodsSpawnHouse2; i++) {
				
				addItem("food", "food", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			break;
		case 4:
			/*
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 400, 200);
			addItem("water", "water", 30, 30, 500, 300);
			addItem("water", "water", 30, 30, 600, 100);
			addItem("water", "water", 30, 30, 550, 100);
			addItem("water", "water", 30, 30, 600, 300);

			addItem("food", "food", 30, 30, 400, 300);
			addItem("food", "food", 30, 30, 400, 500);
			addItem("food", "food", 30, 30, 300, 500);
			*/
			for (int i = 0; i < waterSpawnHouse3; i++) {
				
				addItem("water", "water", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap,  Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			for (int i = 0; i < foodsSpawnHouse3; i++) {
				
				addItem("food", "food", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			break;

		}

		return items;
	}
	
	public ArrayList<House> getHouses() {
		houses = new ArrayList<House>(); 
		switch(map) {
		case 1:
			addHouse("house1", "house1", 100, 100, 50, 100);
			addHouse("house2", "house2", 100, 100, 50, 450);
			addHouse("house3", "house3", 100, 100, 700, 400);
			break;
		}
		return houses;
	}
	
	
	private void addZombie(String enemy, int health, String enemyType, int detectionRange, double speed, double xCoord, double yCoord) {
		
		GImage enemySprite = new GImage(sprites.get(enemy), xCoord, yCoord);
		enemySprite.setSize(30, 30);
		Zombie enemyToAdd = new Zombie(enemySprite, health, enemyType);
		enemyToAdd.setDetectionRange(detectionRange);
		enemyToAdd.setSpeed(speed);
		zombies.add(enemyToAdd);
	}
	
	private void addItem(String item, String itemType, int sizeX, int sizeY, double d, double e) {
		GImage itemSprite = new GImage(sprites.get(item), d, e);
		itemSprite.setSize(sizeX, sizeY);
		Item itemToAdd = new Item(itemSprite, itemType);
		items.add(itemToAdd);
	}
			
	private void addHouse(String house, String houseType, int sizeX, int sizeY, int xCoord, int yCoord) {
		GImage houseSprite = new GImage(sprites.get(house), xCoord, yCoord);
		houseSprite.setSize(sizeX, sizeY);
		House houseToAdd = new House(houseSprite, houseType);
		houses.add(houseToAdd);
	}
	
	public void setPlayerLocation(Player p, double x, double y) {
		p.getSprite().setLocation(x, y);
	}
	
	private void addLocation(double x, double y) {
		GPoint addLocation = new GPoint(x,y);
		locations.add(addLocation);
	}
	
	public String getImageName() {
		return backgroundImageName;
	}
	

}

