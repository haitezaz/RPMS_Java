package backend;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML private Label dateTimeLabel;
    @FXML private Label userLabel;

    // Statistics labels
    @FXML private Label totalPatientsLabel;
    @FXML private Label totalDoctorsLabel;
    @FXML private Label activeSessionsLabel;
    @FXML private Label pendingApprovalsLabel;

    // Charts
    @FXML private PieChart userDistributionChart;
    @FXML private BarChart<String, Number> activityChart;

    // Table views and list views will be empty initially
    @FXML private TableView<?> usersTable;
    @FXML private ListView<String> systemLogsView;
    @FXML private TableView<?> reportsTable;

    private Timeline clock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeClock();
        initializeEmptyComponents();
    }

    private void initializeClock() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            dateTimeLabel.setText("Current Date and Time (UTC - YYYY-MM-DD HH:MM:SS formatted): " +
                    now.format(formatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        userLabel.setText("Current User's Login: Abd-alHannan");
    }

    private void initializeEmptyComponents() {
        // Initialize statistics with empty values
        totalPatientsLabel.setText("0");
        totalDoctorsLabel.setText("0");
        activeSessionsLabel.setText("0");
        pendingApprovalsLabel.setText("0");

        // Clear any existing chart data
        userDistributionChart.getData().clear();
        activityChart.getData().clear();
    }

    // Navigation handlers
    @FXML
    private void handleDashboard() {
        showAlert("Navigation", "Dashboard");
    }

    @FXML
    private void handleUserManagement() {
        showAlert("Navigation", "User Management");
    }

    @FXML
    private void handleSystemLogs() {
        showAlert("Navigation", "System Logs");
    }

    @FXML
    private void handleReports() {
        showAlert("Navigation", "Reports");
    }

    @FXML
    private void handleSettings() {
        showAlert("Navigation", "Settings");
    }

    // Action handlers
    @FXML
    private void handleSearch() {
        showAlert("Search", "Searching...");
    }

    @FXML
    private void handleAddUser() {
        showAlert("Users", "Add New User");
    }

    @FXML
    private void handleAllLogs() {
        showAlert("Logs", "Show All Logs");
    }

    @FXML
    private void handleErrorLogs() {
        showAlert("Logs", "Show Error Logs");
    }

    @FXML
    private void handleActivityLogs() {
        showAlert("Logs", "Show Activity Logs");
    }

    @FXML
    private void handleExportLogs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Logs");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        fileChooser.showSaveDialog(null);
    }

    @FXML
    private void handleUserActivityReport() {
        showAlert("Reports", "Generate User Activity Report");
    }

    @FXML
    private void handleSystemUsageReport() {
        showAlert("Reports", "Generate System Usage Report");
    }

    @FXML
    private void handleErrorReport() {
        showAlert("Reports", "Generate Error Report");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void stop() {
        if (clock != null) {
            clock.stop();
        }
    }
}
