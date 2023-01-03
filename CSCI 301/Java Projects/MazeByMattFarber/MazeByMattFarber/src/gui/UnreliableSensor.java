package gui;
/**
 * UnreliableSensor
 * Gets the distance to the wall in any one specified direction
 * Can be turned on and off to represent being unreliable
 * Sensor doesn't work when it is set to an unreliable state
 * Unreliable state is independent of other instances of the sensor object
 * Failure and repair of the sensor is controlled by user input
 * 
 * Collaborators:
 * Maze.java
 * Floorplan.java
 * Controller.java
 * 
 * @author Matt Farber
 *
 */

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Direction;

public class UnreliableSensor extends ReliableSensor implements Runnable {
	/**
	 * Set threads for the sensor
	 */
	long meanTimeToRepair = 1000 * 2;
	long meanTimeBetweenFailures = 1000 * 4;
	Thread thread = new Thread(this);
	public boolean running = false;
	public boolean operational = true;
	public UnreliableSensor() {
		super();
	}

	
	@Override
	public boolean isOperational() {
		return operational;
	} 
	
	
	/**
	 * Method for switching the sensor to not operational
	 */
	public void changeOperationalStatus() {
		operational = !operational;
	}
	
	
	/**
	 * Start the loop for the threading that is called by the controller
	 */
	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair) {
		this.thread.start();
		running = true;
	}
	
	@Override
	public void stopFailureAndRepairProcess() {
		running = false;
	}
	
	
	/**
	 * Loop that switches between operational and not operational states
	 * Uses a timer from the project specifications to switch between states
	 */
	
	@Override
	public void run() {
		running = true;
		
		while (running) {
			
		try {
			thread.sleep(meanTimeBetweenFailures);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.changeOperationalStatus();
		
		try {
			thread.sleep(meanTimeToRepair);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.changeOperationalStatus();
		
		}
	}
	
	
	public boolean isReliable() {
		return false;
	}
}
