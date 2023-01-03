package generation;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;

public class MazeFactoryTest {
	
	//Initialize both factory and order	variables as instances
	//of the MazeFactory and StubOrder classes
	private static MazeFactory factory = new MazeFactory();
	private static StubOrder order = new StubOrder();
	
	//Create before method to set the skill level and the seed values each time
	@Before
	public static void setUp() {
		order.setSkillLevel(1);
		order.setSeed(5);
	}
	
	//Create an after method to tear down the maze after every test so a new maze can be created
	@After
	public static void tearDown() {	}
	
	
	//Test to make sure a maze is being created when order is called
	//Wait until the maze from order is delivered
	//Check to see that the maze returned exists and is not null
	@Test
	void testBuilder() {
		factory.order(order);
		factory.waitTillDelivered();
		assertNotNull(order.getMaze());
	}
	
	//Test to see if there is a reachable exit in the maze and it is reachable from every point
	//Create a maze with order and wait until it is delivered
	//Get the created maze and store the height and width values
	//Get all of the distances in the maze and store it
	//Check all of the distances for the dimensions of the maze
	//Fail the test if there are any negative values or there is an area of the maze that is unreachable
	@Test
	void testReachableExit() {
		factory.order(order);
		factory.waitTillDelivered();
		Maze maze = order.getMaze();
		
		int width = maze.getWidth();
		int height = maze.getHeight();
		int[][] distance = maze.getMazedists().getAllDistanceValues();
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (distance[x][y] < 0) fail("Negative value occured");
				if (distance[x][y] == Integer.MAX_VALUE) fail("There is an unreachable area in the maze");
			}
		}
		return;
	}
	
	//Test if two created mazes with the same seed and skill level are equivalent
	//Set all of the parameters to create two mazes
	//Create two mazes
	//Assert that the two floor plans of the mazes are the same
	//Fail the test otherwise
	@Test
	void testTwoEqualMazes() {

		order.setSeed(10);
		order.setSkillLevel(3);
		order.setPerfect(true);
		factory.order(order);
		factory.waitTillDelivered();
		Maze maze1 = order.getMaze();
		Floorplan floor1 = maze1.getFloorplan();
		
		factory.order(order);
		factory.waitTillDelivered();
		Maze maze2 = order.getMaze();
		Floorplan floor2 = maze2.getFloorplan();
		
		assertTrue(floor1.equals(floor2));

	}
	
	
	//Test to see if the maze has an exit in general
	//Create the maze and wait until it is delivered
	//Get the maze along with its width and height
	//Check all of the boarder tiles and make sure at least one of them has no wall on any side
	//If there is any boarder tile with no wall, this is an exit and then the test completes
	//With no walls, the test hits a fail assertion at the end
	@Test
	void testHasExit() {
		factory.order(order);
		factory.waitTillDelivered();
		
		Maze maze = order.getMaze();
		int width = maze.getWidth();
		int height = maze.getHeight();
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x == 0) {
					if (!maze.hasWall(x, y, CardinalDirection.West)) return; }
				if (x == width - 1) {
					if (!maze.hasWall(x, y, CardinalDirection.East)) return; }
				if (y == 0) {
					if (!maze.hasWall(x, y, CardinalDirection.North)) return; }
				if (y == height - 1) {
					if (!maze.hasWall(x, y, CardinalDirection.South)) return; }
			}
		}
		fail("None of the boarders contained an exit");
	}
	
	//Test to ensure that if we specify we want a perfect maze that there are no rooms
	//Make perfect True so we can test for rooms
	//Order a maze and wait until it is delivered
	//Get the floor plan of the maze
	//Check every cell in the maze to see if it is in a room
	//Fail the test if any rooms are found
	@Test 
	void testPerfectMaze() {
		
		order.setPerfect(true);
		factory.order(order);
		factory.waitTillDelivered();
		Maze maze = order.getMaze();
		
		Floorplan floorplan = maze.getFloorplan();
		int width = maze.getWidth();
		int height = maze.getHeight();
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (floorplan.isInRoom(x,  y)) fail("There was at least one room found");
			}
		}
	}
	
	//Test to see if there are enough walls to create a minimum spanning tree
	//Count the walls by counting the number of edges, which should be width*height-1
	//Count the number of spaces in between each cell to count the edges
	//Create a perfect maze and wait until it is delivered
	//Get and store the maze, floor plan, width, and height
	//Go through every cell and check if there is a wall in each location
	//Every time there is no wall, add to the number of edges counter
	//Make sure the number of counted edges equals the width*height-1
	@Test
	void testCorrectNumberWalls() {
		
		order.setPerfect(true);
		order.setSkillLevel(2);
		factory.order(order);
		factory.waitTillDelivered();
		
		Maze maze = order.getMaze();
		int width = maze.getWidth();
		int height = maze.getHeight();
		int edges = 0;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x != width - 1) {
					if (!maze.hasWall(x, y, CardinalDirection.East)) edges += 1;
				}
				if (y != height - 1) {
					if (!maze.hasWall(x, y, CardinalDirection.South)) edges += 1;
				}
			}
		}
	}
}
