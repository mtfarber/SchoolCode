package gui;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.CardinalDirection;
import generation.Maze;
import generation.MazeFactory;
import generation.StubOrder;
import gui.Robot.Direction;
import gui.Robot.Turn;

public class ReliableSensorTest {
	//set up objects that will be created before each test
	private static MazeFactory factory;
	private static StubOrder order;
	private static ReliableRobot robot;
	private static Wizard wizard;
	private static Controller controller;
	private static DistanceSensor sensor;
		
	//before each test
	//create factory object
	//create order object
	//create a reliable robot
	//create a wizard
	//create the controller
	@BeforeEach
	void setUp() throws Exception {
		sensor = new ReliableSensor();
		factory = new MazeFactory();
		robot = new ReliableRobot();
		wizard = new Wizard();
		controller = new Controller();
		controller.start();
			
		order = new StubOrder();
		order.setSkillLevel(2);
		order.setSeed(1);
	}
	
	//test the distance to each wall in every direction
	//values are already known because the maze is the same every time
	@Test
	void testWallDistance() throws Exception {
		factory.order(order);
		factory.waitTillDelivered();
		Maze maze = order.getMaze();
		float[] battery = new float[1];
		maze.setStartingPosition(10, 10);
		battery[0] = robot.battery;
		
		controller.setRobotAndDriver(robot, wizard);
		controller.switchFromTitleToGenerating(1);	
		controller.switchFromGeneratingToPlaying(maze);
		robot.setController(controller);
		
		assertEquals(1, robot.frontSensor.distanceToObstacle(robot.getCurrentPosition(), CardinalDirection.North, battery));
		assertEquals(0, robot.frontSensor.distanceToObstacle(robot.getCurrentPosition(), CardinalDirection.East, battery));
		assertEquals(5, robot.frontSensor.distanceToObstacle(robot.getCurrentPosition(), CardinalDirection.West, battery));
		assertEquals(1, robot.frontSensor.distanceToObstacle(robot.getCurrentPosition(), CardinalDirection.South, battery));

	}
	
	//test the creation of a maze to ensure it is correctly passed through to the sensor
	@Test
	void testSetMaze() {
		factory.order(order);
		Maze maze = order.getMaze();
		sensor.setMaze(maze);
		assertEquals(maze, ((ReliableSensor) sensor).getMaze());
	}
	
	//test to make sure sensors are being created facing the correct direction
	@Test
	void testSensorDirection() {
		DistanceSensor s = new ReliableSensor();
		
		s.setSensorDirection(Direction.FORWARD);
		assertEquals(Direction.FORWARD, ((ReliableSensor) s).getDirection());
		
		s.setSensorDirection(Direction.BACKWARD);
		assertEquals(Direction.BACKWARD, ((ReliableSensor) s).getDirection());
		
		s.setSensorDirection(Direction.LEFT);
		assertEquals(Direction.LEFT, ((ReliableSensor) s).getDirection());
		
		s.setSensorDirection(Direction.RIGHT);
		assertEquals(Direction.RIGHT, ((ReliableSensor) s).getDirection());
	}
	
	//test to see if power supply is decremented by 1 for a move
	@Test
	public void testCheckPowerSupply() throws Exception {
	factory.order(order);
	factory.waitTillDelivered();
	Maze maze = order.getMaze();
	controller.setRobotAndDriver(robot, wizard);
	controller.switchFromTitleToGenerating(1);
	controller.switchFromGeneratingToPlaying(maze);
	robot.setController(controller);

	float[] tester = new float[1];
	tester[0] = 1000;
	robot.frontSensor.setMaze(maze);
	robot.frontSensor.distanceToObstacle(robot.getCurrentPosition(), robot.getCurrentDirection(), tester);
	assertEquals(1000, tester[0]);
	}
	
	//test to see that the sensor can keep track of distance after a move
	//make sure that the robot has traveled the correct distance and that hasStopped() is still false
	@Test
	public void testDistanceAfterMove() throws Exception {
	factory.order(order);
	factory.waitTillDelivered();
	Maze maze = order.getMaze();

	controller.setRobotAndDriver(robot, wizard);
	controller.switchFromTitleToGenerating(1);
	controller.switchFromGeneratingToPlaying(maze);
	robot.setController(controller);

	robot.frontSensor.setMaze(maze);
	robot.move(robot.frontSensor.distanceToObstacle(robot.getCurrentPosition(), robot.getCurrentDirection(), robot.getBatteryArray()));
	assertFalse(robot.hasStopped());
	}
}
