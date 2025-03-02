package application;

import java.awt.TextArea;
import java.awt.geom.Rectangle2D;

import com.google.protobuf.UnsafeByteOperations;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MonthView extends KeyInfo
{
	public static void start (String[] args) { launch(args); } 
	
	public Button createButton(double maxW, double maxH)
	{
		Button button = new Button();
		button.setMaxSize(maxW, maxH);
		return button;
	}
	
	public AnchorPane createAP(Button b, double topA, double leftA)
	{
		AnchorPane ap = new AnchorPane(b);
		ap.setTopAnchor(b, topA);
		ap.setLeftAnchor(b, leftA);
		return ap;
	}
	
	public void Month(Stage stage, double sceneW, double sceneH) throws Exception
	{
		//PNGs should be white
		
		//stage.setFullScreen(true);
		stage.setResizable(true); //May change this
		stage.getIcons().add(icon);
		stage.setTitle("Notendar | Month View");
		
		Pane root = new Pane();
    	root.setStyle("-fx-background-color: transparent;");
    	
    	stage.centerOnScreen();
    	//Size too big to have top-right buttons
    	Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight(), linear);
    	scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    	
    	Rectangle ribbonRect = new Rectangle(scene.getWidth(), 100);
        ribbonRect.setFill(Color.WHITE);
        ribbonRect.setOpacity(0.075);
    	
        HBox ribbonButtons = new HBox(15);
        ribbonButtons.setAlignment(Pos.TOP_CENTER);
        //ribbonButtons.setPrefSize(500, 500);

        Button menuButton = createButton(17.5, 17.5);
        Image icon = new Image("MenuIcon.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(30);
        iconView.setFitWidth(30);
        menuButton.setGraphic(iconView);
        AnchorPane mbPane = createAP(menuButton, 23.75, 23.75);
        
        Text cal = new Text("Calendar");
    	cal.setFill(Color.WHITE);
    	cal.setFont(Font.font("Onest", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 35));
    	cal.setBoundsType(TextBoundsType.VISUAL);
    	AnchorPane calPane = new AnchorPane(cal);
        calPane.setTopAnchor(cal, ribbonRect.getHeight()/2-15);
        calPane.setLeftAnchor(cal, 50.0);
        
        Button prev = createButton(17.5, 17.5);
	        Image iconp = new Image("Prev.png");
	        ImageView iconpv = new ImageView(iconp);
	        iconpv.setFitHeight(30);
	        iconpv.setFitWidth(30);
	        prev.setGraphic(iconpv);
	        AnchorPane prevPane = createAP(prev, 23.75, 65.0);
        Button next = createButton(17.5, 17.5);
	        Image iconn = new Image("Next.png");
	        ImageView iconnx = new ImageView(iconn);
	        iconnx.setFitHeight(30);
	        iconnx.setFitWidth(30);
	        next.setGraphic(iconnx);
	        AnchorPane nxtPane = createAP(next, 23.75, -12.0);
	    
	    Text monthYr = new Text("Mar 2025"); //Update
	   	monthYr.setFill(Color.WHITE);
	   	monthYr.setFont(Font.font("Onest", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 35));
	   	monthYr.setBoundsType(TextBoundsType.VISUAL);
	   	AnchorPane myPane = new AnchorPane(monthYr);
        myPane.setTopAnchor(monthYr, ribbonRect.getHeight()/2-15);
        myPane.setLeftAnchor(monthYr, 50.0);
        
        Button dayButton = new Button("Day");
		dayButton.setStyle("-fx-pref-height: 55px");
        Button weekButton = new Button("Week");
        weekButton.setStyle("-fx-pref-height: 55px");
        Button monthButton = new Button("Month");
        monthButton.setStyle("-fx-pref-height: 55px");
        monthButton.setDisable(true);
        HBox dwm = new HBox(0);
        dwm.getChildren().addAll(dayButton, weekButton, monthButton);
        //dwm.setAlignment(Pos.CENTER_RIGHT);
        Pane dwmPn = new Pane(dwm);
        dwm.setLayoutX(525.0);
        dwm.setLayoutY(22.75);
        
        ribbonButtons.getChildren().addAll(mbPane, calPane, prevPane, nxtPane, myPane, dwmPn);
        Pane ribbonPane = new Pane(ribbonRect, ribbonButtons);
        ribbonPane.setLayoutY(15);
        
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
    	Pane aMonthPane = new Pane(aMonth);
    	aMonthPane.setLayoutX(200);
    	aMonthPane.setLayoutY(200);
    	
    	root.getChildren().addAll(ribbonPane, aMonthPane);
        stage.setScene(scene);
        stage.show();
    }
	
}
