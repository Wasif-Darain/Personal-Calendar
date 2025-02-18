package application;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUp extends MonthView {

	@Override
	public void start(Stage arg0) throws Exception { }
	
	protected static void SignupPage(Stage suStage, double sceneW, double sceneH) {
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
    	
    	Label suLabel = new Label();
		suLabel.setStyle(styleRegularLabel + "-fx-background-color: transparent;"
				+ "-fx-font-size: 16px;");
    	
    	Button suButton = new Button("Create Account");
    	suButton.setStyle(sBL_2);
		//These don't work w/ mouse
		suButton.setOnMouseEntered(e->suButton.setStyle(styleMainHover));
		suButton.setOnMouseExited(e->suButton.setStyle(sBL_2));
		suButton.setOnMouseClicked(e->suButton.setStyle(styleMainPressed));
		HBox suB = new HBox();
		suB.getChildren().addAll(suButton);
		suB.setAlignment(Pos.CENTER);
		//Center everything
		//what to do when Login is pressed
		suButton.setOnAction(event -> {
			String p1 = nmField.getText(), p2 = pwField.getText();
			if(!p1.equals(p2)) {
				suLabel.setText("Passwords don't match. Try again.");
				pwField.setOnAction(e -> pwField.clear());
				confPwField.setOnAction(e -> confPwField.clear());
			}
			else {
				suLabel.setText("Account creation succesful! Generating calendar...");
				//isLoggedIn=true;
				PauseTransition pause = new PauseTransition(Duration.millis(250));
	            pause.play();
	            try {
					MonthView monthView = new MonthView();
					monthView.Month(suStage);
				} catch (Exception e1) { //If fails to open that scene
					// TODO Auto-generated catch block
					e1.printStackTrace(); //
				}
			}
		});
    	
    	//root.getChildren().addAll(form, loginRectangle);
        suStage.getIcons().add(icon);
        suStage.setScene(scene);
        suStage.show();
	}
}
