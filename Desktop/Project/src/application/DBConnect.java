package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnect extends KeyInfo {
	protected static String DB_URL = "jdbc:mysql://localhost:3306/d1";
	protected static String DB_USERNAME = "Wasif";
	protected static String DB_PASSWORD = "abcd";
	
	private static BasicDataSource dataSource;

	static {
	    try {
	        dataSource = new BasicDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl(DB_URL);
	        dataSource.setUsername(DB_USERNAME);
	        dataSource.setPassword(DB_PASSWORD);
	        dataSource.setMinIdle(5);
	        dataSource.setMaxIdle(10);
	        dataSource.setMaxTotal(20);
	        dataSource.setValidationQuery("SELECT 1"); //as an example
	        dataSource.setTestOnBorrow(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Could not connect to database.");
	    }
	}
	
	public static BasicDataSource getDataSource() {
        return dataSource;
    }
	
	public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
	
	protected static void saveUser(String username, String password, String emailaddress, int fdow) {
        String query = "INSERT INTO usercredentials (Name, Password, EmailAddress, FirstDayOfWeek) VALUES (?, ?, ?, ?)";

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
	
	static protected boolean isLoggedIn() {
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
	
	private static void updateLoginStatus(boolean isLoggedIn) {
        String query = "UPDATE LoginStatus SET IsLoggedIn = ? WHERE ID = 1";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, isLoggedIn);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	protected static void setLoggedOut() {
        updateLoginStatus(false);
    }

    // Method to set the login status to true
    protected static void setLoggedIn() {
        updateLoginStatus(true);
    }
}
