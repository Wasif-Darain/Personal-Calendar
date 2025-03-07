package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect extends KeyInfo {
	protected static String DB_URL = "jdbc:mysql://localhost:3306/d1";
	protected static String DB_USERNAME = "Wasif";
	protected static String DB_PASSWORD = "abcd";
	
	protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
	
	protected void saveUser(String username, String password, String emailaddress, int fdow) {
        String query = "INSERT INTO users (Name, Password, EmailAddress, FirstDayOfWeek) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, emailaddress);
            preparedStatement.setInt(4, 0); //Change it
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	protected boolean isLoggedIn() {
        String query = "SELECT IsLoggedIn FROM LoginStatus WHERE ID = 1";
        boolean isLoggedIn = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                isLoggedIn = resultSet.getBoolean("IsLoggedIn");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isLoggedIn;
    }
	
	private void updateLoginStatus(boolean isLoggedIn) {
        String query = "UPDATE LoginStatus SET IsLoggedIn = ? WHERE ID = 1";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, isLoggedIn);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	protected void setLoggedOut() {
        updateLoginStatus(false);
    }

    // Method to set the login status to true
    protected void setLoggedIn() {
        updateLoginStatus(true);
    }
}
