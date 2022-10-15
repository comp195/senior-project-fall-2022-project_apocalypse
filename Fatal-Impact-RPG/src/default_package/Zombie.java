package default_package;

import acm.graphics.GImage;

public class Zombie extends Entity {
	private boolean attackAvailable;
	private String enemyType;
	private int detectionRange;
	
	public Zombie(GImage image, int health, String enemyName) {
		super(image, health);
		setEnemyType(enemyName);
		attackAvailable = false;
		detectionRange = 100; // detection range is 100 by default
		super.setSpeed(5); // speed is 5 by default
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
	
	
	public void enemyInvincibility() {
			setInvincibilityCounter(getInvincibilityCounter() + 1); //enemy is invincible for a time.
			if (getInvincibilityCounter() > 100) { //enemy is not invincible.
				setDamaged(false);
				setInvincibilityCounter(0); 
			}
			System.out.println("invincibility counter: " + getInvincibilityCounter());
	}

	public static void main(String[] args) {

	}
}

