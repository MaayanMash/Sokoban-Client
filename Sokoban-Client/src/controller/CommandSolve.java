package controller;

import controller.server.iServerHandler;
import model.iModel;

public class CommandSolve extends CommandA{
	
	private iServerHandler serverHandler;
	private iModel model;
	
	public CommandSolve(iServerHandler serverHandler, iModel model) {
		this.serverHandler=serverHandler;
		this.model=model;
	}

	@Override
	public void execute() {
		System.out.println("solve");
		this.serverHandler.sendLevel(this.model.getCurrentLevel().getBoared());
		
	}

}
