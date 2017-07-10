package model.data;

import commons.TwoPoint;
import commons.type;

/**
 * @see public type createType(TwoPoint pos) Creates a character at pos location
 **/
public interface CreatorType {
	/**
	 * Creates a character at pos location
	 *
	 * @param  pos Where to place the character
	 * @return the new character
	 */
	public type createType(TwoPoint pos);
	
	
}
