package commons;

import java.io.Serializable;

public class StatusLevel implements status, Serializable{
	private int countSteps;
	
	
	//defilt C'tor
	public StatusLevel() {
		this.countSteps =0;
	}	
	//C'tor
	public StatusLevel(int countSteps, int time) {
		this.setCountSteps(countSteps);
	}
	//copy c'tor
	public StatusLevel(StatusLevel status){
		this.countSteps=status.countSteps;
	}
	
	@Override
	public status getStatus() {
		return this;
	}
	@Override
	public void addStep() {
		this.countSteps+=1;		
	}
	//set's && get's
	@Override
	public int getCountSteps() {
		return this.countSteps;
	}
	
	public void setCountSteps(int countSteps) {
		if(countSteps>=0)
			this.countSteps = countSteps;
		else 
			this.countSteps=0;
	}

	



	
	
}
