package application;

import java.sql.Connection;

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
import javafx.util.Duration;

public class SignUp extends DBConnect
{
	@Override
	public void start(Stage arg0) throws Exception { }
	
	protected void SignupPage(Stage suStage, double sceneW, double sceneH, Connection connection) {
		suStage.setTitle("Getting Started");
		StackPane root=new StackPane();
    	root.setStyle("-fx-background-color: transparent;");
    	Scene scene=new Scene(root, sceneW, sceneH, linear);
    	
    	//Fit sizes of text field
    	Label nameLabel = new Label("Enter Name:");
   		nameLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
 		Label passwordLabel = new Label("Set Password:");
   		passwordLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
   		Label confPwLabel = new Label("Confirm Password:");
   		confPwLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
   		Label emailLabel = new Label("Enter Email Address:");
   		emailLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
   		Label fdowLabel = new Label("Enter First Day of Week:");
   		fdowLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
 		TextField nmField = new TextField();
    	nmField.setMaxWidth(500);
    	nmField.setMinHeight(30);
    	PasswordField pwField = new PasswordField();
    	pwField.setMaxWidth(500);
    	pwField.setMinHeight(30);
    	PasswordField confPwField = new PasswordField();
    	confPwField.setMaxWidth(500);
    	confPwField.setMinHeight(30);
    	TextField emailField = new TextField();
    	emailField.setMaxWidth(500);
    	emailField.setMinHeight(30);
    	//Handle properly
    	
    	HBox name = new HBox(10);
    	name.getChildren().addAll(nameLabel, nmField);
		name.setAlignment(Pos.CENTER);
		HBox pass = new HBox(10);
		pass.getChildren().addAll(passwordLabel, pwField);
		pass.setAlignment(Pos.CENTER);
		HBox c_pass = new HBox(10);
		c_pass.getChildren().addAll(confPwLabel, confPwField);
		c_pass.setAlignment(Pos.CENTER);
		HBox em = new HBox(10);
		em.getChildren().addAll(emailLabel, emailField);
		em.setAlignment(Pos.CENTER);
    	
    	Rectangle suRectangle = new Rectangle(500,700,Color.TRANSPARENT);
		suRectangle.setArcWidth(75);
		suRectangle.setArcHeight(75);
		suRectangle.setStroke(Color.WHITE);
		suRectangle.setStrokeWidth(1);
    	
    	Label suLabel = new Label();
		suLabel.setStyle(styleRegularLabel + "-fx-background-color: transparent;"
				+ "-fx-font-size: 16px;");
    	
    	Button suButton = new Button("Create Account");
    	suButton.setStyle(sBL_2 + "-fx-pref-width: 200px; -fx-pref-height: 35px");
    	suButton.setPrefWidth(400);
		//These don't work w/ mouse
		suButton.setOnMouseEntered(e->suButton.setStyle(styleMainHover));
		suButton.setOnMouseExited(e->suButton.setStyle(sBL_2 + "-fx-pref-width: 200px; -fx-pref-height: 35px"));
		suButton.setOnMouseClicked(e->suButton.setStyle(styleMainPressed));
		HBox suB = new HBox();
		suB.getChildren().addAll(suButton);
		suB.setAlignment(Pos.CENTER);
		//Center everything
		
		VBox allVBox = new VBox(20);
        allVBox.getChildren().addAll(name, pass, c_pass, em, suButton, suLabel);
        allVBox.setAlignment(Pos.CENTER);
    	
    	root.getChildren().addAll(allVBox, suRectangle);
        suStage.getIcons().add(icon);
        suStage.setScene(scene);
        suStage.show();
        
      suButton.setOnAction(event -> {
      			String p1 = pwField.getText(), p2 = confPwField.getText();
      			if(!(p1.equals(p2))) {
      				suLabel.setText("Passwords don't match. Try again.");
      				pwField.clear();
      				confPwField.clear();
      			}
      			else {
      				DBConnect db = new DBConnect();
      				db.saveUser(nmField.getText(), pwField.getText(), emailField.getText(), 0); //"0" for now
      				
      				suLabel.setText("Account creation succesful! Generating calendar...");
      				db.setLoggedIn();
      				//Might add some pause transition
      	            try {
      					MonthView monthView = new MonthView();
      					monthView.Month(suStage, sceneW, sceneH, connection);
      				} catch (Exception e1) {
      					e1.printStackTrace(); //Write more meaningful error messages
      				}
      			}
      	});
	}
}
