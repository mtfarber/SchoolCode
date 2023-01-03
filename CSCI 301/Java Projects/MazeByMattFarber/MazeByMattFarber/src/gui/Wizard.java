package gui;

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Turn;

/**
 * Wizard
 * Make decisions based on the setMaze method to escape the maze
 * Will follow the most efficient route possible
 * Has knowledge of where to go next and the most optimal route
 * 
 * Collaborators:
 * ReliableRobot.java
 * StatePlaying.java
 * @author Matt Farber
 *
 */
public class Wizard implements RobotDriver{
	
	//create robot and maze objects
	//initialize variables for the starting amount of energy and how far the robot has currently traveled
	Robot robot;
	Maze maze;
	float startingEnergy;
	int totalLength;

	@Override
	public void setRobot(Robot r) {
		//create an instance of the robot
		//set the initial amount of energy to the amount given to the robot
		this.robot = r;
		this.startingEnergy = r.getBatteryLevel();
	}

	@Override
	public void setMaze(Maze maze) {
		//create the maze object
		this.maze = maze;
	}

	@Override
	public boolean drive2Exit() throws Exception {
		//As long as the robot isn't at the exit and can't see into infinity take one step towards the exit
		//After the loop if the robot still can't see into infinity, it is at the exit so
		//rotate up to three times double check it is at the exit
		//Take one step forward through the exit
		//return true if the robot has successfully reached the exit
		//return false if it has not reached the exit
		//throw an exception if the robot stops due to a lack of energy
try {
			
			while (true) {
				int[] position = robot.getCurrentPosition();
				
				if (robot.isAtExit()) {
					if (position[0] == 0 && robot.getCurrentDirection() == CardinalDirection.West) {
						robot.move(1);
						totalLength += 1;
						return true;
					}
					else if (position[0] == maze.getWidth() - 1 && robot.getCurrentDirection() == CardinalDirection.East) {
						robot.move(1);
						totalLength += 1;
						return true;
					}
					else if (position[1] == maze.getHeight() - 1 && robot.getCurrentDirection() == CardinalDirection.South) {
						robot.move(1);
						totalLength += 1;
						return true;
					}
					else if (position[1] == 0 && robot.getCurrentDirection() == CardinalDirection.North) {
						robot.move(1);
						totalLength += 1;
						return true;
					}
					else {
						robot.rotate(Turn.LEFT);
						continue;
					}
				}
				
				if (drive1Step2Exit()) {
					continue;
				}
				else {
					robot.rotate(Turn.LEFT);
				}
			}
			
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		//Chose the next tile that is closest to the exit
		//find which direction the robot has to turn to to move in the correct direction
		//moving north, south, east, and west changes depending on which direction the robot is currently facing
		//Move one step forwards after choosing the correct direction
		//return true if this was successful
		
		try {
			
			int[] pos = robot.getCurrentPosition();
			
			int[] next = maze.getNeighborCloserToExit(pos[0], pos[1]);
			
			if (next == null) return false;
			
			int differenceInX = pos[0] - next[0];
			int differenceInY = pos[1] - next[1];
			
	
			if (differenceInX == 0) {
				//for moving one step north
				if (differenceInY == -1) {
					switch (robot.getCurrentDirection()) {
					case North:
						robot.rotate(Turn.AROUND);
						robot.move(1);
						totalLength += 1;
						return true;
					case South:
						robot.move(1);
						totalLength += 1;
						return true;
					case East:
						robot.rotate(Turn.LEFT);
						robot.move(1);
						totalLength += 1;
						return true;
					case West:
					default:
						robot.rotate(Turn.RIGHT);
						robot.move(1);
						totalLength += 1;
						return true;					
					}
				}
				//for moving one step south
				else if (differenceInY == 1) {
					switch (robot.getCurrentDirection()) {
					case North:
						robot.move(1);
						totalLength += 1;
						return true;
					case South:
						robot.rotate(Turn.AROUND);
						robot.move(1);
						totalLength += 1;
						return true;
					case East:
						robot.rotate(Turn.RIGHT);
						robot.move(1);
						totalLength += 1;
						return true;
					case West:
					default:
						robot.rotate(Turn.LEFT);
						robot.move(1);
						totalLength += 1;
						return true;					
					}
				}
			}
			
			//for moving one step west
			else if (differenceInY == 0) {
				if (differenceInX == 1) {
					switch (robot.getCurrentDirection()) {
					case North:
						robot.rotate(Turn.RIGHT);
						robot.move(1);
						totalLength += 1;
						return true;
					case South:
						robot.rotate(Turn.LEFT);
						robot.move(1);
						totalLength += 1;
						return true;
					case East:
						robot.rotate(Turn.AROUND);
						robot.move(1);
						totalLength += 1;
						return true;
					case West:
					default:
						robot.move(1);
						totalLength += 1;
						return true;					
					}
				}
				
				//for moving one step east
				else if (differenceInX == -1) {
					switch (robot.getCurrentDirection()) {
					case North:
						robot.rotate(Turn.LEFT);
						robot.move(1);
						totalLength += 1;
						return true;
					case South:
						robot.rotate(Turn.RIGHT);
						robot.move(1);
						totalLength += 1;
						return true;
					case East:
						robot.move(1);
						totalLength += 1;
						return true;
					case West:
					default:
						robot.rotate(Turn.AROUND);
						robot.move(1);
						totalLength += 1;
						return true;					
					}
				}
			}
			
			
			return false;
			
			} catch (Exception e) {
				throw new Exception();
			}
	}

	@Override
	public float getEnergyConsumption() {
		// return how much energy the robot has consumed so far
		return startingEnergy - robot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		//return the current distance the robot has traveled on its path
		return totalLength;
	}

}
