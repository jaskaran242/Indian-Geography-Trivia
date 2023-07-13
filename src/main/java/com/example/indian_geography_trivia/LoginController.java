package com.example.indian_geography_trivia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button loginGuestButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);
    }

    public void loginButtonPushed(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        try{
            if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
                loginMessageLabel.setText("");
                if (DataSource.getInstance().loginValidation(username,password)) {
                    loginMessageLabel.setText("");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/homePage.fxml"));
                    Parent homePage = loader.load();

                    Scene homePageScene = new Scene(homePage);
                    HomeController controller = loader.getController();
                    controller.initData(username);
                    controller.factCycle();
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homePageScene);
                    window.show();
                    }else {
                        loginMessageLabel.setText("Incorrect Username or Password");
                    }
            } else {
                loginMessageLabel.setText("Enter all the Details");
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loginButtonEntered(MouseEvent mouseEvent) {
        loginButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void loginButtonExited(MouseEvent mouseEvent) {
        loginButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void loginAsGuestButtonPushed(ActionEvent event) throws IOException, SQLException {
        int guest_number = (DataSource.getInstance().guestNumber()) + 1;
        String guest_name = "Guest" + guest_number;

        DataSource.getInstance().insertGuest(guest_number,guest_name);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/homePage.fxml"));
        Parent homePage = loader.load();

        Scene homePageScene = new Scene(homePage);
        HomeController controller = loader.getController();
        controller.initData(guest_name);
        controller.factCycle();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homePageScene);
        window.show();
    }

    public void loginAsGuestButtonEntered(MouseEvent mouseEvent) {
        loginGuestButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void loginAsGuestButtonExited(MouseEvent mouseEvent) {
        loginGuestButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void createAccountButtonPushed(ActionEvent event) throws IOException {
        Parent createAccountPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/createAccountPage.fxml"));
        Scene createAccountPageScene = new Scene(createAccountPage);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(createAccountPageScene);
        window.show();
    }

    public void createAccountButtonEntered(MouseEvent mouseEvent) {
        createAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void createAccountButtonExited(MouseEvent mouseEvent) {
        createAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void exitButtonPushed() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void exitButtonEntered(MouseEvent mouseEvent) {
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void exitButtonExited(MouseEvent mouseEvent) {
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}