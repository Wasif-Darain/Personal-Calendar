package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Login extends MonthView {
	
	@Override
	public void start(Stage arg0) throws Exception { }

	protected static void LoginPage(Stage liStage, double sceneW, double sceneH) {
		liStage.setTitle("Login");
		
		StackPane root=new StackPane();
    	root.setStyle("-fx-background-color: transparent;");
    	
    	Scene scene=new Scene(root, sceneW, sceneH , linear);
		
		Label usernameLabel = new Label("Username:");
		usernameLabel.setStyle("-fx-text-fill: white;");
		Label passwordLabel = new Label("Password");
		passwordLabel.setStyle("-fx-text-fill: white;");
		
		TextField unField = new TextField();
		unField.setMaxWidth(200);
		PasswordField pwField = new PasswordField();
		pwField.setMaxWidth(200);
		
		Label loginResultLabel = new Label();
		loginResultLabel.setStyle("-fx-text-fill: white;");
		
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
		
		VBox form = new VBox(10);
        form.getChildren().addAll(usernameLabel, unField, passwordLabel, pwField, loginButton, loginResultLabel);
        form.setAlignment(Pos.CENTER);
        
        root.getChildren().add(form);
        liStage.getIcons().add(icon);
        liStage.setScene(scene);
        liStage.show();
        
		//setLoggedIn(true);
	}

}
