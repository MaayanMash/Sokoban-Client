package commons;

import java.io.Serializable;

/**
 * Defines interface for all types of levels of Sokoban game
 * @see public int getCountTargets() Returns the amount of target the game has;
 * @see public int getCountBoxs() Returns the amount of boxes the game has;
 * @see public int getCountBoxOnTargets() Returns the quantity of boxes that have reached the target;
 * @see public int getCountBoxNotOnTargets() Returns the amount of boxes that have not reached the target;
	public boolean ifSolved () Checks whether the level has been resolved;
 **/
public interface level extends status{
	/**
	 * Returns the amount of target the game has
	 * @return the amount of target
	 */
	public int getCountTargets();
	
	/**
	 *  Returns the amount of boxes the game has;
	 * @return the amount of boxes
	 */
	public int getCountBoxs();
	
	/**
	 * Returns the quantity of boxes that have reached the target;
	 * @return the quantity of boxes that have reached the target
	 */
	public int getCountBoxOnTargets();
	
	/**
	 * Returns the amount of boxes that have not reached the target;
	 * @return the amount of boxes that have not reached the target;
	 */
	public int getCountBoxNotOnTargets();
	
	/**
	 * Checks whether the level has been resolved
	 * @return true if the level has been resolved, false if the level has not been resolved
	 */
	public boolean ifSolved ();

}
