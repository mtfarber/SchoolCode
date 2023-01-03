package gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.CardinalDirection;
import generation.Maze;
import generation.MazeFactory;
import generation.StubOrder;
import gui.Robot.Direction;
import gui.Robot.Turn;

public class ReliableRobotTest {
	//set up objects that will be created before each test
		private static MazeFactory factory;
		private static StubOrder order;
		private static ReliableRobot robot;
		private static Wizard wizard;
		private static Controller controller;
		
		//before each test
		//create factory object
		//create order object
		//create a reliable robot
		//create a wizard
		//create the controller
		@BeforeEach
		void setUp() throws Exception {
			factory = new MazeFactory();
			robot = new ReliableRobot();
			wizard = new Wizard();
			controller = new Controller();
			controller.start();
			
			order = new StubOrder();
			order.setSkillLevel(2);
			order.setSeed(1);
		}
		
		//test all of the sensors and make sure that each of them is facing the right direction
		@Test
		void testFrontSensor() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertNotNull(robot.frontSensor);
			assertEquals(((ReliableSensor) robot.frontSensor).getDirection(), Direction.FORWARD);
			assertNotNull(((ReliableSensor) robot.frontSensor).getMaze());
		}
		@Test
		void testBackSensor() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertNotNull(robot.backSensor);
			assertEquals(((ReliableSensor) robot.backSensor).getDirection(), Direction.BACKWARD);
			assertNotNull(((ReliableSensor) robot.backSensor).getMaze());
		}
		@Test
		void testLeftSensor() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertNotNull(robot.leftSensor);
			assertEquals(((ReliableSensor) robot.leftSensor).getDirection(), Direction.LEFT);
			assertNotNull(((ReliableSensor) robot.leftSensor).getMaze());
		}
		@Test
		void testRightSensor() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertNotNull(robot.rightSensor);
			assertEquals(((ReliableSensor) robot.rightSensor).getDirection(), Direction.RIGHT);
			assertNotNull(((ReliableSensor) robot.rightSensor).getMaze());
		}
		
		//test to see if the created controller for the robot is the same originally created controller
		@Test
		void testSetController() {
			robot.setController(controller);
			assertEquals(controller, robot.controller);
		}
		
		//test the getCurrentPosition method by placing the robot at a position and checking to see if it is equal to what you expect
		@Test
		void testCurrentPosition() throws Exception {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			maze.setStartingPosition(4, 4);
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			int[] coordinate = new int[2];
			coordinate[0] = 4;
			coordinate[1] = 4;
			assertTrue(Arrays.equals(robot.getCurrentPosition(), coordinate));
		}
		
		//check to see if battery level is being set correctly
		@Test
		void testGetBatteryLevel() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			robot.setBatteryLevel(1000);
			assertEquals(1000, robot.getBatteryLevel());	
		}
		
		//check when battery is set but the value is 0
		@Test
		void testBatteryLevel0() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			robot.setBatteryLevel(0);
			assertEquals(0, robot.getBatteryLevel());
			assertEquals(true, robot.hasStopped());
		}
		
		//check to see if the code throws an exception when the battery level is negative
		@Test
		void testSetBatteryLevel() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			robot.stopped = false;
			robot.setBatteryLevel(-100);
			assertEquals(true, robot.hasStopped());
			assertEquals(-100, robot.getBatteryLevel());
		}
		
		//test the energy required for a step forwards
		@Test
		void testGetEnergyForStepForward() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			assertEquals(6, robot.getEnergyForStepForward());
		}
		
		//test the energy required for a rotation
		@Test
		void testGetEnergyForFullRotation() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
		
			assertEquals(12, robot.getEnergyForFullRotation());
		}
		
		//test the odometer readings for a correct value
		@Test
		void testOdometerReading() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertEquals(0, robot.getOdometerReading());
			robot.odometer = 15;
			assertEquals(15, robot.getOdometerReading());
		}
		
		//test the odometer readings for a value of 0
		@Test
		void testOdometerReading0() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertEquals(0, robot.getOdometerReading());
		}
		
		//test the odometer readings for a negative value
		@Test
		void testOdometerReadingNegative() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertEquals(0, robot.getOdometerReading());
			robot.odometer = -15;
			assertEquals(-15, robot.getOdometerReading());
		}
		
		//test rotation left
		@Test
		void testRotateLeft() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertEquals(CardinalDirection.East, robot.getCurrentDirection());
			robot.rotate(Turn.LEFT);
			assertEquals(CardinalDirection.South, robot.getCurrentDirection());
		}
		
		//test rotation right
		@Test
		void testRotateRight() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertEquals(CardinalDirection.East, robot.getCurrentDirection());
			robot.rotate(Turn.RIGHT);
			assertEquals(CardinalDirection.North, robot.getCurrentDirection());
		}
		
		//test 180 rotation (AROUND)
		@Test
		void testRotate180() {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertEquals(CardinalDirection.East, robot.getCurrentDirection());
			robot.rotate(Turn.AROUND);
			assertEquals(CardinalDirection.West, robot.getCurrentDirection());
		}
		
		//test move one cell forwards
		@Test
		void testMove() throws Exception {
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			int[] pos = robot.getCurrentPosition();
			
			robot.move(1);
			assertEquals(pos[0], robot.getCurrentPosition()[0]);
			assertEquals(pos[1], robot.getCurrentPosition()[1]);
		}
		
		//test to see if the robot recognizes that it is at an exit
		@Test
		void testAtExit() throws Exception {
			order.setSeed(13);
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			maze.setStartingPosition(10, 0);
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertEquals(10, robot.getCurrentPosition()[0]);
			assertEquals(0, robot.getCurrentPosition()[1]);
			assertEquals(false, robot.isAtExit());
		}
		
		//test to see if the robot is inside of a room
		@Test
		void testInsideRoom() {
			order.setSeed(13);
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			maze.setStartingPosition(8,3);
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			assertEquals(true, robot.isInsideRoom());	
		}
		
		//test if the canSeeThroughTheExitIntoEternity method
		@Test
		void testCanSeeThroughTheExitIntoEternity() {
			order.setSeed(13);
			factory.order(order);
			factory.waitTillDelivered();
			Maze maze = order.getMaze();
			maze.setStartingPosition(5, 0);
			
			controller.setRobotAndDriver(robot, wizard);
			controller.switchFromTitleToGenerating(1);
			controller.switchFromGeneratingToPlaying(maze);
			robot.setController(controller);
			
			assertFalse(robot.canSeeThroughTheExitIntoEternity(Direction.FORWARD));
			assertFalse(robot.canSeeThroughTheExitIntoEternity(Direction.BACKWARD));
			assertFalse(robot.canSeeThroughTheExitIntoEternity(Direction.RIGHT));
			assertFalse(robot.canSeeThroughTheExitIntoEternity(Direction.LEFT));	
		}
		
}
