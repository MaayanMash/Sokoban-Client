package view;

import java.util.List;

import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @see public void setScorePage(Stage scorePage) Defines the stage;
 * @see	public Stage getScorePage() return the stage;
 * @see	public void setListResult(List listResult) Changes the list of results;
 * @see	public List getListResult() returns the list of results;
 * @see	public Scene getScene()return scene;
 * @see	public void setScene(Scene scene) Defines the scene;
 * @see	public void setData() Changes the list of results in the view;
 * @see	public void init() Initializing the data;
 * @see void exitPrimaryStage() Closes the score window;
 **/

public interface iScore  {

	/**
	 * Defines the stage
	 *
	 * @param  stage
	*/
	public void setScorePage(Stage scorePage);
	
	/**
	 * return the stage
	 *
	 * @return stage
	*/
	public Stage getScorePage();
	
	/**
	 * Changes the list of results;
	 *
	 * @param  list result
	*/
	public void setListResult(List listResult);
	
	/**
	 * returns the list of results;
	 *
	 * @return list
	*/
	public List getListResult();
	
	/**
	 * return the scene
	 *
	 * @return  scene
	*/
	public Scene getScene();
	
	/**
	 * Defines the scene
	 *
	 * @param  scene
	*/
	public void setScene(Scene scene);
	
	/**
	 *  Changes the list of results in the view;
	*/
	public void setData();
	
	/**
	 *  Initializing the data;
	*/
	public void init();
	
	/**
	 * Closes the score window
	 */
	void exitPrimaryStage();

}
