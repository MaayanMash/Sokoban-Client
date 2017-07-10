package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import controller.generic.GenericController;
import controller.generic.iCommand;
import controller.server.SokobanServerHandler;
import controller.server.iServerHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.iModel;
import view.MainWindowController;
import view.ScoreController;
import view.iScore;
import view.iView;

/**
 * Defines the controller of the game, Observer the model and the view
 * @author User
 *
 */
public class SokobanController implements Observer {
	
	private GenericController gc;
	private iModel theModel;
	private iView theView;
	private iScore scoreTable;
	private iServerHandler theServerHandler;
	private StringProperty countSteps;
	private CommandFactory commandFactory; 

	
	
	//Ctor
	public SokobanController(iModel model,iView view, iScore scoreTable) {
		this.scoreTable=scoreTable;
		this.gc=new GenericController();
		this.theModel=model;
		this.theView=view;
		this.countSteps=new SimpleStringProperty();
		this.gc.start();
		this.theView.createBindSteps(this.countSteps);
		commandFactory = new CommandFactory(theModel, theView, scoreTable, gc, countSteps, theServerHandler);
	}
	
	public SokobanController(iModel model,iView view,iScore scoreTable, SokobanServerHandler serverHandler, String ip, int port) {
		this.scoreTable=scoreTable;
		this.gc=new GenericController();
		this.theModel=model;
		this.theView=view;
		this.countSteps=new SimpleStringProperty();
		this.gc.start();
		this.theView.createBindSteps(this.countSteps);
		this.theServerHandler=serverHandler;
		this.theServerHandler.start(ip, port);
		commandFactory = new CommandFactory(theModel, theView, scoreTable, gc, countSteps, theServerHandler);
	}
	
	@Override
	/**
	 * Receives a task and knows how to treat it according to the command factory
	 */
	public void update(Observable o, Object arg) {
		String[] input = ((String)arg).split(" ",2);
		String commandName = input[0].toLowerCase();
		String params = null;	
		if(input.length > 1)
			params = input[1];
		
		iCommand cm = commandFactory.getHmCreator().get(commandName).createCommand();
		cm.setParams(params);
		gc.insertCommand(cm);	
	}
	


}
