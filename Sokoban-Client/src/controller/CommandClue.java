package controller;

import controller.server.iServerHandler;
import model.iModel;

public class CommandClue extends CommandA{
	
	private iServerHandler serverHandler;
	private iModel model;
	
	public CommandClue(iServerHandler serverHandler, iModel model) {
		this.serverHandler=serverHandler;
		this.model=model;
	}

	@Override
	public void execute() {
		this.serverHandler.sendClue(this.model.getCurrentLevel().getBoared());
		
	}

}
