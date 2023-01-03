package gui;

import generation.CardinalDirection;
import gui.Constants.UserInput;

/**
 * ReliableRobot
 * Pass the movement decision from the Wizard to the Controller
 * Travel through maze and track sensor measurements
 * Use four instances of the a sensor class, one for each direction
 * 
 * Collaborators:
 * Wallfollower.java
 * Controller.java
 * Wizard.java
 * ReliableSensor.java
 * UnrealiableSensor.java
 * @author Matt Farber
 *
 */
public class ReliableRobot implements Robot{
	
	//create a controller object
	//set the battery level
	//start the counter for total distance traveled
	//set the values of energy required for each action
	
	Controller controller;
	float battery = 3500;
	boolean stopped = false;
	int odometer = 0;
	float turnEnergy = 3;
	float stepEnergy = 6;
	float jumpEnergy = 40;
	
	//create all of the sensor objects and set their starting directions
	DistanceSensor frontSensor = new ReliableSensor();
	DistanceSensor backSensor = new ReliableSensor();
	DistanceSensor leftSensor = new ReliableSensor();
	DistanceSensor rightSensor = new ReliableSensor();
	
	@Override
	public void setController(Controller controller) {
		//create the controller object and create the sensors
		this.controller = controller;
		frontSensor.setMaze(controller.getMazeConfiguration());
		backSensor.setMaze(controller.getMazeConfiguration());
		leftSensor.setMaze(controller.getMazeConfiguration());
		rightSensor.setMaze(controller.getMazeConfiguration()); 
		frontSensor.setSensorDirection(Direction.FORWARD);
		backSensor.setSensorDirection(Direction.BACKWARD);
		leftSensor.setSensorDirection(Direction.LEFT);
		rightSensor.setSensorDirection(Direction.RIGHT);
	}

	@Override
	public void addDistanceSensor(DistanceSensor sensor, Direction mountedDirection) {
		//create the distance sensor with the specified mounted direction
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		//return the current position
		return controller.getCurrentPosition();
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		//return the current direction
		return controller.getCurrentDirection();
	}

	@Override
	public float getBatteryLevel() {
		//return the battery level
		return battery;
	}

	@Override
	public void setBatteryLevel(float level) {
		//set the battery level according to the users choice of battery level
		this.battery = level;
		if (this.battery <= 0) {
			stopped = true;
		}
	}

	@Override
	public float getEnergyForFullRotation() {
		//return the amount of energy required for a full rotation
		return (turnEnergy * 4);
	}

	@Override
	public float getEnergyForStepForward() {
		//return the energy required for one step forwards
		return stepEnergy;
	}

	@Override
	public int getOdometerReading() {
		//return the value of the odometer
		return odometer;
	}

	@Override
	public void resetOdometer() {
		//reset the odometer back to 0
		this.odometer = 0;
	}

	@Override
	public void rotate(Turn turn) {
		//use the controller to turn the robot and set the battery level
		if (hasStopped()) return;
		
		switch (turn) {
			case LEFT:
				controller.currentState.keyDown(UserInput.LEFT, 1);
				setBatteryLevel(getBatteryLevel() - turnEnergy);
				break;
			case RIGHT:
				controller.currentState.keyDown(UserInput.RIGHT, 1);
				setBatteryLevel(getBatteryLevel() - turnEnergy);
				break;
			case AROUND:
				controller.currentState.keyDown(UserInput.LEFT, 1);
				setBatteryLevel(getBatteryLevel() - turnEnergy);
				if (!hasStopped()) {
					controller.currentState.keyDown(UserInput.LEFT, 1);
					setBatteryLevel(getBatteryLevel() - turnEnergy);
				}
				break;
		}
	}

	@Override
	public void move(int distance) throws Exception {
		//Check to see if there is a wall directly in front of the robot
		//if there is space to move, go one block forwards
		//remove the amount of battery needed for a move
		if (hasStopped()) throw new Exception();
		
		for (int x = 0; x < distance; x++) {
			int [] pos = controller.getCurrentPosition();
			if (controller.getMazeConfiguration().hasWall(pos[0], pos[1], getCurrentDirection())) {
				stopped = true;
			}

			if (hasStopped()) {
				break;
			}
			
			controller.currentState.keyDown(UserInput.UP, 1);
			setBatteryLevel(getBatteryLevel() - getEnergyForStepForward());	
		}
	}

	@Override
	public void jump() {
		//perform the jump operation and remove the required energy from the battery level
		if (hasStopped()) {
			return;
		}
		
		controller.currentState.keyDown(UserInput.JUMP, 1);
		setBatteryLevel(getBatteryLevel() - jumpEnergy);	
	}

	@Override
	public boolean isAtExit() {
		//return true if the robot is at the exit
		//return false if it is not at the exit
		int [] pos = controller.getCurrentPosition();
		return (controller.getMazeConfiguration().getFloorplan().isExitPosition(pos[0], pos[1]));
	}

	@Override
	public boolean isInsideRoom() {
		//return true if the robot is currently inside a room
		//return false if the robot isn't inside of a room
		int [] pos = controller.getCurrentPosition();
		return (controller.getMazeConfiguration().getFloorplan().isInRoom(pos[0], pos[1]));
	}

	@Override
	public boolean hasStopped() {
		//return true is the robot has stopped
		//the stopped flag has to be set to true
		//otherwise return false
		return stopped;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws Exception {
		int dist;
		
		switch (direction) {
		case FORWARD:
			dist = frontSensor.distanceToObstacle(getCurrentPosition(), getCurrentDirection(), getBatteryArray());
			return dist;
		
		case BACKWARD:
			dist = backSensor.distanceToObstacle(getCurrentPosition(), getCurrentDirection(), getBatteryArray());
			return dist;
		
		case LEFT:
			dist = leftSensor.distanceToObstacle(getCurrentPosition(), getCurrentDirection(), getBatteryArray());
			return dist;

		case RIGHT:
			dist = rightSensor.distanceToObstacle(getCurrentPosition(), getCurrentDirection(), getBatteryArray());
			return dist;
		
		default:
			return 0;
		}
	}

	@Override
	public boolean canSeeThroughTheExitIntoEternity(Direction direction) throws UnsupportedOperationException {
		//return true if the robot sensor can see into infinity which means it is at the exit
		try {
			return (distanceToObstacle(direction) == Integer.MAX_VALUE);
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isReliable() {
		return true;
	}
	
	public float[] getBatteryArray() {
		float[] array = new float[1];
		array[0] = battery;
		
		return array;
	}

	@Override
	public void startFailureAndRepairProcess(Direction direction, int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		//Project 4
		
	}

	@Override
	public void stopFailureAndRepairProcess(Direction direction) throws UnsupportedOperationException {
		//Project 4
		
	}

	@Override
	public DistanceSensor getSensor(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}

}
