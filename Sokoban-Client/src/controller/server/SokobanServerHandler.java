package controller.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class SokobanServerHandler extends Observable implements iServerHandler {

	private ObjectInputStream fromServer;
	private PrintWriter toServer;
	private HashMap<String, Runnable> HMSolve;
	private Socket theServer;

	public SokobanServerHandler() {
		this.HMSolve = new HashMap<>();
		initHashMap();

	}

	private void initHashMap() {

		this.HMSolve.put("r", () -> {
			setChanged();
			notifyObservers("move right");
		});

		this.HMSolve.put("l", () -> {
			setChanged();
			notifyObservers("move left");
		});

		this.HMSolve.put("u", () -> {
			setChanged();
			notifyObservers("move up");
		});

		this.HMSolve.put("d", () -> {
			setChanged();
			notifyObservers("move down");
		});
	}

	@Override
	public void start(String ip, int port) {
		try {
			System.out.println("ip: " + ip + " port: " + port);
			theServer = new Socket(ip, port);
			System.out.println("Connect to Server");
			this.fromServer = new ObjectInputStream(theServer.getInputStream());
			this.toServer = new PrintWriter(theServer.getOutputStream());

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void sendLevel(char[][] Board) {
		new Thread(() -> {
			// send level to server
			this.toServer.println("solve");
			this.toServer.flush();
			for (char[] cs : Board) {
				this.toServer.println(String.valueOf(cs));
				this.toServer.flush();
			}
			this.toServer.println("end");
			this.toServer.flush();

			// read solve from server
			String str;

			try {
				// maybe we need to change and add thread
				str = (String) this.fromServer.readObject();
				if (str.equals("no solution")) {
					this.setChanged();
					this.notifyObservers("DisplayMassege There is no solution");
				} else {
					String[] solve = str.split(",");
					for (String string : solve) {
						this.HMSolve.get(string.substring(0, 1)).run();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}).start();
	}

	@Override
	public void addToDB(Object o) {
		this.toServer.println("db");
		this.toServer.flush();
		this.toServer.println(o);
		this.toServer.flush();
		String str;
		try {
			str = (String) this.fromServer.readObject();
			if (str.equals("The name already exists")) {
				this.setChanged();
				notifyObservers("DisplayMassege " + str);
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateDB(Object o) {
		this.toServer.println("db");
		this.toServer.flush();
		this.toServer.println(o);
		this.toServer.flush();
		String str;
		try {
			str = (String) this.fromServer.readObject();
			if (str.equals("Wrong Input")) {
				this.setChanged();
				notifyObservers("DisplayMassege " + str);
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List selectScore(String query) {
		List<Object> list = new ArrayList<Object>();
		this.toServer.println("db");
		this.toServer.flush();
		this.toServer.println(query);
		this.toServer.flush();
		String str;

		try {
			list = (List<Object>) this.fromServer.readObject();
			// list.add(this.fromServer.readObject());

			// list = (List<Object>) this.fromServer.readObject();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void sendStop() {
		String str;
		this.toServer.println("exit");
		this.toServer.flush();
		try {
			this.fromServer.close();
			this.toServer.close();
			this.toServer.flush();
			theServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendClue(char[][] Board) {
		new Thread(() -> {
			// send level to server
			this.toServer.println("solve");
			this.toServer.flush();
			for (char[] cs : Board) {
				this.toServer.println(String.valueOf(cs));
				this.toServer.flush();
			}
			this.toServer.println("end");
			this.toServer.flush();

			// read solve from server
			String str;

			try {
				// maybe we need to change and add thread
				str = (String) this.fromServer.readObject();
				if (str.equals("no solution")) {
					this.setChanged();
					this.notifyObservers("DisplayMassege There is no solution");
				} else {
					String[] solve = str.split(",");
					
					//clone 3 steps
					int count = 0;
					for (String string : solve) {
						if (++count <= 3) {
							this.HMSolve.get(string.substring(0, 1)).run();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
							}
						}
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}).start();

	}

}
