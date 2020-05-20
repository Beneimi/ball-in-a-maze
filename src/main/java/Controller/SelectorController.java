package Controller;

import Dao.GameDao;
import Dao.SavedGame;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectorController {

    @FXML
    public Button start;
    @FXML
    public ListView<String> levelList;

    private FXMLLoader fxmlLoader;

    private SavedGame[] savedGames = {};

    @FXML
    private void initialize(){
        ObservableList<String> levelNames = FXCollections.observableArrayList();
        GameDao gd = new GameDao();

        try {
            savedGames = gd.GetGames();
        }catch (IOException ex){
            System.out.println("Ooops, cant load levels =(");
        }

        for (SavedGame sg: savedGames){
            levelNames.add(sg.getName());
        }

        levelList.setItems(levelNames);
        levelList.refresh();
    }

    public void handleStartButton() throws IOException {

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/gameplay.fxml"));
        Parent root = fxmlLoader.load();

        for (SavedGame sg: savedGames){
            if(sg.getName() == levelList.getSelectionModel().getSelectedItem()){
                fxmlLoader.<GameplayController>getController().SetGame(sg.getGame());
            }
        }



        Stage stage = (Stage)this.start.getScene().getWindow();
        stage.setScene(new Scene(root,640,670));
        stage.show();

    }

}
