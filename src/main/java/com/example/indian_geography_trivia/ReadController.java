package com.example.indian_geography_trivia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReadController implements Initializable {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView backButtonImageView;
    @FXML
    private Label headingLabel;
    @FXML
    private Label contentLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backgroundImageFile = new File("images/IGT_BG.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        backgroundImageView.setImage(backgroundImage);

        File backButtonImageFile = new File("images/back_button_img.png");
        Image backButtonImage = new Image(backButtonImageFile.toURI().toString());
        backButtonImageView.setImage(backButtonImage);
    }

    public void initData(String heading) throws SQLException {
        String content;

        headingLabel.setText(heading);
        content = DataSource.getInstance().getExplanation(heading);
        contentLabel.setText(content);
    }

    public void backButtonPushed(ActionEvent event) throws IOException, SQLException {
        Parent readTopicPage = FXMLLoader.load(getClass().getResource("/com/example/indian_geography_trivia/readTopicPage.fxml"));
        Scene readTopicScene = new Scene(readTopicPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(readTopicScene);
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
}
