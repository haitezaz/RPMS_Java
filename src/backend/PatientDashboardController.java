package backend;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PatientDashboardController implements Initializable {
    @FXML private Label dateTimeLabel;
    @FXML private Label userLabel;
    @FXML private Label patientIdLabel;
    @FXML private Label patientNameLabel;
    @FXML private TextArea recentMedicalReport;
    @FXML private ListView<String> appointmentsList;
    @FXML private ListView<String> notificationsList;
    @FXML private LineChart<String, Number> vitalsChart;

    private Timeline clock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeClock();
        initializePatientInfo();
        loadMedicalReport();
        loadAppointments();
        loadNotifications();
        setupVitalsChart();
    }

    private void initializeClock() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            dateTimeLabel.setText("Current Date and Time (UTC - YYYY-MM-DD HH:MM:SS formatted): " +
                    now.format(formatter).toUpperCase());
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        userLabel.setText("Current User's Login: Abd-alHannan");
    }

    private void initializePatientInfo() {
        patientIdLabel.setText("PATIENT ID: P12345");
        patientNameLabel.setText("PATIENT NAME: John Doe");
    }

    private void loadMedicalReport() {
        recentMedicalReport.setText("""
            Latest Medical Report - Date: 2025-05-01
            
            Vitals:
            - Blood Pressure: 120/80 mmHg
            - Heart Rate: 75 bpm
            - Temperature: 98.6°F
            - Oxygen Level: 98%
            
            Doctor's Notes:
            Patient is showing good progress. Continue with prescribed medications.
            
            Medications:
            1. Medicine A - 1 tablet daily
            2. Medicine B - 2 tablets twice daily
            
            Next Appointment: May 15, 2025
            """);
    }

    private void loadAppointments() {
        appointmentsList.getItems().addAll(
                "09:00 AM - Dr. Smith - General Checkup",
                "02:30 PM - Dr. Johnson - Blood Test Results",
                "04:00 PM - Dr. Williams - Follow-up"
        );
    }

    private void loadNotifications() {
        notificationsList.getItems().addAll(
                "REMINDER: Take Medicine A at 2:00 PM",
                "ALERT: Blood pressure reading due in 1 hour",
                "UPDATE: Dr. Smith approved your appointment request",
                "REMINDER: Upload today's vital readings"
        );
    }

    private void setupVitalsChart() {
        // Create series for different vitals
        XYChart.Series<String, Number> heartRate = new XYChart.Series<>();
        heartRate.setName("Heart Rate");

        XYChart.Series<String, Number> bloodPressure = new XYChart.Series<>();
        bloodPressure.setName("Blood Pressure");

        XYChart.Series<String, Number> oxygenLevel = new XYChart.Series<>();
        oxygenLevel.setName("Oxygen Level");

        // Add sample data
        heartRate.getData().add(new XYChart.Data<>("8:00", 72));
        heartRate.getData().add(new XYChart.Data<>("10:00", 75));
        heartRate.getData().add(new XYChart.Data<>("12:00", 73));

        bloodPressure.getData().add(new XYChart.Data<>("8:00", 120));
        bloodPressure.getData().add(new XYChart.Data<>("10:00", 122));
        bloodPressure.getData().add(new XYChart.Data<>("12:00", 118));

        oxygenLevel.getData().add(new XYChart.Data<>("8:00", 98));
        oxygenLevel.getData().add(new XYChart.Data<>("10:00", 97));
        oxygenLevel.getData().add(new XYChart.Data<>("12:00", 98));

        vitalsChart.getData().addAll(heartRate, bloodPressure, oxygenLevel);
    }

    @FXML
    private void handleUploadVitals() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Vitals CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            // TODO: Implement CSV processing
            showAlert("Success", "Vitals uploaded successfully!");
        }
    }

    @FXML
    private void handlePanicButton() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("EMERGENCY ALERT");
        alert.setHeaderText("Emergency Signal Sent!");
        alert.setContentText("Emergency services and your doctor have been notified. Help is on the way.");
        alert.showAndWait();
    }

    @FXML
    private void handleGenerateReport() {
        // TODO: Implement report generation
        showAlert("Report Generation", "Generating comprehensive health report...");
    }

    // Navigation handlers
    @FXML private void handleHome() { /* TODO */ }
    @FXML private void handleProfile() { /* TODO */ }
    @FXML private void handleMyDoctors() { /* TODO */ }
    @FXML private void handleVitals() { /* TODO */ }
    @FXML private void handleAppointment() { /* TODO */ }
    @FXML private void handleChatVideo() { /* TODO */ }
    @FXML private void handleReports() { /* TODO */ }

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