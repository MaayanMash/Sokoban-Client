package view;
	

import java.io.IOException;
import java.util.List;
import controller.SokobanController;
import controller.generic.GenericController;
import controller.server.SokobanServerHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SokobanModel;
import model.iModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));				 
			BorderPane root = (BorderPane) loader.load();
			MainWindowController view = loader.getController();
			
			Stage scoreStage= new Stage();
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Score.fxml"));				 
			BorderPane root2;
			root2 = (BorderPane) loader2.load();
			ScoreController scorePage= loader2.getController();
			
			SokobanModel model=new SokobanModel();
			SokobanController sokobanController;
			
			//get args
			List<String> args=getParameters().getRaw();
			
			if (args.size()>0 && args.get(0).toLowerCase().equals("-server")){
				int port=Integer.parseInt(args.get(1));
				String ip=args.get(2);
				
				SokobanServerHandler serverHendler= new SokobanServerHandler();
				sokobanController=new SokobanController(model, view, scorePage, serverHendler, ip, port);
				serverHendler.addObserver(sokobanController);
				
	
			}
			else {
				sokobanController=new SokobanController(model, view,scorePage);
			}
			model.addObserver(sokobanController);
			view.addObserver(sokobanController);
			scorePage.addObserver(sokobanController);
			
			//scorePage.init();
			scorePage.setScorePage(scoreStage);
			Scene screne2 = scorePage.getScene();
			screne2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
			scoreStage.setTitle("High Score");
			scoreStage.setScene(screne2);
			scoreStage.setWidth(355);
			scoreStage.setHeight(500);
			
			view.setScoreStage(scoreStage);
			view.setScoreController(scorePage);
			
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Sokoban");
			view.setPrimaryStage(primaryStage);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

