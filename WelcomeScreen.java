package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class WelcomeScreen extends Application {
	
	//mock passwords
	private static final String ACTUAL_USERNAME = "adm";
	private static final String ACTUAL_PASSWORD = "123";
	
	public static void main(String[] args) { launch(args); }
	
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Login");
		
		Label usernameLabel = new Label("Username:");
		Label passwordLabel = new Label("Password");
		
		TextField unField = new TextField();
		PasswordField pwField = new PasswordField();
		
		Label loginResultLabel = new Label();
		
		Button loginButton = new Button("Login");
		
		//what to do when Login is pressed
		loginButton.setOnAction(event -> {
			String enteredUn = unField.getText();
			String enteredPw = pwField.getText();
			
			if(enteredUn.equals(ACTUAL_USERNAME) && enteredPw.equals(ACTUAL_PASSWORD))
				loginResultLabel.setText("Login Succesful!");
			else
				loginResultLabel.setText("Wrong credentials.");
		});
		
		VBox root = new VBox(10);
        root.getChildren().addAll(usernameLabel, unField, passwordLabel, pwField, loginButton, loginResultLabel);
        
        Image icon = new Image("CalendarIcon.jpg");
		primaryStage.getIcons().add(icon);
        
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form App");
        primaryStage.show();
	}
}
