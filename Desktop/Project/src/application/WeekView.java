package application;

import java.awt.Rectangle;
import java.awt.TextArea;
import java.sql.Connection;
import java.time.MonthDay;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WeekView extends KeyInfo //change inh. if needed
{
	public static void start (String[] args) { launch(args); }
	
	public void Week(Stage stage, Pane ribbonPane, HBox weekdays, Button dayButton, Button weekButton, Button monthButton, Text weekNum,
			         HBox dwm, Connection connection) throws Exception
	{
		//PNGs should be white
		
		//stage.setFullScreen(true);
		//stage.setResizable(true); //May change this
		//stage.getIcons().add(icon);
		stage.setTitle("Notendar | Week View");
				
		Pane root = new Pane();
		root.setStyle("-fx-background-color: transparent;");
		    	
    	stage.centerOnScreen();
    	//Size too big to have top-right buttons
    	Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight(), linear);
	   	scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
		
		GridPane weekbox = new GridPane();
		for (int day = 0; day < 7; day++) {
			Button dateclk = new Button(); //In Button(), write the dates
        	dateclk.setAlignment(Pos.TOP_CENTER);
        	dateclk.setId("dateclk");
        	dateclk.setStyle("-fx-pref-height: 550px");
        	
            weekbox.add(dateclk, day, 0);
		}
		
		VBox aWeek = new VBox(5);
    	aWeek.setStyle("-fx-background-color: transparent;");
    	aWeek.getChildren().addAll(weekdays, weekbox);
    	Pane aWeekPane = new Pane(aWeek);
    	aWeekPane.setLayoutX(200);
    	aWeekPane.setLayoutY(200);
    	
    	monthButton.setDisable(false);
    	weekButton.setDisable(true);
    	dayButton.setDisable(false);
    	weekNum.setText("?th Week, Mar 2025"); //Update this
    	dwm.setLayoutX(350.0);
		
		root.getChildren().addAll(ribbonPane, aWeekPane);
		stage.setScene(scene);
        stage.show();
	}
}
