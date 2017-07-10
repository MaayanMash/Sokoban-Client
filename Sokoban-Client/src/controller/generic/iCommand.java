package controller.generic;



/**
 * define command in the game
 * @see public void execute() Operation of command;
 * @see public void setParams(String params) Getting the Parameters of command;
 */
public interface iCommand {
	/**
	 * Operation of command
	 */
	public void execute();
	
	/**
	 * Getting the Parameters of command
	 * @param params
	 */
	public void setParams(String params);
	
}
