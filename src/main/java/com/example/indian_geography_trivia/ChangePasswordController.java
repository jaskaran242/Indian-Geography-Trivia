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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private PasswordField oldPasswordTextField;
    @FXML
    private PasswordField newPasswordTextField;
    @FXML
    private PasswordField confirmNewPasswordTextField;
    @FXML
    private Label passwordChangeUnsuccessfulLabel;
    @FXML
    private Label passwordChangeSuccessfulLabel;
    @FXML
    private Label oldPasswordIncorrectLabel;
    @FXML
    private Label newPasswordMatchLabel;
    @FXML
    private Button changePasswordButton;

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
        Parent optionsPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/optionsPage.fxml"));
        Scene optionsPageScene = new Scene(optionsPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(optionsPageScene);
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

    public void changePasswordButtonPushed(ActionEvent event) throws SQLException {
        String username = HomeController.username;
        String oldPassword = oldPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();
        String confirmNewPassword = confirmNewPasswordTextField.getText();

        if (!oldPasswordTextField.getText().isBlank() && !newPasswordTextField.getText().isBlank() && !confirmNewPasswordTextField.getText().isBlank()) {
            passwordChangeUnsuccessfulLabel.setText("");
            if (DataSource.getInstance().loginValidation(username,oldPassword)) {
                oldPasswordIncorrectLabel.setText("");
                if (newPassword.equals(confirmNewPassword)) {
                    newPasswordMatchLabel.setText("");
                    if (DataSource.getInstance().changePassword(username,newPassword)) {
                        passwordChangeSuccessfulLabel.setText("Update Successful!!");
                    } else {
                        passwordChangeUnsuccessfulLabel.setText("Update Unsuccessful!!");
                    }
                } else {
                    newPasswordMatchLabel.setText("Password Don't Match");
                }
            } else {
                oldPasswordIncorrectLabel.setText("Old Password Incorrect");
            }
        } else {
            passwordChangeUnsuccessfulLabel.setText("Please Enter All The Details");
        }
    }

    public void changePasswordButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        changePasswordButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void changePasswordButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        changePasswordButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}
