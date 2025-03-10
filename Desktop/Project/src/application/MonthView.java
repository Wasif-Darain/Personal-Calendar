package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MonthView extends KeyInfo {
    public static void start(String[] args) {
        launch(args);
    }

    Parent root;
    static Stage stage;
    private YearMonth currentYearMonth = YearMonth.now(); // Track the current year and month

    public void Month(Stage mstage) throws Exception {
        stage = mstage;
        try (Connection connection = DBConnect.getDataSource().getConnection()) {
            System.out.println("Connection from MonthView established successfully.");
            try {
                root = FXMLLoader.load(getClass().getResource("/MonthView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load Month screen.");
            }
            Scene scene = new Scene(root, 1500, 800);
            stage.setTitle("Notendar | Month View");
            // scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

            Button prev = (Button) root.lookup("#m_prev");
            prev.setOnAction(e -> {
                currentYearMonth = currentYearMonth.minusMonths(1);
                updateCalendar();
            });
            Button next = (Button) root.lookup("#m_next");
            next.setOnAction(e -> {
                currentYearMonth = currentYearMonth.plusMonths(1);
                updateCalendar();
            });

            updateCalendar();

            stage.setScene(scene);
            stage.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateNameTxt() {
        String monthName = currentYearMonth.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String mnShort = monthName.substring(0, 3);
        String monthYr = mnShort + " " + currentYearMonth.getYear();
        Text mn = (Text) root.lookup("#m_name");
        mn.setText(monthYr);
    }

    private void updateCalendar() {
        updateNameTxt();

        GridPane days = (GridPane) root.lookup("#m_days");
        days.getChildren().clear(); // Clear the existing children

        LocalDate firstOfMonth = currentYearMonth.atDay(1);
        DayOfWeek starting = firstOfMonth.getDayOfWeek();
        int dayOfWeek = starting.getValue();
        int daysInMonth = currentYearMonth.lengthOfMonth();

        int col = dayOfWeek - 1;
        int row = 0;

        for (int day = 1; day <= daysInMonth; day++) {
            Button aDay = new Button(String.valueOf(day));
            aDay.setId("dateclk");
            days.add(aDay, col, row);

            col++;
            if (col > 6) {
                col = 0;
                row++;
            }

            aDay.setOnAction(e ->{
                try {
                    switchToDay(null);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    public void switchToDay(ActionEvent event) throws IOException {
        DayView dayView = new DayView();
        try {
            dayView.Day(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToWeek(ActionEvent event) throws IOException {
        WeekView weekView = new WeekView();
        try {
            weekView.Week(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}