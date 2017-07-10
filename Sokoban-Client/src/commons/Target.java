package commons;

/**
 * Sets a target in the game
 * @see public Target() Sets the mark of the target 'O'
 * @see public Target(position pos) Sets the mark of the Target 'O' and the location of the target
 */
public class Target extends Path {
	
	//D'ef C'tor
	/**
	 * Sets the mark of the target 'O'
	 */
	public Target() {
		super('O',null);
	}
	//C'tor
	/**
	 * Sets the mark of the Target 'O' and the location of the target
	 * @param pos the location of the target
	 */
	public Target(position pos) {
		super('O',pos);
	}
}
