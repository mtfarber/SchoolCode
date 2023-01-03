package gui;


/**
 * WallFollower
 * Follows the leftmost wall of the maze around until it can find an exit
 * If the maze is correctly built, this algorithm will eventually always be able to find the exit
 * Does not find the exit efficiently
 * 
 * Collaborators;
 * ReliableRobot.java
 * UnreliableRobot.java
 * Maze.java
 * @author Matt Farber
 *
 */

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Direction;
import gui.Robot.Turn;

public class WallFollower extends Wizard {
	/**
	 * If the robot has found the exit then leave through the exit to win the game
	 * Check to see if there is a wall to the left of the robot
	 * If there's a wall, move forwards as long as there is no wall in front of the robot
	 * Turn to the right if the robot runs into a wall and has a wall on the left
	 * Turn left if the robot doesn't have a wall to the left, and then move forwards one cell
	 * Use a different algorithm if either the left or front sensor fail
	 */
	@Override
	public boolean drive1Step2Exit() throws Exception {
		
		if (getDistance(Direction.LEFT) > 0) {
			robot.rotate(Turn.LEFT);
			robot.move(1);
			return true;
		}

		else if (getDistance(Direction.FORWARD) > 0) {
			robot.move(1);
			return true;
		}
		
		else if (getDistance(Direction.RIGHT) > 0) {
			robot.rotate(Turn.RIGHT);
			robot.move(1);
			return true;
		}
		
		else {
			Thread.sleep(100);
			robot.rotate(Turn.AROUND);
			return true;
		}
	}
	
	/**
	 * Method to return the distance in any direction that accounts for failures
	 * Will turn the robot to use a functioning sensor
	 * Return the distance if the sensor is working
	 * Pick another sensor to use if the current sensor isn't working
	 * Turn the robot to use that functioning sensor, get the distance, and then return the robot to be facing
	 * the previous direction
	 * Wait for a sensor to start functioning if all of the sensors fail
	 */
	
	public int getDistance(Direction direction) {
		DistanceSensor sensor = robot.getSensor(direction);
		
		try {
			
		if (sensor.isOperational()) return robot.distanceToObstacle(direction);
		
		else if (robot.getSensor(left(direction)).isOperational()) {
			robot.rotate(Turn.LEFT);
			int dir = robot.distanceToObstacle(left(direction));
			robot.rotate(Turn.RIGHT);
			return dir;
		}
		
		else if (robot.getSensor(right(direction)).isOperational()) {
			robot.rotate(Turn.RIGHT);
			int dir = robot.distanceToObstacle(right(direction));
			robot.rotate(Turn.LEFT);
			return dir;
		}
		
		else if (robot.getSensor(around(direction)).isOperational()) {
			robot.rotate(Turn.AROUND);
			int dir = robot.distanceToObstacle(around(direction));
			robot.rotate(Turn.AROUND);
			return dir;
		}

		else {
			System.out.println("No sensors available, waiting until one opens up");
			while (!sensor.isOperational()) {
				Thread.sleep(100);
			}
			return robot.distanceToObstacle(direction);
		}
		
		} catch (Exception e) {}
		return 1;
	}
	
	/**
	 * Method to return the direction after a right turn
	 */
	private Direction left(Direction direction) {
		switch(direction) {
		case LEFT:
			return Direction.FORWARD;
		case FORWARD:
			return Direction.RIGHT;
		case RIGHT:
			return Direction.BACKWARD;
		case BACKWARD:
		default:
			return Direction.LEFT;
		}
	}
	
	
	/**
	 * Method to return the direction after a left turn
	 */
	private Direction right(Direction direction) {
		switch(direction) {
		case LEFT:
			return Direction.BACKWARD;
		case FORWARD:
			return Direction.LEFT;
		case RIGHT:
			return Direction.FORWARD;
		case BACKWARD:
		default:
			return Direction.RIGHT;
		}
	}
	
	/**
	 * Method to return the direction after a 180 turn
	 */
	private Direction around(Direction direction) {
		switch(direction) {
		case LEFT:
			return Direction.RIGHT;
		case FORWARD:
			return Direction.BACKWARD;
		case RIGHT:
			return Direction.LEFT;
		case BACKWARD:
		default:
			return Direction.FORWARD;
		}
	}
	
}
