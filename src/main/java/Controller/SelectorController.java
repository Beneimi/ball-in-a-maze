package Controller;

import Dao.GameDao;
import Dao.PlayerDao;
import Dao.SavedGame;
import Model.Player;
import com.github.javafaker.Faker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;

public class SelectorController {

    @FXML
    public Button start;
    @FXML
    public ListView<String> levelList;

    @FXML
    public ComboBox<String> playerName;

    private FXMLLoader fxmlLoader;

    private SavedGame[] savedGames = {};

    @FXML
    private void initialize(){
        ObservableList<String> levelNames = FXCollections.observableArrayList();
        GameDao gd = new GameDao();

        try {
            savedGames = gd.GetGames();
        }catch (IOException e ){
            Logger.error(e);
        }

        for (SavedGame sg: savedGames){
            levelNames.add(sg.getName());
        }

        levelList.setItems(levelNames);
        levelList.getSelectionModel().select(0);
        levelList.refresh();

        this.loadPlayerNames();
    }

    private void loadPlayerNames() {
        PlayerDao pd = new PlayerDao();
        ObservableList<String> playerNames = FXCollections.observableArrayList();
        Player[] players = new Player[]{};
        try {
            players = pd.GetPlayers();
        }catch (IOException e ){
            Logger.error(e);
        }
        for (Player p : players){
            playerNames.add(p.getName());
        }
        playerName.setItems(playerNames);

    }

    public void handleStartButton() {

        if(levelList.getSelectionModel().getSelectedItem() != null){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/gameplay.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                Logger.error(e);
            }

            for (SavedGame sg: savedGames){
                if(sg.getName() == levelList.getSelectionModel().getSelectedItem()){
                    fxmlLoader.<GameplayController>getController().SetGame(sg);
                    fxmlLoader.<GameplayController>getController().SetPlayerName(getPlayerName());
                }
            }

            Stage stage = (Stage)this.start.getScene().getWindow();
            stage.setScene(new Scene(root,640,670));
            stage.show();
        }


    }

    private String getPlayerName(){
        if(this.playerName.getSelectionModel().isEmpty()){
            Faker faker = new Faker();
            return faker.funnyName().name();
        }
        return playerName.getSelectionModel().getSelectedItem();
    }

}
