package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Month;
import java.time.YearMonth;
import java.time.LocalDate;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;  
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application
{  
	Parent root = null;
	static Stage stage;
	Scene scene;
	
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		try (Connection connection = DBConnect.getDataSource().getConnection()) {
            System.out.println("Connection from Main established successfully.");
            boolean ili = DBConnect.isLoggedIn();
    		if(!ili) { //Load up Welcome screen
    		
    			try {
    				root = FXMLLoader.load(getClass().getResource("/New.fxml"));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				System.out.println("Failed to load Main screen.");
    			}
    	    	scene = new Scene(root, 600, 600);
    	    	primaryStage.setTitle("Notendar | Welcome");
    	    	primaryStage.setScene(scene);
    	    	primaryStage.show();
    		}
    		else
    		{
    			MonthView monthView = new MonthView();
    			monthView.Month(stage);
    		}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
    
    public static void main (String[] args)  
    {  
        launch(args);
    }
    
    public void switchToLogInScreen(ActionEvent event) throws IOException {
    	Login login = new Login();
    	login.LoginPage(stage);
    }
    
    public void switchToSignUpScreen(ActionEvent event) throws IOException {
  	  SignUp signUp = new SignUp();
  	  signUp.SignupPage(stage);
    }
}