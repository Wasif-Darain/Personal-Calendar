package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class KeyInfo extends Application {
	//Later, store some of these in the database
	protected static final String ACTUAL_USERNAME = "adm";
	protected static final String ACTUAL_PASSWORD = "123";
	
	static protected Image icon = new Image("CalendarIcon.jpg");
	//static protected Image menu = new Image("");
	static protected Stop[] stops = new Stop[] { new Stop(0, Color.rgb(4,68,41)), new Stop(1, Color.rgb(4,12,69))}; //green to blue
    static protected LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops); //Enter more stops

	static String[] weeks = new String[7];
	static protected int firstDayOfWeek=0; //Saturday
	protected static boolean isLoggedIn = false;

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    protected static void setLoggedIn(boolean status) {
        isLoggedIn = status;
    }
	
	protected KeyInfo()
	{
		weeks[0] = "Saturday";
		weeks[1] = "Sunday";
		weeks[2] = "Monday";
		weeks[3] = "Tuesday";
		weeks[4] = "Wednesday";
		weeks[5] = "Thursday";
		weeks[6] = "Friday";
	}
	
	public static String getDayOfWeek(int num, int firstDay) { firstDayOfWeek=firstDay; return weeks[(num%7)+firstDay]; }

	@Override
	public void start(Stage arg0) throws Exception {}
	//public void start(Stage primaryStage) throws Exception { } //from LoginSession
}
