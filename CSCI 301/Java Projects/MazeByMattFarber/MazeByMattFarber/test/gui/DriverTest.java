package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.Maze;
import generation.MazeContainer;
import generation.MazeFactory;
import generation.StubOrder;
import gui.Robot.Direction;
import gui.Robot.Turn;
class DriverTest {
	/**
	 * @author Matt Farber
	 * Tests for WallFollower and Wizard driver methods
	 * Tests through each classes methods
	 * Similar to tests for reliable robot
	 */
	private static MazeFactory factory;
	private static StubOrder order;
	private static ReliableRobot robot;
	private static UnreliableRobot robot2;
	private static Wizard wizard;
	private static WallFollower wallfollower;
	private static Controller controller;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new MazeFactory();
		robot = new ReliableRobot();
		robot2 = new UnreliableRobot("0000");
		wizard = new Wizard();
		wallfollower = new WallFollower();
		controller = new Controller();
		controller.start();
		
		order = new StubOrder();
		order.setSkillLevel(2);
		order.setSeed(1);
		
	}
	
	/**
	 * Create robot object and check to see if it is equivalent
	 */
	@Test
	void testSetRobot() {
		Robot r = new ReliableRobot();
		
		wizard.setRobot(r);
		
		assertEquals(r, wizard.robot);
	}
	
	/**
	 * Creates a maze object to check to see if it is equal when given to the wizard
	 */
	@Test
	void testSetMaze() {
		factory.order(order);
		Maze m = order.getMaze();
		
		wizard.setMaze(m);
		
		assertEquals(m, wizard.maze);
	}
	
	/**
	 * Starts a game and ensures that the conditions to complete a game are met
	 */
	@Test
	void testWizardDrive2Exit() throws Exception {
		factory.order(order);
		factory.waitTillDelivered();

		Maze maze = order.getMaze();
		
		controller.setRobotAndDriver(robot, wizard);
		wizard.setRobot(robot);
		controller.switchFromTitleToGenerating(1);		
		controller.currentState = controller.states[2];
		controller.currentState.setMazeConfiguration(maze);		
		robot.setController(controller);
	
		wizard.drive2Exit();
		
		assertEquals("stateWinning", controller.currentState.toString());
	}
	
	/**
	 * Tests drive one step to exit and makes sure it moves the robot in the correct direction
	 */
	@Test
	void testWizardDrive1Step2Exit() throws Exception {
		factory.order(order);
		factory.waitTillDelivered();

		Maze maze = order.getMaze();
		maze.setStartingPosition(10, 10);
		
		controller.setRobotAndDriver(robot, wizard);
		wizard.setRobot(robot);
		controller.switchFromTitleToGenerating(1);	
		controller.switchFromGeneratingToPlaying(maze);
		robot.setController(controller);
		
		
		int first = maze.getDistanceToExit(10, 10);
		wizard.drive1Step2Exit();
		int[] pos = robot.getCurrentPosition();
		int second = maze.getDistanceToExit(pos[0], pos[1]);
		
		assertEquals(first - 2, second);
		
	}
	
	/**
	 * Creates a maze and checks the energy consumption
	 * Turn the robot 
	 */
	@Test
	void testGetEnergyConsumption() {
		factory.order(order);
		factory.waitTillDelivered();

		Maze maze = order.getMaze();
		
		controller.setRobotAndDriver(robot, wizard);
		controller.switchFromTitleToGenerating(1);	
		controller.switchFromGeneratingToPlaying(maze);
		robot.setController(controller);
		
		assertEquals(0, wizard.getEnergyConsumption());
		
		robot.rotate(Turn.RIGHT);
		assertEquals(3, wizard.getEnergyConsumption());
	}
	
	/**
	 * Create a maze and check the path length
	 * Check the path length after the robot moves two steps
	 * Make sure the energy consumption is correct
	 */
	@Test
	void testGetPathLength() throws Exception {
		factory.order(order);
		factory.waitTillDelivered();

		Maze maze = order.getMaze();
		maze.setStartingPosition(10, 10);
		
		controller.setRobotAndDriver(robot, wizard);
		controller.switchFromTitleToGenerating(1);	
		controller.switchFromGeneratingToPlaying(maze);
		robot.setController(controller);
		wizard.setRobot(robot);
		
		assertEquals(0, wizard.getPathLength());
		
		wizard.drive1Step2Exit();
		
		assertEquals(1, wizard.getPathLength());
	}
	
	/**
	 * For the wallfollower test the getDistance() method and have all of the sensors start as operational
	 * Make each one fail and check to see if the priority of sensors is working correctly
	 */
	@Test
	void testGetDistance() {
		wallfollower.setRobot(robot2);
		
		assertTrue(robot2.getSensor(Direction.FORWARD).isOperational());
		assertTrue(robot2.getSensor(Direction.LEFT).isOperational());
		assertTrue(robot2.getSensor(Direction.RIGHT).isOperational());
		assertTrue(robot2.getSensor(Direction.BACKWARD).isOperational());
		
		MazeFileReader mfr = new MazeFileReader("test/data/input.xml") ;
        Maze m = new MazeContainer();
		m = mfr.getMazeConfiguration();
		
		controller.setRobotAndDriver(robot2, wallfollower);
		controller.switchFromTitleToGenerating(1);	
		controller.switchFromGeneratingToPlaying(m);
		robot2.setController(controller);
		
		
		assertEquals(1, wallfollower.getDistance(Direction.BACKWARD));
		UnreliableSensor s1 = (UnreliableSensor)robot2.getSensor(Direction.BACKWARD);
		s1.operational = false;
		
		assertEquals(1, wallfollower.getDistance(Direction.BACKWARD));
		UnreliableSensor s2 = (UnreliableSensor)robot2.getSensor(Direction.RIGHT);
		s2.operational = false;
		
		assertEquals(1, wallfollower.getDistance(Direction.BACKWARD));
		UnreliableSensor s3 = (UnreliableSensor)robot2.getSensor(Direction.LEFT);
		s3.operational = false;
		
		assertEquals(1, wallfollower.getDistance(Direction.BACKWARD));
	}
}
