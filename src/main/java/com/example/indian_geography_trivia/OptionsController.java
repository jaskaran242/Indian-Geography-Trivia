package com.example.indian_geography_trivia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button deleteAccountButton;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);

        File backButtonImageFile = new File("images/back_button_img.png");
        Image backButtonImage = new Image(backButtonImageFile.toURI().toString());
        backButtonImageView.setImage(backButtonImage);
    }

    public void backButtonPushed(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/homePage.fxml"));
        Parent homePage = loader.load();

        Scene homePageScene = new Scene(homePage);
        HomeController controller = loader.getController();
        controller.initData(HomeController.username);
        controller.factCycle();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homePageScene);
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

    public void changePasswordButtonPushed(ActionEvent event) throws IOException {
        Parent changePasswordPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/changePasswordPage.fxml"));
        Scene changePasswordPageScene = new Scene(changePasswordPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(changePasswordPageScene);
        window.show();
    }

    public void changePasswordButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        changePasswordButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void changePasswordButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        changePasswordButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void deleteAccountButtonPushed(ActionEvent event) throws IOException {
        Parent deleteAccountPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/deleteAccountPage.fxml"));
        Scene deleteAccountPageScene = new Scene(deleteAccountPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(deleteAccountPageScene);
        window.show();
    }

    public void deleteAccountButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        deleteAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void deleteAccountButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        deleteAccountButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}
