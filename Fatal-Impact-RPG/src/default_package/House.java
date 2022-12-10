package default_package;

import acm.graphics.GImage;
import acm.graphics.GLabel;

public class House {
	private GImage sprite;
	private String houseType;
	private GLabel message;
	
	public House(GImage image, String name) {
		// TODO Auto-generated constructor stub
		sprite = image;
		houseType = name;
		message = new GLabel("");
	}
	
	public void setSprite(GImage newImage) {
		sprite = newImage;
	}
	
	public GImage getSprite() {
		return sprite;
	}
	
	public void setItemType(String s) {
		houseType = s;
	}
	
	public String getItemType() {
		return houseType;
	}

}
