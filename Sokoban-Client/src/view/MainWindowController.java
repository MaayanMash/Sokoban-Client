package view;


import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javax.naming.spi.DirStateFactory.Result;
import commons.Level2D;
import java.util.Observable;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;


public class MainWindowController extends Observable implements Initializable, iView {
	
	@FXML private SokobanDisplayer SokobanDisplayer;
	private Stage primaryStage;
	private SokobanKeyEvent myKey;
	private Stage scoreStage;
	private ScoreController scoreController;
	private boolean gotSolution;
	
	//DB
	private String levelName;
	private String userName;
	private boolean check;

	//timer
	@FXML private Text SokobanTimer;
	private int countTime;
	private int minCount;
	private int secCount;
	private StringProperty CounterTime;
	private Timer timer;
	private boolean time;
	
	//steps
	@FXML private Text SokobanSteps;
	
	//music
	private String musicString;
	private MediaPlayer player;
	private boolean music;
	
	//CLI
	private boolean fromCli;
	
	//Ctor'
	public MainWindowController() {
		this.CounterTime=new SimpleStringProperty();
		this.musicString=new File("./resources/music/Game Music.mp3").toURI().toString();
		this.player=new MediaPlayer(new javafx.scene.media.Media(musicString));
		this.music=false;
		this.music=false;
		this.fromCli=true;
		this.gotSolution=false;
		this.myKey = initKeyEvent("./resources/Settings/Sokoban Key.xml");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setFocus();
		startStopMusic();
		//here you give the focus with click on a mouse
		this.SokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->SokobanDisplayer.requestFocus());
		
