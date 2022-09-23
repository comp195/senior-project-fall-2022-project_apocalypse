package default_package;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class ScreenDisplayPane extends GraphicsProgram {
	
	private static final int HEIGHT_CHEST = 200;
	private static final int WIDTH_CHEST = 200;
	private static final int START_Y_CHEST = 265;
	private static final int START_X_CHEST = 265;
	private static final int HEIGHT = 300;
	private static final int WIDTH = 300;
	private static final int START_Y = 215;
	private static final int START_X = 215;
	public static final int PROGRAM_HEIGHT = 600;
	public static final int PROGRAM_WIDTH = 800;
	
	public ScreenDisplayPane() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		
		/** This is a Robot drawing **/
		//STEP 1: Create a rectangle for robot body
		GRect rect = new GRect(START_X, START_Y, WIDTH, HEIGHT);
		add(rect);
		
		//STEP 2: Create oval (inside rectangle) for chest.
		GOval oval = new GOval(START_X_CHEST, START_Y_CHEST, WIDTH_CHEST, HEIGHT_CHEST);
		oval.setFillColor(Color.RED);
		oval.setFilled(true);
		add(oval);
		
		//STEP 3: Create robot rectangle legs.
		GRect leg1 = new GRect(215, 515, 100, 70);
		leg1.setFillColor(Color.ORANGE);
		leg1.setFilled(true);
		add(leg1);
		
		GRect leg2 = new GRect(415, 515, 100, 70);
		leg2.setFillColor(Color.ORANGE);
		leg2.setFilled(true);
		add(leg2);
		
		//STEP 4: Add robot head picture.
		GImage head = new GImage("robot.png", 250, 88);
		add(head);
		
		//STEP 5: Add a line with  an arrow to point at robot.
		GLine arrowLine = new GLine(70, 70, 200, 200);
		add(arrowLine);
		
		GLine arrowPoint = new GLine(200, 200, 200, 180);
		add(arrowPoint);
		
		GLine arrowPoint2 = new GLine(200, 200, 180, 200);
		add(arrowPoint2);
		
		//STEP 5: Add a label stating my name.
		GLabel myName = new GLabel("Eesa Khan", 20, 60);
		myName.setColor(Color.blue);
		myName.setFont("Arial-Bold-24");
		add(myName);
		
	}
	public static void main(String[] args) {
		new ScreenDisplayPane().start();
	}
}
