package generation;

public class StubOrder implements Order{
	
	public int level = 1;
	public int seed = 1;
	public Builder builder = Builder.DFS;
	public boolean isPerfectBool = false;
	public int currentProgress = 0;
	public Maze maze;

	@Override
	public int getSkillLevel() {
		// TODO Auto-generated method stub
		return level;
	}
		
	public void setSkillLevel(int skill) {
		this.level = skill;
	}

	@Override
	public Builder getBuilder() {
		// TODO Auto-generated method stub
		return builder;
	}
	
	public void setBuilder(Builder build){
		this.builder = build;
	}

	@Override
	public boolean isPerfect() {
		// TODO Auto-generated method stub
		return isPerfectBool;
	}
	
	public void setPerfect(boolean bool){
		this.isPerfectBool = bool;
	}
	@Override
	public int getSeed() {
		// TODO Auto-generated method stub
		return seed;
	}
	
	public void setSeed(int seedChoice) {
		this.seed = seedChoice;
	}

	@Override
	public void deliver(Maze mazeConfigurate) {
		this.maze = mazeConfigurate;
		
	}

	@Override
	public void updateProgress(int percentage) {
		this.currentProgress += percentage;
		
	}
	
	public Maze getMaze() {
		return maze;
	}

}
