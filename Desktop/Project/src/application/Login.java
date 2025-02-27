package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Login extends SignUp {
	
	@Override
	public void start(Stage arg0) throws Exception { }

	protected static void LoginPage(Stage liStage, double sceneW, double sceneH) {
		liStage.setTitle("Log In");
		
		StackPane root=new StackPane();
    	root.setStyle("-fx-background-color: transparent;");
    	
    	Scene scene=new Scene(root, sceneW, sceneH, linear);
    	
    	//Fit sizes of text field
		Label usernameLabel = new Label("Username:");
		usernameLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
		Label passwordLabel = new Label("Password:");
		passwordLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
		TextField unField = new TextField();
		unField.setMaxWidth(500);
		unField.setMinHeight(30);
		PasswordField pwField = new PasswordField();
		pwField.setMaxWidth(500);
		pwField.setMinHeight(30);
		HBox username = new HBox(10);
		username.getChildren().addAll(usernameLabel, unField);
		username.setAlignment(Pos.CENTER);
		HBox password = new HBox(10);
		password.getChildren().addAll(passwordLabel, pwField);
		password.setAlignment(Pos.CENTER);
		
		Rectangle loginRectangle = new Rectangle(500,500,Color.TRANSPARENT);
		loginRectangle.setArcWidth(75);
		loginRectangle.setArcHeight(75);
		loginRectangle.setStroke(Color.WHITE);
		loginRectangle.setStrokeWidth(1);
		
		Label loginResultLabel = new Label();
		loginResultLabel.setStyle(styleRegularLabel + "-fx-background-color: transparent;"
				+ "-fx-font-size: 16px;");
		
		Button loginButton = new Button("Login");
		loginButton.setStyle(sBL_2 + "-fx-pref-width: 88px; -fx-pref-height: 35px");
		//These don't work w/ mouse
		loginButton.setOnMouseEntered(e->loginButton.setStyle(styleMainHover));
		loginButton.setOnMouseExited(e->loginButton.setStyle(sBL_2 + "-fx-pref-width: 88px; -fx-pref-height: 35px"));
		loginButton.setOnMouseClicked(e->loginButton.setStyle(styleMainPressed));
		HBox lb = new HBox();
		lb.getChildren().addAll(loginButton);
		lb.setAlignment(Pos.CENTER);
		//Center everything
		
		loginButton.setOnAction(event -> {
			String enteredUn = unField.getText();
			String enteredPw = pwField.getText();
			
			if(enteredUn.equals(ACTUAL_USERNAME) && enteredPw.equals(ACTUAL_PASSWORD))
				loginResultLabel.setText("Login Succesful!");
			else
				loginResultLabel.setText("Wrong credentials.");
			
			//Do something if Log In button pressed with any field empty
		});
		
        //scene.getStylesheets().add(Login.class.getResource("Style.css").toExternalForm());
       
        VBox form = new VBox(20);
        form.getChildren().addAll(username, password, lb, loginResultLabel);
        form.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(form, loginRectangle);
        liStage.getIcons().add(icon);
        liStage.setScene(scene);
        liStage.show();
        
		//setLoggedIn(true);
	}

}
