package commons;

/**
 * Defines each type of character in the game
 *@see public String getType() Returns the name of the character type;
 *@see public char getC() Returns the symbol of the character;
 *@see public position getPos() Returns the position of the character;
 *@see public void setPos(position pos) Changes the position of the character;
 *
 */
public interface type {
	/**
	 * Returns the name of the character type;
	 * @return the name of the character type
	 */
	public String getType();
	
	/**
	 * Returns the symbol of the character
	 * @return the symbol of the character
	 */
	public char getC();
	
	/**
	 * Returns the position of the character
	 * @return the position of the character
	 */
	public position getPos();
	
	/**
	 * Changes the position of the character
	 * @param pos The new location
	 */
	public void setPos(position pos);
}
