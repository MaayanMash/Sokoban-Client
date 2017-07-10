package controller;


import controller.generic.GenericController;
import controller.server.iServerHandler;
import view.iView;

public class CommandExit extends CommandA{

	private GenericController gc;
	private iServerHandler sh;

	
	public CommandExit(GenericController gc,iServerHandler serverHandler) {
		this.gc=gc;
		this.sh=serverHandler;
	}
	
	@Override
	public void execute()  {
		this.gc.stop();
		if(this.sh!=null)
			this.sh.sendStop();
	}

}
