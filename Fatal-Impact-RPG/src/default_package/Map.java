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
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 400, 200);
			addItem("water", "water", 30, 30, 500, 300);
			addItem("water", "water", 30, 30, 600, 100);
			addItem("water", "water", 30, 30, 550, 100);
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 600, 300);

			addItem("food", "food", 30, 30, 400, 300);
			addItem("food", "food", 30, 30, 400, 500);
			addItem("food", "food", 30, 30, 300, 500);
			break;
		case 3:
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 400, 200);
			addItem("water", "water", 30, 30, 500, 300);
			addItem("water", "water", 30, 30, 600, 100);
			addItem("water", "water", 30, 30, 550, 100);
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 600, 300);

			addItem("food", "food", 30, 30, 400, 300);
			addItem("food", "food", 30, 30, 400, 500);
			addItem("food", "food", 30, 30, 300, 500);
			break;
		case 4:
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 400, 200);
			addItem("water", "water", 30, 30, 500, 300);
			addItem("water", "water", 30, 30, 600, 100);
			addItem("water", "water", 30, 30, 550, 100);
			addItem("water", "water", 30, 30, 300, 100);
			addItem("water", "water", 30, 30, 600, 300);

			addItem("food", "food", 30, 30, 400, 300);
			addItem("food", "food", 30, 30, 400, 500);
			addItem("food", "food", 30, 30, 300, 500);
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
	
	private void addItem(String item, String itemType, int sizeX, int sizeY, int xCoord, int yCoord) {
		GImage itemSprite = new GImage(sprites.get(item), xCoord, yCoord);
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

