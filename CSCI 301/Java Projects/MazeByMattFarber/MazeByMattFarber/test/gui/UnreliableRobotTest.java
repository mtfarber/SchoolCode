package gui;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gui.Robot.Direction;

public class UnreliableRobotTest {

	/**
	 * Test cases for Unreliable robot
	 * Similar to test cases from reliableRobot
	 * @author Matt Farber
	 */
	private static UnreliableRobot robot;
	
	/**
	 * Create a robot with 4 unreliable sensors
	 * Check to see that each sensor is working
	 * Check if the sensors are pointing in the right direction
	 * Check that the sensors aren't running before they are created, and that they are
	 * running afterwards
	 */
	@Test
	void testStartFailureAndRepairProcess() {
		robot = new UnreliableRobot("0000");
		
		UnreliableSensor left = (UnreliableSensor) robot.getSensor(Direction.LEFT);
		UnreliableSensor right = (UnreliableSensor) robot.getSensor(Direction.RIGHT);
		UnreliableSensor forward = (UnreliableSensor) robot.getSensor(Direction.FORWARD);
		UnreliableSensor backward = (UnreliableSensor) robot.getSensor(Direction.BACKWARD);
		
		assertEquals(left.running, false);
		assertEquals(right.running, false);
		assertEquals(forward.running, false);
		assertEquals(backward.running, false);
		
		robot.startFailureAndRepairProcess();
		
		assertEquals(left.running, true);
		assertEquals(right.running, true);
		assertEquals(forward.running, true);
		assertEquals(backward.running, true);
	}
	
	
	/**
	 * Creates a robot with 4 sensors that start out as running but are stopped
	 * Checks if the sensors failed correctly
	 */
	@Test
	void testStopFailureAndRepairProcess() {
		robot = new UnreliableRobot("0000");
		
		UnreliableSensor left = (UnreliableSensor) robot.getSensor(Direction.LEFT);
		UnreliableSensor right = (UnreliableSensor) robot.getSensor(Direction.RIGHT);
		UnreliableSensor forward = (UnreliableSensor) robot.getSensor(Direction.FORWARD);
		UnreliableSensor backward = (UnreliableSensor) robot.getSensor(Direction.BACKWARD);
		
		assertEquals(left.running, false);
		assertEquals(right.running, false);
		assertEquals(forward.running, false);
		assertEquals(backward.running, false);
		
		robot.startFailureAndRepairProcess();
		
		assertEquals(left.running, true);
		assertEquals(right.running, true);
		assertEquals(forward.running, true);
		assertEquals(backward.running, true);
		
		robot.stopFailureAndRepairProcess();
		
		assertEquals(left.running, false);
		assertEquals(right.running, false);
		assertEquals(forward.running, false);
		assertEquals(backward.running, false);
	}
	
	/**
	 * Checks the creation of an unreliable robot
	 * Also checks that the isReliable method is returning false
	 */
	@Test
	void testIsReliable() {
		robot = new UnreliableRobot("1110");
		assertFalse(robot.isReliable());
	}
	
	/**
	 * Tests 4 different configurations of sensors
	 * Checks to see if the correct sensors are being set to reliable and unreliable from the input
	 * Also tests the constructor that is used when creating the sensors
	 */
	@Test
	void testGetSensor() {
		robot = new UnreliableRobot("1010");
		assertTrue(robot.getSensor(Direction.FORWARD).isReliable());
		assertFalse(robot.getSensor(Direction.LEFT).isReliable());
		assertTrue(robot.getSensor(Direction.RIGHT).isReliable());
		assertFalse(robot.getSensor(Direction.BACKWARD).isReliable());
		
		robot = new UnreliableRobot("0101");
		assertFalse(robot.getSensor(Direction.FORWARD).isReliable());
		assertTrue(robot.getSensor(Direction.LEFT).isReliable());
		assertFalse(robot.getSensor(Direction.RIGHT).isReliable());
		assertTrue(robot.getSensor(Direction.BACKWARD).isReliable());

		
	}
}
