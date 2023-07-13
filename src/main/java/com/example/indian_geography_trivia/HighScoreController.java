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

public class HighScoreController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private Button backButton;
    @FXML
    private Label highScoreTitleLabel;
    @FXML
    private Label yourScoreLabel;
    @FXML
    private Label username1Label;
    @FXML
    private Label username2Label;
    @FXML
    private Label username3Label;
    @FXML
    private Label username4Label;
    @FXML
    private Label username5Label;
    @FXML
    private Label username6Label;
    @FXML
    private Label username7Label;
    @FXML
    private Label username8Label;
    @FXML
    private Label username9Label;
    @FXML
    private Label username10Label;
    @FXML
    private Label score1Label;
    @FXML
    private Label score2Label;
    @FXML
    private Label score3Label;
    @FXML
    private Label score4Label;
    @FXML
    private Label score5Label;
    @FXML
    private Label score6Label;
    @FXML
    private Label score7Label;
    @FXML
    private Label score8Label;
    @FXML
    private Label score9Label;
    @FXML
    private Label score10Label;

    private String gameMode;
    private String[] usernames;
    private int playerScore = -1;
    private int[] scores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);

        File backButtonImageFile = new File("images/back_button_img.png");
        Image backButtonImage = new Image(backButtonImageFile.toURI().toString());
        backButtonImageView.setImage(backButtonImage);
    }

    public void initData(String game_mode) throws SQLException {
        gameMode = game_mode;

        highScoreTitleLabel.setText("High Score - " + gameMode);

        getHighScore();
    }

    public void initData(String game_mode, int player_score) throws SQLException {
        gameMode = game_mode;
        playerScore = player_score;

        highScoreTitleLabel.setText("High Score - " + gameMode);
        yourScoreLabel.setText("Your Score:\n" + playerScore);

        DataSource.getInstance().insertScore(HomeController.username,gameMode,playerScore);
        getHighScore();
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent playPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/gameModePage.fxml"));
        Scene playPageScene = new Scene(playPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(playPageScene);
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

    public void getHighScore() throws SQLException {
        usernames = DataSource.getInstance().getHighScoreUsername(gameMode);
        scores = DataSource.getInstance().getHighScoreScore(gameMode);

        username1Label.setText(usernames[0]);
        username2Label.setText(usernames[1]);
        username3Label.setText(usernames[2]);
        username4Label.setText(usernames[3]);
        username5Label.setText(usernames[4]);
        username6Label.setText(usernames[5]);
        username7Label.setText(usernames[6]);
        username8Label.setText(usernames[7]);
        username9Label.setText(usernames[8]);
        username10Label.setText(usernames[9]);

        score1Label.setText(String.valueOf(scores[0]));
        score2Label.setText(String.valueOf(scores[1]));
        score3Label.setText(String.valueOf(scores[2]));
        score4Label.setText(String.valueOf(scores[3]));
        score5Label.setText(String.valueOf(scores[4]));
        score6Label.setText(String.valueOf(scores[5]));
        score7Label.setText(String.valueOf(scores[6]));
        score8Label.setText(String.valueOf(scores[7]));
        score9Label.setText(String.valueOf(scores[8]));
        score10Label.setText(String.valueOf(scores[9]));
    }
}
