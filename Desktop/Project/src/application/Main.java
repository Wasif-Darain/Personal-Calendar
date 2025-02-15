package application;

import javafx.application.Application;  
import javafx.event.ActionEvent;  
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;  
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;  
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

public class Main extends Login {  
  
    @Override  
    public void start(Stage primaryStage) throws Exception { 
    	
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
    	
    	if(!isLoggedIn())
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
        	
        	logIn.setOnAction(new EventHandler<ActionEvent>() {  
                @Override
                public void handle(ActionEvent arg0) { 
                    logIn.setOnAction(event->Login.LoginPage(primaryStage, scene.getWidth(), scene.getHeight()));
                }  
            });  
        	
        	Button signUp = new Button("Sign Up");
        	logIn.getStyleClass().add("button");
        	
        	logSignButtons.getChildren().addAll(logIn, signUp);
            logSignButtons.setAlignment(Pos.BOTTOM_CENTER);
            
        	vbMessage.setAlignment(Pos.TOP_CENTER);
        	vbMessage.getChildren().addAll(notLoggedIn, logSignButtons);
    	}
    	else
    	{
    		openNewAcc.setTextAlignment(TextAlignment.CENTER);
    		openNewAcc.setFill(Color.WHITE);
    		openNewAcc.setFont(Font.font("Onest", FontWeight.NORMAL, FontPosture.REGULAR, 25));
    		openNewAcc.setBoundsType(TextBoundsType.VISUAL);
        	vb3.setAlignment(Pos.TOP_CENTER);
        	vb3.getChildren().add(openNewAcc);
        	VBox.setMargin(openNewAcc, new Insets(230, 0, 0, 0));
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
    	root.getStylesheets().add("Style.css");
    	
    	primaryStage.show();

    }  
    
    public static void main (String[] args)  
    {  
        launch(args);  
    }  
}