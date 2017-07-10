package controller;


import controller.server.iServerHandler;
import model.iModel;

public class CommandDisplayCli extends CommandA {
	
	private iServerHandler theServerHandler;
	private iModel theModel;
	
	public CommandDisplayCli(iModel model, iServerHandler serverHandler) {
		this.theModel=model;
		this.theServerHandler=serverHandler;
	}

	@Override
	public void execute() {
		this.theServerHandler.sendLevel(this.theModel.getCurrentLevel().getBoared());
	}

}
