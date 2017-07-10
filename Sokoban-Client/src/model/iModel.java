package model;


import java.util.List;
import commons.Level2D;

/**
 * @see public void moveUp() Checks if the player can move up according to the policy and if so moves it;
 * @see public void moveDown() Checks if the player can move down according to the policy and if so moves it;
 * @see	public void moveRight() Checks if the player can move right according to the policy and if so moves it;
 * @see	public void moveLeft() Checks if the player can move left according to the policy and if so moves it;
 * @see	public void txtLevelLoad(String path) Loading level from a text file;
 * @see	public void objLevelLoad(String path) Loading level from an object file;
 * @see	public void xmlLevelLoad(String path) Loading level from a xml file;
 * @see	public void txtLevelSave(String path) Saving level to a text file;
 * @see	public void objLevelSave(String path) saving level to an object file;
 * @see	public void xmlLevelSave(String path) saving level to a xml file;
 * @see	public int getSteps() Return the amount of steps;
 **/

public interface iModel {
	
	public Level2D getCurrentLevel();
	
	/**
	 * Checks if the player can move up according to the policy and if so moves it
	*/
	public void moveUp();
	
	/**
	 * Checks if the player can move down according to the policy and if so moves it
	*/
	public void moveDown();
	
	/**
	 * Checks if the player can move right according to the policy and if so moves it
	 */
	public void moveRight();
	
	/**
	 * Checks if the player can move left according to the policy and if so moves it
	 */
	public void moveLeft();
	
	/**
	 * Loading level from a text file;
	 */
	public void txtLevelLoad(String path);
	
	/**
	 * Loading level from an object file;
	 */	
	public void objLevelLoad(String path);
	
	/**
	 * Loading level from a xml file;
	 */
	public void xmlLevelLoad(String path);
	
	/**
	 * Saving level to a text file;
	 */
	public void txtLevelSave(String path);
	
	/**
	 * Saving level to an object file;
	 */
	public void objLevelSave(String path);
	
	/**
	 * Saving level to a xml file;
	 */
	public void xmlLevelSave(String path);
	
	/**
	 *  Return the amount of steps;
	 */
	public int getSteps();
	
}
