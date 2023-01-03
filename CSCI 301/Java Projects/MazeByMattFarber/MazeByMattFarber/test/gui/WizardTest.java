package gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generation.Maze;
import generation.MazeFactory;
import generation.StubOrder;
import gui.Robot.Turn;

public class WizardTest {
	
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
	
	//create a robot object and see if it equal to the wizard object
	@Test
	void testRobot() {
		Robot r = new ReliableRobot();
		wizard.setRobot(r);
		assertEquals(r, wizard.robot);
	}
	
	//create a maze and see if it the same as the wizard object maze
	@Test
	void testSetMaze() {
		factory.order(order);
		Maze maze1 = order.getMaze();
		wizard.setMaze(maze1);
		assertEquals(maze1, wizard.maze);
	}
	
	//See if playing a game with the wizard will reach the end of the maze
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
	
	//move 1 cell and check if the robot moved to the right location
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
		int[] position = robot.getCurrentPosition();
		int second = maze.getDistanceToExit(position[0], position[1]);
		assertEquals(first - 2, second);
	}
	
	//test energy consumption for turning with the driver
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
	
	//check to see if totalLength works correctly and actively tracks the length
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
}
