package model.policy;

import commons.Level2D;
import commons.TwoPoint;
import commons.type;

/**
 * @see public boolean canMove(Level2D Thelevel, type t, TwoPoint points) Checks if the character can move;
 **/

public interface policy {
	
	/**
	 * Checks if the character can move;
	 *
	 * @param  level
	 * @param  type of character
	 * @param  points- A location you want to go through
	 * @return if the character can move
	 */
	public boolean canMove(Level2D Thelevel, type t, TwoPoint points);


}
