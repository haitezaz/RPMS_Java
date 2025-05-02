package backend;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField contactInfo;
    @FXML private ChoiceBox<String> userTypeChoice;
    @FXML private Label errorLabel;
    @FXML private Label dateTimeLabel;
    @FXML private Label userLabel;

    private Timeline clock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeClock();
        initializeUserType();
        clearErrorLabel();
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

    private void initializeUserType() {
        userTypeChoice.getItems().addAll("Doctor", "Patient", "Admin");
        userTypeChoice.setValue("Doctor");
    }

    private void clearErrorLabel() {
        errorLabel.setText("");
    }

    @FXML
    private void handleLogin() {
        try {
            if (validateInput()) {
                String user = username.getText().trim();
                String pass = password.getText();
                String userType = userTypeChoice.getValue();

                switch (userType) {
                    case "Doctor" -> {
                        if (user.equals("doctor") && pass.equals("password")) {
                            showAlert("Success", "Doctor logged in successfully!");
                        } else {
                            errorLabel.setText("Invalid doctor credentials");
                        }
                    }
                    case "Patient" -> {
                        if (user.equals("patient") && pass.equals("password")) {
                            showAlert("Success", "Patient logged in successfully!");
                        } else {
                            errorLabel.setText("Invalid patient credentials");
                        }
                    }
                    case "Admin" -> {
                        if (user.equals("admin") && pass.equals("password")) {
                            showAlert("Success", "Admin logged in successfully!");
                        } else {
                            errorLabel.setText("Invalid admin credentials");
                        }
                    }
                }
            }
        } catch (Exception e) {
            errorLabel.setText("Login failed: " + e.getMessage());
        }
    }

    private boolean validateInput() {
        if (username.getText().trim().isEmpty()) {
            errorLabel.setText("Username is required");
            return false;
        }
        if (password.getText().isEmpty()) {
            errorLabel.setText("Password is required");
            return false;
        }
        if (contactInfo.getText().trim().isEmpty()) {
            errorLabel.setText("Contact info is required");
            return false;
        }
        if (userTypeChoice.getValue() == null) {
            errorLabel.setText("Please select a user type");
            return false;
        }
        return true;
    }

    @FXML
    private void showSignUpPage() {
        try {
            // Get the resource using the class loader
            URL signUpUrl = getClass().getResource("signUp.fxml");

            if (signUpUrl == null) {
                // If not found, try with different paths
                signUpUrl = getClass().getResource("/com/example/front_end_rpms/signUp.fxml");
            }

            if (signUpUrl == null) {
                throw new IllegalStateException("signUp.fxml not found in resources");
            }

            FXMLLoader loader = new FXMLLoader(signUpUrl);
            Parent signUp = loader.load();

            Scene scene = new Scene(signUp);

            // Add CSS
            URL cssUrl = getClass().getResource("styles.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            System.err.println("Error loading signup page: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Could not load signup page: " + e.getMessage());
        }
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