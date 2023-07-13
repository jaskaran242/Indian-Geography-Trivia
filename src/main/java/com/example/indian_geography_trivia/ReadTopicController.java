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

public class ReadTopicController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;
    @FXML
    private Label pgNoLabel;
    @FXML
    private Button topicButton1;
    @FXML
    private Button topicButton2;
    @FXML
    private Button topicButton3;
    @FXML
    private Button topicButton4;
    @FXML
    private Button topicButton5;
    @FXML
    private Button topicButton6;
    @FXML
    private Button topicButton7;
    @FXML
    private Button topicButton8;
    @FXML
    private Button topicButton9;
    @FXML
    private Button topicButton10;
    @FXML
    private Button topicButton11;
    @FXML
    private Button topicButton12;
    @FXML
    private Button topicButton13;
    @FXML
    private Button topicButton14;
    @FXML
    private Button topicButton15;
    @FXML
    private Button topicButton16;
    @FXML
    private Button topicButton17;
    @FXML
    private Button topicButton18;
    @FXML
    private Button topicButton19;
    @FXML
    private Button topicButton20;

    private int x=1, pageNo=1;
    private final int totalPages=5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);

        File backButtonImageFile = new File("images/back_button_img.png");
        Image backButtonImage = new Image(backButtonImageFile.toURI().toString());
        backButtonImageView.setImage(backButtonImage);

        try {
            changeTopic();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        pgNoLabel.setText(pageNo + " / " + totalPages);
        prevButton.setDisable(true);
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

    private void changeTopic() throws SQLException {
        String[] topics = new String[20];
        int j=0;

        for(int i=x;i<x+20;i++) {
            if(j<20) {
                topics[j] = DataSource.getInstance().getTopic(i);
                j++;
            }
        }

        if(topics[0].equals("-")) {
            topicButton1.setVisible(false);
        } else {
            topicButton1.setVisible(true);
            topicButton1.setText(topics[0]);
        }

        if(topics[1].equals("-")) {
            topicButton2.setVisible(false);
        } else {
            topicButton2.setVisible(true);
            topicButton2.setText(topics[1]);
        }

        if(topics[2].equals("-")) {
            topicButton3.setVisible(false);
        } else {
            topicButton3.setVisible(true);
            topicButton3.setText(topics[2]);
        }

        if(topics[3].equals("-")) {
            topicButton4.setVisible(false);
        } else {
            topicButton4.setVisible(true);
            topicButton4.setText(topics[3]);
        }

        if(topics[4].equals("-")) {
            topicButton5.setVisible(false);
        } else {
            topicButton5.setVisible(true);
            topicButton5.setText(topics[4]);
        }

        if(topics[5].equals("-")) {
            topicButton6.setVisible(false);
        } else {
            topicButton6.setVisible(true);
            topicButton6.setText(topics[5]);
        }

        if(topics[6].equals("-")) {
            topicButton7.setVisible(false);
        } else {
            topicButton7.setVisible(true);
            topicButton7.setText(topics[6]);
        }

        if(topics[7].equals("-")) {
            topicButton8.setVisible(false);
        } else {
            topicButton8.setVisible(true);
            topicButton8.setText(topics[7]);
        }

        if(topics[8].equals("-")) {
            topicButton9.setVisible(false);
        } else {
            topicButton9.setVisible(true);
            topicButton9.setText(topics[8]);
        }

        if(topics[9].equals("-")) {
            topicButton10.setVisible(false);
        } else {
            topicButton10.setVisible(true);
            topicButton10.setText(topics[9]);
        }

        if(topics[10].equals("-")) {
            topicButton11.setVisible(false);
        } else {
            topicButton11.setVisible(true);
            topicButton11.setText(topics[10]);
        }

        if(topics[11].equals("-")) {
            topicButton12.setVisible(false);
        } else {
            topicButton12.setVisible(true);
            topicButton12.setText(topics[11]);
        }

        if(topics[12].equals("-")) {
            topicButton13.setVisible(false);
        } else {
            topicButton13.setVisible(true);
            topicButton13.setText(topics[12]);
        }

        if(topics[13].equals("-")) {
            topicButton14.setVisible(false);
        } else {
            topicButton14.setVisible(true);
            topicButton14.setText(topics[13]);
        }

        if(topics[14].equals("-")) {
            topicButton15.setVisible(false);
        } else {
            topicButton15.setVisible(true);
            topicButton15.setText(topics[14]);
        }

        if(topics[15].equals("-")) {
            topicButton16.setVisible(false);
        } else {
            topicButton16.setVisible(true);
            topicButton16.setText(topics[15]);
        }

        if(topics[16].equals("-")) {
            topicButton17.setVisible(false);
        } else {
            topicButton17.setVisible(true);
            topicButton17.setText(topics[16]);
        }

        if(topics[17].equals("-")) {
            topicButton18.setVisible(false);
        } else {
            topicButton18.setVisible(true);
            topicButton18.setText(topics[17]);
        }

        if(topics[18].equals("-")) {
            topicButton19.setVisible(false);
        } else {
            topicButton19.setVisible(true);
            topicButton19.setText(topics[18]);
        }

        if(topics[19].equals("-")) {
            topicButton20.setVisible(false);
        } else {
            topicButton20.setVisible(true);
            topicButton20.setText(topics[19]);
        }
    }

    public void topicButton1Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton1.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton1Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton1.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton1Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton1.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton2Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton2.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton2Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton2.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton2Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton2.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton3Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton3.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton3Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton3.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton3Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton3.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton4Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton4.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton4Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton4.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton4Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton4.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton5Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton5.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton5Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton5.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton5Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton5.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton6Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton6.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton6Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton6.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton6Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton6.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton7Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton7.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton7Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton7.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton7Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton7.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton8Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton8.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton8Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton8.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton8Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton8.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton9Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton9.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton9Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton9.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton9Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton9.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton10Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton10.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton10Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton10.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton10Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton10.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton11Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton11.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton11Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton11.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton11Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton11.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton12Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton12.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton12Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton12.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton12Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton12.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton13Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton13.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton13Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton13.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton13Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton13.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton14Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton14.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton14Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton14.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton14Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton14.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton15Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton15.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton15Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton15.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton15Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton15.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton16Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton16.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton16Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton16.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton16Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton16.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton17Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton17.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton17Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton17.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton17Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton17.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton18Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton18.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton18Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton18.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton18Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton18.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton19Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton19.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton19Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton19.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton19Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton19.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void topicButton20Pushed(ActionEvent event) throws IOException, SQLException {
        String topic = topicButton20.getText();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/indian_geography_trivia/readPage.fxml"));
        Parent readPage = loader.load();

        Scene readPageScene = new Scene(readPage);
        ReadController controller = loader.getController();
        controller.initData(topic);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readPageScene);
        window.show();
    }

    public void topicButton20Entered(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton20.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void topicButton20Exited(javafx.scene.input.MouseEvent mouseEvent) {
        topicButton20.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void nextButtonPushed(ActionEvent event) throws SQLException {
        prevButton.setDisable(false);

        x += 20;

        changeTopic();

        pageNo++;
        pgNoLabel.setText(pageNo + " / " + totalPages);

        if (pageNo == totalPages)
            nextButton.setDisable(true);
    }

    public void nextButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        nextButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void nextButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        nextButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }

    public void prevButtonPushed(ActionEvent event) throws SQLException {
        nextButton.setDisable(false);

        x -= 20;

        changeTopic();

        pageNo--;
        pgNoLabel.setText(pageNo + " / " + totalPages);

        if (pageNo == 1) {
            prevButton.setDisable(true);
        }
    }

    public void prevButtonEntered(javafx.scene.input.MouseEvent mouseEvent) {
        prevButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffc300;");
    }

    public void prevButtonExited(javafx.scene.input.MouseEvent mouseEvent) {
        prevButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #c49800;");
    }
}
