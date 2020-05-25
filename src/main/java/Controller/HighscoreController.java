package Controller;

import Dao.*;
import Model.Highscore;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoreController {

    @FXML
    public Button backButton;
    @FXML
    public ComboBox<String> playerSelector;
    @FXML
    public ListView<String> scoreTable;


    public void initialize(){
        PlayerDao pd = new PlayerDao();
        ObservableList<String> playerNames = FXCollections.observableArrayList();
        Player[] players = new Player[]{};
        try {
            players = pd.GetPlayers();
        }catch (IOException ex){
            System.out.println("Ooops, cant load players =(");
        }
        for (Player p : players){
            playerNames.add(p.getName());
        }
        playerSelector.setItems(playerNames);

    }

    public void handleBackButton() throws IOException {
        Stage stage = (Stage)this.backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
    }


    public void handlePlayerSelection(ActionEvent actionEvent) {
        ObservableList<String> scores = FXCollections.observableArrayList();
        PlayerDao pd = new PlayerDao();

        Highscore[] highscores = new Highscore[]{};

        try {
            highscores = pd.getHighscoresOf(playerSelector.getSelectionModel().getSelectedItem());
        }catch (IllegalArgumentException ex){
            System.out.println("Ooops, cant load scores for this user =(");
        }

        for (Highscore hs : highscores){
            scores.add(hs.getLevel()+ " Completed in "+hs.getScore()+" moves.");
        }

        scoreTable.setItems(scores);
        scoreTable.refresh();
    }
}
