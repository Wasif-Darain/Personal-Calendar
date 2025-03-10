package application;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUp extends KeyInfo
{
	@Override
	public void start(Stage arg0) throws Exception { }
	
	Parent root = null;
	static Stage stage;
	Scene scene;
	
	protected void SignupPage(Stage sstage) {
		stage = sstage;
		try {
			root = FXMLLoader.load(getClass().getResource("/SignUpScreen.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root, 600, 600);
		stage.setTitle("Create An Account");
		
		TextField pwField = (TextField) root.lookup("#s_p");
		TextField confPwField = (TextField) root.lookup("#s_cp");
		TextField nmField = (TextField) root.lookup("#s_nm");
		TextField emailField = (TextField) root.lookup("#s_em");
		Button suButton = (Button) root.lookup("#s_ca");
		Text t = (Text) root.lookup("#s_status");
		suButton.setOnAction(event -> {
  			String p1 = pwField.getText(), p2 = confPwField.getText();
  			if(!(p1.equals(p2))) {
  				t.setText("Passwords don't match. Try again.");
  				pwField.clear();
  				confPwField.clear();
  			}
  			else {
  				DBConnect.saveUser(nmField.getText(), pwField.getText(), emailField.getText(), 0); //"0" for now
  				
  				t.setText("Account creation succesful! Generating calendar...");
  				DBConnect.setLoggedIn();
  				//Might add some pause transition
  	            try {
  					MonthView monthView = new MonthView();
  					monthView.Month(stage);
  				} catch (Exception e1) {
  					e1.printStackTrace(); //Write more meaningful error messages
  				}
  			}
		});
		
    	stage.getIcons().add(KeyInfo.icon);
        stage.setScene(scene);
        stage.show();
	}
	
	public void switchToMonthView(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("/?.fxml")); //Give name
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
}
