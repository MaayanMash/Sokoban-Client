package controller;


import view.iView;

public class CommandDisplayMassege extends CommandA {

	private iView view;

	
	public CommandDisplayMassege(iView theView) {
		this.view = theView;
	}
	
	@Override
	public void execute() {
		view.displayMassege(params);
			
	}

}
