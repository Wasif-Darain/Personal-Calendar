package application;

import java.sql.Connection;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;  
import javafx.scene.control.Button;
import javafx.stage.Stage;  
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

public class Main extends KeyInfo
{  
    @Override  
    public void start(Stage primaryStage) throws Exception { 
    	DBConnect dbConnect = new DBConnect();
    	Connection connection = dbConnect.getConnection();
    	boolean ili = dbConnect.isLoggedIn();
    	
    	StackPane root=new StackPane();
    	root.setStyle("-fx-background-color: transparent;");
        
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Notendar | Welcome");
        
        Scene scene=new Scene(root,800,770,linear);

        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        
    	VBox vb2 = new VBox();
    	HBox logSignButtons = new HBox(5);
    	VBox vbMessage = new VBox(20);
    	Text notLoggedIn = new Text("You're not logged in.");
    	Text openNewAcc = new Text("Open A New Account");
    	VBox vb3 = new VBox();
    	
    	if(!ili)
    	{
            notLoggedIn.setTextAlignment(TextAlignment.CENTER);
            notLoggedIn.setFill(Color.WHITE);
            notLoggedIn.setFont(Font.font("Onest", FontWeight.NORMAL, FontPosture.REGULAR, 25));
            notLoggedIn.setBoundsType(TextBoundsType.VISUAL);
        	vb2.setAlignment(Pos.TOP_CENTER);
        	vb2.getChildren().add(notLoggedIn);
        	VBox.setMargin(notLoggedIn, new Insets(547.5, 0, 0, 0));
        	
        	Button logIn = new Button("Log In");
        	logIn.getStyleClass().add("button");
        	
        	logIn.setOnAction(event -> 
        	{
        		Login login = new Login();
        		login.LoginPage(primaryStage, scene.getWidth(), scene.getHeight(), connection, dbConnect);
        	});
        	
        	Button signUpButton = new Button("Sign Up");
        	signUpButton.getStyleClass().add("button");
        	
        	signUpButton.setOnAction(event -> 
        	{
        		SignUp sUp = new SignUp();
        		sUp.SignupPage(primaryStage, firstDayOfWeek, firstDayOfWeek, connection);
        	});
        	
        	logSignButtons.getChildren().addAll(logIn, signUpButton);
            logSignButtons.setAlignment(Pos.BOTTOM_CENTER);
            
        	vbMessage.setAlignment(Pos.TOP_CENTER);
        	vbMessage.getChildren().addAll(notLoggedIn, logSignButtons);
    	}
    	else
    	{
    		//enter all attributes so that specific version is generated
    		MonthView mv=new MonthView();
    		mv.Month(primaryStage, 800, 770, connection);
    	}
    	
    	Text wlcm = new Text("Welcome");
        wlcm.setTextAlignment(TextAlignment.CENTER);
    	wlcm.setFill(Color.WHITE);
    	wlcm.setFont(Font.font("Onest", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 58));
    	wlcm.setBoundsType(TextBoundsType.VISUAL);
    	VBox vbWlcm = new VBox();
    	vbWlcm.setAlignment(Pos.TOP_CENTER);
    	vbWlcm.getChildren().add(wlcm);
    	VBox.setMargin(wlcm, new Insets(220, 0, 0, 0));
    	
    	root.getChildren().addAll(vbWlcm, vbMessage);
    	
    	primaryStage.show();

    }  
    
    public static void main (String[] args)  
    {  
        launch(args);
    }  
}