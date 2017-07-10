package commons;

/**
 * Sets a path in the game
 * @see public Path() Sets the mark of the path ' '
 * @see public Path(position pos) Sets the mark of the path ' ' and the location of the path
 * @see public Path(char c,position pos) Sets the mark of the path and the location of the path
 */
public class Path extends TypeA implements background {

	//D'ef C'tor
	/**
	 * Sets the mark of the path ' '
	 */
	public Path() {
		super(' ',null);
	}

	// C'tors
	/**
	 * Sets the mark of the path ' ' and the location of the path
	 * @param pos the location of the path
	 */
	public Path(position pos) {
		super(' ',pos);
	}
	/**
	 * Sets the mark of the path and the location of the path
	 * @param c the mark of the path
	 * @param pos the location of the path
	 */
	public Path(char c,position pos) {
		super(c,pos);
	}

	
}
