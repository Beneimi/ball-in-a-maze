package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class MenuController {

    @FXML
    public Button startButton;
    @FXML
    public Button highscoreButton;
    @FXML
    public Button exitButton;

    public FXMLLoader fxmlLoader;


    public void handleHighscoreButton() {
        Stage stage = (Stage)this.highscoreButton.getScene().getWindow();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/highscore.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            Logger.error(e);
        }
        stage.setScene(new Scene(root,600,600));
        Logger.info("Entered highscore screen");
    }

    public void handleExitButton(){
        Stage s = (Stage)this.exitButton.getScene().getWindow();
        Logger.info("Application is closing");
        s.close();
    }

    public void handleStartButton() {
        Stage stage = (Stage)this.startButton.getScene().getWindow();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/levelSelector.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            Logger.error(e);
        }
        stage.setScene(new Scene(root,640,670));
        Logger.info("Entered level selection screen");
    }

    public void handleLevelEditorButton() {
        Stage stage = (Stage)this.startButton.getScene().getWindow();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/levelEditor.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            Logger.error(e);
        }
        stage.setScene(new Scene(root,640,670));
        Logger.info("Entered level editor screen");
    }
}
