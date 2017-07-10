package controller.generic;

import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * A department that runs a queue of commands in new thread
 * @see public void start() Starts the queue
 * @see public void stop() stop the queue
 * @see public void insertCommand(iCommand c) Enters a new command to queue
 *
 */
public class GenericController{
	
	private BlockingQueue<iCommand> commandQueue;
	private boolean stop;
	
	public GenericController() {
		this.commandQueue= new ArrayBlockingQueue<>(100);
		this.stop=false;
	}
	
	/**
	 * Starts the queue
	 */
	public void start(){

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stop){
					try {
						iCommand cm=commandQueue.poll(1, TimeUnit.SECONDS);
						if(cm!=null)
								cm.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	
	/**
	 * stop the queue
	 */
	public void stop(){
		this.stop=true;
	}
	
	/**
	 * Enters a new command to queue
	 * @param c new command
	 */
	public void insertCommand(iCommand c){
		try {
			commandQueue.put(c);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