		this.SokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				String command=null;
				if(event.getCode()==myKey.getUp()){
					command="move up";
					SokobanDisplayer.setPlayerFileName("./resources/images/player4.jpg");
				}
				else if(event.getCode()==myKey.getDown()){
					command="move down";
					SokobanDisplayer.setPlayerFileName("./resources/images/player1.jpg");
					
				}
				else if(event.getCode()==myKey.getLeft()){
					command="move left";
					SokobanDisplayer.setPlayerFileName("./resources/images/player3.jpg");
				}
				else if(event.getCode()==myKey.getRight()){
					command="move right";
					SokobanDisplayer.setPlayerFileName("./resources/images/player2.jpg");
				}
				else
				{
					displayMassege("Invalid key");
					command=null;
				}
				if (command!=null){
					setChanged();
					notifyObservers(command);
				}
				
			}
		});
	}
	public void setPrimaryStage(Stage primaryStage){
		this.primaryStage=primaryStage;
		exitPrimaryStage();
	}
	
	@Override
	public void createBindSteps(StringProperty Counter){
		this.SokobanSteps.textProperty().bind(Counter);
	}
	
	/**
	 * Defines the focus of the game
	 */
	private void setFocus()
	{
		SokobanDisplayer.focusedProperty().addListener(new ChangeListener<Boolean>() 
		{
			@Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) 
            {
                Platform.runLater(new Runnable()
                {
                    public void run() 
                    {
                    	SokobanDisplayer.requestFocus();
                    }
                });                    
            }
        });
	}
	
	/**
	 * Checks if the level has been resolved
	 * @param theLevel
	 */
	public void ifsolved(Level2D theLevel){
			
		if (theLevel.ifSolved())
		{
			stopTimer();
			if (!this.gotSolution)
			{
				Platform.runLater(new Runnable() 
				{
					@Override
					public void run() {
						
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("solved!");
						alert.setHeaderText("The level solved!!\n Are you want to save?");
		
						alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);
						
						if (minCount<10 && secCount<10)
							alert.setContentText("Time: 0"+minCount+":0"+secCount+"\nSteps: "+ theLevel.getCountSteps());
						else if (minCount<10 && secCount>10)
							alert.setContentText("Time: 0"+minCount+":"+secCount+"\nSteps: "+ theLevel.getCountSteps());
						if (minCount>10 && secCount<10)
							alert.setContentText("Time: "+minCount+":0"+secCount+"\nSteps: "+ theLevel.getCountSteps());
						if (minCount>10 && secCount>10)
							alert.setContentText("Time: "+minCount+":"+secCount+"\nSteps: "+ theLevel.getCountSteps());
						Optional<ButtonType> result = alert.showAndWait();
	
						if (result.get() == ButtonType.YES){
							ifExsits();
						} 
						else {
						    alert.close();
						}
					}
				});
			}
		}
	}

	/**
	 * Displays a message to an existing user
	 */
	public void userExists(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				check=true;
				TextInputDialog dialog = new TextInputDialog("user name");
				dialog.setTitle("Exsits user");
				dialog.setHeaderText("Insert user name");
				dialog.setContentText("Please enter your name:");
				
				Optional<String> result = dialog.showAndWait();
				userName=result.get().replaceAll("\\s+", "");
				
				if (result.isPresent()){
					setChanged();
				    notifyObservers("db check Users where userName like '"+userName+"'");
				}
				}
				});
	}
	
	/**
	 * Adding a new user
	 */
	public void addUser(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				TextInputDialog dialog = new TextInputDialog("user name");
				check=false;
				dialog.setTitle("log in");
				dialog.setHeaderText("Insert your name");
				dialog.setContentText("Please enter your name:");
				Optional<String> result = dialog.showAndWait();
				
				if (result.isPresent()){
				    userName=result.get().replaceAll("\\s+", "");
				    setChanged();
				    notifyObservers("db check Users where userName like '"+userName+"'");
				
				}
				}
				});

	}
	
	/**
	 * Ask the user if he is a new or existing user
	 */
	public void ifExsits(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Are you a new or existing user?");
		alert.setHeaderText("Are you a new or existing user?");
		
		ButtonType buttonExsits = new ButtonType("Exsits user");
		ButtonType buttonNew = new ButtonType("New user");

		alert.getButtonTypes().setAll(buttonExsits, buttonNew);

		Optional<ButtonType> result = alert.showAndWait();
		//chose exsits user
		if (result.get() == buttonExsits)
		    userExists();
		//chose new user
		if (result.get()==buttonNew)
			addUser();
		}	
	
	@Override
	public void ifUserExsits(boolean exists) {
		if (check)
	    {
			if (!exists)
			{
				userExists();
				displayMassege("The user is not exsits");
			}
			else
			{
				int time=this.minCount*60+this.secCount;
				setChanged();
				notifyObservers("db add userlevel "+this.userName+" "+this.levelName+" "+this.SokobanSteps.getText()+" "+time);
				displayMassege("The score saved");
		    
			}
	    }
	    //exists user
	    else
	    {
	    	if (!exists)
			{
	    		
	    		setChanged();
			    notifyObservers("db add user "+this.userName);
			    int time=this.minCount*60+this.secCount;
			    setChanged();
			    notifyObservers("db add userlevel "+this.userName+" "+this.levelName+" "+this.SokobanSteps.getText()+" "+time);
			    
			    setChanged();
			    displayMassege("The score saved");
		    }
	    	else{
	    		
	    		addUser();
				displayMassege("The name is already exsits");
	    	}
	    	
	    }
	}
	
	/**
	 * Initializing the timer
	 */
	public void startTimer() {
		this.time=true;
		this.countTime=0;
		this.minCount=0;
		this.secCount=0;
		this.timer=new Timer();
		SokobanTimer.textProperty().bind(this.CounterTime);
		this.timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				secCount++;
				if (secCount>59){
					minCount++;
					secCount=0;
				}
				else if (minCount<10)
					if(secCount<10)
						CounterTime.set("0"+(minCount)+":0"+(secCount));
					else
						CounterTime.set("0"+(minCount)+":"+(secCount));
				else
				CounterTime.set(""+(minCount)+":"+(secCount));
					
			}
		}, 0, 1000);		
		
	}
	
	/**
	 * Stops the timer
	 */
	public void stopTimer(){
		if (this.timer!=null)
			this.timer.cancel();
	}
	
	/**
	 * Continues the timer
	 */
	public void continueTime(){
		this.timer=new Timer();
		SokobanTimer.textProperty().bind(this.CounterTime);
		this.timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				secCount++;
				if (secCount>59){
					minCount++;
					secCount=0;
				}
				if (minCount<10)
					if(secCount<10)
						CounterTime.set("0"+(minCount)+":0"+(secCount));
					else
						CounterTime.set("0"+(minCount)+":"+(secCount));
				else
				CounterTime.set(""+(minCount)+":"+(secCount));
					
			}
		}, 0, 1000);		
	}

	/**
	 * If the music is heard then stop the music and if the music does not sound then turn it on
	 */
	public void startStopMusic(){
		if (this.music)
		{
			this.player.pause();
			this.music=false;
		}
		else
		{
			this.player.play();
			this.music=true;
		}
	}
	
	/**
	 * Sets a button to load a level
	 */
	public void openFile(){
		this.gotSolution=false;
		this.fromCli=false;
		FileChooser fc =new FileChooser();
		fc.setTitle("open level");
		fc.setInitialDirectory(new File("./resources/levels"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));
		
		File chosen = fc.showOpenDialog(null);
		if(chosen != null){
			String name=chosen.getName();
			int i=0;
			this.levelName="";
			while (name.charAt(i)!='.')
				this.levelName+=name.charAt(i++);
			
			setChanged();
			notifyObservers("load "+ chosen.getPath());
			SokobanDisplayer.setPlayerFileName("./resources/images/player.jpg");
			stopTimer();
			startTimer();
			this.time=true;
			
		}
	}
	
	/**
	 * Sets a button to save a level
	 */
	public void saveFile(){
		FileChooser fc =new FileChooser();
		fc.setTitle("save level");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));
		
		File chosen = fc.showSaveDialog(null);
		if(chosen != null){
			setChanged();
			notifyObservers("save " + chosen.getPath());
		}
	}
	
	@Override
	public void displayLevel(Level2D theLevel) {
		if (fromCli){
			startTimer();
			this.fromCli=false;
		}
			
		this.SokobanDisplayer.setSokobanCol(theLevel.getSizeCol());
		this.SokobanDisplayer.setSokobanRow(theLevel.getSizeRow());
		this.SokobanDisplayer.setSokobanData(theLevel.getBoared());
		
		ifsolved(theLevel);
	}
	@Override
	public void displayExit() {
		stopTimer();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit?");
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
			if (this.music)
				startStopMusic();
		    setChanged();
		    notifyObservers("exit");
		    Platform.exit();
		} else {
			if (time)
				continueTime();
		    alert.close();
		}
	}
	@Override
	public void displayMassege(String massege) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alart =new Alert(AlertType.INFORMATION);
				alart.setHeaderText(massege);
				alart.show();
			}
		});
		
	}
	public void exitPrimaryStage(){
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				setChanged();
				notifyObservers("exit");
				
			}
			
		});
	}
	
	/**
	 * Sets the key event to move a game
	 * @param path File that defines key event to move a game
	 * @return key event
	 */
	private SokobanKeyEvent initKeyEvent(String path){
 		XMLDecoder xmlDecoder;
 		SokobanKeyEvent sokobanKey = null;
 		
 		try {
			xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File(path))));
	 		sokobanKey = (SokobanKeyEvent) xmlDecoder.readObject();
	 		xmlDecoder.close();

		} 
 		catch (FileNotFoundException e) {
			e.printStackTrace();
			setChanged();
			notifyObservers("DisplayMassege Wrong file KeyEvent input ");
		}
 		
 		return sokobanKey;
	}
	
	/**
	 * return the score stage
	 *
	 * @return stage
	*/
	public Stage getScoreStage() {
		return scoreStage;
	}
	/**
	 * Defines the score stage
	 *
	 * @param  stage
	*/
	public void setScoreStage(Stage scoreStage) {
		this.scoreStage = scoreStage;
	}
	/**
	 * Displays the score window
	 */
	public void showScore(){
		this.scoreController.init();
		this.scoreStage.show();
	}
	/**
	 * Defines the score controller
	 * @param scorePage
	 */
	public void setScoreController(ScoreController scorePage) {
		this.scoreController = scorePage;
		
	}
	
	/**
	 * Requests the server to resolve the level
	 */
	public void solve(){
		this.gotSolution=true;
		setChanged();
		notifyObservers("solve");
	}
	
	/**
	 * Requests the server a clue to resolve the level
	 */
	public void clue(){
		setChanged();
		notifyObservers("clue");
	}
	

 	



	
}