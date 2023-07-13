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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DifficultyController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private Label beginnerInfoLabel;
    @FXML
    private Label expertInfoLabel;
    @FXML
    private Button beginnerButton;
    @FXML
    private Button expertButton;

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
        Parent gameModePage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/gameModePage.fxml"));
        Scene gameModePageScene = new Scene(gameModePage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameModePageScene);
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

    public void beginnerButtonPushed (ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/QnAPage.fxml"));
        Parent QnAPage = loader.load();

        Scene QnAPageScene = new Scene(QnAPage);
        QnAController controller = loader.getController();
        controller.initData(GameModeController.game_mode,"Beginner");
        controller.beginTimer();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(QnAPageScene);
        window.show();
    }

    public void beginnerButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        beginnerButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");

        if(GameModeController.game_mode.equals("Quiz"))
            beginnerInfoLabel.setText("-Easy questions\n-200 points per question\n-Time limit : 2 min per question");
        else
            beginnerInfoLabel.setText("-Easy questions\n-200 points per question\n-Time limit : 1 min per question");
    }

    public void beginnerButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        beginnerButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
        beginnerInfoLabel.setText("");
    }

    public void expertButtonPushed (ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/QnAPage.fxml"));
        Parent QnAPage = loader.load();

        Scene QnAPageScene = new Scene(QnAPage);
        QnAController controller = loader.getController();
        controller.initData(GameModeController.game_mode,"Expert");
        controller.beginTimer();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(QnAPageScene);
        window.show();
    }

    public void expertButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        expertButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");

        if(GameModeController.game_mode.equals("Quiz")) {
            expertInfoLabel.setText("-Hard questions\n-600 points per question\n-Time limit : 1 min per question");
        } else {
            expertInfoLabel.setText("-Hard questions\n-600 points per question\n-Time limit: 30 sec per question");
        }
    }

    public void expertButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        expertButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
        expertInfoLabel.setText("");
    }
}