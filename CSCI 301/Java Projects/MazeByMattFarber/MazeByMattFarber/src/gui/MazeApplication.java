/**
 * 
 */
package gui;

import generation.Order;

import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;


/**
 * This class is a wrapper class to startup the Maze game as a Java application
 * 
 * This code is refactored code from Maze.java by Paul Falstad, www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper
 * 
 * TODO: use logger for output instead of Sys.out
 */
public class MazeApplication extends JFrame {

	// not used, just to make the compiler, static code checker happy
	private static final long serialVersionUID = 1L;
	
	// developments vs production version
	// for development it is more convenient if we produce the same maze over an over again
	// by setting the following constant to false, the maze will only vary with skill level and algorithm
	// but not on its own
	// for production version it is desirable that we never play the same maze 
	// so even if the algorithm and skill level are the same, the generated maze should look different
	// which is achieved with some random initialization
	private static final boolean DEVELOPMENT_VERSION_WITH_DETERMINISTIC_MAZE_GENERATION = false;

	/**
	 * Constructor
	 */
	public MazeApplication() {
		init("", "", "");
	}

	/**
	 * Constructor that loads a maze from a given file or uses a particular method to generate a maze
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that stores an already generated maze that is then loaded, or can be null
	 */
	public MazeApplication(String generation, String drivers, String sensors) {
		init(generation, drivers, sensors);
	}

	/**
	 * Instantiates a controller with settings according to the given parameter.
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
	 * or a filename that contains a generated maze that is then loaded,
	 * or can be null
	 * @return the newly instantiated and configured controller
	 */
	 Controller createController(String generation, String driver, String sensors) {
	    // need to instantiate a controller to return as a result in any case
	    Controller result = new Controller() ;
	    // can decide if user repeatedly plays the same mazes or 
	    // if mazes are different each and every time
	    // set to true for testing purposes
	    // set to false for playing the game
	    if (DEVELOPMENT_VERSION_WITH_DETERMINISTIC_MAZE_GENERATION)
	    	result.setDeterministic(true);
	    else
	    	result.setDeterministic(false);
	    String msg = null; // message for feedback
	    // Case 1: no input
	    if (generation == null) {
	        msg = "MazeApplication: maze will be generated with a randomized algorithm."; 
	    }
	    // Case 2: Prim
	    else if ("Prim".equalsIgnoreCase(generation))
	    {
	        msg = "MazeApplication: generating random maze with Prim's algorithm.";
	        result.setBuilder(Order.Builder.Prim);
	    }
	    // Case 3 a and b: Eller, Kruskal, Boruvka or some other generation algorithm
	    else if ("Kruskal".equalsIgnoreCase(generation))
	    {
	    	// TODO: for P2 assignment, please add code to set the builder accordingly
	        throw new RuntimeException("Don't know anybody named Kruskal ...");
	    }
	    else if ("Eller".equalsIgnoreCase(generation))
	    {
	    	// TODO: for P2 assignment, please add code to set the builder accordingly
	        throw new RuntimeException("Don't know anybody named Eller ...");
	    }
	    else if ("Boruvka".equalsIgnoreCase(generation))
	    {
	    	msg = "MazeApplication: generating random maze with Boruvka's algorithm.";
	        result.setBuilder(Order.Builder.Boruvka);
	    }
	    // Case 4: a file
	    else {
	        File f = new File(generation) ;
	        if (f.exists() && f.canRead())
	        {
	            msg = "MazeApplication: loading maze from file: " + generation;
	            result.setFileName(generation);
	            return result;
	        }
	        else {
	            // None of the predefined strings and not a filename either: 
	            msg = "MazeApplication: unknown parameter value: " + generation + " ignored, operating in default mode.";
	        }
	    }
	    // controller instantiated and attributes set according to given input parameter
	    // output message and return controller
	    System.out.println(msg);

	    Robot r;
		switch (sensors) {
		case(""):
		case("1111"):
			r = new ReliableRobot();
			break;
		default:
			// the sensors are handled in UnreliableRobot's constructor method
			r = new UnreliableRobot(sensors);
			break;
		}
		
		// Third: Set the driver for the program
		RobotDriver d;
		
		switch (driver) {
		case("Wizard"):
			System.out.println("Using automatic player: Wizard driver.");
			d = new Wizard();
			result.setRobotAndDriver(r, d);
			break;
		case("Wallfollower"):
			System.out.println("Using automatic player: Wallfollower driver.");
			d = new WallFollower();
			result.setRobotAndDriver(r, d);
			break;
		case("Manual"):
		default:
			break;
		}
		
		// controller instantiated and attributes set according to given input parameters
		// return controller
		return result;
	}
		
	 
	/**
	 * Initializes some internals and puts the game on display.
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that contains a generated maze that is then loaded, or can be null
	 */
	private void init(String generation, String driver, String sensors) {
	    // instantiate a game controller and add it to the JFrame
	    Controller controller = createController(generation, driver, sensors);
		add(controller.getPanel()) ;
		// instantiate a key listener that feeds keyboard input into the controller
		// and add it to the JFrame
		KeyListener kl = new SimpleKeyListener(this, controller) ;
		addKeyListener(kl) ;
		// set the frame to a fixed size for its width and height and put it on display
		setSize(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT+22) ;
		setVisible(true) ;
		// focus should be on the JFrame of the MazeApplication and not on the maze panel
		// such that the SimpleKeyListener kl is used
		setFocusable(true) ;
		// start the game, hand over control to the game controller
		controller.start();
	}
	
	/**
	 * Main method to launch Maze game as a java application.
	 * The application can be operated in three ways. 
	 * 1) The intended normal operation is to provide no parameters
	 * and the maze will be generated by a randomized DFS algorithm (default). 
	 * 2) If a filename is given that contains a maze stored in xml format. 
	 * The maze will be loaded from that file. 
	 * This option is useful during development to test with a particular maze.
	 * 3) A predefined constant string is given to select a maze
	 * generation algorithm, currently supported is "Prim".
	 * @param args is optional, first string can be a fixed constant like Prim or
	 * the name of a file that stores a maze in XML format
	 */
	public static void main(String[] args) {
		JFrame app;
		
		String generation = "";
		String driver = "";
		String sensors = "";
		
		if (args.length == 0) app = new MazeApplication();
		
		// cycle through the arguments and assign the provided values to the correct variables
		else {
			for (int x = 0; x < args.length - 1; x++) {
				switch(args[x]) {
				case ("-g"):
					generation = args[x+1];
					break;
				case ("-d"):
					driver = args[x+1];
					break;
				case ("-r"):
					sensors = args[x+1];
					break;
				}
			}
			app = new MazeApplication(generation, driver, sensors);
		}
		
		app.repaint();
	}

}
