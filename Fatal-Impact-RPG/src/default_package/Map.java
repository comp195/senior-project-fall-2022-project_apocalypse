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
	
	private int zombiesSpawnLevel3Map;
	private int zombiesSpawnLevel3House;
	private int foodsSpawnLevel3House;
	private int waterSpawnLevel3House;

	
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
		sprites.put("zombieBoss", "FI-Zombie2-Standing-Down.png");
		
		//item sprites for different kinds of items
		sprites.put("food", "chickenDrumstick.jpg");
		sprites.put("water", "waterBottle.jpg");
		
		//house sprites for different house types
		sprites.put("house1", "FI-House1.png");
		sprites.put("house2", "FI-House2.png");
		sprites.put("house3", "FI-House3.png");
		sprites.put("houseLevel3", "FI-Level3-House-Map.png");
		
		//items = new ArrayList<Item>();
		zombies = new ArrayList<Zombie>();
		
	}
	
	public void setNumOfZombies(int numOfZombies) {
		zombieSpawnsMainMap = numOfZombies;
	}
	
	public void setNumOfZombiesLevel3(int numOfZombies) {
		zombiesSpawnLevel3Map = numOfZombies;
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
	
	public void setNumOfZombiesLevel3House(int numOfZombies) {
		zombiesSpawnLevel3House = numOfZombies;
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
	
	public void setNumOffoodsLevel3House(int numOfItems) {
		foodsSpawnLevel3House = numOfItems;
	}
	
	public void setNumOfWaterLevel3House(int numOfItems) {
		waterSpawnLevel3House = numOfItems;
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
	
	public int getNumOfZombiesLevel3() {
		return zombiesSpawnLevel3Map;
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
	
	public int getNumOfZombiesLevel3House() {
		return zombiesSpawnLevel3House;
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
	
	public int getNumOffoodsLevel3House() {
		return foodsSpawnLevel3House;
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
	
	public int getNumOfWaterLevel3House() {
		return waterSpawnLevel3House;
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
		case 5:
			for (int i = 0; i < zombieSpawnsMainMap; i++) {
				addZombie("zombie", 3, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 6:
			for (int i = 0; i < zombiesSpawnLevel3Map; i++) {
				addZombie("zombie", 3, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 7:
			for (int i = 0; i < zombiesSpawnLevel3House; i++) {
				addZombie("zombie", 3, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 8:
			for (int i = 0; i < zombieSpawnsMainMap; i++) {
				addZombie("zombie", 5, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 9:
			for (int i = 0; i < zombieSpawnsMainMap; i++) {
				addZombie("zombie", 5, "zombie", 100, 10, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
		case 10:
			for (int i = 0; i < zombieSpawnsMainMap; i++) {
				addZombie2("zombieBoss", 5, "zombieBoss", 200, 15, Math.random()*(randMaxXMainMap-randMinXMainMap+50)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+50)+randMinYMainMap);
			}
			break;
			
		}
		
		
		
		
		return zombies;
	}
	
	public ArrayList<Item> getItems() {
		items = new ArrayList<Item>();
		switch(map) {
		case 2:
			for (int i = 0; i < waterSpawnHouse1; i++) {
				
				addItem("water", "water", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap,  Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			for (int i = 0; i < foodsSpawnHouse1; i++) {
				
				addItem("food", "food", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			break;
		case 3:
			for (int i = 0; i < waterSpawnHouse2; i++) {
				
				addItem("water", "water", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap,  Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			for (int i = 0; i < foodsSpawnHouse2; i++) {
				
				addItem("food", "food", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			break;
		case 4:
			for (int i = 0; i < waterSpawnHouse3; i++) {
				
				addItem("water", "water", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap,  Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			for (int i = 0; i < foodsSpawnHouse3; i++) {
				
				addItem("food", "food", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			break;
		case 7:
			for (int i = 0; i < waterSpawnLevel3House; i++) {
				
				addItem("water", "water", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap,  Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}
			for (int i = 0; i < foodsSpawnLevel3House; i++) {
				
				addItem("food", "food", 30, 30, Math.random()*(randMaxXMainMap-randMinXMainMap+1)+randMinXMainMap, Math.random()*(randMaxYMainMap-randMinYMainMap+1));
				
			}

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
		case 6:
			addHouseDefaultSize("houseLevel3", "houseLevel3", 97, 3);
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
	
	private void addZombie2(String enemy, int health, String enemyType, int detectionRange, double speed, double xCoord, double yCoord) {
		
		GImage enemySprite = new GImage(sprites.get(enemy), xCoord, yCoord);
		enemySprite.setSize(50, 50);
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
	
	private void addHouseDefaultSize(String house, String houseType, int xCoord, int yCoord) {
		GImage houseSprite = new GImage(sprites.get(house), xCoord, yCoord);
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

	/*
	public ArrayList<Zombie> getZombies2() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}