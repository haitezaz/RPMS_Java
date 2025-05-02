package backend;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DoctorDashboardController implements Initializable {
    @FXML private Label dateTimeLabel;
    @FXML private Label userLabel;
    @FXML private Label doctorIdLabel;
    @FXML private Label doctorNameLabel;
    @FXML private Label specialtyLabel;

    @FXML private ComboBox<String> patientSelector;
    @FXML private LineChart<String, Number> patientVitalsChart;
    @FXML private Label heartRateLabel;
    @FXML private Label bpLabel;
    @FXML private Label tempLabel;
    @FXML private Label oxygenLabel;

    @FXML private TextArea feedbackArea;
    @FXML private TextArea prescriptionArea;

    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment, String> timeColumn;
    @FXML private TableColumn<Appointment, String> patientColumn;
    @FXML private TableColumn<Appointment, String> typeColumn;
    @FXML private TableColumn<Appointment, String> statusColumn;

    @FXML private ListView<String> emergencyList;

    private Timeline clock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeClock();
        initializeDoctorInfo();
        initializePatientSelector();
        setupVitalsChart();
        setupAppointmentsTable();
        loadEmergencyNotifications();
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

    private void initializeDoctorInfo() {
        doctorIdLabel.setText("DOCTOR ID: D12345");
        doctorNameLabel.setText("DOCTOR NAME: Dr. John Smith");
        specialtyLabel.setText("SPECIALTY: Cardiologist");
    }

    private void initializePatientSelector() {
        patientSelector.getItems().addAll(
                "P001 - Alice Johnson",
                "P002 - Bob Wilson",
                "P003 - Carol Martinez"
        );
        patientSelector.setOnAction(e -> updatePatientData());
    }

    private void setupVitalsChart() {
        XYChart.Series<String, Number> heartRate = new XYChart.Series<>();
        heartRate.setName("Heart Rate");

        XYChart.Series<String, Number> bloodPressure = new XYChart.Series<>();
        bloodPressure.setName("Blood Pressure");

        // Add sample data
        heartRate.getData().addAll(
                new XYChart.Data<>("09:00", 72),
                new XYChart.Data<>("10:00", 75),
                new XYChart.Data<>("11:00", 73)
        );

        bloodPressure.getData().addAll(
                new XYChart.Data<>("09:00", 120),
                new XYChart.Data<>("10:00", 122),
                new XYChart.Data<>("11:00", 118)
        );

        patientVitalsChart.getData().addAll(heartRate, bloodPressure);
    }

    private void setupAppointmentsTable() {
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Add sample appointments
        appointmentsTable.getItems().addAll(
                new Appointment("09:00 AM", "Alice Johnson", "Check-up", "Confirmed"),
                new Appointment("10:30 AM", "Bob Wilson", "Follow-up", "Confirmed"),
                new Appointment("02:00 PM", "Carol Martinez", "Video Call", "Pending")
        );
    }

    private void loadEmergencyNotifications() {
        emergencyList.getItems().addAll(
                "EMERGENCY: Patient P001 - Critical Blood Pressure (160/100)",
                "ALERT: Patient P003 - Low Oxygen Levels (92%)",
                "WARNING: Patient P002 - Irregular Heart Rate"
        );
    }

    private void updatePatientData() {
        String selectedPatient = patientSelector.getValue();
        if (selectedPatient != null) {
            // Update vitals labels
            heartRateLabel.setText("75 bpm");
            bpLabel.setText("120/80 mmHg");
            tempLabel.setText("98.6°F");
            oxygenLabel.setText("98%");

            // Update chart
            setupVitalsChart();
        }
    }

    @FXML
    private void handleViewPatientDetails() {
        String selectedPatient = patientSelector.getValue();
        if (selectedPatient != null) {
            showAlert("Patient Details", "Loading detailed view for " + selectedPatient);
        } else {
            showAlert("Error", "Please select a patient first");
        }
    }

    @FXML
    private void handleSendFeedback() {
        if (!feedbackArea.getText().isEmpty()) {
            showAlert("Success", "Feedback sent to patient");
            feedbackArea.clear();
        } else {
            showAlert("Error", "Please enter feedback first");
        }
    }

    @FXML
    private void handleSavePrescription() {
        if (!prescriptionArea.getText().isEmpty()) {
            showAlert("Success", "Prescription saved and sent to patient");
            prescriptionArea.clear();
        } else {
            showAlert("Error", "Please enter prescription details first");
        }
    }

    @FXML
    private void handleStartConsultation() {
        String selectedPatient = patientSelector.getValue();
        if (selectedPatient != null) {
            showAlert("Video Consultation", "Starting video call with " + selectedPatient);
        } else {
            showAlert("Error", "Please select a patient first");
        }
    }

    @FXML
    private void handleGenerateReport() {
        String selectedPatient = patientSelector.getValue();
        if (selectedPatient != null) {
            showAlert("Report Generation", "Generating report for " + selectedPatient);
        } else {
            showAlert("Error", "Please select a patient first");
        }
    }

    // Navigation handlers
    @FXML private void handleDashboard() { /* TODO */ }
    @FXML private void handleMyPatients() { /* TODO */ }
    @FXML private void handleAppointments() { /* TODO */ }
    @FXML private void handleChatVideo() { /* TODO */ }
    @FXML private void handleEmergencyAlerts() { /* TODO */ }
    @FXML private void handleReports() { /* TODO */ }
    @FXML private void handleViewHistory() { /* TODO */ }

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

    // Appointment class for TableView
    public static class Appointment {
        private final String time;
        private final String patient;
        private final String type;
        private final String status;

        public Appointment(String time, String patient, String type, String status) {
            this.time = time;
            this.patient = patient;
            this.type = type;
            this.status = status;
        }

        public String getTime() { return time; }
        public String getPatient() { return patient; }
        public String getType() { return type; }
        public String getStatus() { return status; }
    }
}