package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LevelCompleteController {

    @FXML
    public Label scoreLabel;

    public void setScore(int score){
        scoreLabel.setText("in "+score+" moves");
    }

    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
    }

}
