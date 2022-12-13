package default_package;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList; // for arraylist

import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Player extends Entity {
	private static final int ITEM_SIZE = 25;
	private static final int HEART_SIZE = 50;
	
	private boolean dashAvailable;
	private boolean attackAvailable;
	private int attackDisplayCount;
	private int hunger;
	private int thirst;
	
	public Player(GImage sprite, int health) {
		super(sprite, health);
		attackAvailable = false;
		super.setAttackCooldown(50); // set to 200 by default
		
		SetHunger(100);
		SetThirst(100);
		
	}
	
	public Item nearestItem(ArrayList<Item> items) {
		double lowestDistance = distanceToItem(items.get(0));
		Item i = items.get(0);
		for (int x = 0 ; x < items.size(); x++) {
			if (distanceToItem(items.get(x)) < lowestDistance) {
				lowestDistance = distanceToItem(items.get(x));
				i = items.get(x);
			}
		}	
		return i;
	}
	
	
	public double distanceToItem(Item i) {
		double x = Math.abs(i.getSprite().getX()- super.getSprite().getX()); // find difference in x coordinates
		double y = Math.abs(i.getSprite().getY() - super.getSprite().getY()); // find difference in y coordinates
		return Math.sqrt(x * x + y * y);
		
	}
	
	public double distanceToHouse(House h) {
		double x = Math.abs(h.getSprite().getX()- super.getSprite().getX()); // find difference in x coordinates
		double y = Math.abs(h.getSprite().getY() - super.getSprite().getY()); // find difference in y coordinates
		return Math.sqrt(x * x + y * y);
		
	}
	
	public void SetThirst(int thirst) {
		this.thirst = thirst;
	}
	
	public int GetThirst() {
		return thirst;
	}
	
	public void SetHunger(int h) {
		hunger = h;
	}
	
	public int GetHunger() {
		return hunger;
	}
	
	public ArrayList<GImage> displayHealth() {
		ArrayList<GImage> playerHealth = new ArrayList<GImage>(); 
		for (int x = 0; x < getHealth(); x++) { //add hearts based on player health
			// if health > 10, place the next row of hearts right beneath original row of hearts
			playerHealth.add(new GImage("HP.png", x % 10 * HEART_SIZE, 5 * (x / 10)));
		}
		return playerHealth;
	}

	public Boolean canInteract(double x, double y) {
		double xDiff = Math.abs(x - super.getSprite().getX()); // find difference in x coordinates
		double yDiff = Math.abs(y - super.getSprite().getY()); // find difference in y coordinates
		//return xDiff <= 60 && yDiff <= 60; //returns true if x,y coordinates are within 40 in x direction and y direction
		return xDiff <= 50 && yDiff <= 50; //returns true if x,y coordinates are within 40 in x direction and y direction
	}

	public boolean isDashAvailable() {
		return dashAvailable;
	}

	public void setDashAvailable(boolean dashAvailable) {
		this.dashAvailable = dashAvailable;
	}

	public boolean isAttackAvailable() {
		return attackAvailable;
	}

	public void setAttackAvailable(boolean attackAvailable) {
		this.attackAvailable = attackAvailable;
	}
	
	
	public void randomizeXLocation(double maxX, double y) { // randomizes x location
		getSprite().setLocation((maxX - 1.75 * getSprite().getWidth()) * Math.random(), y - 2.25 * getSprite().getHeight());
	}
	
	public void setAttackDisplayCount (int timerCount) {
		attackDisplayCount = timerCount;
	}
	
	public int getAttackDisplayCount () {
		return attackDisplayCount;
	}
	
	public static void main(String[] args) {
		
	}
}