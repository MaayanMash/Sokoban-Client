package commons;

/**
 * Sets a box in the game
 * @see public Box() Sets the mark of the box @
 * @see public Box(position pos) Sets the mark of the box @ and the location of the box
 */
public class Box extends TypeA implements moveAble {
	
	//def C'tor
	/**
	 * Sets the mark of the box @
	 */
	public Box() {
		super('@',null);			
	}
	//C'tor
	/**
	 * Sets the mark of the box @ and the location of the box
	 * @param pos location of the box
	 */
	public Box(position pos) {
		super('@',pos);			
	}	
	
}
