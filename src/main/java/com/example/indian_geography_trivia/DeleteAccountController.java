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

public class DeleteAccountController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteAccountButton;
    @FXML
    private Label passwordIncorrectLabel;
    @FXML
    private Label deleteAccountUnsuccessfulLabel;
    @FXML
    private Label deleteAccountSuccessfulLabel;

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

    public void deleteAccountButtonPushed(ActionEvent event) throws IOException, SQLException, InterruptedException {
        String username = HomeController.username;
        String password = passwordTextField.getText();

        if (!password.isBlank()) {
            deleteAccountUnsuccessfulLabel.setText("");
            if (DataSource.getInstance().loginValidation(username,password)) {
                passwordIncorrectLabel.setText("");
                if (DataSource.getInstance().deleteAccount(username,password)) {
                    deleteAccountSuccessfulLabel.setText("Deletion Successful!!");
                    Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/loginPage.fxml"));
                    Scene loginPageScene = new Scene(loginPage);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(loginPageScene);
                    window.show();
                } else {
                    deleteAccountUnsuccessfulLabel.setText("Deletion Unsuccessful!!");
                }
            } else {
                passwordIncorrectLabel.setText("Password is Incorrect!!");
            }
        } else {
            deleteAccountUnsuccessfulLabel.setText("Please Enter your Password!!");
        }
    }

    public void deleteAccountButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        deleteAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void deleteAccountButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        deleteAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}
