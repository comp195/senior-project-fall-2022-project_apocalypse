package default_package;

import java.util.ArrayList;

import acm.graphics.GImage;

public class Zombie extends Entity {
	private boolean attackAvailable;
	private String enemyType;
	private int detectionRange;
	private static final int HEART_SIZE = 50;
	
	public Zombie(GImage image, int health, String enemyName) {
		super(image, health);
		setEnemyType(enemyName);
		attackAvailable = false;
		detectionRange = 100; // detection range is 100 by default
		super.setSpeed(10); // speed is 5 by default
		super.setAttackCooldown(300);
	}
	
	public ArrayList<GImage> displayHealth(int xOffset, int yOffset) {
		ArrayList<GImage> bossHealth = new ArrayList<GImage>(); 
		for (int x = 0; x < getHealth(); x++) { //add hearts based on boss health
			// if health > 5, place the next row of hearts right next to original row of hearts
			bossHealth.add(new GImage("HP.png", xOffset + (5 * (x / 5)), x % 5 * HEART_SIZE + yOffset)); 
		}
		return bossHealth;
	}
	
	public boolean canInteract(double x, double y) {
		double xDiff = Math.abs(x - super.getSprite().getX()); // find difference in x coordinates
		double yDiff = Math.abs(y - super.getSprite().getY()); // find difference in y coordinates
		return xDiff <= detectionRange && yDiff <= detectionRange; //returns true if x,y coordinates are within 100 in x direction and y direction
	}

	public boolean isAttackAvailable() {
		return attackAvailable;
	}

	public void setAttackAvailable(boolean attackAvailable) {
		this.attackAvailable = attackAvailable;
	}
	
	public String getEnemyType() {
		return enemyType;
	}

	public void setEnemyType(String enemyType) {
		this.enemyType = enemyType;
	}

	public int getDetectionRange() {
		return detectionRange;
	}

	public void setDetectionRange(int detectionRange) {
		this.detectionRange = detectionRange;
	}
	

	public static void main(String[] args) {

	}
}