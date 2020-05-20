package View.GUI;

import Dao.GameDao;
import Dao.SavedGame;
import Model.Game;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.awt.*;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws Exception {

        SavedGame activeGame = new SavedGame();
        activeGame.setName("first level");
        activeGame.setGame(new Game(8,new Point(1,1),new Point(7,7)));
        activeGame.getGame().SetWall(new Point(4,4),new Point(3,4));
        activeGame.getGame().SetWall(new Point(0,1),new Point(0,2));
        activeGame.getGame().SetWall(new Point(5,3),new Point(5,4));

        //GameDao gd =new GameDao();
        //gd.SaveGame(activeGame);

        //Gson gson = new Gson();

        //System.out.println( gson.toJson(activeGame) );

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Game g = new Game(6,new Point(2,2),new Point(4,4));
        g.SetWall(new Point(4,4),new Point(4,3));
    }
}
