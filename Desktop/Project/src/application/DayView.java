package application;

import java.awt.Rectangle;
import java.awt.TextArea;
import java.sql.Connection;

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

public class DayView extends KeyInfo //change inh. if needed
{
	public static void start (String[] args) { launch(args); }
	
	public void Day(Stage stage, Pane ribbonPane, HBox weekdays, Button dayButton, Button weekButton, Button monthButton, Text weekNum,
			         HBox dwm, Connection connection) throws Exception
	{
		//PNGs should be white
		
		//stage.setFullScreen(true);
		//stage.setResizable(true); //May change this
		//stage.getIcons().add(icon);
		stage.setTitle("Notendar | Day View");
						
		Pane root = new Pane();
		root.setStyle("-fx-background-color: transparent;");
				    	
	   	stage.centerOnScreen();
		//Size too big to have top-right buttons
		Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight(), linear);
		scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
		
		GridPane dayGridPane = new GridPane();
		Button dayclk = new Button(); //In Button(), write the dates
        dayclk.setAlignment(Pos.TOP_CENTER);
        dayclk.setId("dateclk");
        dayclk.setStyle("-fx-pref-height: 550px; -fx-pref-width: 1,295px;");
        	
        dayGridPane.add(dayclk, 0, 0);
				
        VBox aDay = new VBox(5);
    	aDay.setStyle("-fx-background-color: transparent;");
    	aDay.getChildren().addAll(weekdays, dayGridPane);
    	Pane aDayPane = new Pane(aDay);
    	aDayPane.setLayoutX(200);
    	aDayPane.setLayoutY(200);
    	
    	monthButton.setDisable(false);
    	weekButton.setDisable(false);
    	dayButton.setDisable(true);
    	weekNum.setText("? Mar 2025"); //Update this
    	dwm.setLayoutX(350.0);
    	
    	root.getChildren().addAll(ribbonPane, aDayPane);
		stage.setScene(scene);
        stage.show();
	}
}
