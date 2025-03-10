package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage arg0) throws Exception {
    }

    Parent root = null;
    static Stage stage;
    Scene scene;

    protected void LoginPage(Stage lstage) {
        stage = lstage;

        try {
            root = FXMLLoader.load(getClass().getResource("/LogInScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Login screen.");
            return;
        }
        scene = new Scene(root, 600, 600);
        stage.setTitle("Log In to Notendar");

        Button loginButton = (Button) root.lookup("#l_li");
        Text loginResultLabel = (Text) root.lookup("#l_lrl");

        loginButton.setOnAction(e -> {
            TextField emField = (TextField) root.lookup("#l_e");
            TextField pwField = (TextField) root.lookup("#l_p");
            String enteredEA = emField.getText();
            String enteredPw = pwField.getText();

            if (enteredEA.isEmpty() || enteredPw.isEmpty()) {
                loginResultLabel.setText("Please fill in all fields.");
                return;
            }

            String qry = "SELECT * FROM usercredentials WHERE Password = ? AND EmailAddress = ?";

            try (Connection connection = DBConnect.getDataSource().getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(qry)) {

                pstmt.setString(1, enteredPw);
                pstmt.setString(2, enteredEA);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int userCred_ID = rs.getInt("ID");
                        DBConnect.setLoggedIn();
                        loginResultLabel.setText("Login Successful!");

                        String updateLoginStatusTable = "UPDATE loginstatus SET UserCred_ID = ? WHERE ID = 1";
                        try (PreparedStatement pstmtUpdt = connection.prepareStatement(updateLoginStatusTable)) {
                            pstmtUpdt.setInt(1, userCred_ID);
                            pstmtUpdt.executeUpdate();
                        }
                        MonthView monthView = new MonthView();
                        monthView.Month(stage);
                    } else {
                        loginResultLabel.setText("Wrong credentials. Try again.");
                    }
                }
            } catch (SQLException s) {
                s.printStackTrace();
                loginResultLabel.setText("Database error. Please try again.");
            } catch (Exception ex) {
                ex.printStackTrace();
                loginResultLabel.setText("An unexpected error occurred.");
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public void switchToMonthView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/MonthView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}