package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

public class WeekView extends KeyInfo {
	public static void start (String[] args) { launch(args); } 
	Parent root;
	Stage stage;
	protected LocalDate currWeekStart;
	
	public void Week(Stage wstage) throws Exception
	{
		stage = wstage;
		try (Connection connection = DBConnect.getDataSource().getConnection()) {
            System.out.println("Connection from WeekView established successfully.");
            try {
    				root = FXMLLoader.load(getClass().getResource("/WeekView.fxml"));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				System.out.println("Failed to load Week screen.");
    			}
    	    	Scene scene = new Scene(root, 1500, 800);
    	    	stage.getIcons().add(icon);
    	    	stage.setTitle("Notendar | Week View");
    	    	scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    	    	
    	    	currWeekStart = thisDate.with(DayOfWeek.MONDAY);
    	    	
    	    	//don't use current date after this
    	    	Button prev = (Button) root.lookup("#w_prev");
    	    	prev.setOnAction(e -> {
    	    		currWeekStart = currWeekStart.minusWeeks(1);
    		    	updateCalendar();
    	    	});
    	    	Button next = (Button) root.lookup("#w_next");
    	    	next.setOnAction(e -> {
    		    	currWeekStart = currWeekStart.plusWeeks(1);
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
	    String moYr = mnShort + " " + currentYearMonth.getYear();
	    Text mn = (Text) root.lookup("#w_name");
	    mn.setText(moYr);
	}
	
	private void updateCalendar()
	{
		//Do sth to clear existing grid characs.
		updateNameTxt();
		
		GridPane days = (GridPane) root.lookup("#w_days");
		for (int i = 0; i < 7; i++) {
            LocalDate currentDay = currWeekStart.plusDays(i); // Get the day for the current column
            Button dayButton = new Button(currentDay.toString());
            dayButton.setId("dateclk");
            days.add(dayButton, i, 0);

            dayButton.setOnAction(e -> {
                try {
					switchToDay(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            });
        }
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
	
	public void switchToDay(ActionEvent event) throws IOException {
		  DayView dv= new DayView();
	  	  try {
			dv.Day(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
}
