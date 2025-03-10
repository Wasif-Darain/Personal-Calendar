package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.TextStyle;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DayView extends KeyInfo {
	public static void start (String[] args) { launch(args); } 
	Parent root;
	static Stage stage;
	
	public void Day(Stage ds) throws Exception
	{
		stage = ds;
		try (Connection connection = DBConnect.getDataSource().getConnection()) {
            System.out.println("Connection from DayView established successfully.");
            try {
    				root = FXMLLoader.load(getClass().getResource("/DayView.fxml"));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				System.out.println("Failed to load Day screen.");
    			}
    	    	Scene scene = new Scene(root, 1500, 800);
    	    	stage.getIcons().add(icon);
    	    	stage.setTitle("Notendar | Day View");
    	    	scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    	    	
    	    	Button prev = (Button) root.lookup("#d_prev");
    	    	prev.setOnAction(e -> {
    	    		thisDate = thisDate.minusDays(1);
    		    	updateCalendar();
    	    	});
    	    	Button next = (Button) root.lookup("#d_next");
    	    	next.setOnAction(e -> {
    		    	thisDate = thisDate.plusDays(1);
    		    	updateCalendar();
    	    	});
    	    	
    	    	updateCalendar();
    	    	
    	    	stage.setScene(scene);
    	    	stage.show();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	private void updateNameTxt()
	{
		String monthName = currentDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String mnShort = monthName.substring(0, 3);
	    String dayMonthYr = thisDate.toString() + " " + mnShort + " " + currentDate.getYear();
	    Text mn = (Text) root.lookup("#d_name");
	    mn.setText(dayMonthYr);
	}
	
	private void updateCalendar()
	{
		//Do sth to clear existing grid characs.
		updateNameTxt();
		
		GridPane gridPane = (GridPane) root.lookup("#d_day");
		//show only
		Button button = new Button(thisDate.toString());
    	button.setId("dateclk");
    	gridPane.add(button, 0, 0);
	}
	public void switchToMonth(ActionEvent event) throws IOException {
	  MonthView monthView= new MonthView();
  	  try {
		monthView.Month(stage);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	
	public void switchToWeek(ActionEvent event) throws IOException {
		  WeekView weekView= new WeekView();
	  	  try {
			weekView.Week(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	
}
