package controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.DocFlavor.INPUT_STREAM;

import controller.server.iServerHandler;
import model.iModel;
import view.MainWindowController;
import view.ScoreController;
import view.iScore;
import view.iView;

public class CommandDB extends CommandA {

	private iModel model;
	private iServerHandler serverHandler;
	private HashMap<String,Runnable> hmDB;
	private String query;
	private iScore scoreTable;
	private iView view;
	
	
	public CommandDB(iModel model,iView view, iScore scoreTable,iServerHandler serverHandler) {
		this.view=view;
		this.model=model;
		this.serverHandler=serverHandler;
		this.scoreTable=scoreTable;
		this.query=null;
		this.hmDB=new HashMap<String, Runnable>();
		initHashMap();
	}
	/**
	 * Initializing the hash map
	 */
	private void initHashMap(){
	hmDB.put("add", new Runnable() {
		
		@Override
		public void run() {
			//model.addToDB(query);
			System.out.println("CoDb 53 query: add "+query);
			serverHandler.addToDB("add "+query);
		}
	});
	
	hmDB.put("update", new Runnable() {
		
		@Override
		public void run() {
			//model.updateDB(query);
			serverHandler.updateDB("update "+query);
		}
	});
	
	
	hmDB.put("select", new Runnable() {
		@Override
		public void run() {//set list in score controller
			ArrayList<Object> list=new ArrayList<Object>();
			//list=(ArrayList<Object>) model.selectScore(query);
			list= (ArrayList<Object>) serverHandler.selectScore("select "+query);
			scoreTable.setListResult(list);
			scoreTable.setData();
		}
	});
	
	hmDB.put("check", new Runnable() {
		@Override
		public void run() {//set list in score controller
			ArrayList<Object> list=new ArrayList<Object>();
			//list=(ArrayList<Object>) model.selectScore(query);
			list=(ArrayList<Object>) serverHandler.selectScore("select "+query);
			if (list.size()!=0)//exists
				view.ifUserExsits(true);
			else//not exists
				view.ifUserExsits(false);
		}
	});
	}
	@Override
	public void execute() {
		String[] input = (params).split(" ",2);
		this.query=input[1];
		Runnable runnable=hmDB.get(input[0].toLowerCase());
		runnable.run();
	}

}
