package com.example.indian_geography_trivia;

import javafx.application.Platform;
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
import java.util.ResourceBundle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Initializable {

    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label factLabel;
    @FXML
    private Button playButton;
    @FXML
    private Button readButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button highScoreButton;
    @FXML
    private Button signOutButton;
    @FXML
    private Button quitButton;

    public static String username,gameModeButton;
    Timer factTimer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);
    }

    public void initData(String user_name) {
        username = user_name;
        welcomeLabel.setText("Welcome, " + username);

        if(username.startsWith("Guest"))
        {
            optionsButton.setVisible(false);
            readButton.setLayoutY(340);
            playButton.setLayoutY(260);
        }
    }

    public void factCycle() {
        factTimer = new Timer();
        Random random = new Random();

        factTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    int fact_number = random.nextInt(43);

                    try {
                        String fact = DataSource.getInstance().getFact(fact_number);
                        factLabel.setText(fact);
                    } catch (Exception e) {
                        System.out.println("Couldn't get fact!!");
                    }
                });
            }
        }, 0, 8000);
    }

    public void playButtonPushed(ActionEvent event) throws IOException {
        factTimer.cancel();
        gameModeButton = "Play";
        
        Parent gameModePage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/gameModePage.fxml"));
        Scene gameModePageScene = new Scene(gameModePage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameModePageScene);
        window.show();
    }

    public void playButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        playButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void playButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        playButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void readButtonPushed(ActionEvent event) throws IOException {
        factTimer.cancel();

        Parent readTopicPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/readTopicPage.fxml"));
        Scene readTopicScene = new Scene(readTopicPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readTopicScene);
        window.show();
    }

    public void readButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        readButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void readButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        readButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void optionsButtonPushed(ActionEvent event) throws IOException {
        factTimer.cancel();

        Parent optionsPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/optionsPage.fxml"));
        Scene optionsPageScene = new Scene(optionsPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(optionsPageScene);
        window.show();
    }

    public void optionsButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        optionsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void optionsButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        optionsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void highScoreButtonPushed(ActionEvent event) throws IOException {
        factTimer.cancel();
        gameModeButton = "High Score";

        Parent gameModePage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/gameModePage.fxml"));
        Scene gameModePageScene = new Scene(gameModePage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameModePageScene);
        window.show();
    }

    public void highScoreButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        highScoreButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void highScoreButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        highScoreButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void signOutButtonPushed(ActionEvent event) throws IOException {
        factTimer.cancel();

        Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/loginPage.fxml"));
        Scene loginPageScene = new Scene(loginPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginPageScene);
        window.show();
    }

    public void signOutButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        signOutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void signOutButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        signOutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void quitButtonPushed(ActionEvent event) throws IOException {
        factTimer.cancel();
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void quitButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        quitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void quitButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        quitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}
