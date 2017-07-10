package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.db.UserLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class ScoreController extends Observable implements iScore  {
	private TableView<UserLevel> table;
	private Scene scene;
	private Stage scorePage;
	private List listResult;
	ObservableList<UserLevel> data;

	
	public ScoreController() {
		this.data=null;
		this.listResult=new ArrayList<Object>();
		this.table=new TableView();
		this.scene= new Scene(new Group());
		Label label = new Label("High Score");
        label.setFont(new Font("Arial", 20));
        
        TableColumn levelNameCol2 = new TableColumn("Level Name");
        TableColumn stepsCol = new TableColumn("Steps");
        TableColumn timeCol = new TableColumn("Time");
        
        levelNameCol2.setCellValueFactory(new PropertyValueFactory<UserLevel,String>("levelName"));
        stepsCol.setCellValueFactory(new PropertyValueFactory<UserLevel,String>("levelSteps"));
        timeCol.setCellValueFactory(new PropertyValueFactory<UserLevel,String>("levelTime"));
        
        
        TableColumn userNameCol = new TableColumn<>("User Name");
        
        userNameCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<UserLevel,UserLevel>, 
                ObservableValue<UserLevel>>() {

            @Override
            public ObservableValue<UserLevel> call(TableColumn.CellDataFeatures<UserLevel, UserLevel> p) {
                return new ReadOnlyObjectWrapper<UserLevel>(p.getValue());
            }
        });
        
        //Adding the Button to the cell
        userNameCol.setCellFactory(
                new Callback<TableColumn<UserLevel,UserLevel>, TableCell<UserLevel, UserLevel>>() {

            @Override
            public TableCell<UserLevel,UserLevel> call(TableColumn<UserLevel,UserLevel> userNameCol) {
            	UserButtonCell btn= new UserButtonCell();
                return btn;
            }
        
        });
 
        TableColumn levelNameCol = new TableColumn<>("level Name");
        
        
        //levelNeme col
        levelNameCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<UserLevel,UserLevel>, 
                ObservableValue<UserLevel>>() {

            @Override
            public ObservableValue<UserLevel> call(TableColumn.CellDataFeatures<UserLevel, UserLevel> p) {
                return new ReadOnlyObjectWrapper<UserLevel>(p.getValue());
            }
        });
        
        //Adding the Button to the cell
        levelNameCol.setCellFactory(
                new Callback<TableColumn<UserLevel,UserLevel>, TableCell<UserLevel, UserLevel>>() {

            @Override
            public TableCell<UserLevel,UserLevel> call(TableColumn<UserLevel,UserLevel> levelNameCol) {
            	LevelButtonCell btn= new LevelButtonCell();
                return btn;
            }
        
        });
       
        table.getColumns().addAll(userNameCol, levelNameCol, stepsCol, timeCol);
        
        //search user and level
        HBox search1= new HBox();
        
        final TextField user = new TextField();
        user.setPromptText("Enter user name");
        user.setPrefColumnCount(15);
        user.getText();
        search1.getChildren().add(user);
        Button searchUser = new Button("Search user");
        search1.getChildren().add(searchUser);
        
        searchUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				table.getItems().clear();
            	table.refresh();
            	setChanged();
            	notifyObservers("db select UserLevel where UserName like '"+user.getText()+"'");
                
            }
        });
				
        
        HBox search2= new HBox();
        
        final TextField level = new TextField();
        level.setPromptText("Enter Level name");
        level.setPrefColumnCount(15);
        search2.getChildren().add(level);
        
        Button searchLevel = new Button("Search level");
        search2.getChildren().add(searchLevel);
        
        searchLevel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				table.getItems().clear();
            	table.refresh();
              	setChanged();
              	notifyObservers("db select UserLevel where LevelName like '"+level.getText()+"'");
				
			}
		});
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label,search1, search2, table);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
	}
	
	@Override
	public void init() {
        setChanged();
        notifyObservers("db select UserLevel");
	}
	
	@Override
	public void setData(){
        data = FXCollections.observableArrayList(
        		this.listResult
        	);
        table.getItems().clear();
    	table.refresh();
        table.setItems(data);
        table.getSortOrder();
        
	}
	
	@Override
	public Stage getScorePage() {
		return scorePage;
	}

	@Override
	public void setScorePage(Stage scorePage) {
		this.scorePage = scorePage;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	@Override
	public List getListResult() {
		return listResult;
	}
	
	@Override
	public void setListResult(List listResult) {
		this.listResult = listResult;
	}
    
	@Override
	public void exitPrimaryStage(){
	this.scorePage.setOnCloseRequest(new EventHandler<WindowEvent>() {

		@Override
		public void handle(WindowEvent event) {
			Platform.exit();
			
		}
		
	});
	}
	
	/**
	 * Define the User button cell
	 * @see protected void updateItem(UserLevel userLevel, boolean empty) Display button with name of user if the row is not empty
	 */
	private class UserButtonCell extends TableCell<UserLevel,UserLevel>{
	    final Button cellButton = new Button("user level");
	    UserLevel userLevel;
	    
	    UserButtonCell(){
	        
	    	//Action when the button is pressed
	        cellButton.setOnAction(new EventHandler<ActionEvent>(){
	
	            @Override
	            public void handle(ActionEvent t) {
	            	table.getItems().clear();
	            	table.refresh();
	            	setChanged();
	            	notifyObservers("db select UserLevel where UserName like '"+userLevel.getKey().getUserName()+"'");
	            	
	            }
	        });
	    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(UserLevel userLevel, boolean empty) {
       super.updateItem(userLevel, empty);
       if (userLevel!=null){
    	   setGraphic(cellButton);
    	   cellButton.setText(userLevel.getKey().getUserName());
    	   this.userLevel=userLevel;
       }
    }
}

	/**
	 * Define the Level button cell
	 * @see protected void updateItem(UserLevel userLevel, boolean empty) Display button with name of level if the row is not empty
	 */
  private class LevelButtonCell extends TableCell<UserLevel,UserLevel> {
      final Button cellButton = new Button("user level");
      UserLevel userLevel;
      
      LevelButtonCell(){
          
      	//Action when the button is pressed
          cellButton.setOnAction(new EventHandler<ActionEvent>(){

              @Override
              public void handle(ActionEvent t) {
              	table.getItems().clear();
            	table.refresh();
              	setChanged();
              	notifyObservers("db select UserLevel where LevelName like '"+userLevel.getKey().getLevelName()+"'");
                //table.getItems().addAll(listResult);  
              }
          });
      }

      //Display button if the row is not empty
      @Override
      protected void updateItem(UserLevel userLevel, boolean empty) {
         super.updateItem(userLevel, empty);
         if (userLevel!=null){
      	   setGraphic(cellButton);
      	   cellButton.setText(userLevel.getKey().getLevelName());
      	   this.userLevel=userLevel;
         }
      }
    
}










}

