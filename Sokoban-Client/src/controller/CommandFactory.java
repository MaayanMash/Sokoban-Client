package controller;

import java.util.HashMap;
import controller.generic.GenericController;
import controller.generic.iCommand;
import controller.server.iServerHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.iModel;
import view.ScoreController;
import view.iScore;
import view.iView;

public class CommandFactory {
	
	private GenericController gc;
	private iModel theModel;
	private iView theView;
	private iScore scoreTable;
	private StringProperty countSteps;
	private iServerHandler serverHandler;
	private HashMap<String, iCreatorCommand> hmCreator;
	

	//Ctor
	public CommandFactory(iModel model,iView view, iScore scoreTable, GenericController gc,StringProperty countSteps,iServerHandler serverHandler) {
		this.scoreTable=scoreTable;
		this.gc=gc;
		this.theModel=model;
		this.theView=view;
		this.countSteps=countSteps;
		this.serverHandler=serverHandler;
		hmCreator = new HashMap<>();
		initHashMap();
	}
	
	/**
	 * Initializing the hash map
	 */
	private void initHashMap() {
		this.hmCreator.put("load",new LoadCreator());
		this.hmCreator.put("save", new SaveCreator());
		this.hmCreator.put("displaylevel", new DisplayLevelCreator());
		this.hmCreator.put("move",new MoveCreator());
		this.hmCreator.put("exit",new ExitCreator());
		this.hmCreator.put("displaymassege",new DisplayMassegeCreator());
		this.hmCreator.put("display",new DisplayCliCreator());
		this.hmCreator.put("db", new DBCreator());
		this.hmCreator.put("solve",new solveCreator());
		this.hmCreator.put("clue",new clueCreator());
		
		
	}
	
	public HashMap<String, iCreatorCommand> getHmCreator() {
		return hmCreator;
	}
	
	/**
	 * create command load
	 *
	 */
	private class LoadCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandLoad(theModel);
		}
	}
	/**
	 * create command save
	 *
	 */
	private class SaveCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandSave(theModel);
		}
	}
	/**
	 * create command display level
	 *
	 */
	private class DisplayLevelCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandDisplayLevel(theView, theModel);
		}
	}
	/**
	 * create command move
	 *
	 */
	private class MoveCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandMove(theModel, countSteps);
		}
	}
	/**
	 * create command exit
	 *
	 */
	private class ExitCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandExit(gc, serverHandler);
		}
	}
	/**
	 * create command display massage
	 *
	 */
	private class DisplayMassegeCreator implements iCreatorCommand{

		@Override
		public iCommand createCommand() {
			//return new CommandDisplayMassege(theView, TheClientHendler);
			return new CommandDisplayMassege(theView);
		}
	}
	/**
	 * create command send to server to display
	 *
	 */
	private class DisplayCliCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandDisplayCli(theModel, serverHandler);
		}
	}
	/**
	 * create command send to server to db
	 *
	 */
	private class DBCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandDB(theModel, theView, scoreTable, serverHandler);
		}
	}
	/**
	 * create command send to server and asking for a solution
	 *
	 */
	private class solveCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandSolve(serverHandler, theModel);
		}
	}
	/**
	 * create command send to server and asking for a clue
	 *
	 */
	private class clueCreator implements iCreatorCommand{
		@Override
		public iCommand createCommand() {
			return new CommandClue(serverHandler, theModel);
		}
	}
	
	



}
