package default_package;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class Item {
	private GImage sprite;
	private String itemType;
	private GLabel message;
	//private static final String folder = "/images/"; // Later for creating folder for all images
	
	public Item(GImage image, String name) {
		sprite = image;
		itemType = name;
		message = new GLabel("");
	}
	
	public void setSprite(GImage newImage) {
		sprite = newImage;
	}
	
	public GImage getSprite() {
		return sprite;
	}
	
	public void setLabel(String s) {
		message.setLabel(s);
	}
	
	public GLabel getLabel() {
		return message;
	}
	
	public void setItemType(String s) {
		itemType = s;
	}
	
	public String getItemType() {
		return itemType;
	}
	
	public static void main(String[] args) {

	}

	//Method for returning folder with all images. Future concept.
	/*
	public static String getFolder() {
		return folder;
	}
	*/

}
