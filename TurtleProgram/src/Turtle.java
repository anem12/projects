import java.awt.geom.Point2D;
public class Turtle {
	int turtleDirection = 0;
	Point2D turtleLocation = new Point2D.Double();
	boolean penDown = false;
	
	/*Returns the current direction of the turtle.*/
	public int direction(){
		return turtleDirection;
	}
	
	/*Return true if pen is up, false if the pen is down.*/
	public boolean isPenUp(){
		return penDown;
	}
	
	/*Returns the current direction of the turtle.*/
	public Point2D location(){
		return turtleLocation;
	}
	
	/*Move the turtle distance units in the current direction and draw on the screen if
	the pen is down.*/
	public void move(int distance){
		double radians = Math.toRadians(turtleDirection);
		Double deltax;
		Double deltay;
		deltax = (Math.cos(radians) * distance);
		deltay = (Math.sin(radians) * distance);
		turtleLocation.setLocation(deltax + turtleLocation.getX(),deltay +turtleLocation.getY());
	}
	
	/*Put the pen down.*/
	public void penDown(){
		penDown = true;
	}
	
	/*Lift the pen up.*/
	public void penUp(){
		penDown = false;
	}
	
	/*Add degrees to the current heading of the turtle.*/
	public void turn(int degrees){
		turtleDirection += degrees;
		while (turtleDirection >= 360)
			turtleDirection = turtleDirection - 360;
		while (turtleDirection < 0)
			turtleDirection = turtleDirection + 360;
	}
}
