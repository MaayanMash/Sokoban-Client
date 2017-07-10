package view;

import commons.Level2D;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

/**
 * @see public void displayLevel(Level2D theLevel) Displays the level in the gui;
 * @see public void displayMassege(String massege) Displays the message at popUp;
 * @see public void displayExit() Displays exit message;
 * @see public void createBindSteps(StringProperty Counter) create behind for number of steps;
 * @see public void setPrimaryStage(Stage primaryStage)  Defines the stage;
 * @see public void exitPrimaryStage() Closes the main window;
 * @see public void ifUserExsits(boolean result)  Defines what to do if the user exists and if the user does not exist;
 **/
public interface iView {
	/**
	 * Displays the level in the gui
	 */
	public void displayLevel(Level2D theLevel);
	
	/**
	 * Displays the message at popUp
	 * @param massege The message you want to display to the user
	 */
	public void displayMassege(String massege);
	
	/**
	 * Displays exit message
	 */
	public void displayExit();
	
	/**
	 * create behind for number of steps
	 * @param Counter Count the steps
	 */
	public void createBindSteps(StringProperty Counter);
	
	/**
	 * Defines the stage
	 * @param primaryStage
	 */
	public void setPrimaryStage(Stage primaryStage);
	
	/**
	 * Closes the main window
	 */
	public void exitPrimaryStage();
	
	//DB
	/**
	 * Defines what to do if the user exists and if the user does not exist
	 * @param result - result from DB if the user exists
	 */
	public void ifUserExsits(boolean result);
	
}
