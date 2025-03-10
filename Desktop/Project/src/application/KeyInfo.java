package application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class KeyInfo extends Application {
	protected static Image icon = new Image("CalendarIcon.jpg");
	protected Stop[] stops = new Stop[] { new Stop(0, Color.rgb(4,68,41)), new Stop(0.25, Color.rgb(4,54,48)), new Stop(0.5, Color.rgb(4,40,55)), new Stop(0.75, Color.rgb(4,26,62)), new Stop(1, Color.rgb(4,12,69))};
	protected LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops); //Enter more stops
	protected int firstDayOfWeek=0; //Saturday; later move to database
	private int[] arr = { 6,7,1,2,3,4,5 }; //for Sat
	protected static LocalDate currentDate = LocalDate.now();
	protected static LocalDate thisDate = currentDate;
	protected static YearMonth currentYearMonth=YearMonth.of(currentDate.getYear(), currentDate.getMonthValue());
	
	public String getDayOfWeekStr(int currDay) {
        return DayOfWeek.of(arr[currDay]).toString();
    }

    public int getDayOfWeekInt(int currDay) {
        return arr[currDay];
    }
	
    @Override
	public void start(Stage arg0) throws Exception {}
	public void Month(Stage stage) throws Exception {}
}
