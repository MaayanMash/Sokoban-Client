package controller;

import controller.generic.iCommand;
/**
 * Defines a class to create specific command
 * @see  public iCommand createCommand() return new command;
 */
public interface iCreatorCommand {
	/**
	 * create new command
	 * @return new command
	 */
	 public iCommand createCommand();
}
