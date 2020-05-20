package Controller;

import Dao.GameDao;
import Dao.SavedGame;
import Model.Game;
import View.GUI.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.awt.Point;
import java.io.IOException;

public class SelectorController {

    @FXML
    public Button start;
    @FXML
    public ListView<String> levelList;

    private Game selectedGame;

    private void initialize(){
        ObservableList<String> levelNames = FXCollections.observableArrayList();
        GameDao gd = new GameDao();
        SavedGame[] savedGames = {};
        try {
            savedGames = gd.GetGames();
        }catch (IOException ex){
            System.out.println("Ooops, cant load levels =(");
        }

        for (SavedGame sg: savedGames){
            levelNames.add(sg.getName());
        }

        levelList.setItems(levelNames);
    }

    public void handleStartButton() throws IOException {
        Stage stage = (Stage)this.start.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameplay.fxml"));
        stage.setScene(new Scene(root,640,670));

        Application.InitializeNewGame(new Point(2,2),new Point(4,4));
    }

}
