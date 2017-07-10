package commons;

/**
 * @see	public status getStatus() Returns the status of the game;
 * @see	public int getCountSteps() Returns the amount of steps the player has made;
 * @see	public void addStep() Adds a step to the player's amount of steps;
 */
public interface status {
	/**
	 * @return the status of the game 
	 */
	public status getStatus();
	
	/**
	 * 
	 * @return the amount of steps the player has made;
	 */
	public int getCountSteps();
	
	/**
	 * dds a step to the player's amount of steps;
	 */
	public void addStep();
	
	
}
