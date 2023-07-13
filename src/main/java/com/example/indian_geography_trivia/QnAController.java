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
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

public class QnAController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Label questionLabel;
    @FXML
    private Label questionNoLabel;
    @FXML
    private Label gameModeLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label timeLimitLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private Button quitButton;
    @FXML
    private Button optionA;
    @FXML
    private Button optionB;
    @FXML
    private Button optionC;
    @FXML
    private Button optionD;
    @FXML
    private Button confirmButton;
    @FXML
    private Button nextButton;

    private String gameMode,difficulty="",selectedOption = "none",ans;
    private int currentQNo = 0, totalQuestions = 20, min = 0, sec = 0, score = 0;
    private boolean correctAns;
    private int[] qNoUsed = new int[60];

    Timer timeLimit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);
    }

    public void initData(String game_mode) throws SQLException {
        gameMode = game_mode;

        gameModeLabel.setText(gameMode);
        nextButton.setVisible(false);
        ans = setQnA();
    }

    public void initData(String game_mode,String difficultySelected) throws SQLException {
        gameMode = game_mode;
        difficulty = difficultySelected;

        if (gameMode.equals("Rapid Fire"))
            scoreLabel.setVisible(false);

        gameModeLabel.setText(gameMode + " - " + difficulty);
        nextButton.setVisible(false);
        ans = setQnA();
    }

    public String setQnA() throws SQLException {
        IntStream qNo_ints = IntStream.of(-1);
        int qNo;
        String[] QnAtoset;

        Random randomQNo = new Random();

        currentQNo++;

        if (difficulty.equals("Beginner") && !gameMode.equals("Endless")) {
            qNo_ints = randomQNo.ints(1, 64);
            if (gameMode.equals("Quiz")) {
                min = 2;
                sec = 0;
            } else if (gameMode.equals("Rapid Fire")) {
                min = 1;
                sec = 0;
            }
            questionNoLabel.setText("Q :  " + currentQNo + " / " + totalQuestions);
        } else if (difficulty.equals("Expert") && !gameMode.equals("Endless")) {
            qNo_ints = randomQNo.ints(64, 122);
            if (gameMode.equals("Quiz")) {
                min = 1;
                sec = 0;
            } else if (gameMode.equals("Rapid Fire")) {
                min = 0;
                sec = 30;
            }
            questionNoLabel.setText("Q :  " + currentQNo + " / " + totalQuestions);
        } else if (gameMode.equals("Endless")) {
            qNo_ints = randomQNo.ints(1, 122);
            totalQuestions = 121;
            min = 1;
            sec = 30;
            questionNoLabel.setText("Q :  " + currentQNo);
        } else {
            System.out.println("Couldn't get random question no.");
        }

        qNo = qNo_ints.findFirst().getAsInt();

        /*
        for (int i = 0; i < currentQNo; i++) {
            for (int j = 0; j <= i; j++) {
                if (qNo == qNoUsed[j])
                    setQnA();
            }
        }

        qNoUsed[currentQNo-1] = qNo;
        */

        if (gameMode.equals("Endless"))
            difficulty = DataSource.getInstance().getDifficulty(qNo);

        QnAtoset = DataSource.getInstance().getQnA(qNo);

        questionLabel.setText(QnAtoset[0]);
        optionA.setText(QnAtoset[1]);
        optionB.setText(QnAtoset[2]);
        optionC.setText(QnAtoset[3]);
        optionD.setText(QnAtoset[4]);

        return QnAtoset[5];
    }

    public void beginTimer() {
        timeLimit = new Timer();

        timeLimit.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    timeLimitLabel.setText(min + ":" + sec);

                    try {
                        if (min != 0 && sec == 0) {
                            min--;
                            sec = 59;
                        }else if (min == 0 && sec == 0) {
                            confirmButton.setVisible(false);
                            nextButton.setVisible(true);

                            SVGPath optionShape = new SVGPath();
                            optionShape.setContent("M 100, 100 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0");

                            infoLabel.setText("Time Up!!");
                            correctAns = false;
                            switch (selectedOption) {
                                case "option_A" -> {
                                    optionA.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                                    optionA.setShape(optionShape);
                                }
                                case "option_B" -> {
                                    optionB.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                                    optionB.setShape(optionShape);
                                }
                                case "option_C" -> {
                                    optionC.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                                    optionC.setShape(optionShape);
                                }
                                case "option_D" -> {
                                    optionD.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                                    optionD.setShape(optionShape);
                                }
                            }
                            timeLimit.cancel();
                        } else {
                            sec--;
                        }
                    } catch (Exception e) {
                        System.out.println("Error at time limit!!");
                    }
                });
            }
        }, 0, 1000);
    }

    public void optionAPushed() {
        selectedOption = "option_A";

        SVGPath optionShape = new SVGPath();
        optionShape.setContent("M 100, 100 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0");

        optionA.setStyle("-fx-background-color: GOLD; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: BLACK");
        optionA.setShape(optionShape);
        optionB.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionB.setShape(optionShape);
        optionC.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionC.setShape(optionShape);
        optionD.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionD.setShape(optionShape);
    }

    public void optionBPushed() {
        selectedOption = "option_B";

        SVGPath optionShape = new SVGPath();
        optionShape.setContent("M 100, 100 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0");

        optionA.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionA.setShape(optionShape);
        optionB.setStyle("-fx-background-color: GOLD; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: BLACK");
        optionB.setShape(optionShape);
        optionC.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionC.setShape(optionShape);
        optionD.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionD.setShape(optionShape);
    }

    public void optionCPushed() {
        selectedOption = "option_C";

        SVGPath optionShape = new SVGPath();
        optionShape.setContent("M 100, 100 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0");

        optionA.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionA.setShape(optionShape);
        optionB.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionB.setShape(optionShape);
        optionC.setStyle("-fx-background-color: GOLD; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: BLACK");
        optionC.setShape(optionShape);
        optionD.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionD.setShape(optionShape);
    }

    public void optionDPushed() {
        selectedOption = "option_D";

        SVGPath optionShape = new SVGPath();
        optionShape.setContent("M 100, 100 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0");

        optionA.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionA.setShape(optionShape);
        optionB.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionB.setShape(optionShape);
        optionC.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
        optionC.setShape(optionShape);
        optionD.setStyle("-fx-background-color: GOLD; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: BLACK");
        optionD.setShape(optionShape);
    }

    public void quitButtonPushed(ActionEvent event) throws IOException {
        timeLimit.cancel();
        Parent playPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/gameModePage.fxml"));
        Scene playPageScene = new Scene(playPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(playPageScene);
        window.show();
    }

    public void quitButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        quitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void quitButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        quitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void confirmButtonPushed(ActionEvent event) throws IOException, SQLException {
        SVGPath optionShape = new SVGPath();
        optionShape.setContent("M 100, 100 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0");

        if (selectedOption.equals("none")) {
            infoLabel.setText("Please select an option");
        }
        else if(ans.equals(selectedOption)) {
            timeLimit.cancel();
            correctAns = true;
            switch (difficulty) {
                case "Beginner" -> score += 200;
                case "Expert" -> score += 600;
                default -> System.out.println("Couldn't increase score");
            }
            scoreLabel.setText("Score :  " + score);

            if (!gameMode.equals("Rapid Fire")) {
                infoLabel.setStyle("-fx-text-fill: GREEN");
                infoLabel.setText("Correct!!");
                switch (selectedOption) {
                    case "option_A" -> {
                        optionA.setStyle("-fx-background-color: DARKGREEN; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionA.setShape(optionShape);
                    }
                    case "option_B" -> {
                        optionB.setStyle("-fx-background-color: DARKGREEN; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionB.setShape(optionShape);
                    }
                    case "option_C" -> {
                        optionC.setStyle("-fx-background-color: DARKGREEN; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionC.setShape(optionShape);
                    }
                    case "option_D" -> {
                        optionD.setStyle("-fx-background-color: DARKGREEN; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionD.setShape(optionShape);
                    }
                }

                confirmButton.setVisible(false);
                nextButton.setVisible(true);
            } else {
                if (currentQNo<=totalQuestions-1) {
                    switch (selectedOption) {
                        case "option_A" -> {
                            optionA.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionA.setShape(optionShape);
                        }
                        case "option_B" -> {
                            optionB.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionB.setShape(optionShape);
                        }
                        case "option_C" -> {
                            optionC.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionC.setShape(optionShape);
                        }
                        case "option_D" -> {
                            optionD.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionD.setShape(optionShape);
                        }
                    }
                    ans = setQnA();
                    beginTimer();
                } else {
                    //qNoUsed = null;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/highScorePage.fxml"));
                    Parent highScorePage = loader.load();

                    Scene highScorePageScene = new Scene(highScorePage);
                    HighScoreController controller = loader.getController();
                    controller.initData(gameMode,score);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(highScorePageScene);
                    window.show();
                }
            }
        } else {
            timeLimit.cancel();
            correctAns = false;
            if (!gameMode.equals("Rapid Fire")) {
                infoLabel.setText("Incorrect!!");
                switch (selectedOption) {
                    case "option_A" -> {
                        optionA.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionA.setShape(optionShape);
                    }
                    case "option_B" -> {
                        optionB.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionB.setShape(optionShape);
                    }
                    case "option_C" -> {
                        optionC.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionC.setShape(optionShape);
                    }
                    case "option_D" -> {
                        optionD.setStyle("-fx-background-color: DARKRED; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                        optionD.setShape(optionShape);
                    }
                }
                confirmButton.setVisible(false);
                nextButton.setVisible(true);
            } else {
                if (currentQNo<=totalQuestions-1) {
                    switch (selectedOption) {
                        case "option_A" -> {
                            optionA.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionA.setShape(optionShape);
                        }
                        case "option_B" -> {
                            optionB.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionB.setShape(optionShape);
                        }
                        case "option_C" -> {
                            optionC.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionC.setShape(optionShape);
                        }
                        case "option_D" -> {
                            optionD.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                            optionD.setShape(optionShape);
                        }
                    }
                    ans = setQnA();
                    beginTimer();
                } else {
                    //qNoUsed = null;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/highScorePage.fxml"));
                    Parent highScorePage = loader.load();

                    Scene highScorePageScene = new Scene(highScorePage);
                    HighScoreController controller = loader.getController();
                    controller.initData(gameMode,score);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(highScorePageScene);
                    window.show();
                }
            }
        }
    }

    public void confirmButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        confirmButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void confirmButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        confirmButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void nextButtonPushed(ActionEvent event) throws IOException, SQLException {
        SVGPath optionShape = new SVGPath();
        optionShape.setContent("M 100, 100 m -75, 0 a 75,75 0 1,0 150,0 a 75,75 0 1,0 -150,0");

        if (correctAns && currentQNo<=totalQuestions-1) {
            switch (selectedOption) {
                case "option_A" -> {
                    optionA.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                    optionA.setShape(optionShape);
                }
                case "option_B" -> {
                    optionB.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                    optionB.setShape(optionShape);
                }
                case "option_C" -> {
                    optionC.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                    optionC.setShape(optionShape);
                }
                case "option_D" -> {
                    optionD.setStyle("-fx-background-color: BLACK; -fx-border-color: #9c7700; -fx-border-width: 5; -fx-text-fill: #c49800");
                    optionD.setShape(optionShape);
                }
            }
            infoLabel.setText("");
            infoLabel.setStyle("-fx-text-fill: RED");
            confirmButton.setVisible(true);
            nextButton.setVisible(false);
            selectedOption = "none";
            ans = setQnA();
            beginTimer();
        } else {
            qNoUsed = null;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/highScorePage.fxml"));
            Parent highScorePage = loader.load();

            Scene highScorePageScene = new Scene(highScorePage);
            HighScoreController controller = loader.getController();
            controller.initData(gameMode,score);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(highScorePageScene);
            window.show();
        }
    }

    public void nextButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        nextButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void nextButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        nextButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}