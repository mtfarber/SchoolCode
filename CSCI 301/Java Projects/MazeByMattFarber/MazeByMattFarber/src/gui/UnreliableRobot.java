package gui;

import gui.Robot.Direction;

/**
 * Unreliable robot responsibilities:
 * Pass the movement decision from the wall follower to the Controller
 * Travel through maze and track sensor measurements
 * Use four instances of the a sensor class, one for each direction
 * Can handle failures and repairs of sensors
 * 
 * Collaborators:
 * Wallfollower.java
 * Controller.java
 * UnrealiableSensor.java
 * @author Matthew Farber
 *
 */

public class UnreliableRobot extends ReliableRobot {
	/**
	 * Inherit from the Reliable robot class
	 * Create the sensors for the robot
	 * @param is the binary representation for which sensors are going to be reliable and unreliable
	 */
	DistanceSensor[] sensors;
	
	long meanTimeToRepair = 1000 * 2;
	long meanTimeBetweenFailures = 1000 * 4;
	
	
	
	/**
	 * Constructor that takes the binary code from the command line and creates the sensors
	 * as either reliable or unreliable
	 */
	public UnreliableRobot(String code) {
		sensors = new DistanceSensor[4];
		
		for (int x = 0; x < 4; x++) {
			switch (code.charAt(x)) {
			case '0':
				sensors[x] = new UnreliableSensor();
				break;
			case '1':
			default:
				sensors[x] = new ReliableSensor();
			}
		}
		
		/**
		 * Arrange the sensors to point in the correct direction
		 */
		sensors[0].setSensorDirection(Direction.FORWARD);
		sensors[1].setSensorDirection(Direction.LEFT);
		sensors[2].setSensorDirection(Direction.RIGHT);
		sensors[3].setSensorDirection(Direction.BACKWARD);
		
	}
	
	
	
	/**
	 * Begin the repair and failure process for all of the sensors
	 * Start the threads and sleep for 1.3 seconds
	 */
	
	public void startFailureAndRepairProcess(){
		for (DistanceSensor s : sensors) {
			if (!s.isReliable()) {
				s.startFailureAndRepairProcess(0, 0);
				try {
					Thread.sleep(1300);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	
	/**
	 * Stop the repair and failure process for all of the sensors
	 */
	
	public void stopFailureAndRepairProcess() {
		for (DistanceSensor s : sensors) {
			if (!s.isReliable()) s.stopFailureAndRepairProcess();
		}
	}
	
	
	/**
	 * Method to check to see if the robot is reliable or not
	 */
	@Override
	public boolean isReliable() {
		return false;
	}
	
	
	/**
	 * Method to return the sensor facing in a direction
	 * The parameter direction is the direction that will be returned with the function
	 * Sensor at 1 is left
	 * Sensor at 2 is right
	 * Sensor at 3 is backwards
	 * Sensor at 0 is forwards
	 * Preference is 0, then 1, then 2, then 3
	 */
	
	public DistanceSensor getSensor(Direction direction) {
		switch (direction) {
		case LEFT:
			return sensors[1];
		case RIGHT:
			return sensors[2];
		case BACKWARD:
			return sensors[3];
		case FORWARD:
		default:
			return sensors[0];
		}
	}
	
}
