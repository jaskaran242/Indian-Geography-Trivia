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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmTextField;
    @FXML
    private Label creationSuccessfulLabel;
    @FXML
    private Label creationUnsuccessfulLabel;
    @FXML
    private Label emailUsedLabel;
    @FXML
    private Label usernameTakenLabel;
    @FXML
    private Label passwordMatchLabel;
    @FXML
    private Button createAccountButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);

        File backButtonImageFile = new File("images/back_button_img.png");
        Image backButtonImage = new Image(backButtonImageFile.toURI().toString());
        backButtonImageView.setImage(backButtonImage);
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/loginPage.fxml"));
        Scene loginPageScene = new Scene(loginPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginPageScene);
        window.show();
    }

    public void backButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        File backButtonBrightenedImageFile = new File("images/back_button_brightened_img.png");
        Image backButtonBrightenedImage = new Image(backButtonBrightenedImageFile.toURI().toString());
        backButtonImageView.setImage(backButtonBrightenedImage);
    }

    public void backButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        File backButtonImageFile = new File("images/back_button_img.png");
        Image backButtonImage = new Image(backButtonImageFile.toURI().toString());
        backButtonImageView.setImage(backButtonImage);
    }

    public void createAccountButtonPushed() throws SQLException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()){
            creationUnsuccessfulLabel.setText("");
            if (!DataSource.getInstance().usernameValidation(username)) {
                usernameTakenLabel.setText("");
                if (!username.startsWith("Guest")) {
                    usernameTakenLabel.setText("");
                    if (passwordTextField.getText().equals(confirmTextField.getText())) {
                        passwordMatchLabel.setText("");
                        if (DataSource.getInstance().insertUser(username, password)) {
                            creationSuccessfulLabel.setText("Registration Successful!!");
                        } else {
                            creationUnsuccessfulLabel.setText("Registration not successful");
                        }
                    } else {
                        passwordMatchLabel.setText("Password Don't Match");
                    }
                } else {
                    usernameTakenLabel.setText("Username cannot start with \"Guest\"");
                }
            } else {
                usernameTakenLabel.setText("Username already taken");
            }
        }else {
            creationUnsuccessfulLabel.setText("Please Enter All The Details");
        }
    }

    public void createAccountButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        createAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void createAccountButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        createAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}