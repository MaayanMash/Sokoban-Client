package commons;

/**
 * Sets a wall in the game
 * @see public Wall() Sets the mark of the wall '#'
 * @see public Wall(position pos) Sets the mark of the wall '#' and the location of the wall
 */
public class Wall extends TypeA implements background {
	
	//D'ef C'tor
	/**
	 * Sets the mark of the wall '#'
	 */
	public Wall() {
		super('#',null);
	}
	//C'tor
	/**
	 * Sets the mark of the wall '#' and the location of the wall
	 * @param pos the location of the wall
	 */
	public Wall(position pos) {
			super('#',pos);
	}
	
}
