package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class KeyInfo extends Application {
	static protected Image icon = new Image("CalendarIcon.jpg");
	//static protected Image menu = new Image("");
	static protected Stop[] stops = new Stop[] { new Stop(0, Color.rgb(4,68,41)), new Stop(0.25, Color.rgb(4,54,48)), new Stop(0.5, Color.rgb(4,40,55)), new Stop(0.75, Color.rgb(4,26,62)), new Stop(1, Color.rgb(4,12,69))};
    static protected LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops); //Enter more stops
	static String[] weeks = new String[7];
	static protected int firstDayOfWeek=0; //Saturday
	
	static protected String styleRegularLabel = "-fx-font-family: Onest; -fx-font-size: 20px; -fx-text-fill: white;";
	static protected String styleButtonLabel  = "-fx-background-insets: 0;"
												+ "-fx-text-fill: white; "
												+ "-fx-padding: 10 10 10 10; "
												+ "-fx-cursor: pointer; "
												+ "-fx-alignment: center; "
												+ "-fx-font-family: Onest; "
												+ "-fx-border-width: 1px; ";
	static protected String styleMainHover 	  = "-fx-background-color: linear-gradient(to right, #436642, #4E4F83); /*Lighter green on hover*/";
	static protected String styleMainPressed = "-fx-background-color: linear-gradient(to right, #436642, #4E4F83);\r\n"
			+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n"
			+ "    -fx-text-fill: #50CAC1;";
	static protected String sBL_2 = styleButtonLabel
			+ "-fx-background-color: transparent;"
			+ "-fx-background-radius: 3.75;  /*Rounded corners*/"
			+ "-fx-font-size: 16px;"
			+ "-fx-border-radius: 3.75;"
			+ "-fx-border-color: white;";

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
	public void Month(Stage stage) throws Exception {
		// TODO Auto-generated method stub
	}
}
