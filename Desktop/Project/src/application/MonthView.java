/*Assumptions: Feb 2025*/  

package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MonthView extends KeyInfo
{
	public static void Main (String[] args) { launch(args); } 
	
	@Override
	public void start(Stage stage) throws Exception //Later, call it from Main.java
	{
		stage.setFullScreen(true);
		stage.setResizable(false); //For now
		stage.getIcons().add(icon);
		stage.setTitle("Notendar | Month View");
		
		StackPane root = new StackPane();
    	root.setStyle("-fx-background-color: transparent;");
    	
    	Scene scene = new Scene(root, linear);
		
    	HBox weekdays = new HBox(0);
    	weekdays.setStyle("-fx-background-color: transparent;");
    	for(int i=0; i<7; i++)
    	{
    		Label lbl = new Label(getDayOfWeek(i, firstDayOfWeek).toUpperCase());
    		Font lblfont = Font.font("Onest", FontWeight.BLACK, 22);

            lbl.setStyle("-fx-text-fill: white;");
    		lbl.setAlignment(Pos.CENTER);
    		lbl.setFont(lblfont);
    		lbl.setPrefWidth(185); //Same as rect1
    		weekdays.getChildren().add(lbl);
    	}
    	Button clkDyOfWk = new Button();
    	clkDyOfWk.setGraphic(weekdays);
    	
    	scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    	
    	
    	int countOfDays = 1; //For Feb 2025
    	GridPane monthbox = new GridPane();
    	for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 7; col++)
            {
            	Button dateclk;
                if(countOfDays<=28) { dateclk = new Button(Integer.toString(countOfDays)); }
                else dateclk = new Button();
            	dateclk.setAlignment(Pos.TOP_RIGHT);
            	dateclk.setId("dateclk");
            	countOfDays++;
            	
                monthbox.add(dateclk, col, row);
            }
    	}
    	
    	VBox aMonth = new VBox(5);
    	aMonth.setStyle("-fx-background-color: transparent;");
    	aMonth.getChildren().addAll(weekdays, monthbox);
    	aMonth.setAlignment(Pos.TOP_RIGHT);
    	
    	root.getChildren().addAll(aMonth);
    	root.setLayoutX(200);
    	root.setLayoutY(200);
        stage.setScene(scene);
        stage.show();
    }
	
}
