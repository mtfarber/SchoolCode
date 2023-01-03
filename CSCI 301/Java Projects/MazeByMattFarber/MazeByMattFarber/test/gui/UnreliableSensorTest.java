package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnreliableSensorTest {
	
	UnreliableSensor sensor;
	
	@BeforeEach
	void setUp() throws Exception{
	}
	/**
	 * @author Matt Farber
	 * Test the startFailureand RepairProcess
	 * Create a sensor
	 * Set running to true to call run
	 */
	@Test
	void testStartFailureAndRepairProcess() {
		sensor = new UnreliableSensor();
		assertFalse(sensor.running);
		
		sensor.startFailureAndRepairProcess(0, 0);		
		assertTrue(sensor.running);
	}
	
	
	/**
	 * Test to check that the repair and failure process is working correctly
	 * Create a new sensor
	 * Check to see that the sensor is not running
	 * After telling the sensor to start running, check to see if sensor.running is true and it worked correctly
	 * Do the same for stooping the sensor and make sure the sensor has stopped
	 * Check this  by seeing if sensor.running is false
	 */
	@Test
	void testStopFailureAndRepairProcess() {
		sensor = new UnreliableSensor();
		assertFalse(sensor.running);
		
		sensor.startFailureAndRepairProcess(0, 0);
		assertTrue(sensor.running);
				
		sensor.stopFailureAndRepairProcess();
		assertFalse(sensor.running);
	}
	
	
	
	/**
	 * Test for IsOperational
	 * Should be set to true in the beginning, but then change to false once it is set to false
	 */
	@Test
	void testIsOperational() {
		sensor = new UnreliableSensor();
		assertTrue(sensor.isOperational());
		
		sensor.operational = false;
		assertFalse(sensor.isOperational());
	}
	
	
	/**
	 * Test for IsReliable
	 */
	@Test
	void testIsReliable() {
		sensor = new UnreliableSensor();
		assertFalse(sensor.isReliable());
	}
	
	
	/**
	 * Test for threading while creating a sensor
	 * Constructor should create a new thread
	 * Sensor should be equivalent to null before it is created and then not null afterwards
	 */
	@Test
	void testUnreliableSensor() {
		assertNull(sensor);
		sensor = new UnreliableSensor();
		assertNotNull(sensor.thread);
	}
	
	
	/**
	 * Test for changing the operational status
	 * Change the sensor from operational to not operational
	 * Check each time to make sure the sensor is in the correct state
	 */
	@Test
	void testChangeOperationalStatus() {
		sensor = new UnreliableSensor();
		assertTrue(sensor.operational);
		
		sensor.changeOperationalStatus();
		assertFalse(sensor.operational);
		
		sensor.changeOperationalStatus();
		assertTrue(sensor.operational);
	}
}
