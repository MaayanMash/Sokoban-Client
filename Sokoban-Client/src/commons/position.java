package commons;


/**
 * Defines a location in the game
 *@see public position getPosition() return position;
 *@see public boolean equalsPos(position pos) Compares two locations;
 */
public interface position{
	/**
	 * 
	 * @return polsition
	 */
	public position getPosition();
	/**
	 * Compares two locations;
	 * @param pos
	 * @return true if both positions are equal, otherwise false
	 */
	public boolean equalsPos(position pos);
}
