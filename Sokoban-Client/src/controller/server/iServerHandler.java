package controller.server;

import java.util.List;

public interface iServerHandler {
	
	public void start(String ip, int port);
	public void sendLevel(char[][] Board);
	public void sendClue(char[][] Board);
	public void addToDB(Object o) ;
	public void updateDB(Object o);
	public List selectScore(String query);
	public void sendStop();
	
}
