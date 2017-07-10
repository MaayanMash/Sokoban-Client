package commons;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * abstract class for all types of levels of Sokoban game
 *
 */
public abstract class LevelA implements level, Serializable{
	private status st;
	protected Player player; 
	private ArrayList<Box> boxs;
	private ArrayList<Target> targets;
	 
	//Diffult C'tor 
	public LevelA() {
		this.st = new StatusLevel();
		this.setPlayer(null);
		this.boxs = new ArrayList<Box>();
		this.targets = new ArrayList<Target>();
	}	
	//C'tor
	public LevelA(status st,Player player, ArrayList<Box> boxs,ArrayList<Target> targets) {
		this.st = st;
		this.setPlayer(player);
		this.boxs = boxs ;
		this.targets = targets;
	}
	

	
	//copy c'tor
	/**
	 * copy c'tor
	 * @param level
	 */
	public LevelA (LevelA level){
		this.st=level.getStatus();
		this.player=level.getPlayer();
		this.boxs=level.getBoxs();
		this.targets=level.geTargets();
	}

	//gets && sets
	@Override
	public status getStatus() {
		return this.st;
	}
	public ArrayList<Box> getBoxs(){
		return this.boxs;
	}
	public ArrayList<Target> geTargets(){
		return this.targets;

	}	
	public void setStatus(status st) {
		this.st=st;
	}
	public void setBoxs (ArrayList<Box> boxs){
		this.boxs=boxs;
	}
	public void setTargets (ArrayList<Target> targets){
		this.targets=targets;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	@Override
	public void addStep() {
		this.st.addStep();	
	}
	
	
	@Override
	public int getCountSteps() {
		return this.st.getCountSteps();
	}
	

	@Override
	public int getCountTargets() {
		return this.targets.size();
	}
	@Override
	public int getCountBoxs(){
		return this.boxs.size();
	}
	
	@Override
	public boolean ifSolved() {
		if (this.getCountBoxOnTargets()==this.getCountTargets()&&this.getCountTargets()!=0)
			return true;
		return false;
	}



}
