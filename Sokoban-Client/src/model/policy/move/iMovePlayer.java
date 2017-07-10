package model.policy.move;

import commons.Level2D;
import commons.TwoPoint;
import model.policy.policy;

/**
 * @see	public Level2D movePlayer (Level2D TheLevel) Each shift checks whether the player can move in its direction and if so returns the level update, and if not returns null
 * @see	public Level2D moveEveryWhere(Level2D TheLevel, TwoPoint newPointsPlayer, TwoPoint playerPoints) Checks if the player can move to the new location
 **/
public interface iMovePlayer {
	/**
	 * Each shift checks whether the player can move in its direction
	 *
	 * @param  level
	 * @return If the player can move the returns the level is updated and if not return null
	 */
	public Level2D movePlayer (Level2D TheLevel);
	
	
	/**
	 * Checks if the player can move to the new location
	 *
	 * @param  level
	 * @param  newPointsPlayer Where the player wants to move
	 * @param  playerPoints The player's current position
	 * @return If the player can move the returns the level is updated and if not return null
	 */
	public Level2D moveEveryWhere(Level2D TheLevel, TwoPoint newPointsPlayer, TwoPoint playerPoints);
}
