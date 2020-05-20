package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoreController {

    public Button backButton;

    public void handleBackButton() throws IOException {
        Stage stage = (Stage)this.backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
    }
}
