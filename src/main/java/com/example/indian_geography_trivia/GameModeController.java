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

public class GameModeController implements Initializable {

    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private Label quizInfoLabel;
    @FXML
    private Label rapidfireInfoLabel;
    @FXML
    private Label endlessInfoLabel;
    @FXML
    private Button quizButton;
    @FXML
    private Button rapidFireButton;
    @FXML
    private Button endlessButton;

    public static String game_mode;

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

    public void quizButtonPushed(ActionEvent event) throws IOException, SQLException {
        if (HomeController.gameModeButton.equals("Play")) {
            game_mode = "Quiz";

            Parent difficultyPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/difficultyPage.fxml"));
            Scene difficultyScene = new Scene(difficultyPage);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(difficultyScene);
            window.show();
        } else if (HomeController.gameModeButton.equals("High Score")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/highScorePage.fxml"));
            Parent highScorePage = loader.load();

            Scene highScorePageScene = new Scene(highScorePage);
            HighScoreController controller = loader.getController();
            controller.initData("Quiz");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(highScorePageScene);
            window.show();
        }
    }

    public void quizButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        quizButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");

        if (HomeController.gameModeButton.equals("Play"))
            quizInfoLabel.setText("-20 Questions\n-2 Difficulty levels\n-Time limit up to 2 mins\n-Wrong answers end the game");
    }

    public void quizButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        quizButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
        quizInfoLabel.setText("");
    }

    public void rapidFireButtonPushed(ActionEvent event) throws IOException, SQLException {
        if (HomeController.gameModeButton.equals("Play")) {
            game_mode = "Rapid Fire";

            Parent difficultyPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/difficultyPage.fxml"));
            Scene difficultyScene = new Scene(difficultyPage);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(difficultyScene);
            window.show();
        } else if (HomeController.gameModeButton.equals("High Score")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/highScorePage.fxml"));
            Parent highScorePage = loader.load();

            Scene highScorePageScene = new Scene(highScorePage);
            HighScoreController controller = loader.getController();
            controller.initData("Rapid Fire");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(highScorePageScene);
            window.show();
        }
    }

    public void rapidFireButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        rapidFireButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");

        if (HomeController.gameModeButton.equals("Play"))
            rapidfireInfoLabel.setText("-20 Questions\n-2 Difficulty levels\n-Time limit up to 1 min\n-Wrong answers do not end the game");
    }

    public void rapidFireButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        rapidFireButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
        rapidfireInfoLabel.setText("");
    }

    public void endlessButtonPushed(ActionEvent event) throws IOException, SQLException {
        if (HomeController.gameModeButton.equals("Play")) {
            game_mode = "Endless";

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/QnAPage.fxml"));
            Parent QnAPage = loader.load();

            Scene QnAPageScene = new Scene(QnAPage);
            QnAController controller = loader.getController();
            controller.initData(GameModeController.game_mode);
            controller.beginTimer();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(QnAPageScene);
            window.show();
        } else if (HomeController.gameModeButton.equals("High Score")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/highScorePage.fxml"));
            Parent highScorePage = loader.load();

            Scene highScorePageScene = new Scene(highScorePage);
            HighScoreController controller = loader.getController();
            controller.initData("Endless");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(highScorePageScene);
            window.show();
        }
    }

    public void endlessButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        endlessButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");

        if (HomeController.gameModeButton.equals("Play"))
            endlessInfoLabel.setText("-Over 120 questions\n-Questions from all difficulty levels\n-score based on difficulty of question\n-Time limit : 1 min & 30 sec per question\n-Wrong answers end the game");
    }

    public void endlessButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        endlessButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
        endlessInfoLabel.setText("");
    }
}