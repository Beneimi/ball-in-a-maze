package Controller;

import View.GUI.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class MenuController {

    public Button startButton;
    public Button highscoreButton;
    public Button exitButton;
    private FXMLLoader fxmlLoader;


    public void handleHighscoreButton() throws IOException {
        Stage stage = (Stage)this.highscoreButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/highscore.fxml"));
        stage.setScene(new Scene(root,600,600));
    }

    public void handleExitButton(){
        Stage s = (Stage)this.exitButton.getScene().getWindow();
        s.close();
    }

    public void handleStartButton() throws IOException {
        Stage stage = (Stage)this.startButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/levelSelector.fxml"));
        stage.setScene(new Scene(root,640,670));
    }
}
