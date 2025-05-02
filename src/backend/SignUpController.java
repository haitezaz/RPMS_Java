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

public class SignUpController implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField repeatPassword;
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
        userTypeChoice.getItems().addAll("Doctor", "Patient");
        userTypeChoice.setValue("Doctor");
    }

    private void clearErrorLabel() {
        errorLabel.setText("");
    }

    @FXML
    private void handleSignUp() {
        try {
            if (validateInput()) {
                showAlert("Success", userTypeChoice.getValue() + " registered successfully!");
                backToLogin();
            }
        } catch (Exception e) {
            errorLabel.setText("Registration failed: " + e.getMessage());
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

        if (repeatPassword.getText().isEmpty()) {
            errorLabel.setText("Please confirm password");
            return false;
        }

        if (!password.getText().equals(repeatPassword.getText())) {
            errorLabel.setText("Passwords do not match");
            return false;
        }

        if (contactInfo.getText().trim().isEmpty()) {
            errorLabel.setText("Contact info is required");
            return false;
        }

        return true;
    }

    @FXML
    private void backToLogin() {
        try {
            URL loginUrl = getClass().getResource("login.fxml");
            if (loginUrl == null) {
                loginUrl = getClass().getResource("/com/example/front_end_rpms/login.fxml");
            }

            if (loginUrl == null) {
                throw new IllegalStateException("Could not find login.fxml");
            }

            FXMLLoader loader = new FXMLLoader(loginUrl);
            Parent login = loader.load();

            Scene scene = new Scene(login);

            URL cssUrl = getClass().getResource("styles.css");
            if (cssUrl == null) {
                cssUrl = getClass().getResource("/com/example/front_end_rpms/styles.css");
            }
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            System.err.println("Error loading login page: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Could not load login page: " + e.getMessage());
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