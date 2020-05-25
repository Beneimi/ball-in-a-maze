package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class LevelCompleteController {

    @FXML
    public Label scoreLabel;

    private FXMLLoader fxmlLoader;

    public void setScore(int score){
        scoreLabel.setText("in "+score+" moves");
    }

    public void handleBackButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            Logger.error(e);
        }
        stage.setScene(new Scene(root,600,600));
    }

}
