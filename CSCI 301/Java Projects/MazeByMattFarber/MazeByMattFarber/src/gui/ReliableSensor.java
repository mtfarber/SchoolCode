package gui;

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Direction;

/**
 * ReliableSensor
 * Gets the distance to a wall in one specified direction
 * Works in any of the four directions it can be positioned in
 * 
 * Collaborators:
 * ReliableRobot.java
 * UnreliableRobot.java
 * Maze.java
 * Floorplan.java
 * Controller.java
 * 
 * @author Matt Farber
 *
 */
public class ReliableSensor implements DistanceSensor{
	
	Direction direction;
	public Maze maze;
	int SensingEnergy;
	

	private CardinalDirection findDirection (CardinalDirection robotDirection, Direction sensorDirection) {
		
		switch (sensorDirection) {
		case BACKWARD:
			if (robotDirection == CardinalDirection.North) {
				return CardinalDirection.South;
			}
			if (robotDirection == CardinalDirection.South) {
				return CardinalDirection.North;
			}
			if (robotDirection == CardinalDirection.East) {
				return CardinalDirection.West;
			}
			if (robotDirection == CardinalDirection.West) {
				return CardinalDirection.East;
			}
		case RIGHT:
			if (robotDirection == CardinalDirection.North) {
				return CardinalDirection.West;
			}
			if (robotDirection == CardinalDirection.South) {
				return CardinalDirection.East;
			}
			if (robotDirection == CardinalDirection.East) {
				return CardinalDirection.North;
			}
			if (robotDirection == CardinalDirection.West) {
				return CardinalDirection.South;
			}
		case LEFT:
			if (robotDirection == CardinalDirection.North) {
				return CardinalDirection.East;
			}
			if (robotDirection == CardinalDirection.South) {
				return CardinalDirection.West;
			}
			if (robotDirection == CardinalDirection.East) {
				return CardinalDirection.South;
			}
			if (robotDirection == CardinalDirection.West) {
				return CardinalDirection.North;
			}
		case FORWARD:
		default:
			return robotDirection;
		}
	}
	
	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		//returns the the distance to the wall in front of the sensor as long as there is enough power in the robot
		//returns integer.max_value if it is facing infinity when it is at the exit
		//this is the same value as the maximum width of the maze
		//needs to determine what cardinal direction the sensor is facing by using robot direction and sensor directions
		try {
			
			if(!isOperational()) throw new Exception();
			int distance = 0;
			CardinalDirection cardDir = findDirection(currentDirection, direction);
			int x = currentPosition[0];
			int y = currentPosition[1];
			
			switch (cardDir) {
			
			case North:
				while(!maze.hasWall(x,  y,  CardinalDirection.North)) {
					if (distance > 300) {
						return Integer.MAX_VALUE;
					}
					y--;
					distance++;
				}
				break;
				
			case South:
				while(!maze.hasWall(x,  y,  CardinalDirection.South)) {
					if (distance > 300) {
						return Integer.MAX_VALUE;
					}
					y++;
					distance++;
				}
				break;
				
			case East:
				while(!maze.hasWall(x,  y,  CardinalDirection.East)) {
					if (distance > 300) {
						return Integer.MAX_VALUE;
					}
					x++;
					distance++;
				}
				break;
				
			case West:
			default:
				while(!maze.hasWall(x,  y,  CardinalDirection.West)) {
					if (distance > 300) {
						return Integer.MAX_VALUE;
					}
					x--;
					distance++;
				}
				break;
			}
			return distance;
			
		} catch (Exception e) {
			System.out.println("Exception");
			System.out.println(e);
			return 0;
		}
	}
		

	@Override
	public void setMaze(Maze maze) {
		//creates the maze object
		this.maze = maze;
	}
	
	public Maze getMaze() {
		return this.maze;
	}

	@Override
	public void setSensorDirection(Direction mountedDirection) {
		//sets the direction the specific sensor is supposed to be mounted in
		this.direction = mountedDirection;
	}
	
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public float getEnergyConsumptionForSensing() {
		//returns the total energy left in the robot
		return SensingEnergy;
	}
	
	public boolean isOperational() {
		return true;
	}

	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		//project 4
		
	}

	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		//project 4
		
	}

	@Override
	public boolean isReliable() {
		// TODO Auto-generated method stub
		return false;
	}

}
