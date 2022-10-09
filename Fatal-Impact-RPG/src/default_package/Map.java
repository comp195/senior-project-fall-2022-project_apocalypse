package default_package;
import java.util.ArrayList; // for ArrayList
import java.util.HashMap;

import acm.graphics.GImage; // for GImage
import acm.graphics.GPoint;

public class Map {
	
	private double width; // width of program
	private double height; // height of program
	private int map; // room number
	private HashMap<String, String> sprites;
	private ArrayList<GPoint> locations;
	private String backgroundImageName;
	
	public Map(int mapNumber, double w, double h) {
		map = mapNumber;
		width = w;
		height = h;
		sprites = new HashMap<String, String>();
		
		if (mapNumber == 1) {
			backgroundImageName = "city-map.jpg";
		}
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
