package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class Login extends KeyInfo {
	
	@Override
	public void start(Stage arg0) throws Exception { }

	protected void LoginPage(Stage liStage, double sceneW, double sceneH, Connection connection, DBConnect dbConnect) {
		liStage.setTitle("Log In");
		
		StackPane root=new StackPane();
    	root.setStyle("-fx-background-color: transparent;");
    	
    	Scene scene=new Scene(root, sceneW, sceneH, linear);
    	
    	//Fit sizes of text field
		Label emailLabel = new Label("Email:");
		emailLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
		Label passwordLabel = new Label("Password:");
		passwordLabel.setStyle(styleRegularLabel + "-fx-font-size: 16px;");
		TextField emField = new TextField();
		emField.setMaxWidth(500);
		emField.setMinHeight(30);
		PasswordField pwField = new PasswordField();
		pwField.setMaxWidth(500);
		pwField.setMinHeight(30);
		HBox email = new HBox(10);
		email.getChildren().addAll(emailLabel, emField);
		email.setAlignment(Pos.CENTER);
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
			String enteredEA = emField.getText();
			String enteredPw = pwField.getText();
			//Do something if Log In button pressed with any field empty
			String qry = "SELECT * FROM usercredentials WHERE EmailAddress = ? AND Password = ?";
			
			try (PreparedStatement pstmt = connection.prepareStatement(qry)) {

		        pstmt.setString(2, enteredEA);
		        pstmt.setString(1, enteredPw);

		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		        	dbConnect.setLoggedIn();
		            loginResultLabel.setText("Login Successful!");
		            try {
      					MonthView monthView = new MonthView();
      					monthView.Month(liStage, sceneW, sceneH, connection);
      				} catch (Exception e1) {
      					e1.printStackTrace(); //Write more meaningful error messages
      				}
		        } else {
		            loginResultLabel.setText("Wrong credentials. Try again.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        loginResultLabel.setText("Database error.");
		    }
		});
		
        //scene.getStylesheets().add(Login.class.getResource("Style.css").toExternalForm());
       
        VBox form = new VBox(20);
        form.getChildren().addAll(email, password, lb, loginResultLabel);
        form.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(form, loginRectangle);
        liStage.getIcons().add(icon);
        liStage.setScene(scene);
        liStage.show();
	}

}
