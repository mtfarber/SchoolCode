package generation;

import com.sun.tools.javac.util.ArrayUtils;

public class MazeBuilderBoruvka extends MazeBuilder implements Runnable {
	
	public MazeBuilderBoruvka() {
		super();
		System.out.println("MazeBuilderBoruvka uses Boruvka's algorithm to generate maze.");
	}
	public int getEdgeWeight(Wallboard w) {
		return w.getWallboardWeight();
	}
	
	@Override protected void generatePathways() {
		//call assignWeights method to set all of the wallboard weights
		//each wallboard will start with a randomly assigned weight
		//start at first random cell and iterate through them in the maze
		//for each cell, look at all available directions and pick the wallboard that is the cheapest
		//destroy the cheapest wall by flagging it for future deletion
		//after all wallboards are iterated through, iterate a second time to delete them
		//use deleteWallboard to delete every wallboard that has the deleted variable marked as true
		//this creates minimum spanning trees, so continue to loop through until all trees are connected
		//all of the MST's connect to create the final maze
	}
	
	private void assignWeights() {
		//initialize an empty dictionary the same size as the number of wallboards
		//find  random number from 0 to number of wallboards using a seed
		//check to see if that number has already been generated in a set
		//assign that number to the next wallboard through iteration
		//add the number to the dictionary so it won't be generated again
		int wallboardCount = (height * (width-1)) + (width*(height-1));
		int[] intArray = new int[wallboardCount];
		do {
			int possibleInt = random.nextInt(wallboardCount);
		} while(ArrayUtils.contains(intArray, possibleInt));
		intArray += possibleInt;
		w.weight = possibleInt;
	}
}
